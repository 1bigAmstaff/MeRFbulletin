package com.example.merfbulletin

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var listView: ListView
    private lateinit var firebaseAnalytics: FirebaseAnalytics
    public lateinit var authorizationLevel: AuthorizationLevel
    private lateinit var btnLogout: Button
    private lateinit var btnBulletin: Button
    private lateinit var btnBulletinArchive: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        btnLogout = findViewById(R.id.btn_logout)
        btnBulletin = findViewById(R.id.btn_bulletin)
        btnBulletinArchive = findViewById(R.id.btn_bulletin_archive)

        val authLevel = sharedPreferences.getString("AUTH_LEVEL", null)

        authorizationLevel = if (authLevel != null) {
            try {
                AuthorizationLevel.valueOf(authLevel)
            } catch (e: IllegalArgumentException) {
                AuthorizationLevel.USER
            }
        } else {
            val user = auth.currentUser
            if (user == null) {
                val intent = Intent(applicationContext, Login::class.java)
                startActivity(intent)
                finish()
                return
            } else {
                AuthorizationLevel.USER
            }
        }

        btnLogout.setOnClickListener {
            auth.signOut()
            val intent = Intent(applicationContext, Login::class.java)
            startActivity(intent)
            finish()
        }
        btnBulletin.setOnClickListener {
            val assetManager = assets
            val files = assetManager.list("docx") ?: arrayOf()
            if (files.isNotEmpty()) {
                val mostRecentFile = files.sorted().last()
                val intent = Intent(applicationContext, BulletinActivity::class.java)
                intent.putExtra("FILE_NAME", mostRecentFile)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "No bulletin files found", Toast.LENGTH_SHORT).show()
            }
        }
        btnBulletinArchive.setOnClickListener {
            val intent = Intent(applicationContext, BulletinArchiveActivity::class.java)
            startActivity(intent)
            finish()
        }

        updateUI()
    }

    private fun updateUI() {
        if (authorizationLevel == AuthorizationLevel.GUEST) {
            btnBulletinArchive.visibility = Button.GONE
        } else {
            btnBulletinArchive.visibility = Button.VISIBLE
        }
    }
}