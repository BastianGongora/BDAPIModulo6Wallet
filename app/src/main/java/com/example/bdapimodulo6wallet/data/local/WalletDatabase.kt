package com.example.bdapimodulo6wallet.data.local

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bdapimodulo6wallet.data.local.dao.TransactionDao
import com.example.bdapimodulo6wallet.data.local.dao.UserDao
import com.example.bdapimodulo6wallet.data.local.entity.TransactionEntity
import com.example.bdapimodulo6wallet.data.local.entity.UserEntity


@Database(entities = [TransactionEntity::class, UserEntity::class], version = 1, exportSchema = false)
abstract class WalletDatabase : RoomDatabase() {

    abstract fun transactionDao(): TransactionDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: WalletDatabase? = null

        fun getDatabase(context: Context): WalletDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WalletDatabase::class.java,
                    "wallet_database"
                ).build()
                Log.d("WalletDatabase", "Base de datos creada") // Añadir log aquí
                INSTANCE = instance
                instance
            }
        }
    }
}