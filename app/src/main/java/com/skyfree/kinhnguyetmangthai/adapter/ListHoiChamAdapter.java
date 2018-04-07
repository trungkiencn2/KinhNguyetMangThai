package com.skyfree.kinhnguyetmangthai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.skyfree.kinhnguyetmangthai.R;
import com.skyfree.kinhnguyetmangthai.model.HoiCham;

import java.util.ArrayList;

/**
 * Created by KienBeu on 4/5/2018.
 */

public class ListHoiChamAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<HoiCham> mListHoiCham;

    public ListHoiChamAdapter(Context mContext, ArrayList<HoiCham> mListHoiCham) {
        this.mContext = mContext;
        this.mListHoiCham = mListHoiCham;
    }

    @Override
    public int getCount() {
        return mListHoiCham.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mRow = inflater.inflate(R.layout.item_hoi_cham, null);
        ImageView mImg = (ImageView) mRow.findViewById(R.id.img_item_hoicham);
        TextView mTv = (TextView) mRow.findViewById(R.id.tv_item_hoicham);

        mImg.setImageResource(mListHoiCham.get(position).getmImg());
        mTv.setText(mListHoiCham.get(position).getmName());
        return mRow;
    }
}
