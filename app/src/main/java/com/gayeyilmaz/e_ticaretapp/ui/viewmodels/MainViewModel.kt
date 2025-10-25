package com.gayeyilmaz.e_ticaretapp.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gayeyilmaz.e_ticaretapp.data.entity.Products
import com.gayeyilmaz.e_ticaretapp.data.repos.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(var productRepository : ProductsRepository): ViewModel() {

    var productsList = MutableLiveData<List<Products>>()


    var categoriesList = MutableLiveData<List<String>>()
    init{
        loadProducts()
        loadCategories()
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