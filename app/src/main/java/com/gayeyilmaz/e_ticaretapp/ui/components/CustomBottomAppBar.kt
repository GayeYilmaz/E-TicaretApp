package com.gayeyilmaz.e_ticaretapp.ui.components




import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gayeyilmaz.e_ticaretapp.R
import com.gayeyilmaz.e_ticaretapp.data.entity.CartProducts
import com.gayeyilmaz.e_ticaretapp.data.entity.Products
import com.google.gson.Gson


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomBottomAppBar(navController: NavController){
               val cartProduct = CartProducts(0,"","","",0,"",0,"")


                BottomAppBar(
                    modifier = Modifier
                        .clip(RoundedCornerShape(topStart=0.dp, topEnd = 0.dp, bottomStart = 0.dp, bottomEnd = 0.dp)),
                    containerColor = colorResource(R.color.bottom_bar_background),



                    actions = {
                        IconButton(onClick = {
                       navController.navigate( "mainScreen" )
                        },
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(Icons.Filled.Home, contentDescription = "Localized description", tint = colorResource(R.color.icon_color_dark))
                        }
                        IconButton(onClick = {
                            navController.navigate( "favoriteScreen" )},
                            modifier = Modifier.weight(1f)
                            ) {
                            Icon(
                                Icons.Filled.Favorite,
                                contentDescription = "Localized description",tint = colorResource(R.color.icon_color_dark)
                            )
                        }

                        IconButton(onClick = { /* do something */ },
                            modifier = Modifier.weight(1f)
                            ) {
                            Icon(
                                Icons.Filled.Person,
                                contentDescription = "Localized description",tint = colorResource(R.color.icon_color_dark)
                            )
                        }
                    },
                    floatingActionButton = {
                        FloatingActionButton(
                            modifier = Modifier,
                            onClick = {
                                val cartProduct = CartProducts(0,"","","",0,"",0,"")
                                var cartProductJson = Gson().toJson(cartProduct)
                                navController.navigate( "cartScreen/$cartProductJson" )
                                      /* do something */ },
                           shape = CircleShape,
                            containerColor = colorResource(R.color.main_color),
                            elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(),

                        ) {
                            Icon(Icons.Filled.ShoppingBasket, "Localized description",tint = colorResource(R.color.icon_color_dark))
                        }
                    }
                )



}