package com.example.wings.data.dataclass

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

data class TransactionDetailWithProducts(
    @Embedded val transactionDetail: TransactionDetail,
    @Relation(
        parentColumn = "documentCode",
        entityColumn = "productCode"
    )
    val products: List<Product>?=null
)