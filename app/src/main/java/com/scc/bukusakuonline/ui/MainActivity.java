package com.scc.bukusakuonline.ui;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.scc.bukusakuonline.R;
import com.scc.bukusakuonline.ui.pengaduan.PengaduanFragment;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = findViewById(R.id.bottom_navigation_main);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_history, R.id.navigation_dashboard, R.id.navigation_profile, R.id.navigation_pengaduan)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigation, navController);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        Log.d("request", String.valueOf(requestCode));
        switch (requestCode) {
            case 10: {
                if (grantResults.length > 0) {
                    StringBuilder permissionsDenied = new StringBuilder();
                    boolean denied = false;
                    for (String per : permissions) {
                        Log.d("grant", String.valueOf(grantResults[0]));
                        if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                           permissionsDenied.append("yyy");
                            denied = true;
                        }

                    }
                    Log.d("permission", String.valueOf(permissionsDenied));
                    if (denied){
                        Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                    }else {
                        Log.d("hmm","hmm");
                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }
}
