package com.example.merfbulletin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class BulletinActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bulletin)

        auth = FirebaseAuth.getInstance()

        val bulletinTextView: TextView = findViewById(R.id.bulletin_text)
        val bulletinArray = BulletinArray()
        bulletinTextView.text = bulletinArray.getCurr()

        val btnBack: Button = findViewById(R.id.btn_back)
        btnBack.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val btnLogout: Button = findViewById(R.id.btn_logout)
        btnLogout.setOnClickListener {
            auth.signOut()
            val intent = Intent(applicationContext, Login::class.java)
            startActivity(intent)
            finish()
        }
    }
}