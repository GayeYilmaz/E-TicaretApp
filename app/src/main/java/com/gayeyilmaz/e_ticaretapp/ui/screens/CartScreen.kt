package com.gayeyilmaz.e_ticaretapp.ui.screens

import android.app.admin.TargetUser
import android.text.LoginFilter
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gayeyilmaz.e_ticaretapp.R
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.gayeyilmaz.e_ticaretapp.data.entity.CartProducts
import com.gayeyilmaz.e_ticaretapp.ui.components.CustomBottomAppBar
import com.gayeyilmaz.e_ticaretapp.ui.components.CustomCartCard
import com.gayeyilmaz.e_ticaretapp.ui.components.CustomTopAppBAr
import com.gayeyilmaz.e_ticaretapp.ui.viewmodels.CartViewModel
import kotlinx.coroutines.launch

import com.android.volley.Request
import com.android.volley.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(navController: NavController,cartViewModel: CartViewModel,username:String,cartProduct: CartProducts){


    val cartProductsList = cartViewModel.cartProductsList.observeAsState(listOf())

    //TOTAL PRICE OF CART PRODUCTS
    val cartPriceTotal = cartProductsList.value.sumOf { it.price * it.ordered }
    val cartPriceTotalState = remember { mutableStateOf(0) }
    cartPriceTotalState.value = cartPriceTotal

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current



    LaunchedEffect(true) {
        cartViewModel.addCart(cartProduct)
        cartViewModel.loadCartProducts(username)

    }



    Scaffold(

        topBar = {
            CustomTopAppBAr(navController,"Sepetim")

        },
        bottomBar = {
            Column(){
                Column (
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(
                                topStart = 0.dp,
                                topEnd = 0.dp,
                                bottomStart = 0.dp,
                                bottomEnd = 0.dp
                            )
                        )
                        .background(colorResource(R.color.bottom_bar_background))
                        .padding(start=16.dp,end=16.dp,top=16.dp)
                        .fillMaxWidth()
                        .height(100.dp)

                ){
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween

                    ){
                        Text(fontSize = 12.sp,
                            color =colorResource(R.color.text_color_main),
                            text = "Taşıma ücreti :")
                        Text(fontSize = 12.sp,
                            color =colorResource(R.color.text_color_main),
                            text =  " ₺0 " )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Text(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color =colorResource(R.color.text_color_main),
                            text = "Toplam Fiyat :")
                        Text(fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color =colorResource(R.color.text_color_main),

                            text =  " ₺${cartPriceTotalState.value}" )
                    }
                    Button(
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        colors = ButtonDefaults.buttonColors(colorResource(R.color.main_color)),
                        onClick = {}
                    ) {
                        Text(text = "Sepeti Onayla")
                    }
                }
                CustomBottomAppBar(navController)
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    )
    { innerpadding ->

        var cartCheckedPrice = cartPriceTotal
        //CONTENT
        for (cartProduct in cartProductsList.value) {



            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(R.color.white))
                    .padding(innerpadding)
                    .padding(10.dp)
            ) {
                itemsIndexed(cartProductsList.value) { index, cartProduct ->


                    CustomCartCard(
                        cartProduct = cartProduct,
                        context = context,
                        onDeleteClick = {
                            scope.launch {
                                val sb = snackbarHostState.showSnackbar(
                                    message = " ${cartProduct.name} sepetten kaldırmak istiyormusun?",
                                    actionLabel = "EVET"
                                )
                                if (sb == SnackbarResult.ActionPerformed) {
                                    cartViewModel.delete(cartProduct.cartId,cartProduct.username)
                                }
                            }
                        },
                        onUpdateClick ={
                            cartViewModel.addCart(cartProduct)
                        },
                        onCheckClick={ isChecked->
                            if(!isChecked){
                                cartCheckedPrice = cartCheckedPrice - (cartProduct.price * cartProduct.ordered)
                                cartPriceTotalState.value = cartCheckedPrice
                            }else{
                                cartCheckedPrice = cartCheckedPrice + (cartProduct.price * cartProduct.ordered)
                                cartPriceTotalState.value = cartCheckedPrice
                            }
                        }
                    )
                }
            }
        }
    }}

