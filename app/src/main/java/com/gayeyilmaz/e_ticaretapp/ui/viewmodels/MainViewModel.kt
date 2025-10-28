package com.gayeyilmaz.e_ticaretapp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gayeyilmaz.e_ticaretapp.data.entity.FavoriteProducts
import com.gayeyilmaz.e_ticaretapp.data.entity.Products
import com.gayeyilmaz.e_ticaretapp.data.repos.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(var productRepository : ProductsRepository): ViewModel() {


    val favoritiesList = productRepository.favoritiesList
    var productsList = MutableLiveData<List<Products>>()
    var categoriesList = MutableLiveData<List<String>>()


    init{
        loadProducts()
        loadCategories()
        loadFavorites()
    }
    fun loadFavorites(){
        CoroutineScope(Dispatchers.Main).launch {
            productRepository.loadFavorites()
        }
    }

    fun addFavorites(favoriteProducts: FavoriteProducts){
        CoroutineScope(Dispatchers.Main).launch {
            favoritiesList.add(favoriteProducts)
            loadFavorites()
        }

    }

    fun deleteFavorites(favoriteProducts: FavoriteProducts){
        CoroutineScope(Dispatchers.Main).launch {
            if(favoriteProducts.isFavorite==false){
                favoriteProducts.isFavorite = true
                favoritiesList.remove(favoriteProducts)
            }else{
                favoritiesList.remove(favoriteProducts)
            }
            loadFavorites()
        }
    }



    fun loadCategories(){
        CoroutineScope(Dispatchers.Main).launch {
           categoriesList.value=productRepository.loadCategories()
        }
    }
    fun loadProducts(){
        CoroutineScope(Dispatchers.Main).launch {
            productsList.value=productRepository.loadProducts()
        }
    }

}