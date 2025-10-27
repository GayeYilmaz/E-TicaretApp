package com.gayeyilmaz.e_ticaretapp.ui.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gayeyilmaz.e_ticaretapp.R
import com.gayeyilmaz.e_ticaretapp.ui.components.CategoriesCard
import com.gayeyilmaz.e_ticaretapp.ui.components.CustomBottomAppBar
import com.gayeyilmaz.e_ticaretapp.ui.components.CustomProductCard
import com.gayeyilmaz.e_ticaretapp.ui.viewmodels.MainViewModel
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.LaunchedEffect
import com.gayeyilmaz.e_ticaretapp.data.entity.FavoriteProducts
import com.gayeyilmaz.e_ticaretapp.data.entity.Products


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController,mainViewModel: MainViewModel){

    var scrollState = rememberScrollState()
    val searchQuery = remember { mutableStateOf("") }
    val favList = mainViewModel.favoriteProductsList






   val context = LocalContext.current

    val images = remember{listOf("bilgisyar","gozluk","kemer","telefon")}

    val productsList = mainViewModel.productsList.observeAsState(listOf())

    var categoryList = mutableListOf<String>()
    for(product in productsList.value){
        if(categoryList.isNotEmpty()){
            if(categoryList.contains(product.category)){
                Log.e("CATEGORY","listede ${product.category}")
            }
            else{
                categoryList.add(product.category)
                Log.e("CATEGORY","listede değil  ${product.category}")
            }

        }else{
            categoryList.add(product.category)
            Log.e("CATEGORY","${product.category}")
        }


    }


    LaunchedEffect(true) {
        mainViewModel.loadProducts()
    }

    Scaffold(

        topBar = {
            Row(modifier = Modifier.statusBarsPadding().padding(horizontal = 16.dp)
            ){
                IconButton(
                    onClick = { /* do something */ },
                    modifier = Modifier
                        .offset(y=17.dp)
                        .padding(end = 10.dp)
                        .clip(CircleShape)
                        .size(36.dp)
                        .background(colorResource(R.color.main_color)),
                ){
                    Icon(Icons.Filled.Storefront,
                        contentDescription = "Localized description",
                        modifier = Modifier.size(30.dp),
                        tint = Color.White)
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    OutlinedTextField(
                        value = searchQuery.value,
                        onValueChange = {
                            searchQuery.value = it
                           // mainViewModel.search(it)
                        },
                        label = { Text(modifier = Modifier,
                            text = "Search")},
                        leadingIcon = { Icon(
                            Icons.Filled.Search,
                            contentDescription = "Search Field",
                            tint = colorResource(id = R.color.main_color))
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = colorResource(id = R.color.white),
                            unfocusedContainerColor = colorResource(id = R.color.white),
                            disabledContainerColor = colorResource(id = R.color.white),
                            focusedLabelColor = colorResource(id = R.color.main_color),
                            cursorColor = colorResource(id = R.color.main_color),
                            focusedBorderColor = colorResource(id = R.color.main_color),
                            unfocusedBorderColor = colorResource(id = R.color.main_color),
                        ),
                        shape = RoundedCornerShape(30.dp),
                        singleLine = true,
                    )
                }
            }
        },
        bottomBar = {
            CustomBottomAppBar(navController,)
        }
    ){innerpadding ->
        Column(
            modifier= Modifier
                .fillMaxSize()
                .padding(innerpadding)
                .padding(16.dp)
                .verticalScroll(scrollState)
        ){

           //HOT SALES HEADLINE
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
            //ADD BLOCK
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

               //CATEGORY HEADLINE
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

               //CATEGORIES ROW
                LazyRow(
                    modifier = Modifier.fillMaxWidth()
                ) {


                    items(categoryList){category->
                        CategoriesCard(category)
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(500.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    if(!(productsList.value==null)){
                        items(productsList.value) { product ->
                            CustomProductCard(navController=navController,
                                product=product,
                                context=context,
                                onFavoriteClick ={ favProduct ->
                                    //Log.e("FAV","${favProduct.name} - In Favor :${favProduct.isFavorite}")
                                    if(favProduct.isFavorite == true){
                                        Log.e("FAV","${favProduct.name} - Added :${favList} ")
                                        favList.add(favProduct)
                                    }else{
                                        val index = favList.indexOfFirst {it.id == favProduct.id  }
                                        favList.removeAt(index)

                                        Log.e("FAV","${product.name} - Deleted :${favList}")
                                    }


                            } ,
                                isFavorite=favList.any { it.id == product.id }




                            )
                        }

                    }

                }




        }


    }


}