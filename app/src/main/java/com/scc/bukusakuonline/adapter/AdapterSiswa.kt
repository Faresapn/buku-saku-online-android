package com.scc.bukusakuonline.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.scc.bukusakuonline.R
import com.scc.bukusakuonline.model.siswa.SiswaKelasItem
import com.scc.bukusakuonline.ui.detailsiswa.DetailSiswaActivity
import java.text.DecimalFormat

class AdapterSiswa (private  val context: Context, private val Items:List<SiswaKelasItem>): RecyclerView.Adapter<AdapterSiswa.ViewHolder>() {
    class ViewHolder (view: View): RecyclerView.ViewHolder(view)  {
        private val name = view.findViewById<TextView>(R.id.namaSiswa)
        private val nis = view.findViewById<TextView>(R.id.nisSiswa)
        private val point = view.findViewById<TextView>(R.id.point)


        fun bindItem(items: SiswaKelasItem, context: Context){
            point.text = items.point
            name.text = items.name
            val decimal = DecimalFormat("#")
            decimal.maximumFractionDigits = 0
            nis.text = decimal.format(items.nis)
            itemView.setOnClickListener {
                val intent = Intent(context, DetailSiswaActivity::class.java)
                intent.putExtra("id",items.id)
                intent.putExtra("name",items.name)
                context.startActivity(intent)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_siswa, parent, false))
    }

    override fun getItemCount(): Int  = Items.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(Items[position],context)
    }
}