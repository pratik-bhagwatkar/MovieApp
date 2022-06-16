package com.example.movieapp.navigation

sealed class NavigationSealed(val route: String){
    object HomeScreen:NavigationSealed("home")
    object DetailScreen:NavigationSealed("{data}/detail"){
        fun createRoute(data:String)="$data/detail"
    }

}