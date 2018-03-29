package com.skyfree.kinhnguyetmangthai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.skyfree.kinhnguyetmangthai.R;

import java.util.ArrayList;

/**
 * Created by KienBeu on 3/29/2018.
 */

public class ListOptionAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Integer> mList;

    public ListOptionAdapter(Context mContext, ArrayList<Integer> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
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
        View mRow = inflater.inflate(R.layout.item_list_note_calendar, null);
        ImageView mImg = (ImageView) mRow.findViewById(R.id.img_item_list_note_calendar);
        mImg.setImageResource(mList.get(position));
        return mRow;
    }
}
