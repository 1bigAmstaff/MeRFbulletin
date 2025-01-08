package com.example.merfbulletin

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Login : AppCompatActivity() {
    private lateinit var editTextEmail: TextInputEditText
    private lateinit var editTextPassword: TextInputEditText
    private lateinit var buttonLogin: Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var progressBar: ProgressBar
    private lateinit var textView: TextView
    private lateinit var guestLoginButton: Button
    private lateinit var sharedPreferences: SharedPreferences


    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth.currentUser
        println("mAuth:")
        println(mAuth)
        if (currentUser != null) {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        mAuth = FirebaseAuth.getInstance()
        sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)
        editTextEmail = findViewById(R.id.email)
        editTextPassword = findViewById(R.id.password)
        buttonLogin = findViewById(R.id.btn_login)
        progressBar = findViewById(R.id.progressBar)
        textView = findViewById(R.id.registerNow)
        guestLoginButton = findViewById(R.id.guest_login)
        guestLoginButton.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.putExtra("AUTH_LEVEL", AuthorizationLevel.GUEST.name)
            sharedPreferences.edit().putString("AUTH_LEVEL", AuthorizationLevel.GUEST.name).apply() // Save auth level
            startActivity(intent)
            finish()
        }

        progressBar.visibility = View.GONE

        textView.setOnClickListener {
            val intent = Intent(applicationContext, Register::class.java)
            startActivity(intent)
            finish()
        }

        buttonLogin.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show()
                progressBar.visibility = View.GONE
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show()
                progressBar.visibility = View.GONE
                return@setOnClickListener
            }

            // Check for admin credentials
            if (email == "oliverocho@gmail.com" && password == "adminpassword") {
                val intent = Intent(applicationContext, MainActivity::class.java)
                intent.putExtra("AUTH_LEVEL", AuthorizationLevel.ADMIN.name)
                sharedPreferences.edit().putString("AUTH_LEVEL", AuthorizationLevel.ADMIN.name).apply() // Save auth level
                startActivity(intent)
                finish()
                return@setOnClickListener
            }

            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(applicationContext, "Login successful", Toast.LENGTH_SHORT).show()
                        val intent = Intent(applicationContext, MainActivity::class.java)
                        val authLevel = if (email == "oliverocho@gmail.com") {
                            AuthorizationLevel.ADMIN.name
                        } else {
                            AuthorizationLevel.USER.name
                        }
                        intent.putExtra("AUTH_LEVEL", authLevel)
                        sharedPreferences.edit().putString("AUTH_LEVEL", authLevel).apply() // Save auth level
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                }
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        // Update UI based on user object
    }
}
