package com.gayeyilmaz.e_ticaretapp.ui.components

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.CheckBoxOutlineBlank
import androidx.compose.material.icons.filled.CreditCardOff
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.IndeterminateCheckBox
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gayeyilmaz.e_ticaretapp.R
import com.gayeyilmaz.e_ticaretapp.data.entity.CartProducts
import com.skydoves.landscapist.glide.GlideImage
import kotlin.Unit


@Composable
fun CustomCartCard(cartProduct: CartProducts, context: Context,onDeleteClick:(Int) -> Unit,onUpdateClick:(cartProduct:CartProducts) -> Unit ,onCheckClick:(isChecked:Boolean) -> Unit  ){
    var ordered = remember { mutableStateOf(cartProduct.ordered) }
    var price = 0
    var totalPrice = 0
    var isChecked = remember { mutableStateOf(true) }


    Card (
        modifier = Modifier.padding(5.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(colorResource(R.color.card_container_background))
    )
    {



            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            )
            {   //CHECKBOX
                Column (
                    modifier = Modifier.align(alignment = Alignment.CenterVertically).size(36.dp),

                )
                {
                    IconButton(onClick = {
                        isChecked.value = !isChecked.value
                        onCheckClick(isChecked.value)
                    }) {
                        Icon(
                            imageVector = if(!isChecked.value) Icons.Filled.CheckBoxOutlineBlank else{Icons.Filled.CheckBox},
                            contentDescription = "Add Cart",

                        )
                    }
                }
                //IMAGE
                Row(
                    modifier = Modifier.fillMaxHeight().align(Alignment.CenterVertically),

                ){
                    val url = "http://kasimadalan.pe.hu/urunler/resimler/${cartProduct.image}"
                    GlideImage(imageModel = url,
                        modifier = Modifier.size(96.dp).
                        border(BorderStroke(1.dp, colorResource(R.color.dark_gray)), shape = RoundedCornerShape(10.dp)),
                        )


                }

                //DETAIL
                Column (
                    modifier = Modifier.padding(top=10.dp,start=5.dp)
                )
                {  Row {
                    Text(fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        text = cartProduct.brand )
                    Text(fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        text = "  ${cartProduct.name}")

                }
                    Row(
                        modifier = Modifier, verticalAlignment = Alignment.CenterVertically
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

                    Spacer(modifier = Modifier.padding(top=5.dp))
                    Text(fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(R.color.text_color),
                        text = " Fiyat ₺${cartProduct.price} ")
                    Spacer(modifier =  Modifier.padding(top=5.dp))

                    //ORDERED
                    Row (
                        modifier = Modifier.height(30.dp).width(100.dp)
                            .border(BorderStroke(1.dp, colorResource(R.color.dark_gray)), shape = RoundedCornerShape(30.dp)),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ){
                        IconButton(onClick = {
                           /** if(ordered.value!=0){
                                ordered.value = ordered.value-1
                                cartProduct.ordered = ordered.value
                                onUpdateClick(cartProduct)

                            }
                            if(ordered.value==1){
                                ordered.value = ordered.value-1
                                cartProduct.ordered = ordered.value
                                onDeleteClick(cartProduct.cartId)
                            }**/
                        }) {
                            Icon(
                                modifier = Modifier.size(20.dp).padding(end=3.dp),
                                imageVector = Icons.Filled.IndeterminateCheckBox,
                                contentDescription = "Add Cart",

                                )
                        }
                        Text(text = "${cartProduct.ordered}")

                        IconButton(onClick = {
                            /**ordered.value = ordered.value+1
                            cartProduct.ordered = ordered.value
                            onUpdateClick(cartProduct)**/

                        }) {
                            Icon(
                                modifier = Modifier.size(20.dp).padding(start = 3.dp,end=2.dp),
                                imageVector = Icons.Filled.AddBox,
                                contentDescription = "Add Cart",

                                )
                        }

                    }

                }

                //DELETE PRICE
                Column (
                    modifier = Modifier,

                    ){
                    IconButton(onClick = {onDeleteClick(cartProduct.cartId)
                    },
                        modifier = Modifier.align(Alignment.End)) {
                        Icon(Icons.Filled.Delete, contentDescription = "Localized description")
                    }
                    price = cartProduct.ordered * cartProduct.price
                    Text(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.End).padding(top=73.dp, bottom = 5.dp),
                        text = "₺${price} ")
                    totalPrice = totalPrice + price

                }

            }



    }


}