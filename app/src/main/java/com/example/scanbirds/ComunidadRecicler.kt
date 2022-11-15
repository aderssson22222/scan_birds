package com.example.scanbirds

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class ComunidadRecicler(var listComunity: MutableList<ComunidadItems>):RecyclerView.Adapter<ComunidadRecicler.MyHolder>() {

     inner class MyHolder(itemView: View): RecyclerView.ViewHolder(itemView){
             lateinit var nombres:TextView
             lateinit var apellidos:TextView
             lateinit var avez:TextView
             lateinit var rango:TextView
             lateinit var avatar:ImageView

            init {
                nombres = itemView.findViewById(R.id.Item_Nombres)
                apellidos = itemView.findViewById(R.id.Item_Apellidos)
                avez = itemView.findViewById(R.id.Item_Avez)
                rango = itemView.findViewById(R.id.Item_Rango)
                avatar= itemView.findViewById(R.id.Item_Imagen)
            }
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.items_comunidad,parent,false)
        return MyHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        var comunidad = listComunity[position]
        holder.nombres.text=comunidad.nombres
        holder.apellidos.text=comunidad.apellidos
        holder.avez.text=comunidad.avez
        holder.rango.text=comunidad.rango
        holder.avatar.setImageResource(comunidad.avatar)
    }

    override fun getItemCount(): Int {
        return listComunity.size
    }
}