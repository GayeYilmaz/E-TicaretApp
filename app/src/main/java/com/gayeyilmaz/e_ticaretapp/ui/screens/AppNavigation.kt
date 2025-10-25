package com.gayeyilmaz.e_ticaretapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gayeyilmaz.e_ticaretapp.data.entity.Products
import com.gayeyilmaz.e_ticaretapp.ui.viewmodels.CartViewModel
import com.gayeyilmaz.e_ticaretapp.ui.viewmodels.DetailViewModel
import com.gayeyilmaz.e_ticaretapp.ui.viewmodels.MainViewModel
import com.google.gson.Gson

@Composable
fun AppNavigation(mainViewModel: MainViewModel,detailViewModel: DetailViewModel,cartViewModel: CartViewModel

){
    val navController = rememberNavController()

    NavHost(navController=navController,startDestination="mainScreen"){
        composable("mainScreen"){
            MainScreen(navController,mainViewModel=mainViewModel)
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
                DetailScreen(navController,product = product,detailViewModel=detailViewModel)
            }


        }

        composable("cartScreen"){
            CartScreen(navController,cartViewModel=cartViewModel)
        }
    }
}


