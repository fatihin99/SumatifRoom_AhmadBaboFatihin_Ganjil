package com.example.sumatifroom_ahmadbabofatihin_ganjil.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class tbKaryawan (
    @PrimaryKey
    val Id :Int,
    val Nama : String,
    val Alamat : String,
    val Usia : String
    )