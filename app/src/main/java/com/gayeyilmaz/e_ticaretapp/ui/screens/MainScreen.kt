package com.gayeyilmaz.e_ticaretapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.gayeyilmaz.e_ticaretapp.R
import com.gayeyilmaz.e_ticaretapp.data.entity.Products
import com.gayeyilmaz.e_ticaretapp.ui.components.CustomBottomAppBar
import com.google.gson.Gson
import kotlin.math.round
import kotlin.text.get

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
            Row(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 15.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                IconButton(
                    onClick = { /* do something */ },
                    modifier = Modifier
                        .padding(end=10.dp)
                        .clip(CircleShape)
                        .size(36.dp)
                        .background(colorResource(R.color.main_color)),

                ){
                    Icon(Icons.Filled.Storefront,
                        contentDescription = "Localized description",
                        modifier = Modifier.size(30.dp),
                        tint = Color.White)
                }
                OutlinedTextField(
                    value=searchQuery.value,
                    shape = RoundedCornerShape(50.dp),
                    onValueChange = {searchQuery.value = it
                        // mainViewModel.search(it)
                    },
                    label={Text(text =stringResource(R.string.main_screen_search_hint))},
                    leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Seacrh Field", tint = colorResource(R.color.black_1))},
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = colorResource(R.color.white),
                        unfocusedContainerColor = colorResource(R.color.background),
                        disabledContainerColor  = colorResource(R.color.white),
                        focusedLabelColor  = colorResource(R.color.main_color),
                        cursorColor  = colorResource(R.color.main_color),
                        focusedBorderColor = colorResource(R.color.main_color),
                        unfocusedBorderColor = colorResource(R.color.text_color),

                        )
                )

            }





        },
        bottomBar = {
            CustomBottomAppBar()
        }
    ){innerpadding ->
        Column(
            modifier= Modifier.fillMaxSize().padding(innerpadding).padding(16.dp).verticalScroll(rememberScrollState())
        ){
           // item{
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        text = "Hot Sales"
                    )
                    Text(
                        modifier = Modifier,
                        fontSize = 15.sp,
                        color = colorResource(R.color.text_color),
                        fontWeight = FontWeight.Bold,
                        text = "see more"
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .background(colorResource(R.color.add_container_background)),
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .weight(1f)
                                .align(Alignment.CenterVertically)
                        ) {
                            IconButton(
                                onClick = { /* do something */ },
                                modifier = Modifier
                                    .padding(end = 10.dp)
                                    .clip(CircleShape)
                                    .size(30.dp)
                                    .background(colorResource(R.color.main_color)),
                            ) {
                                Icon(
                                    Icons.Filled.Storefront,
                                    contentDescription = "Localized description",
                                    modifier = Modifier.size(24.dp),
                                    tint = Color.White
                                )
                            }

                            Text(
                                modifier = Modifier.padding(top = 16.dp),
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold,
                                text = "iPhone 14",
                                color = colorResource(R.color.white)
                            )

                            Text(
                                modifier = Modifier,
                                fontSize = 10.sp,
                                text = "MegaClear",
                                color = colorResource(R.color.white)
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            Button(
                                onClick = { /* TODO */ },
                                modifier = Modifier
                                    .height(30.dp)
                                    .width(120.dp),
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.White
                                )
                            ) {
                                Text(
                                    text = "Buy Now!",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                            }
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(),
                            contentAlignment = Alignment.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(150.dp)
                                    .background(Color.Gray, shape = CircleShape)
                            ) {
                                Image(
                                    modifier = Modifier,
                                    painter = painterResource(R.drawable.add_photo),
                                    contentDescription = "picture"
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        text = "Select Category"
                    )
                    Text(
                        modifier = Modifier,
                        fontSize = 15.sp,
                        color = colorResource(R.color.text_color),
                        fontWeight = FontWeight.Bold,
                        text = "view all"
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
                val categories = listOf("Phones", "Computer", "Health", "Books")
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(categories.size) { category ->
                        val name = categories[category]
                        Card(
                            modifier = Modifier
                                .height(100.dp)
                                .width(75.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(10.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    Icons.Filled.PhoneAndroid,
                                    contentDescription = "Phone",
                                    modifier = Modifier.size(36.dp),
                                    tint = Color.White
                                )
                                Text(
                                    modifier = Modifier.padding(top = 10.dp),
                                    fontSize = 12.sp,
                                    text = name
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        text = "Products"
                    )
                    Text(
                        modifier = Modifier,
                        fontSize = 15.sp,
                        color = colorResource(R.color.text_color),
                        fontWeight = FontWeight.Bold,
                        text = "see more"
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(count = 2),
                    modifier = Modifier.fillMaxWidth().height(500.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(ProductsList.size) { index ->
                        val product = ProductsList[index]
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .background(colorResource(R.color.background))
                                    .fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                IconButton(
                                    onClick = { /* do something */ },
                                    modifier = Modifier.align(Alignment.End)
                                ) {
                                    Icon(
                                        Icons.Filled.FavoriteBorder,
                                        contentDescription = "Add to favorites"
                                    )
                                }

                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clickable {
                                            val productJson = Gson().toJson(product)
                                            navController.navigate("detailScreen/$productJson")
                                        }
                                        .padding(10.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    val productImage = product.image
                                    Image(
                                        painter = painterResource(
                                            context.resources.getIdentifier(
                                                productImage,
                                                "drawable",
                                                context.packageName
                                            )
                                        ),
                                        contentDescription = "product image",
                                        modifier = Modifier.size(80.dp)
                                    )

                                    Text(
                                        text = product.name,
                                        modifier = Modifier.padding(top = 8.dp)
                                    )

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = "$${product.price}",
                                            modifier = Modifier.weight(1f)
                                        )
                                        IconButton(onClick = { /* do something */ }) {
                                            Icon(
                                                Icons.Filled.AddBox,
                                                contentDescription = "Add to cart"
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
           // }


        }


    }


}