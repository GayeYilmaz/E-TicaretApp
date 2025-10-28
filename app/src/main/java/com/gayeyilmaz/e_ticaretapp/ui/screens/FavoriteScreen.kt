package com.gayeyilmaz.e_ticaretapp.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.gayeyilmaz.e_ticaretapp.data.entity.Products
import com.gayeyilmaz.e_ticaretapp.ui.components.CustomBottomAppBar
import com.gayeyilmaz.e_ticaretapp.ui.components.CustomFavoriteCard
import com.gayeyilmaz.e_ticaretapp.ui.components.CustomTopAppBAr
import com.gayeyilmaz.e_ticaretapp.ui.viewmodels.DetailViewModel
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
            CustomBottomAppBar(navController)
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
                                message = "Delete the ${favProduct.name} from cart?",
                                actionLabel = "YES"
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