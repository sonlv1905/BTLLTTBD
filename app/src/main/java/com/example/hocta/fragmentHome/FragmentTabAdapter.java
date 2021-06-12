package com.example.hocta.fragmentHome;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class FragmentTabAdapter extends FragmentStatePagerAdapter {
    private int numTab = 4;

    public FragmentTabAdapter(FragmentManager fm, int behavior) {
        super(fm, behavior);
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new FragmentAll();
            case 1:return new FragmentDt();
            case 2: return new FragmentDt1();
            case 3: return new FragmentTt();
            default:return new FragmentAll();
        }
    }

    @Override
    public int getCount() {
        return numTab;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "Tất cả";
            case 1:return "Danh từ";
            case 2: return "Động từ";
            case 3: return "Tính từ";
            default: return "All";
        }
    }
}
