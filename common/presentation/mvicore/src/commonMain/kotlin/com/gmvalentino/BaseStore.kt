package com.gmvalentino

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
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch

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
    private vararg val middlewares: Middleware
) : Store<INTENT, STATE, EVENT> {

    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private val intents = MutableSharedFlow<INTENT>()

    private val _state = MutableStateFlow(initialState)
    override val state: StateFlow<STATE> = _state

    override val events: Flow<EVENT> =
        processor.events.shareIn(scope, SharingStarted.WhileSubscribed(), 1)

    init {
        @Suppress("UNCHECKED_CAST")
        scope.launch {
            merge(
                loaders.actions.asFlow(),
                intents.map { intent ->
                    interpreter.interpret(
                        middlewares
                            .filterIsInstance<IntentMiddleware>()
                            .fold(intent) { value, middleware ->
                                middleware.transform(value) as? INTENT ?: value
                            }
                    )
                }
            )
                .flatMapMerge { action ->
                    processor.process(
                        state.value,
                        middlewares
                            .filterIsInstance<ActionMiddleware>()
                            .fold(action) { value, middleware ->
                                middleware.transform(value) as? ACTION ?: value
                            }
                    )
                }
                .map { result ->
                    reducer.reduce(
                        state.value,
                        middlewares
                            .filterIsInstance<ResultMiddleware>()
                            .fold(result) { value, middleware ->
                                middleware.transform(value) as? RESULT ?: value
                            }
                    )
                }
                .collect { state ->
                    _state.value = middlewares
                        .filterIsInstance<StateMiddleware>()
                        .fold(state) { value, middleware ->
                            middleware.transform(value) as? STATE ?: value
                        }
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
}
