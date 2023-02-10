package com.example.wings.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wings.data.WingsRepository
import com.example.wings.data.dataclass.Product
import com.example.wings.ui.navigation.ProductDetail.itemIdArg
import kotlinx.coroutines.flow.*

class ProductDetailViewModel(
    savedStateHandle: SavedStateHandle,
    val wingsRepository: WingsRepository) : ViewModel() {

    private val itemId: Int = checkNotNull(savedStateHandle[itemIdArg])

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val productDetailState: StateFlow<Product> =
        wingsRepository.getDetailById(itemId)
            .filterNotNull()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = Product()
            )
}