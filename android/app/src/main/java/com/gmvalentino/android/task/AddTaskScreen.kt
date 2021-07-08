package com.gmvalentino.android.task

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gmvalentino.addtask.contract.AddTaskEvent
import com.gmvalentino.addtask.contract.AddTaskIntent
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.getViewModel

@Composable
fun AddTaskScreen(
    navController: NavController
) {
    val viewModel = getViewModel<AddTaskViewModel>()
    val state = viewModel.state.collectAsState()

    LaunchedEffect(viewModel.events) {
        viewModel.events
            .onEach {
                when (it) {
                    AddTaskEvent.Close -> navController.popBackStack()
                }
            }
            .collect()
    }

    Column(
        modifier = Modifier.padding(24.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.value.title,
            onValueChange = {
                if (it != state.value.title) {
                    viewModel.dispatch(AddTaskIntent.InputTitle(it))
                }
            },
            label = { Text("Title") }
        )
        
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = TextFieldValue(
                state.value.date,
                TextRange(
                    state.value.date.length
                ),
            ),
            onValueChange = {
                if (it.text != state.value.date) {
                    viewModel.dispatch(AddTaskIntent.InputDate(it.text))
                }
            },
            label = { Text("Date") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = { viewModel.dispatch(AddTaskIntent.CreateClicked) },
            enabled = state.value.isCreateEnabled
        ) {
            Text("Create")
        }
    }
}