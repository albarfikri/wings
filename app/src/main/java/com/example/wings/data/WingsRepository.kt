package com.example.wings.data

import com.example.wings.data.dataclass.Login
import com.example.wings.data.dataclass.Product
import com.example.wings.data.dataclass.TransactionDetail
import com.example.wings.data.dataclass.TransactionDetailWithProducts
import kotlinx.coroutines.flow.Flow

interface WingsRepository {
    fun loginVerification(
        user: String, password: String
    ): Flow<Login>

    fun getAllProductList(): Flow<List<Product>>

    fun getDetailById(id: Int): Flow<Product>

    fun getAllCheckoutData(): Flow<List<TransactionDetailWithProducts>>

    //fun getProductNameById(id: Int): Flow<Product>
    fun getProductNameById(id: Int): Product

    suspend fun insertBoughtProduct(transactionDetail: TransactionDetail)

    suspend fun insertData(login: Login)
}