package com.example.merfbulletin

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import java.io.InputStream
import android.content.Intent

class BulletinActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bulletin)

        auth = FirebaseAuth.getInstance()

        val bulletinTextView: TextView = findViewById(R.id.bulletin_text)
        val btnBack: Button = findViewById(R.id.btn_back)
        val btnLogout: Button = findViewById(R.id.btn_logout)

        // Load the DOCX file from assets
        val fileName = intent.extras?.getString("FILE_NAME")
        if (fileName != null) {
            val inputStream: InputStream = assets.open("docx/" + fileName)
            val docxContent = readDocxFile(inputStream)
            bulletinTextView.text = docxContent
        } else {
            bulletinTextView.text = "No file name provided"
        }

        btnBack.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnLogout.setOnClickListener {
            auth.signOut()
            val intent = Intent(applicationContext, Login::class.java)
            startActivity(intent)
            finish()
        }
    }
}