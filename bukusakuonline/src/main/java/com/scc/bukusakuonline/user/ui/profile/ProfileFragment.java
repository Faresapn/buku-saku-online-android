package com.scc.bukusakuonline.user.ui.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.scc.bukusakuonline.user.R;
import com.scc.bukusakuonline.user.ui.LoginActivity;

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
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this,root);
        profileViewModel.loadData(Objects.requireNonNull(getContext()));
        profileViewModel.getListData().observe(getViewLifecycleOwner(), userItems -> {
            name.setText(userItems.get(0).getName());
            email.setText(userItems.get(0).getEmail());
            phone.setText(userItems.get(0).getPhone());

        });
        return root;
    }
    @OnClick(R.id.btn_out) void Logout(){
        SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences("PREF", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("TOKEN");
        editor.apply();
        startActivity(new Intent(getActivity(), LoginActivity.class));
    }
}