package com.gmvalentino.components

import com.gmvalentino.contract.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.sync.Mutex

/**
 * Abstract class that implements a [Store] that provides unidirectional dataflow logic
 */
@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
abstract class BaseStore<
        INTENT : Intent,
        in ACTION : Action,
        in RESULT : Result,
        STATE : State,
        EVENT : Event>(
    initialState: STATE,
    private val interpreter: Interpreter<INTENT, ACTION>,
    private val processor: BaseProcessor<STATE, ACTION, RESULT, EVENT>,
    private val reducer: Reducer<RESULT, STATE>,
    private val modifiers: Modifiers<INTENT, ACTION, RESULT, STATE> = Modifiers()
) : Store<INTENT, STATE, EVENT> {

    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private val intents = MutableSharedFlow<INTENT>()
    private val mutex = Mutex()

    private val _state = MutableStateFlow(initialState)
    override val state: StateFlow<STATE> = _state
    private val currentState get() = _state.value

    override val events: Flow<EVENT> = processor.events

    init {
        @Suppress("UNCHECKED_CAST")
        scope.launch {
            intents
                .applyIntentModifiers(modifiers.intentModifiers)
                .map { intent ->
                    interpreter.interpret(intent)
                }
                .applyActionModifiers(modifiers.actionModifiers)
                .flatMapMerge { action ->
                    processor.process(state.value, action)
                }
                .applyResultModifiers(modifiers.resultModifiers)
                .map { result ->
                    reducer.reduce(result, state.value)
                }
                .applyStateModifiers(modifiers.stateModifiers)
                .collect { state ->
                    _state.value = state
                }
        }
    }

    override fun dispatch(intent: INTENT) {
        scope.launch {
            intents.emit(intent)
        }
    }

    override fun dispose() {
        scope.cancel()
    }

    override fun collect(
        onState: ((STATE) -> Unit),
        onEvent: ((EVENT) -> Unit)
    ): Job = scope.launch {
        state.collect { onState(it) }
        events.collect { onEvent(it) }
    }

    /**
     * Helper functions to apply [Modifier] to their intended stream
     */
    private fun <INTENT : Intent> Flow<INTENT>.applyIntentModifiers(
        middlewares: List<IntentModifier<INTENT>>
    ): Flow<INTENT> = middlewares.fold(this) { action, middleware ->
        middleware.apply(action)
    }

    private fun <RESULT : Result> Flow<RESULT>.applyResultModifiers(
        middlewares: List<ResultModifier<RESULT>>,
    ): Flow<RESULT> = middlewares.fold(this) { action, middleware ->
        middleware.apply(action)
    }

    private fun <ACTION : Action> Flow<ACTION>.applyActionModifiers(
        middlewares: List<ActionModifier<ACTION>>,
    ): Flow<ACTION> = middlewares.fold(this) { action, middleware ->
        middleware.apply(action)
    }

    private fun <STATE : State> Flow<STATE>.applyStateModifiers(
        middlewares: List<StateModifier<STATE>>
    ): Flow<STATE> = middlewares.fold(this) { action, middleware ->
        middleware.apply(action)
    }
}