package com.example.learncompose

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learncompose.ui.theme.LearnComposeTheme

/**
 *@date 2021/9/7 16:33
 *description:
 */
class SecondActivity :ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnComposeTheme {
                val data = remember {
                    mutableStateListOf<Message>()
                }
                for (i in 0..10){
                    data.add(Message("李磊$i","what is your name $i",false))
                }


                LazyColumn{
                    items(data.size){ index->
                        MessageCardX(this@SecondActivity,data[index]){
                            data[index] = it
                        }
                    }
                }

            }
        }
    }

}

data class Message(var author:String,var body:String,var isExpand:Boolean)

@Composable
fun MessageCard(msg:Message){
    Column {
        Text(text = msg.author)
        Text(text = msg.body)
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewMessageCard(){
    MessageCard(msg = Message("李磊","What is your name",false))
}


@Composable
fun MessageCardX(activity: ComponentActivity,message: Message,change:(message: Message)->Unit){
//
//    var isExpand by remember {
//        mutableStateOf(message.isExpand)
//    }

    var mxessage by remember {
        mutableStateOf(message)
    }

    val surfaceColor: Color by  animateColorAsState(targetValue = if (mxessage.isExpand) MaterialTheme.colors.primary else MaterialTheme.colors.onSecondary)

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(all = 8.dp)
        .clickable {
            mxessage = mxessage.copy(author = "ddddd")
            change.invoke(mxessage)
            Log.d("TAG", "${mxessage.author}--")
            Toast
                .makeText(activity, "哈哈", Toast.LENGTH_LONG)
                .show()
        }) {
        Image(painter = painterResource(id = R.mipmap.header),contentScale = ContentScale.FillBounds, contentDescription = "Wallful",
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape))
        Spacer(modifier = Modifier.width(8.dp))

        Column{
            Text(color = surfaceColor,text = if (mxessage.isExpand)"展开sfdasfdsaf大萨达大师大的地方的反复的萨达十大啥的三发放是的撒佛挡杀佛发送方鼎折覆餗范德萨发顺丰第三方第三方撒辅导费" else mxessage.author,maxLines = if (mxessage.isExpand)Int.MAX_VALUE else 1)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = mxessage.body)
        }
    }
}
