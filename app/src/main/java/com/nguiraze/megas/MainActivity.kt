package com.nguiraze.megas

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val texto = TextView(this).apply {
            text = "App Megas online"
            textSize = 24f
            setPadding(48, 96, 48, 48)
        }
        setContentView(texto)
    }
}
