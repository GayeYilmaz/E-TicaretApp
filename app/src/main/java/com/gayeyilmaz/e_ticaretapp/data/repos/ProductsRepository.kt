package com.gayeyilmaz.e_ticaretapp.data.repos


import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.MutableLiveData
import com.gayeyilmaz.e_ticaretapp.data.datasources.ProductsDatasource
import com.gayeyilmaz.e_ticaretapp.data.entity.CartProducts
import com.gayeyilmaz.e_ticaretapp.data.entity.FavoriteProducts
import com.gayeyilmaz.e_ticaretapp.data.entity.Products

class ProductsRepository(var productsDatasource: ProductsDatasource) {

    var favoriteProductsList = mutableStateListOf<FavoriteProducts>()



    suspend fun loadCategories(): List<String>{
        return productsDatasource.loadCategories()
    }
    suspend fun loadProducts():List<Products>{
        return productsDatasource.loadProducts()
    }
    suspend fun loadCartProducts(username:String):List<CartProducts>{
        return productsDatasource.loadCartProducts(username)
    }



    suspend fun addCart(cartProduct: CartProducts){

      return productsDatasource.addCart(cartProduct)
    }

    suspend fun delete(id:Int,username:String){
       productsDatasource.delete(id,username)
    }


}