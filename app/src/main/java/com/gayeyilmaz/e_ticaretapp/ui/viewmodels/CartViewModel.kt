package com.gayeyilmaz.e_ticaretapp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gayeyilmaz.e_ticaretapp.data.entity.CartProducts
import com.gayeyilmaz.e_ticaretapp.data.repos.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(var productRepository : ProductsRepository) : ViewModel() {

    var cartProductsList = MutableLiveData<List<CartProducts>>()


    fun loadCartProducts(username:String){
        CoroutineScope(Dispatchers.Main).launch {
            cartProductsList.value=productRepository.loadCartProducts(username)
        }
    }


    fun delete(id:Int){
        CoroutineScope(Dispatchers.Main).launch {
            productRepository.delete(id)

        }
    }
}