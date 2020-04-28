package com.scc.bukusakuonline.user.ui.daftarkelas

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.scc.bukusakuonline.user.R
import com.scc.bukusakuonline.user.adapter.AdapterSiswa
import java.util.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class TkjFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var siswaViewModel: SiswaViewModel
    private lateinit var siswaAdapter: AdapterSiswa
    lateinit var kelasViewModel: KelasViewModel
    lateinit var rv_rpl : RecyclerView
    lateinit var adapter: ArrayAdapter<String>
    lateinit var spinner: Spinner
    lateinit var skeletonScreen: Skeleton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? { // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_tkj, container, false)
        spinner = v.findViewById(R.id.spinner_tkj)
        rv_rpl= v.findViewById(R.id.rv_tkj)
        rv_rpl.layoutManager= LinearLayoutManager(context)
        siswaAdapter = context?.let { AdapterSiswa(it) }!!
        rv_rpl.adapter = siswaAdapter
        skeletonScreen= rv_rpl.applySkeleton(R.layout.item_siswa,5)
        skeletonScreen.showSkeleton()
        init()

        return v

    }
    private fun init() {
        kelasViewModel = ViewModelProviders.of(this).get(KelasViewModel::class.java)
        context?.let { kelasViewModel.loadData(it,"TKJ") }
        kelasViewModel.listData.observe(viewLifecycleOwner, Observer {
            val kelas = ArrayList<String>()
            for (i in it.indices) {
                it.get(i).kelas?.let { it1 -> kelas.add(it1) }
            }
            adapter= ArrayAdapter(context!!,android.R.layout.simple_spinner_item,kelas)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
            spinner.onItemSelectedListener = this

        })
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        siswaViewModel = ViewModelProviders.of(this).get(SiswaViewModel::class.java)
        context?.let { siswaViewModel.loadData(it,p0?.getItemAtPosition(p2).toString()) }
        siswaViewModel.listData.observe(this, Observer {
            siswaAdapter.setId(it)
            rv_rpl.adapter = siswaAdapter
            siswaAdapter.notifyDataSetChanged()
            skeletonScreen.showOriginal()
        })
    }

}