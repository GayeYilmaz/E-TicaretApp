package com.gayeyilmaz.e_ticaretapp.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gayeyilmaz.e_ticaretapp.R
import com.gayeyilmaz.e_ticaretapp.data.entity.NavigationItemData

@Composable
fun CategoriesCardX(catItems: List<String>,itemSelected: (index: Int, reselected: Boolean) -> Unit,defaultSelectedIndex:Int){



    var selectedItemIndex by remember {
        mutableIntStateOf(defaultSelectedIndex)
    }
    LazyRow(
        modifier = Modifier.fillMaxWidth()
    )
    {
        item{
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                catItems.forEachIndexed { index, item ->

                    val isSelected = selectedItemIndex == index


                    Card(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .clickable {
                                val reselected = selectedItemIndex == index
                                selectedItemIndex = index
                                itemSelected(selectedItemIndex, reselected)
                            }
                            .then(
                            if(isSelected){
                                Modifier.border(2.dp, colorResource(R.color.main_color), RoundedCornerShape(12.dp))
                            }
                            else{
                                Modifier
                            }
                        )
                            .height(100.dp)
                            .width(100.dp),

                        colors = CardDefaults.cardColors(colorResource(R.color.card_container_background)),
                        elevation = CardDefaults.cardElevation(8.dp),
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize()
                        ){
                            Column(
                                modifier = Modifier.padding(5.dp).align(Alignment.Center)
                                ,
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally

                            ) {

                                IconButton(onClick = {},

                                    modifier = Modifier.size(56.dp)
                                ) {

                                    Image(
                                        painter =
                                            if(item == "Tümü"){
                                                painterResource(id = R.drawable.all)
                                            }
                                            else if(item == "Teknoloji"){
                                                painterResource(id = R.drawable.teknoloji)
                                            }
                                            else if(item == "Aksesuar"){
                                                painterResource(id = R.drawable.aksesuar)
                                            }
                                            else if(item == "Kozmetik"){
                                                painterResource(id = R.drawable.kozmetik)
                                            }
                                            else{
                                                painterResource(id = R.drawable.placeholder)
                                             }

                                        , // drawable içindeki icon
                                        contentDescription = "Custom Icon",
                                        modifier = Modifier.size(36.dp) // istediğin boyut
                                    )
                                }
                                Text(
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(top = 10.dp),
                                    fontSize = 12.sp,
                                    color =colorResource(R.color.add_container_background),
                                    text = item
                                )



                            }
                        }

                    }



                }

            }

        }



}
}