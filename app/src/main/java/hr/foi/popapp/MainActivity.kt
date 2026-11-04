package hr.foi.popapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hr.foi.popapp.navigation.components.EntryPage
import hr.foi.popapp.navigation.components.LoginPage
import hr.foi.popapp.navigation.components.RegistrationPage
import hr.foi.popapp.ui.theme.POPAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            POPAppTheme(dynamicColor = false) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        val navController = rememberNavController()

                        NavHost(navController, startDestination = "entry") {
                            composable("entry") {
                                EntryPage(
                                    onLoginButtonClick = {
                                        navController.navigate("login")
                                    },
                                    onRegistrationButtonClick = {
                                        navController.navigate("register")
                                    }
                                )
                            }
                            composable("register") {
                                RegistrationPage()
                            }
                            composable("login") {
                                LoginPage()
                            }
                        }
                    }
                }
            }
        }
    }
}