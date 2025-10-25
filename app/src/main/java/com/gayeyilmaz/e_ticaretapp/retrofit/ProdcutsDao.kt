package com.gayeyilmaz.e_ticaretapp.retrofit

import com.gayeyilmaz.e_ticaretapp.data.entity.CRUDResponse
import com.gayeyilmaz.e_ticaretapp.data.entity.CartProductsResponse
import com.gayeyilmaz.e_ticaretapp.data.entity.ProductsResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductsDao {
    //http://kasimadalan.pe.hu/urunler/tumUrunleriGetir.php
    //http://kasimadalan.pe.hu/ ->BASE URL
    //urunler/tumUrunleriGetir.php ->API URL

    @GET("urunler/tumUrunleriGetir.php")
    suspend fun  loadProducts() : ProductsResponse

    //http://kasimadalan.pe.hu/urunler/sepeteUrunEkle.php
    @POST("urunler/sepeteUrunEkle.php")
    @FormUrlEncoded
    suspend fun  addCart(@Field("ad") name:String,
                         @Field("resim") image:String,
                         @Field("kategori") category:String,
                         @Field("fiyat") price:Int,
                         @Field("marka") brand:String,
                         @Field("siparisAdedi") ordered:Int,
                         @Field("kullaniciAdi") username:String, ) : CRUDResponse



    //http://kasimadalan.pe.hu/urunler/sepettekiUrunleriGetir.php
    @POST("urunler/sepettekiUrunleriGetir.php")
    @FormUrlEncoded
    suspend fun  loadCartProducts(@Field("kullaniciAdi") username:String) : CartProductsResponse

    //http://kasimadalan.pe.hu/urunler/sepettenUrunSil.php
    @POST("/urunler/sepettenUrunSil.php")
    @FormUrlEncoded
    suspend fun  delete(@Field("sepedId") id:Int): CRUDResponse
     //http://kasimadalan.pe.hu/urunler/tumUrunleriGetir.php

    /** @POST("/urunler/tumUrunleriGetir.php")
    @FormUrlEncoded
    suspend fun  search(@Field("sepedId") id:Int): CRUDResponse **/




}