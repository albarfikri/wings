package com.example.wings.data.dataclass

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Login")
data class Login(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val user: String = "",
    val password: String = "",
    var isLogin: Boolean = false
)