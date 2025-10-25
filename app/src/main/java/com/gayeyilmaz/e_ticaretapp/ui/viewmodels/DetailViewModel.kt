package com.gayeyilmaz.e_ticaretapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.gayeyilmaz.e_ticaretapp.data.entity.CartProducts
import com.gayeyilmaz.e_ticaretapp.data.entity.Products
import com.gayeyilmaz.e_ticaretapp.data.repos.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel@Inject constructor(var productRepository : ProductsRepository) : ViewModel() {

    fun addCart(cartProduct:CartProducts){
        CoroutineScope(Dispatchers.Main).launch {
            productRepository.addCart(cartProduct)
        }
    }
}