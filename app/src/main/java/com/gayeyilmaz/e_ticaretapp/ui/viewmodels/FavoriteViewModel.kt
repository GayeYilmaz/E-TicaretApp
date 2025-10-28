package com.gayeyilmaz.e_ticaretapp.ui.viewmodels


import androidx.lifecycle.ViewModel
import com.gayeyilmaz.e_ticaretapp.data.entity.FavoriteProducts
import com.gayeyilmaz.e_ticaretapp.data.repos.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class FavoriteViewModel @Inject constructor(var productRepository : ProductsRepository) : ViewModel() {
    val favoritiesList = productRepository.favoritiesList

    init{
        loadFavorites()
    }

    fun loadFavorites(){
        CoroutineScope(Dispatchers.Main).launch {
       productRepository.loadFavorites()
        }
    }

    fun deleteFavorites(favoriteProducts: FavoriteProducts){
        CoroutineScope(Dispatchers.Main).launch {
           favoritiesList.remove(favoriteProducts)
            loadFavorites()
        }
    }



}