//
//  CombineStore.swift
//  ios
//
//  Created by Marco Valentino on 2021/06/27.
//

import Foundation
import MVIMultiplatform
import Combine

protocol SwiftUIReactor: Store, ObservableObject {
    var state: State { get }
    var event: Event? { get }
}

extension KotlinBase: Identifiable {}

class ViewModel<STORE: BaseStore<INTENT, ACTION, RESULT, STATE, EVENT>,
                INTENT: Intent, ACTION: Action, RESULT: Result, STATE: State, EVENT: Event>: ObservableObject {
    
    @Published var state: STATE
    @Published var events: EVENT?
    
    private let internalState: AnyPublisher<STATE, Never>
    private let internalEvent: AnyPublisher<EVENT, Never>
    private var cancellables: Set<AnyCancellable> = []
    private let store: STORE
    
    init(store: STORE) {
        let initState = CurrentValueSubject<STATE, Never>(
            store.state.value as! STATE
        )
        let initEvent = PassthroughSubject<EVENT, Never>()
        let job = store.collect(
            onState: { initState.value = $0 },
            onEvent: { initEvent.send($0) }
        )

        let cancellable = AnyCancellable {
            job.cancel(cause_: nil)
        }
        
        self.store = store
        self.internalState = initState.eraseToAnyPublisher()
        self.internalEvent = initEvent.eraseToAnyPublisher()
        self.state = store.state.value as! STATE
        
        initState.sink { [weak self] in self?.state = $0 }.store(in: &cancellables)
        initEvent.sink { [weak self] in self?.events = $0 }.store(in: &cancellables)
        cancellable.store(in: &cancellables)
    }

    func dispatch(_ intent: INTENT) {
        
    }
}
