package com.example.scanbirds

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity

class Usuario_Entidad {

    @PrimaryKey
    var email:String
    var nombres:String
    var apellidos:String
    var direccion:String
    var telefono:String
    var contrasena:String

    constructor(
        email: String,
        nombres: String,
        apellidos: String,
        direccion: String,
        telefono: String,
        contrasena: String
    ) {
        this.email = email
        this.nombres = nombres
        this.apellidos = apellidos
        this.direccion = direccion
        this.telefono = telefono
        this.contrasena = contrasena
    }
}