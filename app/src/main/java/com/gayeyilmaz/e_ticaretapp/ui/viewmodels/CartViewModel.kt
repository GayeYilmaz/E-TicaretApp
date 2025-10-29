package com.gayeyilmaz.e_ticaretapp.ui.viewmodels


import android.util.Log
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

   val cartProductsList = MutableLiveData<List<CartProducts>>()

    init {
        loadCartProducts("gaye_yilmaz")
        Log.e("PRODUCT",    "init  ${cartProductsList.value}")
    }


    fun loadCartProducts(username: String) {
        CoroutineScope(Dispatchers.Main).launch {
            cartProductsList.value = productRepository.loadCartProducts(username)
            Log.e("PRODUCT",    "loadCartProducts  ${cartProductsList.value}")
        }
    }


    fun delete(id: Int, username: String) {
        CoroutineScope(Dispatchers.Main).launch {
            productRepository.delete(id, username)
            loadCartProducts(username)
        }
    }

    fun updateCart(cartProduct: CartProducts){
        CoroutineScope(Dispatchers.Main).launch {
            var InCart=false
            var newCartProduct = CartProducts(0,"","","",0,"",0,cartProduct.username)
            if(cartProduct.ordered != 0){
                if(cartProductsList.value.size != 0){
                    for(cartProductsItem in cartProductsList.value){
                        if((cartProductsItem.name == cartProduct.name) ){
                            newCartProduct.name=cartProductsItem.name
                            newCartProduct.image=cartProductsItem.image
                            newCartProduct.category=cartProductsItem.category
                            newCartProduct.price=cartProductsItem.price
                            newCartProduct.brand=cartProductsItem.brand
                            newCartProduct.ordered= cartProduct.ordered
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




    fun addCart(cartProduct: CartProducts) {
        Log.e("PRODUCT",    "addcart function  ${cartProduct}")
        CoroutineScope(Dispatchers.Main).launch {
            var InCart=false
            var newCartProduct = CartProducts(0,"","","",0,"",0,cartProduct.username)

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
                            Log.e("PRODUCT",    "added product  in list   ${newCartProduct}")
                        }
                    }
                    if(InCart == false){
                        productRepository.addCart(cartProduct)
                        loadCartProducts(cartProduct.username)
                        Log.e("PRODUCT",    "added product not in list   ${cartProduct}")
                    }
                }else{

                    productRepository.addCart(cartProduct)
                    loadCartProducts(cartProduct.username)
                    Log.e("PRODUCT",    "added product emmpty list   ${cartProduct}")
                }


            loadCartProducts(cartProduct.username)
        }
    }




}