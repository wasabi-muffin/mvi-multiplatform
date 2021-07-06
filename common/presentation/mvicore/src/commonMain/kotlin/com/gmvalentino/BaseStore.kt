package com.gmvalentino

import co.touchlab.kermit.Kermit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex

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
    private val reducer: Reducer<STATE, RESULT>,
    private val loaders: Loader<ACTION> = Loader(),
    private val applier: Applier<INTENT, ACTION, RESULT, STATE> = Applier()
) : Store<INTENT, STATE, EVENT> {

    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private val intents = MutableSharedFlow<INTENT>()
    private val mutex = Mutex()

    private val _state = MutableStateFlow(initialState)
    override val state: StateFlow<STATE> = _state
    private val currentState get() = _state.value

    override val events: SharedFlow<EVENT> =
        processor.events.shareIn(scope, SharingStarted.WhileSubscribed(), 1)

    init {
        @Suppress("UNCHECKED_CAST")
        scope.launch {
            merge(
                loaders.actions.asFlow(),
                intents
                    .applyIntentMiddlewares(applier.intentMiddlewares)
                    .map { intent ->
                        interpreter.interpret(intent)
                    }
            )
                .applyActionMiddlewares(applier.actionMiddlewares)
                .onEach {
                    Kermit(defaultTag = "MARCO").d { "Current State: $currentState" }
                }
                .flatMapMerge { action ->
                    processor.process(state.value, action)
                }
                .applyResultMiddlewares(applier.resultMiddlewares)
                .map { result ->
                    reducer.reduce(state.value, result)
                }
                .applyStateMiddlewares(applier.stateMiddlewares)
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

    private fun <INTENT : Intent> Flow<INTENT>.applyIntentMiddlewares(
        middlewares: List<IntentMiddleware<INTENT>>
    ): Flow<INTENT> = middlewares.fold(this) { action, middleware ->
        middleware.apply(action)
    }

    private fun <RESULT : Result> Flow<RESULT>.applyResultMiddlewares(
        middlewares: List<ResultMiddleware<RESULT>>,
    ): Flow<RESULT> = middlewares.fold(this) { action, middleware ->
        middleware.apply(action)
    }

    private fun <ACTION : Action> Flow<ACTION>.applyActionMiddlewares(
        middlewares: List<ActionMiddleware<ACTION>>,
    ): Flow<ACTION> = middlewares.fold(this) { action, middleware ->
        middleware.apply(action)
    }

    private fun <STATE : State> Flow<STATE>.applyStateMiddlewares(
        middlewares: List<StateMiddleware<STATE>>
    ): Flow<STATE> = middlewares.fold(this) { action, middleware ->
        middleware.apply(action)
    }
}
