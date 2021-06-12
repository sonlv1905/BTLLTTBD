package com.example.hocta.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class FragmentBottomAdapter extends FragmentStatePagerAdapter {

    private int numPage = 4;

    public FragmentBottomAdapter(FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @Override
    public Fragment getItem(int position) {
       switch (position){
           case 0: return new FragmentHome();
           case 1: return new FragmentLearn();
           case 2: return new FragmentReview();
           case 3: return new FragmentInfo();
           default:return new FragmentHome();
       }
    }

    @Override
    public int getCount() {
        return numPage;
    }
}
