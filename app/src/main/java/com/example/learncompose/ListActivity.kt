package com.example.learncompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.learncompose.ui.theme.LearnComposeTheme
import kotlinx.coroutines.launch

/**
 *@date 2021/9/9 17:36
 *description:
 */
class ListActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnComposeTheme {
                ScrollingList()
            }
        }
    }

}


@Composable
fun simpleList() {
    LazyColumn() {
        items(100) {
            Text(text = "Item $it")
        }
    }
}


@Composable
fun ImageListItem(index: Int){
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(painter = rememberImagePainter(data = "https://developer.android.com/images/brand/Android_Robot.png"), contentDescription = "Android logo",modifier = Modifier.size(50.dp))
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = "Item #$index",style = MaterialTheme.typography.subtitle1)
    }
}

@Preview
@Composable
fun ScrollingList() {
    val listSize = 100
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Column() {
        Row() {
            Button(onClick = {
                coroutineScope.launch {
                    scrollState.animateScrollToItem(0)
                }
            }) {
                Text(text = "Scroll to the top")
            }
            Button(onClick = {
                coroutineScope.launch {
                    scrollState.animateScrollToItem(listSize-1)
                }
            }) {
                Text(text = "Scroll to the end")
            }
        }

        LazyColumn(state = scrollState){

            items(listSize){
                ImageListItem(it)
            }
        }
    }
}