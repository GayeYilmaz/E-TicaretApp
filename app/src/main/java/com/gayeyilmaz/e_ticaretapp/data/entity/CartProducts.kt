package com.gayeyilmaz.e_ticaretapp.data.entity

import com.google.gson.annotations.SerializedName

data class CartProducts (
    @SerializedName("sepetId") var cartId:Int,
    @SerializedName("ad")var name:String,
    @SerializedName("resim") var image:String,
    @SerializedName("kategori") var category:String,
    @SerializedName("fiyat")var price: Int,
    @SerializedName("marka")var brand:String,
    @SerializedName("siparisAdeti") var ordered:Int,
    @SerializedName("kullaniciAdi")var username:String){


}