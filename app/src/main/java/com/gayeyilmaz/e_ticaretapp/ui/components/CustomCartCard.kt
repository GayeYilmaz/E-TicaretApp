package com.gayeyilmaz.e_ticaretapp.ui.components

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gayeyilmaz.e_ticaretapp.R
import com.gayeyilmaz.e_ticaretapp.data.entity.CartProducts
import kotlin.Unit


@Composable
fun CustomCartCard(cartProduct: CartProducts, context: Context,onDeleteClick:(Int) -> Unit){
    val product = cartProduct.product
    var price = 0
    var totalPrice = 0

    Card (
        modifier = Modifier.padding(5.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(colorResource(R.color.card_container_background))
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
            Text(fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                text = product.name )
            Spacer(modifier = Modifier.padding(top=5.dp))
            Text(fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.text_color),
                text = "Price: $${product.price} ")
            Spacer(modifier =  Modifier.padding(top=5.dp))
            Text(fontSize = 15.sp,
                color = colorResource(R.color.text_color),
                text = "Adet:  ${cartProduct.ordered} ")

        }
        Column (
            modifier = Modifier,
            horizontalAlignment = Alignment.End,



            ){
            IconButton(onClick = {onDeleteClick(product.id)
            },
                modifier = Modifier
            ) {
                Icon(Icons.Filled.Delete, contentDescription = "Localized description")
            }

            price = cartProduct.ordered * product.price
            Text(fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top=25.dp),
                text = "$${price} ")
            totalPrice = totalPrice + price

        }

    }
    }


}