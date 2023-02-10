package com.example.wings.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wings.data.WingsRepository
import com.example.wings.data.dataclass.Login
import com.example.wings.data.dataclass.Product
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.math.log

class WingsViewModel(val wingsRepository: WingsRepository) : ViewModel() {

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    var loginUiState by mutableStateOf(Login())
        private set


//    fun login(username: String, password: String): StateFlow<Login> =
//        wingsRepository.loginVerification(username, password)
//            .filterNotNull()
//            .map {
//                it.copy(
//                    isLogin = true
//                )
//            }.stateIn(
//                scope = viewModelScope,
//                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
//                initialValue = Login()
//            )

    fun loginUser(username: String, password: String) {
        viewModelScope.launch {
            loginUiState = wingsRepository.loginVerification(username, password)
                .filterNotNull()
                .first()
                .toLoginState(isLogin = true)
        }
    }


    fun Login.toLoginState(isLogin: Boolean = false): Login = Login(
        id = id,
        user = user,
        password = password,
        isLogin = isLogin
    )
}
