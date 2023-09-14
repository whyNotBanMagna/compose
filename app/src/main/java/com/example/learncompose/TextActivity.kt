package com.example.learncompose

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity

class TextActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text)

        val tv = findViewById<TextView>(R.id.tv)
        tv.typeface = Typeface.createFromAsset(assets,"gift_num.fnt")
        tv.text = "23"

    }
}