package com.gayeyilmaz.e_ticaretapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.gayeyilmaz.e_ticaretapp.data.entity.CartProducts
import com.gayeyilmaz.e_ticaretapp.data.entity.Products
import com.gayeyilmaz.e_ticaretapp.data.repos.ProductsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {
    var productRepository = ProductsRepository()
    fun addCart(cartProductList:MutableList<CartProducts>,product:Products,ordered:Int){
        CoroutineScope(Dispatchers.Main).launch {
            productRepository.addCart(cartProductList,product,ordered)
        }
    }
}