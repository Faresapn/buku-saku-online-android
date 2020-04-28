package com.scc.bukusakuonline.connection;


import com.scc.bukusakuonline.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroConfig {
    private static final String BASE_URL = BuildConfig.BASE_URL;
    private static Retrofit retrofit;

    public static Retrofit getRetrofit(){
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.MINUTES)
                .connectTimeout(60, TimeUnit.MINUTES)
                .build();
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();

        }
        return retrofit;
    }
}
