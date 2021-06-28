//
//  MainApp.swift
//  ios
//
//  Created by Marco Valentino on 2021/06/28.
//

import Foundation
import SwiftUI
import MVIMultiplatform

@main
struct MainApp: App {
    init() {
        MultiplatformApplication().initialize()
    }
    
    var body: some Scene {
        WindowGroup {
          ContentView()
        }
      }
}
