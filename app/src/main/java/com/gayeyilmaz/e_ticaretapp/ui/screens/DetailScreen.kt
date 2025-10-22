package com.gayeyilmaz.e_ticaretapp.ui.screens

import android.net.TetheringManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.IndeterminateCheckBox
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
import androidx.navigation.NavController
import com.gayeyilmaz.e_ticaretapp.R
import com.gayeyilmaz.e_ticaretapp.data.entity.Products
import com.gayeyilmaz.e_ticaretapp.ui.components.CustomStarRatingBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavController,product : Products){
    val productNumber = remember { mutableStateOf(0) }
    val context = LocalContext.current
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
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "Localized description"
                        )
                    }
                }


            )
        },
        bottomBar = {
            Row (){
                val total = productNumber.value*product.price
                Text(text = total.toString(), modifier = Modifier.weight(1f))
                Button(onClick ={} ) {
                    Text(text = "Add Basket")
                }
            }
        }


    ) {innerpadding->
        Column(modifier = Modifier.padding(innerpadding).fillMaxWidth().padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {


            val productImage = product.image
            Image(modifier = Modifier.padding(20.dp).size(200.dp), painter= painterResource(context.resources.getIdentifier(
                productImage,"drawable",context.packageName)), contentDescription = "picture")

            Text(text = product.name)

            Row(
                modifier = Modifier,
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