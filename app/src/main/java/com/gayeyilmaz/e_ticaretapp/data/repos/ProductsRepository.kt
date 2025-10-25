package com.gayeyilmaz.e_ticaretapp.data.repos


import com.gayeyilmaz.e_ticaretapp.data.datasources.ProductsDatasource
import com.gayeyilmaz.e_ticaretapp.data.entity.CartProducts
import com.gayeyilmaz.e_ticaretapp.data.entity.Products

class ProductsRepository {
    var productsDatasource = ProductsDatasource()

    suspend fun loadCategories(): List<String>{
        return productsDatasource.loadCategories()
    }
    suspend fun loadProducts():List<Products>{
        return productsDatasource.loadProducts()
    }
    suspend fun loadCartProducts():List<CartProducts>{
        return productsDatasource.loadCartProducts()
    }


    suspend fun search(searchText:String):List<Products> {
        return productsDatasource.search(searchText)
    }
    suspend fun addCart(cartProductList:MutableList<CartProducts>,product:Products,ordered:Int){
        return productsDatasource.addCart(cartProductList,product,ordered)
    }

    suspend fun delete(id:Int){
       productsDatasource.delete(id)
    }

}