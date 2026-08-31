package br.com.fiap.bioscan.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.fiap.bioscan.screens.camera.CameraScreen
import br.com.fiap.bioscan.screens.home.HomeScreen
import br.com.fiap.bioscan.screens.profile.UpdateScreen
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

        composable(
            Destination.HomeScreen.route
        ){
            HomeScreen(navController)
        }

        composable(
            Destination.CameraScreen.route
        ){
            CameraScreen(navController)
        }

        composable (
            Destination.UpdateScreen.route,
            arguments = listOf(
                navArgument(name = "email"){
                    type =  NavType.StringType
                }
            )
        ){backStackEntry ->
            var email = backStackEntry.arguments?.getString("email")
            UpdateScreen(navController, email)
        }
    }


}