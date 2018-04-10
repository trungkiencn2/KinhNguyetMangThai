package com.skyfree.kinhnguyetmangthai.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.skyfree.kinhnguyetmangthai.R;
import com.skyfree.kinhnguyetmangthai.model.CalendarItem;
import com.skyfree.kinhnguyetmangthai.model.NoteObj;
import com.skyfree.kinhnguyetmangthai.utils.Utils;
import com.skyfree.kinhnguyetmangthai.utils.VietCalendar;

import java.util.ArrayList;
import java.util.Calendar;

import io.realm.Realm;

/**
 * Created by KienBeu on 3/16/2018.
 */

public class RecycleViewCalendarAdapter extends RecyclerView.Adapter<RecycleViewCalendarAdapter.ViewHolder> {

    private ArrayList<CalendarItem> mListItem;
    private Context mContext;
    public static int position = 0;
    private Calendar mCa = Calendar.getInstance();

    private Calendar mCaNgayBatDau = Calendar.getInstance();
    private Realm realm;

    private int mChuKyHanhKinh;
    private int mChuKyKinhNguyet;
    private ArrayList<Calendar> mListCalHanhKinh = new ArrayList<>();
    private ArrayList<Calendar> mListCalRungTrung = new ArrayList<>();
    private ArrayList<Calendar> mListCalThuThai = new ArrayList<>();

    public RecycleViewCalendarAdapter(ArrayList<CalendarItem> mListItem, Context mContext) {
        realm = Realm.getDefaultInstance();
        this.mListItem = mListItem;
        this.mContext = mContext;

        mCaNgayBatDau.setTimeInMillis(Long.parseLong(Utils.readFromFile(Utils.FILE_NGAY_BAT_DAU_CHU_KY_KINH_NGUYET, mContext)));
        mChuKyHanhKinh = Integer.parseInt(Utils.readFromFile(Utils.FILE_CHU_KY_HANH_KINH, mContext));
        mChuKyKinhNguyet = Integer.parseInt(Utils.readFromFile(Utils.FILE_CHU_KY_KINH_NGUYET, mContext));

        mListCalHanhKinh.add(mCaNgayBatDau);
        for (int j = 0; j <= 100; j++) {
            Calendar mCaTam = Calendar.getInstance();
            mCaTam.setTimeInMillis(mCaNgayBatDau.getTimeInMillis() + j * mChuKyKinhNguyet * Utils.mOneDay);
            if (mCaTam.getTimeInMillis() > mCaNgayBatDau.getTimeInMillis() + 2 * 365 * Utils.mOneDay - 30 * Utils.mOneDay) {
                return;
            }
            mListCalHanhKinh.add(mCaTam);
            for (int i = 0; i < mChuKyHanhKinh; i++) {
                Calendar mCaTamTam = Calendar.getInstance();
                mCaTamTam.setTimeInMillis(mCaTam.getTimeInMillis() + i * Utils.mOneDay);
                mListCalHanhKinh.add(mCaTamTam);
                if (i == mChuKyHanhKinh - 1) {
                    for (int k = 1; k <= 7; k++) {
                        if(k == 6){
                            Calendar mCaTamRungTrung = Calendar.getInstance();
                            mCaTamRungTrung.setTimeInMillis(mCaTamTam.getTimeInMillis() + 6 * Utils.mOneDay);
                            mListCalRungTrung.add(mCaTamRungTrung);
                        }else {
                            Calendar mCaTamThuThai = Calendar.getInstance();
                            mCaTamThuThai.setTimeInMillis(mCaTamTam.getTimeInMillis() + k * Utils.mOneDay);
                            mListCalThuThai.add(mCaTamThuThai);
                        }

                    }
                }

            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_calendar, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (mListItem.get(position).getmBg() != null) {
            holder.mImgBg.setBackgroundResource(mListItem.get(position).getmBg());
        }
        if (mListItem.get(position).getmDes() != null) {
            holder.mImgDes.setImageResource(mListItem.get(position).getmDes());
        }
        holder.mTvDate.setText(mListItem.get(position).getmDate());

        if(!mListItem.get(position).getmDate().equals("") && !mListItem.get(position).getmMonth().equals("") && !mListItem.get(position).getmYear().equals("")){
            holder.mTvAmLich.setText(VietCalendar.convertSolar2Lunar(Integer.parseInt(mListItem.get(position).getmDate()),
                    Integer.parseInt(mListItem.get(position).getmMonth()), Integer.parseInt(mListItem.get(position).getmYear()), 7.0) + "");
        }

        for (int j = 0; j < mListCalHanhKinh.size(); j++) {
            for (int i = 0; i < mListItem.size(); i++) {
                if (!mListItem.get(position).getmDate().equals("") && !mListItem.get(position).getmMonth().equals("") && !mListItem.get(position).getmYear().equals("")) {
                    if (Integer.parseInt(mListItem.get(position).getmDate()) == mListCalHanhKinh.get(j).get(Calendar.DAY_OF_MONTH) && Integer.parseInt(mListItem.get(position).getmMonth()) == mListCalHanhKinh.get(j).get(Calendar.MONTH)
                            && Integer.parseInt(mListItem.get(position).getmYear()) == mListCalHanhKinh.get(j).get(Calendar.YEAR)) {
                        holder.mImgBg.setBackgroundResource(R.drawable.icon_kinhnguyet);
                        holder.mTvDate.setTextColor(Color.WHITE);
                        holder.mTvAmLich.setTextColor(Color.WHITE);
                    } else if (Integer.parseInt(mListItem.get(position).getmDate()) == mListCalHanhKinh.get(j).get(Calendar.DAY_OF_MONTH) && Integer.parseInt(mListItem.get(position).getmMonth()) == mListCalHanhKinh.get(j).get(Calendar.MONTH)
                            && Integer.parseInt(mListItem.get(position).getmYear()) == mListCalHanhKinh.get(j).get(Calendar.YEAR)) {
                        holder.mImgBg.setBackgroundResource(R.drawable.icon_kinhnguyet);
                        holder.mTvDate.setTextColor(Color.WHITE);
                        holder.mTvAmLich.setTextColor(Color.WHITE);
                    } else if (Integer.parseInt(mListItem.get(position).getmDate()) == mListCalHanhKinh.get(j).get(Calendar.DAY_OF_MONTH) && Integer.parseInt(mListItem.get(position).getmMonth()) == mListCalHanhKinh.get(j).get(Calendar.MONTH)
                            && Integer.parseInt(mListItem.get(position).getmYear()) == mListCalHanhKinh.get(j).get(Calendar.YEAR)) {
                        holder.mImgBg.setBackgroundResource(R.drawable.icon_kinhnguyet);
                        holder.mTvDate.setTextColor(Color.WHITE);
                        holder.mTvAmLich.setTextColor(Color.WHITE);
                    }
                }
            }
        }

        for (int i = 0; i < mListCalThuThai.size(); i++) {
            if (!mListItem.get(position).getmDate().equals("") && !mListItem.get(position).getmMonth().equals("") && !mListItem.get(position).getmYear().equals("")) {
                if (Integer.parseInt(mListItem.get(position).getmDate()) == mListCalThuThai.get(i).get(Calendar.DAY_OF_MONTH)
                        && Integer.parseInt(mListItem.get(position).getmMonth()) == mListCalThuThai.get(i).get(Calendar.MONTH)
                        && Integer.parseInt(mListItem.get(position).getmYear()) == mListCalThuThai.get(i).get(Calendar.YEAR)) {
                    holder.mImgDes.setImageResource(R.drawable.icon_fertile);
                }
            }
        }

        for(int i = 0; i<mListCalRungTrung.size(); i++){
            if (!mListItem.get(position).getmDate().equals("") && !mListItem.get(position).getmMonth().equals("") && !mListItem.get(position).getmYear().equals("")) {
                if (Integer.parseInt(mListItem.get(position).getmDate()) == mListCalRungTrung.get(i).get(Calendar.DAY_OF_MONTH)
                        && Integer.parseInt(mListItem.get(position).getmMonth()) == mListCalRungTrung.get(i).get(Calendar.MONTH)
                        && Integer.parseInt(mListItem.get(position).getmYear()) == mListCalRungTrung.get(i).get(Calendar.YEAR)) {
                    holder.mImgDes.setImageResource(R.drawable.icon_ovulation);
                }
            }
        }

        Calendar mCaCurrent = Calendar.getInstance();
        if (!mListItem.get(position).getmDate().equals("") && !mListItem.get(position).getmMonth().equals("") && !mListItem.get(position).getmYear().equals("")) {
            if (Integer.parseInt(mListItem.get(position).getmDate()) == mCaCurrent.get(Calendar.DAY_OF_MONTH)
                    && Integer.parseInt(mListItem.get(position).getmMonth()) == mCaCurrent.get(Calendar.MONTH)
                    && Integer.parseInt(mListItem.get(position).getmYear()) == mCaCurrent.get(Calendar.YEAR)) {
                holder.mImgBgInside.setBackgroundResource(R.drawable.bg_cal_today);
            }
        }

        NoteObj mNoteObjTam = Utils.getNoteObj(realm, mListItem.get(position).getmDate() + "" + mListItem.get(position).getmMonth() + "" + mListItem.get(position).getmYear());
        if(mNoteObjTam != null){
            if(!mNoteObjTam.getmNoteNote().equals("")){
                holder.mImgBgNote.setBackgroundResource(R.drawable.bg_note_calendar_item);
            }
        }

    }

    @Override
    public int getItemCount() {
        return mListItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mImgBgNote;
        ImageView mImgBgInside;
        ImageView mImgBg;
        ImageView mImgDes;
        TextView mTvDate;
        TextView mTvAmLich;

        public ViewHolder(View itemView) {
            super(itemView);
            mImgBgNote = (ImageView) itemView.findViewById(R.id.img_bg_note_item_calendar);
            mImgBgInside = (ImageView) itemView.findViewById(R.id.img_bg_inside_item_calendar);
            mImgBg = (ImageView) itemView.findViewById(R.id.img_bg_item_calendar);
            mImgDes = (ImageView) itemView.findViewById(R.id.img_item_calendar);
            mTvDate = (TextView) itemView.findViewById(R.id.tv_date_item_calendar);
            mTvAmLich = (TextView) itemView.findViewById(R.id.tv_am_lich_item_calendar);
            mContext = itemView.getContext();
        }
    }

}
