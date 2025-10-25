package com.gayeyilmaz.e_ticaretapp.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gayeyilmaz.e_ticaretapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBAr(navController: NavController,name:String){
    CenterAlignedTopAppBar(
        modifier = Modifier.statusBarsPadding().height(60.dp)
            .clip(RoundedCornerShape(topStart=0.dp, topEnd = 0.dp, bottomStart = 30.dp, bottomEnd =30.dp)),
        title = { Text(text=name) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(id= R.color.main_color),
            titleContentColor = colorResource(R.color.white)
        ),
        navigationIcon = {
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    Icons.Filled.Close,
                    tint = colorResource(R.color.white),
                    contentDescription = "Localized description"
                )
            }
        },
        actions = {
            if(name =="Details"){
                IconButton(onClick = {
                    navController.navigate("cartScreen") }) {
                    Icon(
                        imageVector = Icons.Filled.ShoppingBasket,
                        tint = colorResource(R.color.white),
                        contentDescription = "Localized description"
                    )
                }
            }
            else{

            }

        }


    )
}