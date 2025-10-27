package com.gayeyilmaz.e_ticaretapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.gayeyilmaz.e_ticaretapp.data.repos.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class FavoriteViewModel @Inject constructor(var productRepository : ProductsRepository) : ViewModel() {
    val favoriteProductsList = productRepository.favoriteProductsList




}