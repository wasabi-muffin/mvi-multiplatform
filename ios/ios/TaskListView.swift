//
//  TaskListView.swift
//  ios
//
//  Created by Marco Valentino on 2021/06/28.
//

import Foundation
import SwiftUI
import MVIMultiplatform

struct TaskListView: View {
    var tasks: [Task]
    var onItemSelected: (Task) -> ()
    
    var body: some View {
        List(tasks, id:\.id) { task in
            TaskItemView(task: task, onClick: { onItemSelected(task) })
        }
    }
}

struct TaskListView_Previews: PreviewProvider {
    static var previews: some View {
        TaskListView(
            tasks: [
                Task(id: "1", title: "Title 1", details: "Description 1", date: LocalDateTime.init(year: 2020, monthNumber: 1, dayOfMonth: 1, hour: 12, minute: 0, second: 0, nanosecond: 0), isComplete: true)
            ]) { _ in }
    }
}
