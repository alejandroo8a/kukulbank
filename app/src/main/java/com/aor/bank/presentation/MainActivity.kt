package com.aor.bank.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.aor.bank.core.data.navigation.NavigationRoute
import com.aor.bank.data.navigation.MainNavigation
import com.aor.bank.data.navigation.NavigationState
import com.aor.bank.ui.theme.BankTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BankTheme {
                Surface {
                    val navController = rememberNavController()
                    val viewModel: MainViewModel = hiltViewModel()
                    val navState = viewModel.navigationState.collectAsState()

                    LaunchedEffect(Unit) {
                        viewModel.checkUserStatus()
                    }

                    MainNavigation(navController = navController)

                    LaunchedEffect(navState.value) {
                        when (navState.value) {
                            NavigationState.SignIn -> {
                                navController.navigate(NavigationRoute.SignIn.route) {
                                    popUpTo(0)
                                }
                            }
                            NavigationState.Home -> {
                                navController.navigate(NavigationRoute.Home.route) {
                                    popUpTo(0)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

