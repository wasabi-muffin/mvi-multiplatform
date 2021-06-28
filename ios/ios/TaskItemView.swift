//
//  TaskItemView.swift
//  ios
//
//  Created by Marco Valentino on 2021/06/28.
//

import Foundation
import MVIMultiplatform
import SwiftUI

struct TaskItemView: View {
    var task: Task
    var onClick: () -> ()
    
    var body: some View {
        HStack {
            Text(task.title)
            Spacer()
            CheckBox(checked: task.isComplete)
        }
        .onTapGesture {
            onClick()
        }
    }
}

struct CheckBox: View {
    var checked: Bool

    var body: some View {
        Image(systemName: checked ? "checkmark.square.fill" : "square")
            .foregroundColor(checked ? Color(UIColor.systemBlue) : Color.secondary)
    }
}


struct TaskItemView_Previews: PreviewProvider {
    struct TaskItemViewHolder : View {
        @SwiftUI.State var task = Task(id: "1", title: "Title 1", content: "Description 1", date: LocalDateTime.init(year: 2020, month: Month.april, dayOfMonth: 1, hour: 12, minute: 0, second: 0, nanosecond: 0), isComplete: true)

        var body: some View {
            TaskItemView(task: task) { }
        }
    }
    
    static var previews: some View {
        TaskItemViewHolder()
    }
}

