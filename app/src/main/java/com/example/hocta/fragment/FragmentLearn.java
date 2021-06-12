package com.example.hocta.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hocta.AnhVietActivity;
import com.example.hocta.R;
import com.example.hocta.VietAnhActivity;


public class FragmentLearn extends Fragment {

    private TextView tvAV, tvVA;
    private View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.fragment_learn, container, false);
        tvAV = v.findViewById(R.id.tvAV);
        tvVA = v.findViewById(R.id.tvVA);
        tvVA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), VietAnhActivity.class);
                startActivity(intent);
            }
        });
        tvAV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AnhVietActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }
}