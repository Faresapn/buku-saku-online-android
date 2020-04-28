package com.scc.bukusakuonline.user.ui.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.faltenreich.skeletonlayout.Skeleton;
import com.scc.bukusakuonline.user.R;
import com.scc.bukusakuonline.user.adapter.AdapterSiswa;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.rv_search)
    RecyclerView recyclerView;
    AdapterSiswa adapterSiswa;
    SearchViewModel searchViewModel;
    AlertDialog alertDialog;
    Skeleton skeletonScreen;
    String nis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.searchsiswa);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        nis = getIntent().getStringExtra("nis");
        adapterSiswa = new AdapterSiswa(this);
        recyclerView.setAdapter(adapterSiswa);
        alertDialog = new SpotsDialog.Builder().setContext(this).build();
        alertDialog.show();
        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
        searchViewModel.loadData(this, nis);
        searchViewModel.getListData().observe(this, siswaKelasItems -> {
            alertDialog.hide();
            recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
            if (siswaKelasItems.size() > 0) {
                adapterSiswa.setId(siswaKelasItems);
                adapterSiswa.notifyDataSetChanged();
            } else {
                Toast.makeText(this, "Siswa Tidak Ditemukan", Toast.LENGTH_SHORT).show();
                //yuhuu
            }

        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
