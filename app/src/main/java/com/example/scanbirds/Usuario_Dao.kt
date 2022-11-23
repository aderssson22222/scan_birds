package com.example.scanbirds

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Usuario_Dao {
    @Query("select * from Usuario_Entidad")
    suspend fun obtenerUsuarios():List<Usuario_Entidad>

    @Query("select * from Usuario_Entidad where email=:emailUsu")
    suspend fun buscarUsuario(emailUsu: String):Usuario_Entidad

    @Insert
    suspend fun agregarusuario(usuario: Usuario_Entidad)

    @Query("update Usuario_Entidad set telefono=:tel, direccion=:dir, contrasena=:pass where email=:emailUsu")
    suspend fun actualizarusuario(tel:String, dir:String, pass:String, emailUsu:String)

    @Query("delete from Usuario_Entidad where email=:emailUsu")
    suspend fun borrarusuario(emailUsu:String)
    }