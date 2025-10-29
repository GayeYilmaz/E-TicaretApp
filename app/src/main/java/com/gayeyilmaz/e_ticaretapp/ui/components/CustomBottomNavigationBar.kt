package com.gayeyilmaz.e_ticaretapp.ui.components
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gayeyilmaz.e_ticaretapp.R
import com.gayeyilmaz.e_ticaretapp.data.entity.NavigationItemData
//import com.tanishranjan.jetpack_compose_components.composables.bottom_navbar.data.NavigationItemData

@Composable
fun CustomBottomNavigationBar(

    navItems: List<NavigationItemData>,
    modifier: Modifier = Modifier,
    internalPadding: Dp = 8.dp,
    iconSize: Dp = 30.dp,
    fontSize: TextUnit = 14.sp,
    defaultSelectedIndex: Int = 0,
    selectedItemOffset: Dp = 8.dp,
    shape: Shape = RoundedCornerShape(8.dp),
    navigationBarColor: Color = colorResource(R.color.white),
    itemTint: Color = colorResource(R.color.icon_color_dark),
    selectedItemTint: Color =colorResource(R.color.icon_color_dark),
    backgroundTint: Color = Color.Transparent,
    selectedBackgroundTint: Color = MaterialTheme.colorScheme.primary,
    itemSelected: (index: Int, reselected: Boolean) -> Unit

) {

    Box(
        modifier = Modifier
            .height(70.dp)
            .background(navigationBarColor, shape)
            .then(modifier),
    ) {

        var selectedItemIndex by remember {
            mutableIntStateOf(defaultSelectedIndex)
        }

        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            navItems.forEachIndexed { index, item ->

                val isSelected = selectedItemIndex == index

                Box(
                    Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .clickable {
                            val reselected = selectedItemIndex == index
                            selectedItemIndex = index
                            itemSelected(selectedItemIndex, reselected)
                        },



                    contentAlignment = Alignment.Center
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .then(
                                if (isSelected) {
                                    Modifier.offset(y = selectedItemOffset * (-1))
                                } else {
                                    Modifier
                                }
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Box(
                            Modifier
                                .clip(CircleShape)
                                .background(
                                    if (isSelected) selectedBackgroundTint else backgroundTint
                                )
                                .padding(internalPadding)
                        ) {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.title,
                                modifier = Modifier.size(iconSize),
                                tint = if (isSelected) selectedItemTint else itemTint
                            )
                        }

                        AnimatedVisibility(visible = isSelected) {
                            Text(
                                text = item.title,
                                modifier = Modifier.padding(top = 4.dp),
                                color = itemTint,
                                fontSize = fontSize
                            )
                        }

                    }

                }

            }

        }

    }

}