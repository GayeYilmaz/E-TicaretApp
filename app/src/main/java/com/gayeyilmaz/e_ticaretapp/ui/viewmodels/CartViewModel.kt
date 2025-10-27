package com.gayeyilmaz.e_ticaretapp.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gayeyilmaz.e_ticaretapp.data.entity.CartProducts
import com.gayeyilmaz.e_ticaretapp.data.entity.Products
import com.gayeyilmaz.e_ticaretapp.data.repos.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.collections.isNotEmpty

@HiltViewModel
class CartViewModel @Inject constructor(var productRepository : ProductsRepository) : ViewModel() {

   val cartProductsList = MutableLiveData<List<CartProducts>>()

    init {
        loadCartProducts("gaye_yilmaz")
    }


    fun loadCartProducts(username: String) {
        CoroutineScope(Dispatchers.Main).launch {
            cartProductsList.value = productRepository.loadCartProducts(username)
        }


    }


    fun delete(id: Int, username: String) {
        CoroutineScope(Dispatchers.Main).launch {

            productRepository.delete(id, username)
            loadCartProducts(username)

        }
    }

    fun addCart(cartProduct: CartProducts) {

        CoroutineScope(Dispatchers.Main).launch {
            var InCart=false
            var newCartProduct = CartProducts(0,"","","",0,"",0,cartProduct.username)
            if(cartProduct.ordered != 0){

                if(cartProductsList.value.size != 0){
                    for(cartProductsItem in cartProductsList.value){

                        if((cartProductsItem.name == cartProduct.name) ){

                            var newordered = cartProductsItem.ordered + cartProduct.ordered
                            newCartProduct.name=cartProductsItem.name
                            newCartProduct.image=cartProductsItem.image
                            newCartProduct.category=cartProductsItem.category
                            newCartProduct.price=cartProductsItem.price
                            newCartProduct.brand=cartProductsItem.brand
                            newCartProduct.ordered=newordered
                            delete(cartProductsItem.cartId,cartProductsItem.username)

                            productRepository.addCart(newCartProduct)
                            loadCartProducts(cartProduct.username)
                            InCart=true



                        }
                    }
                    if(InCart == false){
                        productRepository.addCart(cartProduct)
                        loadCartProducts(cartProduct.username)
                    }


                }else{

                    productRepository.addCart(cartProduct)
                }

            }





        }
    }
}