package com.example.randomnumber

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.randomnumber.data.constants.Constants.INFO_ROUTE
import com.example.randomnumber.data.constants.Constants.NUMBER_INFO_ROUTE
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
                NavHost(navController = navController, startDestination = NUMBER_INFO_ROUTE) {
                    composable(NUMBER_INFO_ROUTE) {
                        NumberInfoScreen(onInfoTap = { id ->
                            navController.navigate("$INFO_ROUTE/${id}")
                        }
                        )
                    }
                    composable("$INFO_ROUTE/{id}",
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

