package com.gayeyilmaz.e_ticaretapp.data.entity

import com.google.gson.annotations.SerializedName

data class Products(
    @SerializedName("id") var id:Int,
    @SerializedName("ad")var name:String,
    @SerializedName("resim") var image:String,
    @SerializedName("kategori") var category:String,
    @SerializedName("fiyat")var price: Int,
    @SerializedName("marka")var brand:String){
}