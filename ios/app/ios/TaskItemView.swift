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
            VStack {
                Text(task.title)
                Text(task.details)
            }
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
    static var previews: some View {
        TaskItemView(task: Task(id: "1", title: "Title", details: "Details", date: LocalDateTime.init(year: 2020, monthNumber: 1, dayOfMonth: 1, hour: 12, minute: 0, second: 0, nanosecond: 0), isComplete: true), onClick: { })
    }
}

struct CheckBox_Preview: PreviewProvider {
    static var previews: some View {
        CheckBox(checked: true)
    }
}
