//
//  IosStore.swift
//  ios
//
//  Created by Marco Valentino on 2021/06/28.
//

import Foundation
import Combine
import MVIMultiplatform

class BaseViewModel<INTENT: Intent, STATE: State, EVENT: Event> : ObservableObject {
    private var store: Store
    @Published var state: STATE
    @Published var event: EVENT?
    private var cancellables: Set<AnyCancellable> = []

    init(store: Store) {
        self.store = store
        self.state = store.state.value as! STATE
        let job = store.collect { state in
            self.state = state as! STATE
        } onEvent: { event in
            self.event = event as? EVENT
        }
        let cancellable = AnyCancellable {
            job.cancel(cause_: nil)
        }
        cancellable.store(in: &cancellables)
    }
    
    deinit {
        store.dispose()
    }
    
    func dispatch(_ intent: INTENT) {
        store.dispatch(intent: intent)
    }
}
