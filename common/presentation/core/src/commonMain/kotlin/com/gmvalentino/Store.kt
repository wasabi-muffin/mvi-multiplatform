package com.gmvalentino

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
abstract class Store<out STATE : State, in INTENT : Intent, in ACTION : Action, in RESULT : Result>(
    initialState: STATE,
    private val interpreter: Interpreter<INTENT, ACTION>,
    private val processor: Processor<STATE, ACTION, RESULT>,
    private val reducer: Reducer<STATE, RESULT>,
    private val loaders: Loader<ACTION> = Loader(),
    private val applier: Applier<STATE, ACTION> = Applier()
) {

    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private val mutex = Mutex()
    private val intents = MutableSharedFlow<INTENT>()
    private val actions = MutableSharedFlow<ACTION>(1)
    private val results = MutableSharedFlow<RESULT>()

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<STATE> get() = _state

    init {
        handleLoaders()
        handleIntents()
        handleActions()
        handleResults()
    }

    fun dispatch(intent: INTENT) {
        scope.launch {
            intents.emit(intent)
        }
    }

    fun dispose() {
        scope.cancel()
    }

    private fun handleLoaders() {
        loaders.actions.asFlow()
            .onEach {
                actions.emit(it)
            }
            .launchIn(scope)
        // loader?.actions?.forEach {
        //     scope.launch {
        //         actions.emit(it)
        //     }
        // }
    }

    private fun handleIntents() {
        intents
            .onEach { intent ->
                actions.emit(interpreter.interpret(intent))
            }
            .launchIn(scope)
    }

    private fun handleActions() {
        actions
            .onEach { action ->
                val currentState = state.value
                val newAction = applier.middleware.fold(action) { nextAction, nextMiddleware ->
                    nextMiddleware.apply(currentState, nextAction)
                }
                processor.process(currentState, newAction)
                    .onEach { processed ->
                        (processed as? RESULT)?.let {
                            results.emit(it)
                        }
                        (processed as? ACTION)?.let {
                            actions.emit(it)
                        }
                    }
                    .launchIn(scope)
            }
            .launchIn(scope)
    }

    private fun handleResults() {
        results
            .onEach { result ->
                mutex.withLock {
                    _state.value = reducer.reduce(state.value, result)
                }
            }
            .launchIn(scope)
    }
}
