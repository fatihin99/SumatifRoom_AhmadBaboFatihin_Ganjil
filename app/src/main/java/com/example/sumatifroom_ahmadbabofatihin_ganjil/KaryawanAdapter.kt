package com.example.sumatifroom_ahmadbabofatihin_ganjil

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sumatifroom_ahmadbabofatihin_ganjil.room.tbKaryawan
import kotlinx.android.synthetic.main.activity_karyawan_adapter.view.*

class KaryawanAdapter(private val karyawan :ArrayList<tbKaryawan>,private val listener:OnAdapterListener):
    RecyclerView.Adapter<KaryawanAdapter.KaryawanViewHolder>() {
    class KaryawanViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KaryawanViewHolder {
        return KaryawanViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_karyawan_adapter, parent, false)
        )
    }

    override fun onBindViewHolder(holder: KaryawanViewHolder, position: Int) {
        val krywn = karyawan[position]
        holder.view.TVnama1.text=krywn.Id.toString()
        holder.view.TVnama2.text=krywn.Nama
        holder.view.Tview.setOnClickListener { listener.onclik(krywn) }
        holder.view.iconedit.setOnClickListener { listener.onupdate(krywn) }
        holder.view.icondelete.setOnClickListener { listener.ondelete(krywn) }

    }

    override fun getItemCount() = karyawan.size

        fun setData(list: List<tbKaryawan>) {
            karyawan.clear()
            karyawan.addAll(list)
            notifyDataSetChanged()
        }
    interface OnAdapterListener {
        fun onclik(tbKar: tbKaryawan)
        fun onupdate(tbKar: tbKaryawan)
        fun ondelete(tbKar: tbKaryawan)
    }
}
