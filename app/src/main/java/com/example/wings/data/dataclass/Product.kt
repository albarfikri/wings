package com.example.wings.data.dataclass

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Product")
data class Product(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="productCode")
    val productCode: Int = 0,
    val productName: String = "",
    val price: Int = 0,
    val currency: String = "",
    val discount: Int = 0,
    val dimension: String = "",
    val unit: String = ""
)