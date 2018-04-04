package com.skyfree.kinhnguyetmangthai.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.skyfree.kinhnguyetmangthai.R;
import com.skyfree.kinhnguyetmangthai.model.Setting;

import java.util.ArrayList;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by KienBeu on 4/4/2018.
 */

public class MyAdapter extends ArrayAdapter<Setting>{

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Setting> mListSetting;

    public MyAdapter(@NonNull Context context, int resource, @NonNull List<Setting> objects) {
        super(context, resource, objects);
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mListSetting = (ArrayList<Setting>) objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View mViewHeader = mInflater.inflate(R.layout.item_header, null, false);
        View mViewItem = mInflater.inflate(R.layout.item_setting, null, false);

        TextView mTvHeader = mViewHeader.findViewById(R.id.tv_header);
        ImageView mImgSetting = mViewItem.findViewById(R.id.img_avatar_item_setting);
        TextView mTvNameSetting = mViewItem.findViewById(R.id.tv_name_item_setting);
        TextView mTvCountDaySetting = mViewItem.findViewById(R.id.tv_count_day_item_setting);

        Setting mSetting = mListSetting.get(position);

        switch (mSetting.getmType()){
            case 0:
                mTvHeader.setText(mSetting.getmName());
                return mViewHeader;
            case 1:
                mImgSetting.setImageResource(mSetting.getmImg());
                mTvNameSetting.setText(mSetting.getmName());
                mTvCountDaySetting.setText(mSetting.getmCountDay());
                return mViewItem;
            default:
                return null;
        }

    }

}