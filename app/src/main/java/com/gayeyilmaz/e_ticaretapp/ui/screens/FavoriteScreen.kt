package com.gayeyilmaz.e_ticaretapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.navigation.NavController
import com.gayeyilmaz.e_ticaretapp.data.entity.Products
import com.gayeyilmaz.e_ticaretapp.ui.components.CustomBottomAppBar
import com.gayeyilmaz.e_ticaretapp.ui.components.CustomTopAppBAr
import com.gayeyilmaz.e_ticaretapp.ui.viewmodels.DetailViewModel
import com.gayeyilmaz.e_ticaretapp.ui.viewmodels.FavoriteViewModel

@Composable
fun FavoriteScreen(navController: NavController,favoriteViewModel: FavoriteViewModel){

    Scaffold (
        topBar = {
            CustomTopAppBAr(navController,"Favorites")
        },
        bottomBar = {
            CustomBottomAppBar(navController)
        }


    ){ innerpadding ->
        Column(
            modifier = Modifier.padding(innerpadding)
        ) {

        }

    }
}