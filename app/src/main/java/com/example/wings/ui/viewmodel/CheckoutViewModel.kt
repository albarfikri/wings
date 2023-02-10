package com.example.wings.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wings.data.WingsRepository
import com.example.wings.data.dataclass.Product
import com.example.wings.data.dataclass.TransactionDetail
import com.example.wings.data.dataclass.TransactionDetailWithProducts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CheckoutViewModel(
    val wingsRepository: WingsRepository
) : ViewModel() {
    //
//    private val _uiState = MutableStateFlow(Product())
//    val uiState: StateFlow<Product> = _uiState
    private val _uiState = MutableStateFlow(Product())
    val uiState: StateFlow<Product> = _uiState

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val checkoutState: StateFlow<CheckoutUiState> =
        wingsRepository.getAllCheckoutData()
            .map {
                CheckoutUiState(it)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = CheckoutUiState()
            )

    fun getProductNameById(id: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                _uiState.value = wingsRepository.getProductNameById(id)
            }
        }
    }

    data class CheckoutUiState(val checkoutList: List<TransactionDetailWithProducts> = listOf())

    data class Output(val data: Product)

//    val checkoutState: StateFlow<CheckoutUiState> =
//        wingsRepository.getAllCheckoutData()
//            .map {
//                CheckoutUiState(it)
//            }
//            .stateIn(
//                scope = viewModelScope,
//                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
//                initialValue = CheckoutUiState()
//            )
//
//     data class CheckoutUiState(val checkoutList: List<TransactionDetailWithProducts> = listOf())

}
