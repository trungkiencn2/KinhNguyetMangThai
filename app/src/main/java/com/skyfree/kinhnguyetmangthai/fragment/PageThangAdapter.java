package com.skyfree.kinhnguyetmangthai.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by lamnd on 3/2/2017.
 */

public class PageThangAdapter extends FragmentStatePagerAdapter {
    ArrayList<Fragment> list;
    public PageThangAdapter(FragmentManager fm) {

        super(fm);
        this.list= new ArrayList<Fragment>();
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return this.list.size();
    }
    public void addPage(Fragment f){
        this.list.add(f);
    }
}
