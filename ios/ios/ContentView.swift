//
//  ContentView.swift
//  ios
//
//  Created by Marco Valentino on 2021/06/27.
//

import SwiftUI
import MVIMultiplatform

struct ContentView: View {
    
    let viewModel = ViewModel<BaseStore<MainIntent, MainAction, MainResult, MainState, MainEvent>, MainIntent, MainAction, MainResult, MainState, MainEvent>(
        store: StoreProvider().main()
    )
    var body: some View {
        Text("Hello, world!")
            .padding()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
