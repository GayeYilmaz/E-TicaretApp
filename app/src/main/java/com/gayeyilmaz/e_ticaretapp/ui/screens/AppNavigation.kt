package com.gayeyilmaz.e_ticaretapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gayeyilmaz.e_ticaretapp.data.entity.CartProducts
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

        composable("detailScreen/{product}",
            arguments = listOf(
                navArgument("product"){
                    type = NavType.StringType
                }
            )){
            val jsonProduct = it.arguments?.getString("product")
            val product = Gson().fromJson(jsonProduct, Products::class.java)
            if(product != null){
                DetailScreen(navController,product = product,detailViewModel=detailViewModel,username=username)
            }


        }


        composable("cartScreen/{cartProduct}",

            arguments= listOf(
                navArgument("cartProduct"){
                    type = NavType.StringType
                })) {
                val jsonCartProduct = it.arguments?.getString("cartProduct")
                val cartProduct = Gson().fromJson(jsonCartProduct, CartProducts::class.java)
            if(cartProduct !=null){
                CartScreen(navController,cartViewModel=cartViewModel,username=username,cartProduct=cartProduct)
            }

        }

        composable("favoriteScreen"){
            FavoriteScreen(navController,favoriteViewModel=favoriteViewModel)
        }
    }
}


