package com.example.wings.data.dataclass

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "TransactionDetail")
data class TransactionDetail(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="documentCode")
    val documentCode: Int = 0,
    val documentNumber: String = "",
    @ColumnInfo(name="productCode")
    val productCode: Int = 0,
    val price: String = "",
    val quantity: Int = 0,
    var unit: String = "",
    val subTotal: Int = 0,
    val currency: String = ""
)