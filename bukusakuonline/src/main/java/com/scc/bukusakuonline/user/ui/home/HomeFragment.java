package com.scc.bukusakuonline.user.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ethanhua.skeleton.SkeletonScreen;
import com.faltenreich.skeletonlayout.Skeleton;
import com.faltenreich.skeletonlayout.SkeletonLayoutUtils;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.scc.bukusakuonline.user.R;
import com.scc.bukusakuonline.user.adapter.AdapterAktivitasTerbaru;
import com.scc.bukusakuonline.user.ui.LainyaActivity;
import com.scc.bukusakuonline.user.ui.daftarkelas.DaftarKelas;
import com.scc.bukusakuonline.user.ui.detailpoint.DetailPoint;
import com.scc.bukusakuonline.user.ui.peraturan.PeraturanActivity;
import com.scc.bukusakuonline.user.ui.search.SearchActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {
    AdapterAktivitasTerbaru mAdapterAktivitasTerbaru;

    private HomeViewModel homeViewModel;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private AppBarLayout appBarLayout;
    @BindView(R.id.rv_aktivitas_new) RecyclerView mRecyclerView;
    Skeleton skeletonScreen;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,root);
        CardView menu_peraturan = root.findViewById(R.id.menu_peraturan);
        CardView menu_daftar_kelas = root.findViewById(R.id.menu_daftar_kelas);
        skeletonScreen = SkeletonLayoutUtils.applySkeleton(mRecyclerView, R.layout.item_aktivitas_new, 5);
        skeletonScreen.showSkeleton();
        SearchView searchView = root.findViewById(R.id.sercing);
        CardView menu_pelanggaran = root.findViewById(R.id.menu_pelanggaran);
        CardView menu_lainya = root.findViewById(R.id.menu_lainya);
        menu_peraturan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), PeraturanActivity.class));
            }
        });
        menu_daftar_kelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), DaftarKelas.class));
            }
        });
        menu_pelanggaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), DetailPoint.class));
            }
        });
        menu_lainya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), LainyaActivity.class));
            }
        });
        try {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    Intent search = new Intent(getContext(), SearchActivity.class);
                    search.putExtra("nis",s);
                    startActivity(search);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    return false;
                }
            });
        }catch (Exception e){

        }
        getData();
        search();

        return root;
    }

    private void search() {


    }

    private void getData() {
        try {
            homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));
            homeViewModel.loadData(getContext());
            homeViewModel.getListData().observe(getActivity(), dataItems -> {
                try {
                    if (dataItems != null){
                        skeletonScreen.showOriginal();
                        mAdapterAktivitasTerbaru = new AdapterAktivitasTerbaru(getContext(),dataItems);
                        mRecyclerView.setAdapter(mAdapterAktivitasTerbaru);
                        mAdapterAktivitasTerbaru.notifyDataSetChanged();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //to hide toolbar if conflict please resolve
    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }
    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }

}
