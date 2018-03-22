package com.skyfree.kinhnguyetmangthai.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.skyfree.kinhnguyetmangthai.R;
import com.skyfree.kinhnguyetmangthai.custom_interface.IUpdateCalItem;
import com.skyfree.kinhnguyetmangthai.database.DatabaseHelper;
import com.skyfree.kinhnguyetmangthai.model.CalendarItem;
import com.skyfree.kinhnguyetmangthai.utils.Utils;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by KienBeu on 3/16/2018.
 */

public class RecycleViewCalendarAdapter extends RecyclerView.Adapter<RecycleViewCalendarAdapter.ViewHolder> implements IUpdateCalItem {

    private ArrayList<CalendarItem> mListItem;
    private Context mContext;
    int highlightPosition;
    public static int position = 0;
    private Calendar mCa = Calendar.getInstance();

    public RecycleViewCalendarAdapter(ArrayList<CalendarItem> mListItem, Context mContext) {
        this.mListItem = mListItem;
        this.mContext = mContext;
    }

    public void updateHighlightPosition(int position) {
        highlightPosition = position;
        notifyItemChanged(position);
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
        holder.mLinear.setMinimumHeight(holder.mLinear.getWidth());
        holder.mTvDate.setText(mListItem.get(position).getmDate());

    }

    @Override
    public int getItemCount() {
        return mListItem.size();
    }

    @Override
    public void updateCalItem(int position) {
        highlightPosition = position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout mLinear;
        ImageView mImgDes;
        TextView mTvDate;

        public ViewHolder(View itemView) {
            super(itemView);
            mLinear = (LinearLayout) itemView.findViewById(R.id.linear_item_calendar);
            mImgDes = (ImageView) itemView.findViewById(R.id.img_item_calendar);
            mTvDate = (TextView) itemView.findViewById(R.id.tv_date_item_calendar);
            mContext = itemView.getContext();
        }
    }

}
