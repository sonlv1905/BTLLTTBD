package com.example.hocta.fragmentHome;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.hocta.R;
import com.example.hocta.model.TuMoi;
import com.example.hocta.recyclerview.RecyclerViewTuMoiAdapter;
import com.example.hocta.sqlite.SQLiteTuMoiHelper;

import java.util.List;

public class FragmentAll extends Fragment {
    private RecyclerView rev;
    private RecyclerViewTuMoiAdapter adapter;
    private SQLiteTuMoiHelper SQLite;
    private List<TuMoi> list;

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.iSearch);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                list = SQLite.getByTu(newText);
                adapter.setData(list);
                rev.setAdapter(adapter);
                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_all, container, false);
        rev = v.findViewById(R.id.rev);

        adapter = new RecyclerViewTuMoiAdapter(getContext());
        SQLite = new SQLiteTuMoiHelper(getContext());
        list = SQLite.getAll();
        adapter.setData(list);

        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rev.setLayoutManager(manager);
        rev.setAdapter(adapter);

        return v;

    }

    @Override
    public void onResume() {
        list = SQLite.getAll();
        adapter.setData(list);
        super.onResume();
    }
}