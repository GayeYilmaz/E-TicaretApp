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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.IndeterminateCheckBox
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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
                modifier = Modifier.height(60.dp),
                title = { Text(text="Details") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id= R.color.main_color),
                    titleContentColor = colorResource(R.color.white)
                ),
                navigationIcon = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            Icons.Filled.Close,
                            tint = colorResource(R.color.white),
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("basketScreen") }) {
                        Icon(
                            imageVector = Icons.Filled.ShoppingBasket,
                            tint = colorResource(R.color.white),
                            contentDescription = "Localized description"
                        )
                    }
                }


            )
        },
        bottomBar = {
            Row (
                modifier = Modifier.background(colorResource(R.color.add_container_background)).padding( vertical = 20.dp, horizontal = 30.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                var total = productNumber.value*product.price
                val totalS = total.toString()
                Text(text ="$ $totalS", modifier = Modifier

                    .weight(1f),
                    color = colorResource(R.color.white),
                            fontSize = 20.sp)
                Button(onClick ={
                    addBasket(product,productNumber.value)
                       productNumber.value = 0         },
                    modifier = Modifier.width(150.dp).height(50.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.main_color))
                ) {
                    Text(
                        fontWeight = FontWeight.Bold,
                        text = "Add to Cart")
                }
            }
        }


    ) {innerpadding->
        Column(modifier = Modifier
            .background(colorResource(R.color.add_container_background))
            .padding(innerpadding)
            .fillMaxSize()
            .padding(16.dp)
          ,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Card (modifier = Modifier.padding(5.dp).fillMaxWidth().height(300.dp),
            colors= CardDefaults.cardColors(colorResource(R.color.white)))
            {
                IconButton(onClick = { /* do something */ },
                    modifier = Modifier.padding(start =310.dp)
                ) {
                    Icon(Icons.Filled.FavoriteBorder, contentDescription = "Localized description")
                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    val productImage = product.image
                    Image(modifier = Modifier.size(200.dp),
                        painter= painterResource(context.resources.getIdentifier(
                            productImage,"drawable",context.packageName)), contentDescription = "picture")
                    IconButton(onClick = { /* do something */ },
                        modifier = Modifier
                    ) {
                        Icon(
                            Icons.Filled.MoreHoriz, contentDescription = "Localized description",
                            modifier = Modifier.size(56.dp),
                            tint = colorResource(R.color.black_1))
                    }
                }
            }
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 10.dp,start=20.dp),
                    verticalAlignment = Alignment.CenterVertically

                ){
                    Text(text = product.name,
                        fontSize = 20.sp,
                        color = colorResource(R.color.white),
                        modifier = Modifier
                    )
                    Row(
                        modifier = Modifier.padding(start = 150.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        IconButton(onClick = {
                            if(productNumber.value != 0){
                                productNumber.value = productNumber.value-1
                            }
                        },
                            modifier = Modifier.padding(end=20.dp)

                        ) {
                            Icon(Icons.Filled.IndeterminateCheckBox,
                                contentDescription = "Localized description",
                                tint = colorResource(R.color.white),
                                modifier = Modifier.size(50.dp))
                        }

                        Text(modifier = Modifier,
                            color=colorResource(R.color.white),
                            text = productNumber.value.toString())

                        IconButton(onClick = {productNumber.value = productNumber.value+1},
                            modifier = Modifier.padding(start=20.dp)
                        ) {
                            Icon(Icons.Filled.AddBox,
                                contentDescription = "Localized description",
                                tint = colorResource(R.color.white),
                                modifier = Modifier.size(50.dp))
                        }

                    }

                }
            Row(modifier = Modifier.fillMaxWidth().padding(top=16.dp),
                horizontalArrangement = Arrangement.SpaceAround

                ){

                    Text( fontWeight = FontWeight.Bold,
                        modifier = Modifier.drawWithContent{
                            drawContent()
                            drawLine(
                                color = Color(0xFFFC7022),
                                start = Offset(0f, size.height),
                                end = Offset(size.width, size.height),
                                strokeWidth = 5f
                            )
                        },
                        color = colorResource(R.color.white),
                        text =  "Details")


                Text(modifier = Modifier,
                    color = colorResource(R.color.text_color),
                    text = "Shop")
                Text(modifier = Modifier,
                    color = colorResource(R.color.text_color),
                    text =  "Features")
            }










        }

    }
}