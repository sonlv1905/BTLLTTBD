package com.example.hocta.fragmentHome;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hocta.R;
import com.example.hocta.model.TuMoi;
import com.example.hocta.recyclerview.RecyclerViewTuMoiAdapter;
import com.example.hocta.sqlite.SQLiteTuMoiHelper;

import java.util.List;


public class FragmentDt extends Fragment {

    private RecyclerView rev;
    private RecyclerViewTuMoiAdapter adapter;
    private SQLiteTuMoiHelper SQLite;
    private List<TuMoi> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_dt, container, false);
        rev = v.findViewById(R.id.rev);

        adapter = new RecyclerViewTuMoiAdapter(getContext());
        SQLite = new SQLiteTuMoiHelper(getContext());
        list = SQLite.getByLoaiTu("danh từ");
        adapter.setData(list);

        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rev.setLayoutManager(manager);
        rev.setAdapter(adapter);

        return v;
    }

    @Override
    public void onResume() {
        list = SQLite.getByLoaiTu("danh từ");
        adapter.setData(list);
        super.onResume();
    }
}