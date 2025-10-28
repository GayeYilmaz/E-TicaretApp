package com.gayeyilmaz.e_ticaretapp.ui.components

import android.content.Context
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
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gayeyilmaz.e_ticaretapp.R
import com.gayeyilmaz.e_ticaretapp.data.entity.FavoriteProducts
import com.gayeyilmaz.e_ticaretapp.data.entity.Products
import com.gayeyilmaz.e_ticaretapp.ui.screens.FavoriteScreen
import com.gayeyilmaz.e_ticaretapp.ui.viewmodels.MainViewModel
import com.google.gson.Gson
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.launch

@Composable
fun CustomProductCard(navController: NavController, product: Products, context: Context, onFavoriteClick: (favoriteProduct: FavoriteProducts) -> Unit,  isFavorite: Boolean){

    var isFavorite = remember { mutableStateOf(isFavorite) }




    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .fillMaxWidth()
        ,
        colors= CardDefaults.cardColors(
            containerColor = colorResource(R.color.card_container_background)
        )
    ) {
        Column(
            modifier = Modifier
                .background(colorResource(R.color.background))
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(
                onClick = {
                    isFavorite.value = !isFavorite.value
                    Log.e("fav-delete","${product.name} - In Favor :${isFavorite.value}")
                    onFavoriteClick(FavoriteProducts(product.id,product.name,product.image,product.category,product.price,product.brand,isFavorite.value))
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Icon(

                    imageVector = if(isFavorite.value){ Icons.Filled.Favorite}else{Icons.Filled.FavoriteBorder},
                    contentDescription = "Add to favorites",
                    tint = if (isFavorite.value) colorResource(R.color.hearth_color) else MaterialTheme.colorScheme.onSurface


                )
            }
            Log.e("FAV","${product.name} - In Favor Outside Icon Button :${isFavorite.value}")

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

                val url = "http://kasimadalan.pe.hu/urunler/resimler/${product.image}"
                GlideImage(imageModel = url,  modifier = Modifier.size(80.dp) )
                Box(modifier = Modifier.width(200.dp).height(20.dp)
                    .background(colorResource(R.color.bottom_bar_background)),
                    contentAlignment = Alignment.Center
                ) {

                    Text(fontSize = 12.sp,
                        text = "Hızlı Teslimat",
                        color = colorResource(R.color.background),
                    )
                }
                Row(
                    modifier = Modifier.fillMaxSize(),


                ){
                    Text(
                        fontSize =15.sp,
                        fontWeight = FontWeight.ExtraBold,
                        text = product.brand,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Text(
                        fontSize =15.sp,
                        fontWeight = FontWeight.Bold,
                        text =" ${product.name}",
                        modifier = Modifier.padding(top = 8.dp)
                    )

                }
                Spacer(modifier = Modifier.height(10.dp))


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        fontSize =18.sp,
                        fontWeight = FontWeight.Bold,
                        text = "₺${product.price}",
                        modifier = Modifier.weight(1f),
                        color = colorResource(R.color.add_container_background)
                    )

                }
            }
        }
    }

}
