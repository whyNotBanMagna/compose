package com.example.learncompose.seven

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview(showBackground = true)
@Composable
fun WaterCounter(modifier: Modifier = Modifier){
    Column(modifier = Modifier.padding(16.dp)) {
        val count = rememberSaveable {
            mutableStateOf(0)
        }
        if(count.value > 0){
            val showTask = rememberSaveable {
                mutableStateOf(true)
            }
            if (showTask.value){
                WellnessTaskItem(
                    taskName = "Have you taken your 15 minute walk today?",
                    onClose = {
                        showTask.value = false
                    })
            }
            Text(text = "You've had ${count.value} glasses.")
        }

        Row (Modifier.padding(top = 8.dp)){
            Button(onClick = {
                count.value ++
            },enabled = count.value < 10) {
                Text(text = "Add one")
            }

            Button(onClick = {
                count.value = 0
            },modifier = Modifier.padding(start = 8.dp)) {
                Text(text = "Clear water count")
            }
        }
    }
}