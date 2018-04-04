package com.skyfree.kinhnguyetmangthai.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.skyfree.kinhnguyetmangthai.R;
import com.skyfree.kinhnguyetmangthai.model.CalendarItem;
import com.skyfree.kinhnguyetmangthai.utils.Utils;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by KienBeu on 3/16/2018.
 */

public class RecycleViewCalendarAdapter extends RecyclerView.Adapter<RecycleViewCalendarAdapter.ViewHolder> {

    private ArrayList<CalendarItem> mListItem;
    private Context mContext;
    public static int position = 0;
    private Calendar mCa = Calendar.getInstance();
    int dayStart =3;
    public RecycleViewCalendarAdapter(ArrayList<CalendarItem> mListItem, Context mContext) {
        this.mListItem = mListItem;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_calendar, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Utils utils = new Utils();
        if(mListItem.get(position).getmBg()!=null){
            holder.mImgBg.setBackgroundResource(mListItem.get(position).getmBg());
        }
        if(mListItem.get(position).getmDes()!=null){
            holder.mImgDes.setImageResource(mListItem.get(position).getmDes());
        }
        holder.mTvDate.setText(mListItem.get(position).getmDate());

    }

    @Override
    public int getItemCount() {
        return mListItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImgBg;
        ImageView mImgDes;
        TextView mTvDate;

        public ViewHolder(View itemView) {
            super(itemView);
            mImgBg = (ImageView) itemView.findViewById(R.id.img_bg_item_calendar);
            mImgDes = (ImageView) itemView.findViewById(R.id.img_item_calendar);
            mTvDate = (TextView) itemView.findViewById(R.id.tv_date_item_calendar);
            mContext = itemView.getContext();
        }
    }

}
