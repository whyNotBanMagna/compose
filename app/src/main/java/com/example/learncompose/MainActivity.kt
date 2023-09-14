package com.example.learncompose

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.WindowCompat
import com.example.learncompose.ui.theme.LearnComposeTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            LearnComposeTheme {
                val systemUiController = rememberSystemUiController()
                val useDarkIcons = MaterialTheme.colors.isLight
                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = useDarkIcons
                    )
//                    systemUiController.setNavigationBarColor()
//                    systemUiController.setStatusBarColor()
                }
                MyApp(this)
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
   /* val expand = rememberSaveable {
        mutableStateOf(false)
    }

//    val extraPadding = if (expand.value) 48.dp else 0.dp
    val extraPadding by animateDpAsState(
        targetValue = if (expand.value) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    //注意 Surface 会了解，当该背景设置为 primary 颜色后，其上的任何文本都应使用 onPrimary 颜色
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding.coerceAtLeast(0.dp))
            ) {
                Text(text = "Hello，")
                Text(text = name)
            }
            OutlinedButton(onClick = {
                expand.value = !expand.value
            }) {
                Text(text = if (expand.value) "Show less" else "Show more")
            }
        }
    }
*/

    Card(backgroundColor = MaterialTheme.colors.primary,
    modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)) {
        CardContent(name)
    }

}


@Composable
private fun CardContent(name:String){
    val expanded = rememberSaveable {
        mutableStateOf(false)
    }
    Row(modifier = Modifier
        .padding(12.dp)
        .animateContentSize(
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )){
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            Text(text = "Hello, ")
            Text(
                text = name,
                style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            if (expanded.value) {
                Text(
                    text = ("Composem ipsum color sit lazy, " +
                            "padding theme elit, sed do bouncy. ").repeat(4),
                )
            }
        }
        IconButton(onClick = { expanded.value= !expanded.value }) {
            Icon(
                imageVector = if (expanded.value) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if (expanded.value) {
                    stringResource(R.string.show_less)
                } else {
                    stringResource(R.string.show_more)
                }

            )
        }
    }
}


@Composable
fun MyApp(context: Context) {
    var shouldShowOnboarding by rememberSaveable {
        mutableStateOf(true)
    }

    if (shouldShowOnboarding) {
        OnboardingScreen(context) {
            shouldShowOnboarding = false

        }
    } else {
        Greetings(context)
    }
}

@Composable
private fun Greetings(context: Context,
    names: List<String> = List(100) {
        "$it"
    }
) {
    var isRefreshing by rememberSaveable{
        mutableStateOf(false)
    }

    SwipeRefresh(state = rememberSwipeRefreshState(isRefreshing), onRefresh = {
        isRefreshing = true
        Toast.makeText(context, "刷新", Toast.LENGTH_SHORT).show()
    }) {
        LazyColumn(
            modifier = Modifier
                .padding(vertical = 4.dp)
                .fillMaxSize()
                .systemBarsPadding()
        ) {
            items(items = names) { name ->
                Greeting(name = name)
            }
            item {
                LaunchedEffect(key1 = Unit){
                    Toast.makeText(context, "加载更多", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}


@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    LearnComposeTheme {
//        MyApp()
//        Greeting("Android")
    }
}


@Composable
fun OnboardingScreen(context: Context,onContinueClicked: () -> Unit) {
    val c = LocalContext.current
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Welcome to the Basics Codelab!")
            Button(onClick = {
//                c.startActivity(Intent(c,ThirdActivity::class.java))
                onContinueClicked.invoke()
            }, modifier = Modifier.padding(24.dp)) {
                Text(text = "Continue")
            }
//            //widget.TextView
//            AndroidView(factory = { ctx ->
//                //Here you can construct your View
//                TextView(ctx).apply {
//                    layoutParams = LinearLayout.LayoutParams(500, 300)
//                    this.setOnClickListener {
//                        ctx.startActivity(Intent(ctx,ThirdActivity::class.java))
//
////                        val build = AlertDialog.Builder(ctx)
////                        build.setTitle("请选择").setPositiveButton("美") { p0, p1 ->
////
////                        }.setNegativeButton("不美") { p0, p1 ->
////
////                        }
////                        build.create().show()
//                    }
//                }
//            }, update = {
//                it.text = "You have clicked the buttons"
//            })
        }
    }
}


@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    LearnComposeTheme {
//        OnboardingScreen {}
    }
}


//@Composable
//fun NewsStory(){
//    Column(modifier = Modifier
//        .padding(16.dp)
//        .background(Color.White)) {
//
//        Image(painter = painterResource(id = R.mipmap.header),
//            contentDescription = null,
//            modifier = Modifier.clip(shape = RoundedCornerShape(4.dp)))
//
//        /**
//         * 分隔
//         */
//
//        /**
//         * 分隔
//         */
//        Spacer(modifier = Modifier.height(10.dp))
//
//        Text("A day in Shark Fin Cove,到发送方的顺丰到付第三方第三方的首付多少的方式发的是 第三方发多少多少对反射发的发的发的是",
//            style = MaterialTheme.typography.h6,
//            maxLines = 2,
//            overflow = TextOverflow.Ellipsis)
//        Text("Davenport, California",
//            style = MaterialTheme.typography.body2)
//        Text("December 2018")
//    }
//
//}
//
//
//@Preview
//@Composable
//fun DefaultPreview() {
//    NewsStory()
//}