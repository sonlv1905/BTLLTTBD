package com.example.hocta.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hocta.DangNhapActivity;
import com.example.hocta.R;
import com.example.hocta.UpdateUserActivity;
import com.example.hocta.model.User;
import com.example.hocta.sqlite.SQLiteUserHelper;

public class FragmentInfo extends Fragment {

    private TextView tvHoTen, tvDate, tvGioiTinh, tvThayDoi, tvDangXuat;
    private SQLiteUserHelper SQlite;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_info, container, false);

        SQlite = new SQLiteUserHelper(getContext());

        Intent intent = getActivity().getIntent();
        User user = (User) intent.getSerializableExtra("user");


        tvHoTen = v.findViewById(R.id.tvHoTen);
        tvDate = v.findViewById(R.id.tvDate);
        tvGioiTinh = v.findViewById(R.id.tvGioiTinh);
        tvThayDoi = v.findViewById(R.id.tvThayDoi);
        tvDangXuat = v.findViewById(R.id.tvDangXuat);

        tvHoTen.setText("Họ & Tên: "+user.getHoten());
        tvDate.setText("Ngày sinh: "+user.getNgaysinh());
        tvGioiTinh.setText("Giới tính: "+user.getGioitinh());
        tvThayDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getContext(), UpdateUserActivity.class);
                intent1.putExtra("user1", user);
                startActivity(intent1);
            }
        });
        tvDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getContext(), DangNhapActivity.class);
                startActivity(intent1);
            }
        });

        return v;
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        Intent intent = getActivity().getIntent();
//        User user = (User) intent.getSerializableExtra("user");
//        User user1= SQlite.getUserByUserName(user.getUsername());
//        tvHoTen.setText(user1.getHoten());
//        tvDate.setText(user1.getNgaysinh());
//        tvGioiTinh.setText(user1.getGioitinh());
//    }
}