package com.gayeyilmaz.e_ticaretapp.ui.screens

import android.net.TetheringManager
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.IndeterminateCheckBox
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gayeyilmaz.e_ticaretapp.R
import com.gayeyilmaz.e_ticaretapp.data.entity.Products
import com.gayeyilmaz.e_ticaretapp.data.entity.ProductsBasket
import com.gayeyilmaz.e_ticaretapp.ui.components.CustomStarRatingBar
import com.google.gson.Gson

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavController,product : Products){
    var productNumber = remember { mutableStateOf(0) }
    val context = LocalContext.current

    val productsBasketList = remember { mutableListOf<ProductsBasket>()  }

    fun addBasket(product:Products,productNumber:Int){
        if( productNumber !=0 ){
            if((!productsBasketList.any{it.productId == product.id}) ){
                val index = productsBasketList.size
                val productBasket = ProductsBasket(1,product.id,productNumber,"gaye_yilmaz")
                productsBasketList.add(index,productBasket)

                Log.e("DetailScreen","Index:$index-ProductID:${productBasket.productId}-Total:$productNumber")
            }
            else{
                val basket = productsBasketList.indexOfFirst{ it.productId == product.id }
                val orderNumber =  productsBasketList[basket].orderNumber + productNumber
                Log.e("DetailScreen","-ORDER:${productsBasketList[basket].orderNumber}")
                productsBasketList[basket] = productsBasketList[basket].copy(orderNumber=orderNumber )
                Log.e("DetailScreen","Index:$basket-ProductID:${productsBasketList[basket].productId}-Total:${productsBasketList[basket].orderNumber}")
            }

        }

    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text="Product Detail") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id= R.color.main_color),
                    titleContentColor = colorResource(R.color.white)
                ),
                navigationIcon = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            Icons.Filled.Close,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("basketScreen") }) {
                        Icon(
                            imageVector = Icons.Filled.ShoppingBasket,
                            contentDescription = "Localized description"
                        )
                    }
                }


            )
        },
        bottomBar = {
            Row (
                modifier = Modifier.padding( 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                var total = productNumber.value*product.price
                Text(text = total.toString(), modifier = Modifier
                    .weight(1f),
                    fontSize = 20.sp)
                Button(onClick ={
                    addBasket(product,productNumber.value)
                       productNumber.value = 0         },
                    modifier = Modifier.weight(2f)) {
                    Text(text = "Add Basket")
                }
            }
        }


    ) {innerpadding->
        Column(modifier = Modifier.padding(innerpadding).fillMaxWidth().padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Card (modifier = Modifier.padding(5.dp).width(250.dp).height(250.dp))
            {
                IconButton(onClick = { /* do something */ },
                    modifier = Modifier.padding(start = 210.dp)
                ) {
                    Icon(Icons.Filled.FavoriteBorder, contentDescription = "Localized description")
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    val productImage = product.image
                    Image(modifier = Modifier.size(300.dp).padding(bottom  =50.dp),
                        painter= painterResource(context.resources.getIdentifier(
                        productImage,"drawable",context.packageName)), contentDescription = "picture")

                    Text(text = product.name, modifier = Modifier)
                }
            }
           /** val productImage = product.image
            Image(modifier = Modifier.padding(20.dp).size(200.dp), painter= painterResource(context.resources.getIdentifier(
                productImage,"drawable",context.packageName)), contentDescription = "picture")

            Text(text = product.name)**/




            Row(
                modifier = Modifier.padding(top = 20.dp),
                verticalAlignment = Alignment.CenterVertically

            ){
                IconButton(onClick = {
                    if(productNumber.value != 0){
                        productNumber.value = productNumber.value-1
                    }
                },
                    modifier = Modifier.padding(end=20.dp)

                ) {
                    Icon(Icons.Filled.IndeterminateCheckBox, contentDescription = "Localized description", modifier = Modifier.size(50.dp))
                }

                Text(text = productNumber.value.toString())

                IconButton(onClick = {productNumber.value = productNumber.value+1},
                    modifier = Modifier.padding(start=20.dp)
                ) {
                    Icon(Icons.Filled.AddBox, contentDescription = "Localized description",modifier = Modifier.size(50.dp))
                }


            }



        }

    }
}