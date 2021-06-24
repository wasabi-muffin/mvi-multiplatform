package com.gmvalentino

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
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
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
abstract class Store<
    in INTENT : Intent,
    in ACTION : Action,
    in RESULT : Result,
    out STATE : State,
    out EVENT : Event>(
    initialState: STATE,
    private val interpreter: Interpreter<INTENT, ACTION>,
    private val processor: Processor<STATE, ACTION, RESULT, EVENT>,
    private val reducer: Reducer<STATE, RESULT>,
    private val loaders: Loader<ACTION> = Loader()
) {

    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private val mutex = Mutex()
    private val intents = MutableSharedFlow<INTENT>()

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<STATE> get() = _state

    private val _error: Channel<Error> = Channel(UNLIMITED)
    val error: Flow<Throwable> by lazy {
        _error.receiveAsFlow().shareIn(scope, SharingStarted.WhileSubscribed())
    }

    val events: Flow<EVENT> = processor.events.shareIn(scope, SharingStarted.WhileSubscribed(), 1)

    init {
        scope.launch {
            merge(
                loaders.actions.asFlow(),
                intents.map { interpreter.interpret(it) }
            )
                .flatMapMerge {
                    processor.process(state.value, it)
                }
                .map {
                    reducer.reduce(state.value, it)
                }
                .collect { _state.value = it }
        }
    }

    fun dispatch(intent: INTENT) {
        scope.launch {
            intents.emit(intent)
        }
    }

    fun dispose() {
        scope.cancel()
    }
}
