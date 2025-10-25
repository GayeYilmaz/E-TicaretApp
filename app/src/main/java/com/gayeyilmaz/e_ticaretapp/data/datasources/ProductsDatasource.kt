package com.gayeyilmaz.e_ticaretapp.data.datasources

import android.util.Log

import com.gayeyilmaz.e_ticaretapp.data.entity.CartProducts
import com.gayeyilmaz.e_ticaretapp.data.entity.Products
import com.gayeyilmaz.e_ticaretapp.retrofit.ProductsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class ProductsDatasource (var productsDao: ProductsDao){
    //LOAD CATEGORIES
    suspend fun loadCategories(): List<String> =withContext(Dispatchers.IO){
        return@withContext   listOf("Phones", "Computer", "Health", "Books","Headphones")

    }
    //LOAD PRODUCT
    suspend fun loadProducts():List<Products> = withContext(Dispatchers.IO){
        try {
           // Log.e("Datasource","${productsDao.loadProducts().success}")
            //Log.e("Datasource","${productsDao.loadProducts().urunler}")
            return@withContext productsDao.loadProducts().urunler
        }catch (e: Exception){
            return@withContext emptyList<Products>()
        }

        /**return@withContext listOf(
            Products(1,"Telefon","telefon.png","Teknoloji",18,"Apple"),
            Products(2,"Gözlük","gozluk.png","Aksesuar",35,"Casio"),

            Products(3,"Bilgisayar","bilgisayar.png","Teknoloji",18,"Apple"),

            Products(4,"Kemer","kemer.png","Aksesuar",35,"Casio"),

            )**/

    }
    //LOAD CART PRODUCTS
    suspend fun loadCartProducts(username:String):List<CartProducts> = withContext(Dispatchers.IO){
        Log.e("Datasource","${productsDao.loadCartProducts(username).urunler_sepeti}")
        try {

            return@withContext productsDao.loadCartProducts(username).urunler_sepeti
        }catch (e: Exception){
            return@withContext emptyList<CartProducts>()
        }

    }

    //ADD TO CART
    suspend fun addCart(cartProduct: CartProducts){
        productsDao.addCart(cartProduct.name,cartProduct.image,cartProduct.category,
            cartProduct.price,cartProduct.brand,cartProduct.ordered,cartProduct.username)

        /** Log.e("ProductDatasource","Cart added")
        if( ordered !=0 ){
        if((!cartProductList.any{it.product.id == product.id}) ){
        val index = cartProductList.size
        val cartProduct = CartProducts(1,product,ordered,"gaye_yilmaz")
        cartProductList.add(index,cartProduct)

        Log.e("ProductDatasource","Index:$index-ProductID:${cartProduct.product.id}-Total:$ordered")
        }
        else{
        val cart = cartProductList.indexOfFirst{ it.product.id == product.id }
        val ordered=  cartProductList[cart].ordered + ordered
        Log.e("DetailScreen","-ORDER:${cartProductList[cart].ordered}")
        cartProductList[cart] = cartProductList[cart].copy(ordered=ordered )
        Log.e("ProductDatasource","Index:$cart-ProductID:${cartProductList[cart].product.id}-Total:${cartProductList[cart].ordered}")
        }

        }**/

    }

    //DELETE
    suspend fun delete(id:Int){
        productsDao.delete(id)
        //Log.e("ProductDatasource","Deleted $id")
    }

    //SEARCH
    /**suspend fun search(searchText:String):List<Products> = withContext(Dispatchers.IO){
        return@withContext listOf(
            Products(1,"Telefon","telefon.png","Teknoloji",18,"Apple"),
            ) }**/



}