package com.skyfree.kinhnguyetmangthai.adapter;

import android.content.Context;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.skyfree.kinhnguyetmangthai.R;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by KienBeu on 3/25/2018.
 */

public class ListPillBirthControlAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<String> mListPillBirthControl;

    public ListPillBirthControlAdapter(Context mContext, ArrayList<String> mListPillBirthControl) {
        this.mContext = mContext;
        this.mListPillBirthControl = mListPillBirthControl;
    }

    @Override
    public int getCount() {
        return mListPillBirthControl.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mRow = inflater.inflate(R.layout.item_drug, null);
        TextView mTvDrug = (TextView) mRow.findViewById(R.id.tv_item_drug);
        mTvDrug.setText(mListPillBirthControl.get(i));
        return mRow;
    }
}
