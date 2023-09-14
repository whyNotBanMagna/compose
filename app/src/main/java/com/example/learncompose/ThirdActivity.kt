package com.example.learncompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learncompose.ui.theme.LearnComposeTheme

class ThirdActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnComposeTheme() {
                MaterialTheme {
                    Column {
                        AppBar()
                        PhotographerCard()
                    }

                }
            }
        }
    }
}


@Composable
fun PhotographerCard() {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colors.secondary)
            .padding(20.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable {
                Log.d("hhq", "点击Row")
            }
    ) {
        Surface(
            modifier = Modifier.size(50.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {

        }

        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically)
                .clickable {
                    Log.d("hhq", "点击Column")
                }
        ) {
            Text("Alfred Sisley", fontWeight = FontWeight.Bold)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text("3 minutes ago", style = MaterialTheme.typography.body2)
            }
        }
    }

}

@Preview
@Composable
fun PhotographerCardPreview() {
    MaterialTheme {
        PhotographerCard()
    }
}

@Composable
fun AppBar() {
    TopAppBar(title = {
        Text(text = "登录", maxLines = 1)
    }, navigationIcon = {
        Icon(painter = painterResource(id = R.mipmap.header), contentDescription = "")
    })
}