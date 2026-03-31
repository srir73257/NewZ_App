package com.example.newzapp.ui.theme

import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newzapp.ui.theme.Screen.Detail
import com.example.newzapp.ui.theme.Screen.Home
import kotlin.getValue

@Composable
fun Appnav(){
   val vieww: vieww = viewModel()
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Home) {

        composable(Home){
            Homescreen(vieww = vieww,navController)
        }

        composable(Detail){
           DetailedScreen(vieww = vieww, navController = navController)
        }
    }

}


object Screen {
    const val Home = "home"
    const val Detail = "detail"
}