package com.skyfree.kinhnguyetmangthai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.skyfree.kinhnguyetmangthai.R;
import com.skyfree.kinhnguyetmangthai.model.RealmDrug;

import java.util.ArrayList;

import io.realm.RealmList;

/**
 * Created by KienBeu on 3/25/2018.
 */

public class ListOtherDrugAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<String> mListOtherDrug;

    public ListOtherDrugAdapter(Context mContext, ArrayList<String> mListOtherDrug) {
        this.mContext = mContext;
        this.mListOtherDrug = mListOtherDrug;
    }

    @Override
    public int getCount() {
        return mListOtherDrug.size();
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
        mTvDrug.setText(mListOtherDrug.get(i));
        return mRow;
    }
}
