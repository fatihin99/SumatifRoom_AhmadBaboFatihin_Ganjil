package com.example.sumatifroom_ahmadbabofatihin_ganjil

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sumatifroom_ahmadbabofatihin_ganjil.room.Codepelita
import com.example.sumatifroom_ahmadbabofatihin_ganjil.room.tbKaryawan
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    val db by lazy { Codepelita(this) }
    private lateinit var karyawanAdapter: KaryawanAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        halEdit()
        setuprecyclerView()
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    fun loadData() {
        CoroutineScope(Dispatchers.IO).launch {
            val karyawan = db.KrywnDAO().tampilall()
            Log.d("MainActivity", "dbResponse:$karyawan")
            withContext(Dispatchers.Main) {
                karyawanAdapter.setData(karyawan)
            }
        }
    }

    fun intentedit(tbkarid: Int, intentType: Int) {
        startActivity(
            Intent(applicationContext, EditActivity::class.java)
                .putExtra("intent_id", tbkarid)
                .putExtra("intent_type", intentType)
        )
    }

    fun setuprecyclerView() {
        karyawanAdapter = KaryawanAdapter(arrayListOf(), object : KaryawanAdapter.OnAdapterListener {
                override fun onclik(tbKar: tbKaryawan) {
                    intentedit(tbKar.Id, Constant.TYPE_READ)
                }

                override fun onupdate(tbKar: tbKaryawan) {
                    intentedit(tbKar.Id, Constant.TYPE_UPDATE)
                }

                override fun ondelete(tbKar: tbKaryawan) {
                    deletalert(tbKar)
                }

            })
        ListDataKaryawan.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = karyawanAdapter
        }
    }

    fun halEdit() {
        btnInput.setOnClickListener {
            intentedit(0, Constant.TYPE_CREATE)
        }
    }
    private fun deletalert(tbkar : tbKaryawan){
        val dialog = AlertDialog.Builder(this)
        dialog.apply {
            setTitle("Konfirmasi Hapus")
            setMessage("Yakin Mau Hapus ${tbkar.Nama}")
            setNegativeButton("Batal") {DialogInterface, i ->
                DialogInterface.dismiss()
            }
            setPositiveButton("Hapus") {DialogInterface, i ->
                CoroutineScope(Dispatchers.IO).launch {
                    db.KrywnDAO().delKrywn(tbkar)
                    DialogInterface.dismiss()
                    loadData()
                }
            }
        }
        dialog.show()
    }
}
