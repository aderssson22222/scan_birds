package com.example.scanbirds

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Usuario_Entidad::class],
    version = 1
)
abstract class bdUsuarios: RoomDatabase() {

    abstract fun daoUsuario():Usuario_Dao

}