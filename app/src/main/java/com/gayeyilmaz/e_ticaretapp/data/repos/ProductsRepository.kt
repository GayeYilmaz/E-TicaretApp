package com.gayeyilmaz.e_ticaretapp.data.repos

import androidx.compose.runtime.mutableStateListOf
import com.gayeyilmaz.e_ticaretapp.data.datasources.ProductsDatasource
import com.gayeyilmaz.e_ticaretapp.data.entity.CartProducts
import com.gayeyilmaz.e_ticaretapp.data.entity.FavoriteProducts
import com.gayeyilmaz.e_ticaretapp.data.entity.Products

class ProductsRepository(var productsDatasource: ProductsDatasource) {
    var favoritiesList = mutableStateListOf<FavoriteProducts>()

    //FAVORITE OPERATIONS
    suspend fun loadFavorites(): List<FavoriteProducts>{
       // Log.e("FAV","Repository  Load :${productsDatasource.loadFavorites()} ")
        return favoritiesList

    }


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