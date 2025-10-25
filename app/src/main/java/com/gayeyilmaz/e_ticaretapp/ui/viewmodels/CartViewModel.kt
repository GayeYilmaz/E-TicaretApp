package com.gayeyilmaz.e_ticaretapp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gayeyilmaz.e_ticaretapp.data.entity.CartProducts
import com.gayeyilmaz.e_ticaretapp.data.repos.ProductsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartViewModel : ViewModel() {
    var productRepository = ProductsRepository()
    var cartProductsList = MutableLiveData<List<CartProducts>>()

    fun loadCartProducts(){
        CoroutineScope(Dispatchers.Main).launch {
            cartProductsList.value=productRepository.loadCartProducts()
        }
    }
    init{
        loadCartProducts()
    }

    suspend fun delete(id:Int){
        CoroutineScope(Dispatchers.Main).launch {
            productRepository.delete(id)

        }
    }
}