package com.gayeyilmaz.e_ticaretapp.ui.components

import android.content.Context
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.CreditCardOff
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.IndeterminateCheckBox
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gayeyilmaz.e_ticaretapp.R
import com.gayeyilmaz.e_ticaretapp.data.entity.CartProducts
import com.gayeyilmaz.e_ticaretapp.data.entity.FavoriteProducts
import com.gayeyilmaz.e_ticaretapp.data.entity.Products
import com.google.gson.Gson
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun CustomFavoriteCard(navController: NavController,favoriteProducts: FavoriteProducts,onDeleteClick:(favoriteProducts:FavoriteProducts) -> Unit ){
    Card (
        modifier = Modifier.padding(16.dp).fillMaxWidth(),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(colorResource(R.color.card_container_background))
    )
    {
        Row (
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Column (){

                val url = "http://kasimadalan.pe.hu/urunler/resimler/${favoriteProducts.image}"
                GlideImage(imageModel = url,
                    modifier = Modifier.size(100.dp).padding(vertical = 16.dp)
                        .border(BorderStroke(1.dp, colorResource(R.color.dark_gray)), shape = RoundedCornerShape(10.dp)),
                )
            }
            Column (
                modifier = Modifier.padding(start=10.dp, top = 16.dp, bottom = 16.dp)
            ){
                Row {
                    Text(fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        text = favoriteProducts.brand )
                    Text(fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        text = "  ${favoriteProducts.name}")

                }
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        modifier = Modifier.size(16.dp),
                        imageVector = Icons.Filled.LocalShipping,
                        contentDescription = "Bugün kargoda",

                        )
                    Text(fontSize = 12.sp,
                        text = "  Bugün kargoda" )
                }
                Row(
                    modifier = Modifier, verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        modifier = Modifier.size(16.dp),
                        imageVector = Icons.Filled.CreditCardOff,
                        contentDescription = "Karta taksit yapılmaz",
                        tint = colorResource(R.color.add_container_background)


                    )
                    Text(fontSize = 12.sp,
                        color = colorResource(R.color.add_container_background),
                        text = "  Karta taksit yapılmaz" )
                }
                Spacer(modifier = Modifier.padding(top=20.dp))
                Text(fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    text = " Price ₺${favoriteProducts.price} ")
                Spacer(modifier =  Modifier.padding(top=5.dp))
            }
            Column(modifier = Modifier,
                horizontalAlignment = Alignment.End,
                ) {
                IconButton(onClick = {
                    Log.e("FAV","ıcon button on click")
                    onDeleteClick(favoriteProducts)
                },
                    modifier = Modifier.padding(1.dp),


                ) {
                    Icon(Icons.Filled.Delete, contentDescription = "Localized description")
                }
                Spacer(modifier = Modifier.padding(top=60.dp))
                Button(onClick ={

                },
                    contentPadding = PaddingValues(2.dp),
                    modifier = Modifier.width(300.dp).height(30.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.main_color))
                ) {
                    Text(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        text = "Add to Cart")
                }
            }



        }





    }

}