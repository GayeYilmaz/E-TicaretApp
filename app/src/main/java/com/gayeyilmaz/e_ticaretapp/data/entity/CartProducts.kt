package com.gayeyilmaz.e_ticaretapp.data.entity

import com.google.gson.annotations.SerializedName

data class CartProducts (
    @SerializedName("sepetId") val cartId:Int,
    @SerializedName("ad")val name:String,
    @SerializedName("resim") val image:String,
    @SerializedName("kategori") val category:String,
    @SerializedName("fiyat")val price: Int,
    @SerializedName("marka")val brand:String,
    @SerializedName("siparisAdedi") val ordered:Int,
    @SerializedName("kullaiciAdi")val username:String){


}