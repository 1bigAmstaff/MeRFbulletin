package com.example.merfbulletin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class BulletinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bulletin)

        val bulletinTextView: TextView = findViewById(R.id.bulletin_text)
        val bulletinArray = BulletinArray()
        bulletinTextView.text = bulletinArray.getCurr()
    }
}