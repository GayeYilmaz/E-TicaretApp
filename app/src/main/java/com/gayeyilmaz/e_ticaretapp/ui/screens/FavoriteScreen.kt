package com.gayeyilmaz.e_ticaretapp.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.gayeyilmaz.e_ticaretapp.data.entity.NavigationItemData
import com.gayeyilmaz.e_ticaretapp.ui.components.CustomBottomNavigationBar
import com.gayeyilmaz.e_ticaretapp.ui.components.CustomFavoriteCard
import com.gayeyilmaz.e_ticaretapp.ui.components.CustomTopAppBAr
import com.gayeyilmaz.e_ticaretapp.ui.viewmodels.FavoriteViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(navController: NavController,favoriteViewModel: FavoriteViewModel){

    val favoritiesList = favoriteViewModel.favoritiesList
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }


    LaunchedEffect(true) {
        favoriteViewModel.loadFavorites()

    }
    Scaffold (
        topBar = {
            CustomTopAppBAr(navController,"Favorilerim")
        },
        bottomBar = {
            CustomBottomNavigationBar(
                modifier = Modifier.fillMaxWidth(),
                navItems = listOf(
                    NavigationItemData(Icons.Filled.Home, "Ana Sayfa"),
                    NavigationItemData(Icons.Filled.Favorite, "Favoriler"),
                    NavigationItemData(Icons.Filled.Person, "Profil"),
                    NavigationItemData(Icons.Filled.ShoppingBasket, "Sepet")
                ),
                defaultSelectedIndex = 1,
                itemSelected = { index, reselected ->
                    if(index == 0)
                        navController.navigate("mainScreen")
                    else if(index == 1)
                        navController.navigate("favoriteScreen")
                    else if(index == 2)
                        navController.navigate("cartScreen")
                    else if(index == 3){
                        navController.navigate("cartScreen")}

                }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }


    ){ innerpadding ->
        //FAVORITE CARD
        LazyColumn(
            modifier = Modifier.padding(innerpadding)
        ) {
            items(favoritiesList.size){ i ->
                CustomFavoriteCard(navController,
                    favoritiesList[i],
                    onDeleteClick = { favProduct->
                        scope.launch {
                            val sb = snackbarHostState.showSnackbar(
                                message = " ${favProduct.name} ürününü favorilerimden silmek istiyormusun?",
                                actionLabel = "EVET"
                            )
                            if (sb == SnackbarResult.ActionPerformed) {
                                favoriteViewModel.deleteFavorites(favProduct)
                            }
                        }
                })
            }

        }

    }
}