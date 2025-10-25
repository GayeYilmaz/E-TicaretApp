package com.gayeyilmaz.e_ticaretapp.data.datasources

import android.util.Log
import androidx.compose.runtime.remember
import com.gayeyilmaz.e_ticaretapp.data.entity.CartProducts
import com.gayeyilmaz.e_ticaretapp.data.entity.Products
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductsDatasource {
    //LOAD CATEGORIES
    suspend fun loadCategories(): List<String> =withContext(Dispatchers.IO){
        return@withContext   listOf("Phones", "Computer", "Health", "Books","Headphones")

    }
    //LOAD PRODUCT
    suspend fun loadProducts():List<Products> = withContext(Dispatchers.IO){
        return@withContext listOf(
            Products(1,"Telefon","telefon","Teknoloji",18,"Apple"),
            Products(2,"Gözlük","gozluk","Aksesuar",35,"Casio"),

            Products(3,"Bilgisayar","bilgisayar","Teknoloji",18,"Apple"),

            Products(4,"Kemer","kemer","Aksesuar",35,"Casio"),

            )

    }
    //LOAD CART PRODUCTS
    suspend fun loadCartProducts():List<CartProducts> = withContext(Dispatchers.IO){

        val p1 =Products(1,"Telefon","telefon","Teknoloji",18,"Apple")
        val p2 =Products(2,"Gözlük","gozluk","Aksesuar",35,"Casio")
        val p3 =Products(3,"Bilgisayar","bilgisayar","Teknoloji",18,"Apple")
        val p4 =Products(4,"Kemer","kemer","Aksesuar",35,"Casio")






        return@withContext listOf(
              CartProducts(1,p1,2,"gaye_yilmaz") ,
              CartProducts(4,p2,1,"gaye_yilmaz"),
              CartProducts(3,p3,4,"gaye_yilmaz")
            )

    }

    //SEARCH
    suspend fun search(searchText:String):List<Products> = withContext(Dispatchers.IO){
        return@withContext listOf(
            Products(1,"Telefon","telefon","Teknoloji",18,"Apple"),


            )

    }
     //ADD TO CART
    suspend fun addCart(cartProductList: MutableList<CartProducts>, product:Products, ordered:Int){
        Log.e("ProductDatasource","Cart added")
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

        }

    }

    //DELETE
    suspend fun delete(id:Int){
        Log.e("ProductDatasource","Deleted $id")
    }

}