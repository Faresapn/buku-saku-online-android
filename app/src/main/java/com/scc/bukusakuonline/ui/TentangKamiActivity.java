package com.scc.bukusakuonline.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.scc.bukusakuonline.R;

import java.util.Objects;

public class TentangKamiActivity extends AppCompatActivity {
    Button scc,stematel;
    ConstraintLayout fiud,gawang,daffa,fakhrul,sindi,faresa,yudhan,dafa,bagas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang_kami);
        scc = findViewById(R.id.sccpwt);
        stematel = findViewById(R.id.Stematel);
        fiud = findViewById(R.id.rafiud);
        gawang = findViewById(R.id.ramadhani);
        daffa = findViewById(R.id.daffa_raihan);
        fakhrul = findViewById(R.id.fakhrul);
        sindi = findViewById(R.id.sindi);
        faresa = findViewById(R.id.faresa);
        yudhan = findViewById(R.id.yudhan);
        dafa = findViewById(R.id.daffa_pradipta);
        bagas = findViewById(R.id.makhali);
        scc.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.igscc)));
            startActivity(intent);
        });
        stematel.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.igstematel)));
            startActivity(intent);
        });
        fiud.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.igrafiud)));
            startActivity(intent);
        });
        gawang.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.iggawang)));
            startActivity(intent);
        });
        fakhrul.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.igfakhrul)));
            startActivity(intent);
        });
        sindi.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.igsindi)));
            startActivity(intent);
        });
        faresa.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.igfaresa)));
            startActivity(intent);
        });
        daffa.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.igdafarz)));
            startActivity(intent);
        });
        dafa.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.igdafa)));
            startActivity(intent);
        });
        yudhan.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.igyudhan)));
            startActivity(intent);
        });
        bagas.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.igbagas)));
            startActivity(intent);
        });

        (getSupportActionBar()).setTitle(getString(R.string.detail_4));
        (getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

}
