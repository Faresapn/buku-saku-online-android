package com.scc.bukusakuonline.ui.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.scc.bukusakuonline.R;
import com.scc.bukusakuonline.ui.LoginActivity;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    // name ,textview13, subjects
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.subjects)
    TextView email;
    @BindView(R.id.imgprofile)
    CircleImageView imgProfile;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this,root);

        getProfile();
        return root;
    }

    private void getProfile() {
        try {
            profileViewModel.loadData(Objects.requireNonNull(getContext()));
            profileViewModel.getListData().observe(this, userItems -> {

                try {
                    String url =  Objects.requireNonNull(userItems.getPhoto());
                    Picasso.get().load(url).into(imgProfile);
                    name.setText(userItems.getName());
                    email.setText(userItems.getEmail());
                }catch (Exception e){
                    Log.e(String.valueOf(e),"lol" );
                }

            });
        }
        catch(Exception e){
            e.printStackTrace();
        }
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