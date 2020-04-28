package com.scc.bukusakuonline.user.connection;

import com.scc.bukusakuonline.user.model.Login;
import com.scc.bukusakuonline.user.model.detailpoint.DetailPointResponse;

import com.scc.bukusakuonline.user.model.kelas.KelasResponse;

import com.scc.bukusakuonline.user.model.riwayat.LaporanByIdResponse;


import com.scc.bukusakuonline.user.model.riwayat.LaporanResponse;
import com.scc.bukusakuonline.user.model.siswa.DetailSiswaResponse;
import com.scc.bukusakuonline.user.model.siswa.SiswaKelasResponse;

import com.scc.bukusakuonline.user.model.UploadPelanggaran;
import com.scc.bukusakuonline.user.model.peraturan.DetailPasalResponse;
import com.scc.bukusakuonline.user.model.peraturan.PeraturanById;
import com.scc.bukusakuonline.user.model.peraturan.PeraturanResponse;
import com.scc.bukusakuonline.user.model.user.UserResponse;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    // point
    @GET("api/point/all")
    Call<DetailPointResponse> getDetailPoint(@Header("Authorization") String Auth);
    //login / users
    @FormUrlEncoded
    @POST("api/users/login")
    Call<Login> login(@Field("email") String email, @Field("password") String password, @Field("secretCode") String secret);
    @GET("api/users/search")
    Call<SiswaKelasResponse> searchSiswa(@Header("Authorization") String Auth, @Query("nis") String nis);
    @GET("api/users/me")
    Call<UserResponse> profile(@Header("Authorization") String Auth);


    //kelas + siswa
    @GET("api/kelas")
    Call<SiswaKelasResponse>getSiswa(@Header("Authorization") String Auth, @Query("kelas") String kelas);

    @GET("api/kelas/all")
    Call<KelasResponse> getKelas(@Header("Authorization") String Auth, @Query("jurusan") String jurusan);

    @GET("api/users/get")
    Call<DetailSiswaResponse> getDetailSsiswa(@Header("Authorization") String Auth, @Query("id") String id);

    //lapor
    @GET("api/lapor/all")
    Call<LaporanResponse>getLapor(@Header("Authorization") String Auth);

    @GET("api/lapor/me")
    Call<LaporanResponse>getLaporByMe(@Header("Authorization") String Auth);

    @GET("api/lapor")
    Call<LaporanByIdResponse>getLaporById(@Header("Authorization") String Auth, @Query("id") String id);

    @FormUrlEncoded
    @POST("api/lapor/upload")
    Call<UploadPelanggaran> uploadPelanggaran(@Header("Authorization") String auth,@Field("pelanggaran_kategori") String Pelanggaran, @Field("nis") Double Nis , @Field("image") String Image);

    //peraturan

    @GET("api/peraturan/all")
    Call<PeraturanResponse> getPeraturan(@Header("Authorization") String auth);
    @GET("api/peraturan")
    Call<PeraturanById> getPeraturanById(@Header("Authorization") String auth, @Query("id") String id);

    @GET("api/peraturan/pasal")
    Call<DetailPasalResponse> getPasal(@Header("Authorization") String auth, @Query("id") String id, @Query("idPasal") String idPasal);


}
