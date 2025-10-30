package com.gayeyilmaz.e_ticaretapp.data.entity



data class FavoriteProducts ( var id:Int,
                              var name:String,
                              var image:String,
                              var category:String,
                              var price: Int,
                              var brand:String,
                               var isFavorite:Boolean=false){
}