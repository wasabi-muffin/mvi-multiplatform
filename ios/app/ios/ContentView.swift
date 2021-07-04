//
//  ContentView.swift
//  ios
//
//  Created by Marco Valentino on 2021/06/27.
//

import SwiftUI
import MVIMultiplatform

struct ContentView: View {
    @StateObject var viewModel = BaseViewModel<MainIntent, MainState, MainEvent>(
        store: StoreProvider().main()
    )
    
    var body: some View {
        TaskListView(tasks: viewModel.state.tasks) { task in
            viewModel.dispatch(MainIntent.Toggle(id: task.id))
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
