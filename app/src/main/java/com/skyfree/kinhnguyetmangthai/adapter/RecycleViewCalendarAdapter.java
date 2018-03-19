package com.skyfree.kinhnguyetmangthai.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.skyfree.kinhnguyetmangthai.R;
import com.skyfree.kinhnguyetmangthai.custom_interface.UpdateListCaItem;
import com.skyfree.kinhnguyetmangthai.model.CalendarItem;
import com.skyfree.kinhnguyetmangthai.utils.Utils;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by KienBeu on 3/16/2018.
 */

public class RecycleViewCalendarAdapter extends RecyclerView.Adapter<RecycleViewCalendarAdapter.ViewHolder>{

    private ArrayList<CalendarItem> mListItem;
    private Context mContext;

    private UpdateListCaItem mUpdateListCaItem;
    private Calendar mCa = Calendar.getInstance();

    public RecycleViewCalendarAdapter(ArrayList<CalendarItem> mListItem, Context mContext) {
        this.mListItem = mListItem;
        this.mContext = mContext;
    }

    public RecycleViewCalendarAdapter(UpdateListCaItem mUpdateListCaItem) {
        this.mUpdateListCaItem = mUpdateListCaItem;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_calendar, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mLinear.setMinimumHeight(holder.mLinear.getWidth());
//        holder.mImgDes.setImageResource(mListItem.get(position).getmDes());
        holder.mTvDay.setText(mListItem.get(position).getmDay());

//        switch (position){
//            case 0:
//                break;
//            case 1:
//                break;
//            case 2:
//                break;
//            case 3:
//                break;
//            case 4:
//                mUpdateListCaItem.createListCaItem(Utils.createListCaItem(Utils.getNumberDataNull(Utils.getThuMayLaMung1(mCa.get(Calendar.MONTH) - 1, mCa.get(Calendar.YEAR))), Utils.getSoNgayTrong1Thang(mCa.get(Calendar.MONTH) - 1, mCa.get(Calendar.YEAR))));
//                break;
//            case 5:
//                mUpdateListCaItem.createListCaItem(Utils.createListCaItem(Utils.getNumberDataNull(Utils.getThuMayLaMung1(mCa.get(Calendar.MONTH), mCa.get(Calendar.YEAR))), Utils.getSoNgayTrong1Thang(mCa.get(Calendar.MONTH), mCa.get(Calendar.YEAR))));
//                break;
//            case 6:
//                mUpdateListCaItem.createListCaItem(Utils.createListCaItem(Utils.getNumberDataNull(Utils.getThuMayLaMung1(mCa.get(Calendar.MONTH) + 1, mCa.get(Calendar.YEAR))), Utils.getSoNgayTrong1Thang(mCa.get(Calendar.MONTH) + 1, mCa.get(Calendar.YEAR))));
//                break;
//            case 7:
//                break;
//            case 8:
//                break;
//            case 9:
//                break;
//            case 10:
//                break;
//            case 11:
//                break;
//
//        }

    }

    @Override
    public int getItemCount() {
        return mListItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout mLinear;
        ImageView mImgDes;
        TextView mTvDay;

        public ViewHolder(View itemView) {
            super(itemView);
            mLinear = (LinearLayout) itemView.findViewById(R.id.linear_item_calendar);
            mImgDes = (ImageView) itemView.findViewById(R.id.img_item_calendar);
            mTvDay = (TextView) itemView.findViewById(R.id.tv_date_item_calendar);
            mContext = itemView.getContext();
        }
    }
    
}
