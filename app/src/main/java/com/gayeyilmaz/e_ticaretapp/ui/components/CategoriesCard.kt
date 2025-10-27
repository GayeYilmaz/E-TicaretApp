package com.gayeyilmaz.e_ticaretapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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

@Composable
fun CategoriesCard(category: String){
    Card(
        modifier = Modifier
            .padding(start = 8.dp)
            .height(110.dp)
            .width(100.dp),

        colors = CardDefaults.cardColors(colorResource(R.color.card_container_background)),
        elevation = CardDefaults.cardElevation(8.dp),
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ){
            Column(
                modifier = Modifier.padding(10.dp).align(Alignment.Center),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

                ) {
                if(category=="Teknoloji"){
                    Image(
                        painter = painterResource(id = R.drawable.teknoloji), // drawable içindeki icon
                        contentDescription = "Custom Icon",
                        modifier = Modifier.size(36.dp) // istediğin boyut
                    )
                }
               else if(category=="Aksesuar"){
                    Image(
                        painter = painterResource(id = R.drawable.aksesuar), // drawable içindeki icon
                        contentDescription = "Custom Icon",
                        modifier = Modifier.size(25.dp) // istediğin boyut
                    )
                }
                else if(category=="Kozmetik"){
                    Image(
                        painter = painterResource(id = R.drawable.kozmetik), // drawable içindeki icon
                        contentDescription = "Custom Icon",
                        modifier = Modifier.size(25.dp) // istediğin boyut
                    )
                }
                else{
                    Image(
                        painter = painterResource(id = R.drawable.placeholder), // drawable içindeki icon
                        contentDescription = "Custom Icon",
                        modifier = Modifier.size(25.dp) // istediğin boyut
                    )
                }

                Text(
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 10.dp),
                    fontSize = 12.sp,
                    color = colorResource(R.color.add_container_background),
                    text = category
                )
            }
        }

    }
}