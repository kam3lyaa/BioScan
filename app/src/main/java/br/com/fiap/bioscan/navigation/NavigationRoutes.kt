package br.com.fiap.bioscan.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.bioscan.screens.initial.InitialScreen
import br.com.fiap.bioscan.screens.login.LoginScreen
import br.com.fiap.bioscan.screens.singup.SignupScreen


@Composable
fun NavigationRoutes() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Destination.InitialScreen.route
    ){
        composable(
            Destination.InitialScreen.route
        ){
            InitialScreen(navController)
        }

        composable(
            Destination.LoginScreen.route
        ){
            LoginScreen(navController)
        }

        composable(
            Destination.SignupScreen.route
        ){
            SignupScreen(navController)
        }
    }


}
