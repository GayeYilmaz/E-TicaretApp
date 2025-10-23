package com.gayeyilmaz.e_ticaretapp.ui.components




import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.windowInsetsEndWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
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
import com.gayeyilmaz.e_ticaretapp.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomBottomAppBar(){



                BottomAppBar(
                    modifier = Modifier.height(80.dp)
                        .clip(RoundedCornerShape(topStart=40.dp, topEnd = 40.dp, bottomStart = 0.dp, bottomEnd = 0.dp)),
                    containerColor = colorResource(R.color.bottom_bar_background),



                    actions = {
                        IconButton(onClick = { /* do something */ },
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(Icons.Filled.Home, contentDescription = "Localized description", tint = colorResource(R.color.white))
                        }
                        IconButton(onClick = { /* do something */ },
                            modifier = Modifier.weight(1f)
                            ) {
                            Icon(
                                Icons.Filled.Favorite,
                                contentDescription = "Localized description",tint = colorResource(R.color.white)
                            )
                        }

                        IconButton(onClick = { /* do something */ },
                            modifier = Modifier.weight(1f)
                            ) {
                            Icon(
                                Icons.Filled.Person,
                                contentDescription = "Localized description",tint = colorResource(R.color.white)
                            )
                        }
                    },
                    floatingActionButton = {
                        FloatingActionButton(
                            modifier = Modifier,
                            onClick = { /* do something */ },
                           shape = CircleShape,
                            containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                            elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                        ) {
                            Icon(Icons.Filled.ShoppingBasket, "Localized description")
                        }
                    }
                )



}