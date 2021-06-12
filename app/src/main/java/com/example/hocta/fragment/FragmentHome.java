package com.example.hocta.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TableLayout;

import com.example.hocta.AddTuMoiActivity;
import com.example.hocta.R;
import com.example.hocta.fragmentHome.FragmentTabAdapter;
import com.example.hocta.model.TuMoi;
import com.example.hocta.sqlite.SQLiteTuMoiHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.List;


public class FragmentHome extends Fragment {
    private TabLayout tab;
    private ViewPager viewPager;
    private FragmentTabAdapter adapter;

    private FloatingActionButton fab;

    private View v;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.fragment_home, container, false);
        tab = v.findViewById(R.id.tab);
        viewPager = v.findViewById(R.id.viewPager);
        adapter = new FragmentTabAdapter(getChildFragmentManager(),
                FragmentTabAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
        tab.setupWithViewPager(viewPager);
        fab = v.findViewById(R.id.fabAdd);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddTuMoiActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }
}