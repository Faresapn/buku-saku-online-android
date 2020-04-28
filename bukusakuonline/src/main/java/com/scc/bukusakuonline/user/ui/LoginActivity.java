package com.scc.bukusakuonline.user.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.scc.bukusakuonline.user.R;
import com.scc.bukusakuonline.user.connection.ApiService;
import com.scc.bukusakuonline.user.connection.RetroConfig;
import com.scc.bukusakuonline.user.model.Login;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.txtEmail)
    EditText txtEmail;

    @BindView(R.id.txtPassword)
    EditText txtPassword;

    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        preferences = this.getSharedPreferences("PREF", Context.MODE_PRIVATE);
        String token = preferences.getString("TOKEN","abc");

        if (!token.equals("abc")){
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }

    }
    @OnClick(R.id.btnLogin) void login(){
        try {
            Log.d("email",txtEmail.getText().toString());
            Log.d("pass",txtPassword.getText().toString());
            RetroConfig.getRetrofit().create(ApiService.class).
                    login(Objects.requireNonNull(txtEmail.getText()).toString(), Objects.requireNonNull(txtPassword.getText()).toString(),"001").enqueue(new Callback<Login>() {
                @Override
                public void onResponse(@NotNull Call<Login> call, @NotNull Response<Login> response) {
                    if (response.isSuccessful()){
                        Log.d("response",response.body().getCode().toString());
                        assert response.body() != null;
                        if (response.body().getCode() == 200){
                            String token = response.body().getToken();
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("TOKEN",token);
                            editor.apply();
                            Log.d("response", token);
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            finish();
                        }else {
                            Log.d("response", String.valueOf(response.body()));
                            Toast.makeText(getApplicationContext(), "Email Atau Password Anda Salah", Toast.LENGTH_LONG).show();
                        }

                    }
                }
                @Override
                public void onFailure(@NotNull Call<Login> call, @NotNull Throwable t) {
                    Log.d("failure", Objects.requireNonNull(t.getMessage()));
                    Toast.makeText(getApplicationContext(), "Network Error", Toast.LENGTH_LONG).show();
                }
            });
        }catch (Exception e){
            Log.d("error", Objects.requireNonNull(e.getMessage()));
        }
    }
}
