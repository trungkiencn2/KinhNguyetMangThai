package com.skyfree.kinhnguyetmangthai.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.skyfree.kinhnguyetmangthai.R;
import com.skyfree.kinhnguyetmangthai.fragment.FragmentHanhKinh;
import com.skyfree.kinhnguyetmangthai.fragment.FragmentRungTrung;
import com.skyfree.kinhnguyetmangthai.fragment.FragmentThuThai;

/**
 * Created by KienBeu on 3/21/2018.
 */

public class PageAdapter extends FragmentStatePagerAdapter {

    private Context mContext;

    public PageAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag = null;
        switch (position){
            case 0:
                frag = new FragmentHanhKinh();
                break;
            case 1:
                frag = new FragmentThuThai();
                break;
            case 2:
                frag = new FragmentRungTrung();
                break;
        }

        return frag;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = mContext.getString(R.string.menstruation);
                break;
            case 1:
                title = mContext.getString(R.string.conception);
                break;
            case 2:
                title = mContext.getString(R.string.ovulation);
                break;
        }
        return title;
    }
}
