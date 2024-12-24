package com.example.merfbulletin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.merfbulletin.ui.theme.MeRFbulletinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MeRFbulletinTheme {
                var authorizationLevel by remember { mutableStateOf(getAuthorizationLevel()) }
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    when (authorizationLevel) {
                        AuthorizationLevel.GUEST -> GuestScreen { authorizationLevel = it }
                        AuthorizationLevel.USER -> UserScreen { authorizationLevel = it }
                        AuthorizationLevel.ADMIN -> AdminScreen { authorizationLevel = it }
                    }
                }
            }
        }
    }
}

fun getAuthorizationLevel(): AuthorizationLevel {
    // Replace this with your actual logic to determine the authorization level
    return AuthorizationLevel.GUEST
}

@Composable
fun GuestScreen(onAuthorizationLevelChange: (AuthorizationLevel) -> Unit) {
    Button(onClick = { onAuthorizationLevelChange(AuthorizationLevel.USER) }) {
        Text(text = "Switch to User")
    }
}

@Composable
fun UserScreen(onAuthorizationLevelChange: (AuthorizationLevel) -> Unit) {
    Button(onClick = { onAuthorizationLevelChange(AuthorizationLevel.ADMIN) }) {
        Text(text = "Switch to Admin")
    }
}

@Composable
fun AdminScreen(onAuthorizationLevelChange: (AuthorizationLevel) -> Unit) {
    Button(onClick = { onAuthorizationLevelChange(AuthorizationLevel.GUEST) }) {
        Text(text = "Switch to Guest")
    }
}

@Preview(showBackground = true)
@Composable
fun GuestScreenPreview() {
    MeRFbulletinTheme {
        GuestScreen {}
    }
}

@Preview(showBackground = true)
@Composable
fun UserScreenPreview() {
    MeRFbulletinTheme {
        UserScreen {}
    }
}

@Preview(showBackground = true)
@Composable
fun AdminScreenPreview() {
    MeRFbulletinTheme {
        AdminScreen {}
    }
}