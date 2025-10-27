package com.gayeyilmaz.e_ticaretapp.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
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

   /** var cartProductsList = MutableLiveData<List<CartProducts>>()**/

    /**fun addCart(cartProduct:CartProducts){
        CoroutineScope(Dispatchers.Main).launch {

            val cartProductsList =productRepository.loadCartProducts(cartProduct.username)
            Log.e("AddCartRepos","Cart Product List Size ${cartProductsList.size} ")
            if(cartProductsList.size !=0){
                for(cartProductItem in cartProductsList){
                    Log.e("AddCartRepos","Product already in ${cartProduct.name} ordered ${cartProduct.ordered}")
                    if((cartProductItem.name == cartProduct.name) && (cartProductItem.brand == cartProduct.brand)){
                        val newOrdered = cartProduct.ordered + cartProductItem.ordered
                        productRepository.delete(cartProduct.cartId,cartProduct.username)
                        cartProduct.ordered =newOrdered


                    }
                    //Log.e("AddCartRepos","Product not added${cartProduct.name} new ordered ${cartProduct.ordered}")
                }

            }
            Log.e("AddCartRepos","Product added${cartProduct.name} new ordered ${cartProduct.ordered}")

            productRepository.addCart(cartProduct)

            //productRepository.loadCartProducts(cartProduct.username)

        }
    }**/
}