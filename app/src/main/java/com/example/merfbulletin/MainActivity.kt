package com.example.merfbulletin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.merfbulletin.ui.theme.MeRFbulletinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MeRFbulletinTheme {
                var authorizationLevel by remember { mutableStateOf(getAuthorizationLevel()) }
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CombinedScreen(
                        authorizationLevel = authorizationLevel,
                        onAuthorizationLevelChange = { authorizationLevel = it },
                        modifier = Modifier.padding(innerPadding)
                    )
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
fun CombinedScreen(
    authorizationLevel: AuthorizationLevel,
    onAuthorizationLevelChange: (AuthorizationLevel) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        AuthorizationButtons(onAuthorizationLevelChange = onAuthorizationLevelChange)
        GuestInfo(onAuthorizationLevelChange = onAuthorizationLevelChange)
        if (authorizationLevel != AuthorizationLevel.GUEST) {
            UserInfo(onAuthorizationLevelChange = onAuthorizationLevelChange)
        }
        if (authorizationLevel == AuthorizationLevel.ADMIN) {
            AdminInfo(onAuthorizationLevelChange = onAuthorizationLevelChange)
        }
    }
}

@Composable
fun AuthorizationButtons(onAuthorizationLevelChange: (AuthorizationLevel) -> Unit, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Button(onClick = { onAuthorizationLevelChange(AuthorizationLevel.GUEST) }) {
            Text(text = "Guest")
        }
        Button(onClick = { onAuthorizationLevelChange(AuthorizationLevel.USER) }) {
            Text(text = "User")
        }
        Button(onClick = { onAuthorizationLevelChange(AuthorizationLevel.ADMIN) }) {
            Text(text = "Admin")
        }
    }
}

@Composable
fun GuestInfo(onAuthorizationLevelChange: (AuthorizationLevel) -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = "Guest Info", modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 16.dp))
    }
}

@Composable
fun UserInfo(onAuthorizationLevelChange: (AuthorizationLevel) -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = "User Info", modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 16.dp))
    }
}

@Composable
fun AdminInfo(onAuthorizationLevelChange: (AuthorizationLevel) -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = "Admin Info", modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun CombinedScreenPreview() {
    MeRFbulletinTheme {
        CombinedScreen(
            authorizationLevel = AuthorizationLevel.GUEST,
            onAuthorizationLevelChange = {}
        )
    }
}