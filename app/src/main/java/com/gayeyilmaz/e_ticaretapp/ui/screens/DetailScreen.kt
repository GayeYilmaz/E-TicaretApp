package com.gayeyilmaz.e_ticaretapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.IndeterminateCheckBox
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gayeyilmaz.e_ticaretapp.R
import com.gayeyilmaz.e_ticaretapp.data.entity.CartProducts
import com.gayeyilmaz.e_ticaretapp.data.entity.Products
import com.gayeyilmaz.e_ticaretapp.ui.components.CustomTopAppBAr
import com.gayeyilmaz.e_ticaretapp.ui.viewmodels.CartViewModel
import com.gayeyilmaz.e_ticaretapp.ui.viewmodels.DetailViewModel
import com.skydoves.landscapist.glide.GlideImage


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavController,product : Products,detailViewModel: DetailViewModel,username:String,cartViewModel: CartViewModel){

    var ordered = remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            CustomTopAppBAr(navController,"Ürün Detayı")
        },
        bottomBar = {
            Row (
                modifier = Modifier
                .clip(RoundedCornerShape(topStart=0.dp, topEnd = 0.dp, bottomStart = 0.dp, bottomEnd = 0.dp))
                    .background(colorResource(R.color.bottom_bar_background)).padding( vertical = 20.dp, horizontal = 30.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                var total = ordered.value*product.price
                val totalS = total.toString()
                Text(text ="₺$totalS", modifier = Modifier

                    .weight(1f),
                    color = colorResource(R.color.text_color_main),
                            fontSize = 20.sp)
                Button(onClick ={
                    cartViewModel.addCart(CartProducts(0,product.name,product.image,product.category,product.price,product.brand,ordered.value,username))
                    navController.navigate( "cartScreen" )
                       ordered.value = 0 },
                    modifier = Modifier.width(150.dp).height(50.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.main_color))
                ) {
                    Text(
                        fontWeight = FontWeight.Bold,
                        text = "Sepete Ekle")
                }
            }
        }


    ) {innerpadding->
        //CONTENT
        Column(modifier = Modifier
            .background(colorResource(R.color.background))
            .padding(innerpadding)
            .fillMaxSize()
            .padding(16.dp)
          ,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {   //IMAGES
            Card (modifier = Modifier.padding(5.dp).fillMaxWidth().height(300.dp),
                elevation = CardDefaults.cardElevation(8.dp),
            colors= CardDefaults.cardColors(colorResource(R.color.card_container_background)))
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
                    val url = "http://kasimadalan.pe.hu/urunler/resimler/${product.image}"
                    GlideImage(imageModel = url,  modifier  = Modifier.size(200.dp))
                    IconButton(onClick = { /* do something */ },
                        modifier = Modifier
                    ) {
                        Icon(
                            Icons.Filled.MoreHoriz, contentDescription = "Localized description",
                            modifier = Modifier.size(56.dp),
                            tint = colorResource(R.color.text_color_main))
                    }
                }
            }   //DETAILS

                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 10.dp,start=20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween


                ){
                    Row {
                        Text(text = product.brand,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(R.color.add_container_background),
                            modifier = Modifier
                        )
                        Text(text = " ${product.name}",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium,
                            color = colorResource(R.color.add_container_background),
                            modifier = Modifier
                        )
                    }

                    val price =product.price.toString()

                    Text(text = " ₺$price",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(R.color.add_container_background),
                        modifier = Modifier,
                    )



                }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically

            ){
                IconButton(onClick = {
                    if(ordered.value != 0){
                        ordered.value = ordered.value-1
                    }
                },
                    modifier = Modifier

                ) {
                    Icon(Icons.Filled.IndeterminateCheckBox,
                        contentDescription = "Localized description",
                        tint = colorResource(R.color.add_container_background),
                        modifier = Modifier.size(60.dp))
                }

                Text(modifier = Modifier.padding(horizontal = 10.dp),
                    color=colorResource(R.color.add_container_background),
                    text = ordered.value.toString())

                IconButton(onClick = {
                    ordered.value = ordered.value+1
                                     },
                    modifier = Modifier
                ) {
                    Icon(Icons.Filled.AddBox,
                        contentDescription = "Localized description",
                        tint = colorResource(R.color.add_container_background),
                        modifier = Modifier.size(60.dp))
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
                        color = colorResource(R.color.text_color_main),
                        text =  "Detay")


                Text(modifier = Modifier,
                    color = colorResource(R.color.dark_gray),
                    text = "Kargo")
                Text(modifier = Modifier,
                    color = colorResource(R.color.dark_gray),
                    text =  "Yorumlar")
            }










        }

    }
}