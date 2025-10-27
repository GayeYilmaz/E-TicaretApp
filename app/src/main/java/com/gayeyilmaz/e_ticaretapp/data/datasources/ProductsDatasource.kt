package com.gayeyilmaz.e_ticaretapp.data.datasources

import android.util.Log
import androidx.compose.ui.platform.LocalContext
import com.android.volley.Request.Method
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

import com.gayeyilmaz.e_ticaretapp.data.entity.CartProducts
import com.gayeyilmaz.e_ticaretapp.data.entity.Products
import com.gayeyilmaz.e_ticaretapp.retrofit.ProductsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import kotlin.text.category

class ProductsDatasource (var productsDao: ProductsDao){
    //LOAD CATEGORIES

    suspend fun loadCategories(): List<String> =withContext(Dispatchers.IO){
        return@withContext   listOf("Phones", "Computer", "Health", "Books","Headphones")

    }
    //LOAD PRODUCT
    suspend fun loadProducts():List<Products> = withContext(Dispatchers.IO){
        try {

            return@withContext productsDao.loadProducts().urunler
        }catch (e: Exception){
            return@withContext emptyList<Products>()
        }

    }
    //LOAD CART PRODUCTS
    suspend fun loadCartProducts(username:String):List<CartProducts> = withContext(Dispatchers.IO){
        try {

            return@withContext productsDao.loadCartProducts(kullaniciAdi = username).urunler_sepeti
        }catch (e: Exception){

            return@withContext emptyList<CartProducts>()
        }

    }

    //ADD TO CART
    suspend fun addCart(cartProduct: CartProducts){



        Log.e( "CartProduct","Datasource cart product name : ${cartProduct.name}")
        Log.e( "CartProduct","addCart : ${productsDao.addCart(cartProduct.name,cartProduct.image,cartProduct.category,
            cartProduct.price,cartProduct.brand,cartProduct.ordered,cartProduct.username).success
        }")



    }

    //DELETE
    suspend fun delete(id:Int,username:String){
        productsDao.delete(id,username)

    }


}