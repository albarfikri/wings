package com.example.wings.data.dataclass

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time
import java.sql.Timestamp
import java.util.*

@Entity(tableName = "TransactionHeader")
data class TransactionHeader(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val documentCode: String = "",
    val documentNumber: String = "",
    val user: String = "",
    val total: Int = 0,
    val date: String = ""
)
