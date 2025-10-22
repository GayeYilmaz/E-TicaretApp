package com.gayeyilmaz.e_ticaretapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gayeyilmaz.e_ticaretapp.data.entity.Products
import com.google.gson.Gson

@Composable
fun AppNavigation(){
    val navController = rememberNavController()

    NavHost(navController=navController,startDestination="mainScreen"){
        composable("mainScreen"){
            MainScreen(navController)
        }

        composable("detailScreen/{product}",
            arguments = listOf(
                navArgument("product"){
                    type = NavType.StringType
                }
            )){
            val jsonProduct = it.arguments?.getString("product")
            val product = Gson().fromJson(jsonProduct, Products::class.java)
            if(product != null){
                DetailScreen(navController,product = product)
            }


        }

        composable("basketScreen"){
            BasketScreen(navController)
        }
    }
}


