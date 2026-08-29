package br.com.fiap.bioscan.navigation


sealed class Destination(val route:  String){

    object InitialScreen : Destination(route = "initial")

    object SignupScreen: Destination(route = "signup")

    object LoginScreen: Destination(route = "login")

    object HomeScreen: Destination(route = "home")
}