package com.example.wings.data

import androidx.room.*
import com.example.wings.data.dataclass.Login
import com.example.wings.data.dataclass.Product
import com.example.wings.data.dataclass.TransactionDetail
import com.example.wings.data.dataclass.TransactionDetailWithProducts
import kotlinx.coroutines.flow.Flow

@Dao
interface WingsDao {
    @Query("SELECT * FROM Login WHERE user=:user AND password=:password")
    fun loginVerification(
        user: String, password: String
    ): Flow<Login>

    @Query("SELECT * FROM Product")
    fun getAllProductsList(): Flow<List<Product>>

    @Transaction
    @Query("SELECT * FROM TransactionDetail")
    fun getAllCheckoutData(): Flow<List<TransactionDetailWithProducts>>

    @Query("SELECT * FROM Product WHERE productCode=:id")
    fun getProductNameById(id: Int): Product

//    @Query("SELECT * FROM Product WHERE productCode=:id")
//    fun getProductNameById(id: Int): Flow<Product>

    @Query("SELECT * FROM Product WHERE productCode=:id")
    fun getDetailById(id: Int): Flow<Product>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(login: Login)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBoughtProduct(transactionDetail: TransactionDetail)
}