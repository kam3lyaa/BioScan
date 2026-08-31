package br.com.fiap.bioscan.navigation


sealed class Destination(val route:  String){

    object InitialScreen : Destination(route = "initial")

    object SignupScreen: Destination(route = "signup")

    object LoginScreen: Destination(route = "login")

    object HomeScreen: Destination(route = "home/{email}") {
        fun createRoute(email: String): String {
            return "home/$email"
        }
    }

    object UpdateScreen: Destination(route = "update/{email}"){
        fun createRoute(email: String): String{
            return "update/$email"
        }
    }

    object CatalogScreen: Destination(route= "catalog/{email}"){
        fun createRoute(email: String): String{
            return "catalog/$email"
        }
    }

    object DetailsScreen: Destination(
        route = "details/{email}/{speciesId}"
    ){
        fun createRoute(
            email: String,
            speciesId: String
        ): String{
            return "details/$email/$speciesId"
        }
    }

}