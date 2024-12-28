package com.example.merfbulletin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.analytics.FirebaseAnalytics
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var button: Button
    private lateinit var textView: TextView
    private lateinit var bulletinTextView: TextView
    private lateinit var firebaseAnalytics: FirebaseAnalytics
    private lateinit var authorizationLevel: AuthorizationLevel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        button = findViewById(R.id.logout)
        textView = findViewById(R.id.user_details)
        bulletinTextView = findViewById(R.id.bulletin)
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)

        val authLevel = intent.getStringExtra("AUTH_LEVEL")
        authorizationLevel = if (authLevel != null) {
            AuthorizationLevel.valueOf(authLevel)
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

        updateUI()

        button.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(applicationContext, Login::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun updateUI() {
        val bulletin = Bulletin().getBulletin(authorizationLevel)
        bulletinTextView.text = bulletin

        when (authorizationLevel) {
            AuthorizationLevel.GUEST -> {
                textView.text = "Hi guest"
            }
            AuthorizationLevel.USER -> {
                val user = auth.currentUser
                textView.text = "User info: ${user?.email}"
            }
            AuthorizationLevel.ADMIN -> {
                textView.text = "Hi admin"
            }
        }
    }
}