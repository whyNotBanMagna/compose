package com.example.learncompose.seven

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview(showBackground = true)
@Composable
fun WaterCounter(modifier: Modifier = Modifier){
    Column(modifier = Modifier.padding(16.dp)) {
        val count = remember {
            mutableStateOf(0)
        }
        Text(text = "You've had ${count.value} glasses.")
        Button(onClick = {
            count.value ++
        },modifier = Modifier.padding(top = 8.dp)) {
            Text(text = "Add one")
        }
    }
}