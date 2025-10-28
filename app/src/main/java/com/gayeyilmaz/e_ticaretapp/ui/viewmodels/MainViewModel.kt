package com.gayeyilmaz.e_ticaretapp.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
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
            Log.e("fav-delete","mainview-load - ${favoritiesList}")
        }
    }

    fun addFavorites(favoriteProducts: FavoriteProducts){
        CoroutineScope(Dispatchers.Main).launch {
            favoritiesList.add(favoriteProducts)
          // productRepository.add(favoriteProducts)
            Log.e("fav-delete","mainview-add list - ${favoritiesList}")
            loadFavorites()
        }

    }

    fun deleteFavorites(favoriteProducts: FavoriteProducts){
       // Log.e("FAV","MainViewModel- ${favoriteProducts.name} - Deleted :${favoritiesList}")
        CoroutineScope(Dispatchers.Main).launch {
            if(favoriteProducts.isFavorite==false){
                favoriteProducts.isFavorite = true
                favoritiesList.remove(favoriteProducts)
            }else{
                favoritiesList.remove(favoriteProducts)
            }

            Log.e("fav-delete","mainview-delete list - ${favoritiesList}")
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

   /** fun search(searchText:String){
        CoroutineScope(Dispatchers.Main).launch {
            productsList.value=productRepository.search(searchText)

        }
    }**/
}