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
import com.gayeyilmaz.e_ticaretapp.ui.viewmodels.FavoriteViewModel
import com.gayeyilmaz.e_ticaretapp.ui.viewmodels.MainViewModel
import com.google.gson.Gson

@Composable
fun AppNavigation(mainViewModel: MainViewModel,detailViewModel: DetailViewModel,cartViewModel: CartViewModel,favoriteViewModel: FavoriteViewModel,username:String){
    val navController = rememberNavController()

    NavHost(navController=navController,startDestination="mainScreen"){
        composable("mainScreen"){
            MainScreen(navController,mainViewModel=mainViewModel)
        }

        composable("detailScreen/{product}/{isFavorite}",
            arguments = listOf(
                navArgument("product"){
                    type = NavType.StringType
                }
                ,
                navArgument("isFavorite"){
                    type = NavType.StringType
                }
            )){
            val jsonIsFavorite = it.arguments?.getString("isFavorite")
            val jsonProduct = it.arguments?.getString("product")
            val product = Gson().fromJson(jsonProduct, Products::class.java)
            val isFavorite = Gson().fromJson(jsonIsFavorite, Boolean::class.java)
            if(product != null){
                DetailScreen(navController,product = product,isFavorite=isFavorite,detailViewModel=detailViewModel,username=username,cartViewModel=cartViewModel)
            }
        }

        composable("cartScreen"){
            CartScreen(navController,cartViewModel=cartViewModel,username=username)

        }

        composable("favoriteScreen"){
            FavoriteScreen(navController, favoriteViewModel =favoriteViewModel)
        }
    }
}


