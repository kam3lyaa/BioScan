package br.com.fiap.bioscan.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.fiap.bioscan.screens.catalog.CatalogScreen
import br.com.fiap.bioscan.screens.description.DetailsScreen
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

        composable (
            Destination.UpdateScreen.route,
            arguments = listOf(
                navArgument(name = "email"){
                    type =  NavType.StringType
                }
            )
        ){backStackEntry ->

            var email = backStackEntry.arguments?.getString("email") ?: ""

            UpdateScreen(navController, email)
        }

        composable (
            Destination.HomeScreen.route,
            arguments =  listOf(
                navArgument(name = "email"){
                    type = NavType.StringType
                }
            )
        ){backStackEntry ->
            var email = backStackEntry.arguments?.getString("email") ?: ""
            HomeScreen(navController, email)

        }

        composable(
            Destination.CatalogScreen.route,
            arguments = listOf(
                navArgument(name = "email"){
                    type = NavType.StringType
                }
            )
            ){backStackentry ->
                var email = backStackentry.arguments?.getString("email")
                CatalogScreen(navController, email!!)
            }

        composable(
            Destination.DetailsScreen.route,
            arguments = listOf(
                navArgument("email"){
                    type = NavType.StringType
                },
                navArgument("speciesId"){
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            val speciesId = backStackEntry.arguments?.getString("speciesId") ?: ""

            DetailsScreen(navController = navController,
                email = email,
                speciesId = speciesId

            )


        }




    }
}
