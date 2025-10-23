package com.gayeyilmaz.e_ticaretapp.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.RestoreFromTrash
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.findFirstRoot
import androidx.compose.ui.modifier.ModifierLocalReadScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gayeyilmaz.e_ticaretapp.R
import com.gayeyilmaz.e_ticaretapp.data.entity.Products
import com.gayeyilmaz.e_ticaretapp.data.entity.ProductsBasket
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.runtime.mutableStateOf

import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasketScreen(navController: NavController){



    val productsList = remember { mutableListOf<Products>() }
    val p1 =Products(1,"Telefon","telefon","Teknoloji",18,"Apple")
    val p2 =Products(2,"Gözlük","gozluk","Aksesuar",35,"Casio")
    val p3 =Products(3,"Bilgisayar","bilgisayar","Teknoloji",18,"Apple")
    val p4 =Products(4,"Kemer","kemer","Aksesuar",35,"Casio")

    productsList.add(p1)
    productsList.add(p2)
    productsList.add(p3)
    productsList.add(p4)

    val productsBasketList = remember { mutableListOf<ProductsBasket>()  }
    val pb1 = ProductsBasket(1,1,2,"gaye_yilmaz")
    val pb2 = ProductsBasket(2,3,1,"gaye_yilmaz")
    val pb3 = ProductsBasket(3,2,4,"gaye_yilmaz")
    productsBasketList.add(pb1)
    productsBasketList.add(pb2)
    productsBasketList.add(pb3)
    var price = 0
    var totalPrice = 0
    for(basketProduct in productsBasketList){
        val product = productsList[basketProduct.productId]

        totalPrice = (basketProduct.orderNumber * product.price)+totalPrice
        Log.e("BasketScreen" ,"$totalPrice")
    }






    val context = LocalContext.current
    Scaffold(

        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text="Basket ") },
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
            )
        },
        bottomBar = {
            Column (
                modifier = Modifier.fillMaxWidth().padding( 20.dp),


            ){
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween

                ){
                    Text(text = "Tranasport fee :")
                    Text(text =  " 0 " )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(text = "Total :")
                    Text(text =  "${totalPrice}" )
                }
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {}
                ) {
                    Text(text = " Approve Basket")
                }


            }
        }



    )
    { innerpadding->
        Column(
            modifier = Modifier
                .padding(innerpadding)
                .padding(10.dp)
        ){
            for(basketProduct in productsBasketList){

                val productIndex = basketProduct.productId
                val product = productsList[productIndex]
                Card (
                    modifier = Modifier.padding(5.dp)
                    )
                { Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                           )
                    {
                        Image(
                            modifier = Modifier.size(100.dp),
                            painter= painterResource(context.resources.getIdentifier(
                            product.image,"drawable",context.packageName)), contentDescription = "picture")
                    Column (
                        modifier = Modifier.padding(top = 10.dp)
                    )
                    {
                        Text(text = product.name )
                        Spacer(modifier = Modifier.padding(top=5.dp))
                        Text(text = "Price: ${product.price} ")
                        Spacer(modifier = Modifier.padding(top=5.dp))
                        Text(text = "Number: ${basketProduct.orderNumber} ")

                    }
                    Column (
                        modifier = Modifier,
                        verticalArrangement = Arrangement.SpaceBetween


                    ){
                        IconButton(onClick = { /* do something */ },
                            modifier = Modifier
                        ) {
                            Icon(Icons.Filled.Delete, contentDescription = "Localized description")
                        }

                        price = basketProduct.orderNumber * product.price
                        Text(modifier = Modifier.padding(top=25.dp),
                            text = "${price} ")
                        totalPrice = totalPrice + price

                    }

                    }
                }



            }
        }

    }

}