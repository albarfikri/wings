package com.example.wings.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wings.data.WingsRepository
import com.example.wings.data.dataclass.Product
import com.example.wings.data.dataclass.TransactionDetail
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProductViewModel(
    private val wingsRepository: WingsRepository
) : ViewModel() {
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val productUiState: StateFlow<ProductUiState> = wingsRepository.getAllProductList()
        .map {
            ProductUiState(it)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = ProductUiState()
        )


    fun insertBoughtProduct(product: Product) {
        val transactionDetail = TransactionDetail()
        viewModelScope.launch {
            wingsRepository.insertBoughtProduct(
                transactionDetail.copy(
                    documentCode = product.productCode,
                    documentNumber = "00${product.productCode}",
                    productCode = product.productCode,
                    price = product.price.toString(),
                    quantity = product.unit.toInt() - 1,
                    unit = product.unit,
                    subTotal = product.price,
                    currency = product.currency
                )
            )
        }
    }

    data class ProductUiState(val productlist: List<Product> = listOf())


}