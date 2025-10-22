package com.gayeyilmaz.e_ticaretapp.ui.screens



import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gayeyilmaz.e_ticaretapp.R
import com.gayeyilmaz.e_ticaretapp.data.entity.Products
import com.gayeyilmaz.e_ticaretapp.ui.components.CustomBottomAppBar
import com.google.gson.Gson

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController){
    val images = remember{listOf("bilgisyar","gozluk","kemer","telefon")}

    val searchQuery = remember { mutableStateOf("") }
    val ProductsList = remember { mutableListOf<Products>() }
    val p1 =Products(1,"Telefon","telefon","Teknoloji",18000,"Apple")
    val p2 =Products(2,"Gözlük","gozluk","Aksesuar",3500,"Casio")
    val p3 =Products(3,"Bilgisayar","bilgisayar","Teknoloji",18000,"Apple")
    val p4 =Products(4,"Kemer","kemer","Aksesuar",3500,"Casio")

    ProductsList.add(p1)
    ProductsList.add(p2)
    ProductsList.add(p3)
    ProductsList.add(p4)


   val context = LocalContext.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text="Home") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id= R.color.main_color),
                    titleContentColor = colorResource(R.color.white)
                )
            )
        },
        bottomBar = {
            CustomBottomAppBar()
        }
    ){innerpadding ->
        Column(
            modifier = Modifier.padding(innerpadding)
        ){
            OutlinedTextField(
                value=searchQuery.value,
                onValueChange = {searchQuery.value = it
                   // mainViewModel.search(it)


                },
                label={Text(text =stringResource(R.string.main_screen_search_hint))},
                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Seacrh Field", tint = colorResource(R.color.main_color))},
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = colorResource(R.color.white),
                    unfocusedContainerColor = colorResource(R.color.white),
                    disabledContainerColor  = colorResource(R.color.white),
                    focusedLabelColor  = colorResource(R.color.main_color),
                    cursorColor  = colorResource(R.color.main_color),
                    focusedBorderColor = colorResource(R.color.main_color),
                    unfocusedBorderColor = colorResource(R.color.white),

                    )
            )

            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                columns = GridCells.Fixed(count=2)

            ) {
                items(
                    count = ProductsList.count(),
                    itemContent = {
                        val product = ProductsList[it]
                        Card (modifier = Modifier.padding(5.dp)

                        ){
                            Column (
                                modifier = Modifier.background(colorResource(R.color.background)),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ){
                                IconButton(onClick = { /* do something */ },
                                    modifier = Modifier.padding(start = 120.dp)
                                ) {
                                    Icon(Icons.Filled.FavoriteBorder, contentDescription = "Localized description")
                                }
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clickable{
                                            val productJson = Gson().toJson(product)
                                            navController.navigate("detailScreen/$productJson")
                                        }
                                        .padding(10.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ){
                                    val productImage = product.image
                                    Image(painter= painterResource(context.resources.getIdentifier(
                                        productImage,"drawable",context.packageName)), contentDescription = "picture")

                                    Text(text = product.name, modifier = Modifier)
                                    Row(
                                        modifier= Modifier,
                                        verticalAlignment = Alignment.CenterVertically
                                    ){
                                        val productProduct = product.price.toString()
                                        Text(text = "$ $productProduct ", modifier = Modifier)
                                        Spacer(modifier = Modifier.padding(30.dp))
                                        IconButton(onClick = { /* do something */ },
                                            modifier = Modifier
                                        ) {
                                            Icon(Icons.Filled.AddBox, contentDescription = "Localized description")
                                        }



                                    }
                                }


                            }
                        }
                    }
                )

            }
        }

    }


}