package com.example.sumatifroom_ahmadbabofatihin_ganjil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.sumatifroom_ahmadbabofatihin_ganjil.room.Codepelita
import com.example.sumatifroom_ahmadbabofatihin_ganjil.room.tbKaryawan
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditActivity : AppCompatActivity() {
    val db by lazy { Codepelita(this) }
    private var tbkaryawanid: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        tombolperintah()
        setupView()
        Toast.makeText(this, tbkaryawanid.toString(), Toast.LENGTH_SHORT).show()
    }

    fun setupView() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val intentType = intent.getIntExtra("intent_type", 0)
        when (intentType) {
            Constant.TYPE_CREATE -> {
                btnUpdate.visibility = View.GONE
            }
            Constant.TYPE_READ -> {
                btnUpdate.visibility = View.GONE
                btnSimpan.visibility = View.GONE
                ETid.visibility = View.GONE
                tampildataId()
            }
            Constant.TYPE_UPDATE -> {
                btnSimpan.visibility = View.GONE
                ETid.visibility = View.GONE
                tampildataId()
            }
        }
    }

    fun tombolperintah() {
        btnSimpan.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.KrywnDAO().addKrywn(
                    tbKaryawan(
                        ETid.text.toString().toInt(),
                        ETnama.text.toString(),
                        ETalamat.text.toString(),
                        ETusia.text.toString(),
                    )
                )
                finish()
            }
        }
        btnUpdate.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.KrywnDAO().updateKrywn(
                    tbKaryawan(
                        tbkaryawanid,
                        ETnama.text.toString(),
                        ETalamat.text.toString(),
                        ETusia.text.toString(),
                    )
                )
                finish()
            }
        }
    }
    fun tampildataId() {
        tbkaryawanid = intent.getIntExtra("intent_id", 0)
        CoroutineScope(Dispatchers.IO).launch {
            val tenagakerja = db.KrywnDAO().tampilId(tbkaryawanid).get(0)
            val dataId: String = tenagakerja.Id.toString()
            ETid.setText(dataId)
            ETnama.setText(tenagakerja.Nama)
            ETalamat.setText(tenagakerja.Alamat)
            ETusia.setText(tenagakerja.Usia)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}