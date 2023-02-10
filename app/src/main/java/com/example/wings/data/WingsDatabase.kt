package com.example.wings.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.wings.data.dataclass.Login
import com.example.wings.data.dataclass.Product
import com.example.wings.data.dataclass.TransactionDetail
import com.example.wings.data.dataclass.TransactionDetailWithProducts

@Database(
    entities = [Login::class, Product::class, TransactionDetail::class],
    version = 4,
    exportSchema = false
)
abstract class WingsDatabase : RoomDatabase() {
    abstract fun wingsDao(): WingsDao

    companion object {
        private const val databaseName = "item_database"

        @Volatile
        private var INSTANCE: WingsDatabase? = null

        fun getDatabase(context: Context): WingsDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(context, WingsDatabase::class.java, databaseName)
                    .fallbackToDestructiveMigration()
                    .build()
                    .also {
                        INSTANCE = it
                    }
            }
        }
    }
}
