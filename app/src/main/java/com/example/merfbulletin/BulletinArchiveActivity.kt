package com.example.merfbulletin

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class BulletinArchiveActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var authorizationLevel: AuthorizationLevel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bulletin_archive)

        auth = FirebaseAuth.getInstance()
        sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)

        val authLevel = sharedPreferences.getString("AUTH_LEVEL", null)
        authorizationLevel = if (authLevel != null) {
            try {
                AuthorizationLevel.valueOf(authLevel)
            } catch (e: IllegalArgumentException) {
                AuthorizationLevel.USER
            }
        } else {
            AuthorizationLevel.USER
        }

        val scrollView: ScrollView = findViewById(R.id.scrollView)
        val linearLayout: LinearLayout = findViewById(R.id.linearLayout)

        val assetManager = assets
        val files = assetManager.list("docx") ?: arrayOf()

        for (file in files) {
            val button = Button(this)
            button.text = file
            button.setOnClickListener {
                val intent = Intent(applicationContext, BulletinActivity::class.java)
                intent.putExtra("FILE_NAME", file)
                startActivity(intent)
            }
            linearLayout.addView(button)
        }

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