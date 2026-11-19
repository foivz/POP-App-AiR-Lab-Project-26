package hr.foi.popapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import hr.foi.popapp.core.login.Auth
import hr.foi.popapp.login_username_password.UsernamePasswordLoginHandler
import hr.foi.popapp.navigation.components.CreateStorePage
import hr.foi.popapp.navigation.components.EntryPage
import hr.foi.popapp.navigation.components.HomePage
import hr.foi.popapp.navigation.components.LoginPage
import hr.foi.popapp.navigation.components.PostRegistrationNotice
import hr.foi.popapp.navigation.components.ProductPage
import hr.foi.popapp.navigation.components.RegistrationPage
import hr.foi.popapp.ui.theme.POPAppTheme

class MainActivity : ComponentActivity() {
    private val loginHandlers = listOf(UsernamePasswordLoginHandler())
    private val currentLoginHandler = loginHandlers[0]

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
                                RegistrationPage(
                                    onSuccessfulRegistration = { newUsername ->
                                        navController.navigate("register/$newUsername/notice")
                                    }
                                )
                            }
                            composable(
                                "register/{username}/notice",
                                arguments = listOf(navArgument("username") { type = NavType.StringType })
                            ) { backStackEntry ->
                                PostRegistrationNotice(
                                    newUsername = backStackEntry.arguments?.getString("username") ?: "?",
                                    onNoticeUnderstood = {
                                        // Drop the form and the notice so Back can't return to them.
                                        navController.popBackStack("entry", inclusive = false)
                                    }
                                )
                            }
                            composable("login") {
                                LoginPage(
                                    onSuccessfulLogin = {
                                        val userData = Auth.loggedInUserData!!
                                        // Always show the store maps after login (even if the user already has a store),
                                        // so the whole class can share one seller and one buyer account.
                                        val destination = when (userData.role) {
                                            "seller" -> "create-store"
                                            "buyer" -> "select-store"
                                            else -> "home"
                                        }
                                        navController.navigate(destination)
                                    },
                                    currentLoginHandler
                                )
                            }
                            composable("home") {
                                HomePage(onMenuOptionSelected = { selectedRoute ->
                                    when (selectedRoute) {
                                        "products" -> navController.navigate("products")
                                        else -> Toast.makeText(
                                            applicationContext,
                                            "Not implemented yet",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                })
                            }
                            composable("products") {
                                ProductPage()
                            }
                            composable("select-store") {
                                SelectStorePage(onStoreSelected = {
                                    navController.navigate("home")
                                })
                            }
                            composable("create-store") {
                                CreateStorePage(onStoreCreated = {
                                    navController.navigate("home")
                                })
                            }
                        }
                    }
                }
            }
        }
    }
}