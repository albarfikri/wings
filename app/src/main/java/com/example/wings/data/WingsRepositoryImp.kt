package com.example.wings.data

import com.example.wings.data.dataclass.Login
import com.example.wings.data.dataclass.Product
import com.example.wings.data.dataclass.TransactionDetail
import com.example.wings.data.dataclass.TransactionDetailWithProducts
import kotlinx.coroutines.flow.Flow
import kotlin.math.log

class WingsRepositoryImp(
    val wingsDao: WingsDao
) : WingsRepository {
    override fun loginVerification(user: String, password: String): Flow<Login> =
        wingsDao.loginVerification(user, password)

    override fun getAllProductList(): Flow<List<Product>> =
        wingsDao.getAllProductsList()

    override fun getDetailById(id: Int): Flow<Product> =
        wingsDao.getDetailById(id)

    override fun getAllCheckoutData(): Flow<List<TransactionDetailWithProducts>> =
        wingsDao.getAllCheckoutData()

    override fun getProductNameById(id: Int): Product =
        wingsDao.getProductNameById(id)

//    override fun getProductNameById(id: Int): Flow<Product> =
//        wingsDao.getProductNameById(id)

    override suspend fun insertBoughtProduct(transactionDetail: TransactionDetail) =
        wingsDao.insertBoughtProduct(transactionDetail)

    override suspend fun insertData(login: Login) {
        wingsDao.insert(login)
    }
}