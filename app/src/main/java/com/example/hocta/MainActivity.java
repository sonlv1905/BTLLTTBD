package com.example.hocta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.hocta.fragment.FragmentBottomAdapter;
import com.example.hocta.model.TuMoi;
import com.example.hocta.sqlite.SQLiteTuMoiHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private BottomNavigationView navigationView;
    private FragmentBottomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewPager);
        navigationView = findViewById(R.id.navigation);

        adapter = new FragmentBottomAdapter(getSupportFragmentManager(),
                FragmentBottomAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.iHome: viewPager.setCurrentItem(0);
                        break;
                    case R.id.iLearn1:viewPager.setCurrentItem(1);
                        break;
                    case R.id.iLearn2:viewPager.setCurrentItem(2);
                        break;
                    case R.id.iInfo:viewPager.setCurrentItem(3);
                    break;
                }
                return true;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0: navigationView.getMenu().findItem(R.id.iHome);
                    break;
                    case 1: navigationView.getMenu().findItem(R.id.iLearn1);
                    break;
                    case 2: navigationView.getMenu().findItem(R.id.iLearn2);
                    break;
                    case 3: navigationView.getMenu().findItem(R.id.iInfo);
                    break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}