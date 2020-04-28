package com.scc.bukusakuonline.user.ui.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.scc.bukusakuonline.user.R;
import com.scc.bukusakuonline.user.ui.LoginActivity;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    // name ,textview13, subjects
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.subjects)
    TextView email;
    @BindView(R.id.textView13)
    TextView phone;

    public TextView kelas;
    ImageView imageView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        kelas = root.findViewById(R.id.textView14);
        imageView = root.findViewById(R.id.imgprofile);
        ButterKnife.bind(this,root);

        try {
            profileViewModel.loadData(Objects.requireNonNull(getContext()));
            profileViewModel.getListData().observe(getViewLifecycleOwner(), userItems -> {
                try{
                    String url =  Objects.requireNonNull(userItems.getPhoto());
                    Picasso.get().load(url).into(imageView);
                    Log.d("cekimage", url);
                    name.setText(userItems.getName());
                    email.setText(userItems.getEmail());
                    phone.setText(userItems.getPhone());
                    kelas.setText(userItems.getKelas());

                }catch (Exception e) {
                    Toast.makeText(getContext(), "Harap Login Ulang Terlebih Dahulu", Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){
            Toast.makeText(getContext(),"Harap Login Ulang Terlebih Dahulu" , Toast.LENGTH_LONG).show();
        }
        return root;
    }
    @OnClick(R.id.btn_out) void Logout(){
        SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences("PREF", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("TOKEN");
        editor.apply();
        startActivity(new Intent(getActivity(), LoginActivity.class));
        getActivity().finish();
    }
}