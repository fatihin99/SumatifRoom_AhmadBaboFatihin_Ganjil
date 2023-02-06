package com.example.sumatifroom_ahmadbabofatihin_ganjil.room

import androidx.room.*

@Dao
interface KaryawanDAO {
    @Insert
    fun addKrywn(tbKar : tbKaryawan)

    @Update
    fun updateKrywn(tbKar : tbKaryawan)

    @Delete
    fun delKrywn(tbKar : tbKaryawan)

    @Query("SELECT * FROM tbKaryawan")
    fun tampilall(): List<tbKaryawan>

    @Query("SELECT * FROM tbKaryawan WHERE id=:karyawan_id")
    fun tampilId(karyawan_id: Int) : List<tbKaryawan>
}