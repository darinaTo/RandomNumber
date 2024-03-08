package com.example.randomnumber

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.randomnumber.ui.activity.InfoScreen
import com.example.randomnumber.ui.activity.NumberInfoScreen
import com.example.randomnumber.ui.theme.RandomNumberTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RandomNumberTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "number_info_screen") {
                    composable("number_info_screen") {
                        NumberInfoScreen(onInfoTap = { id ->
                            navController.navigate("info_screen/${id}")
                        }
                        )
                    }
                    composable("info_screen/{id}",
                        arguments = listOf(
                            navArgument("id") {
                                type = NavType.IntType
                            }
                        )
                    ) {
                        InfoScreen()
                    }
                }
            }
        }
    }
}

