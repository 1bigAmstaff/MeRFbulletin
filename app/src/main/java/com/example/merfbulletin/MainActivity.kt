package com.example.merfbulletin

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.example.merfbulletin.R
import com.example.merfbulletin.AuthorizationLevel
import com.example.merfbulletin.Login
import com.example.merfbulletin.Bulletin
import com.example.merfbulletin.ButtonListAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var listView: ListView
    private lateinit var firebaseAnalytics: FirebaseAnalytics
    private lateinit var authorizationLevel: AuthorizationLevel
    private lateinit var btnLogout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)
        listView = findViewById(R.id.list_view)
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        btnLogout = findViewById(R.id.btn_logout)

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

        updateUI()
    }

    private fun updateUI() {
        val bulletin = Bulletin().getBulletin(authorizationLevel)
        val adapter = ButtonListAdapter(this, bulletin)
        listView.adapter = adapter
    }
}