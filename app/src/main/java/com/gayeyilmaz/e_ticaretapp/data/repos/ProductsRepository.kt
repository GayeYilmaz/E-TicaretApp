package com.gayeyilmaz.e_ticaretapp.data.repos


import com.gayeyilmaz.e_ticaretapp.data.datasources.ProductsDatasource
import com.gayeyilmaz.e_ticaretapp.data.entity.CartProducts
import com.gayeyilmaz.e_ticaretapp.data.entity.Products

class ProductsRepository(var productsDatasource: ProductsDatasource) {


    suspend fun loadCategories(): List<String>{
        return productsDatasource.loadCategories()
    }
    suspend fun loadProducts():List<Products>{
        return productsDatasource.loadProducts()
    }
    suspend fun loadCartProducts(username:String):List<CartProducts>{
        return productsDatasource.loadCartProducts(username)
    }


   /** suspend fun search(searchText:String):List<Products> {
        return productsDatasource.search(searchText)
    }**/
    suspend fun addCart(cartProduct: CartProducts){
        return productsDatasource.addCart(cartProduct)
    }

    suspend fun delete(id:Int){
       productsDatasource.delete(id)
    }

}