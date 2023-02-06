package com.example.sumatifroom_ahmadbabofatihin_ganjil.room

import android.content.Context
import androidx.room.*

@Database(
    entities = [tbKaryawan::class],
    version = 1)
abstract  class Codepelita: RoomDatabase() {
    abstract fun KrywnDAO() : KaryawanDAO

    companion object{

        @Volatile private var instance: Codepelita? = null
        private var LOCK= Any()
        operator fun invoke (Context : Context) = instance?:
        synchronized(LOCK){
            instance?: BuilDatabase(Context).also{
                instance = it
            }
        }
        private fun BuilDatabase(Context: Context)= Room.databaseBuilder(
            Context.applicationContext,
            Codepelita::class.java,
            "21.5704_db"
        )
            .build()
    }
}