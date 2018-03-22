package com.skyfree.kinhnguyetmangthai.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.skyfree.kinhnguyetmangthai.R;
import com.skyfree.kinhnguyetmangthai.utils.Utils;

import java.util.Calendar;

/**
 * Created by KienBeu on 3/21/2018.
 */

public class FragmentThuThai extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_conception_diary, container, false);

        TextView mTvDoDaiKiKinh = (TextView) mView.findViewById(R.id.tv_do_dai_ky_kinh_fragment_conception_diar);
        TextView mTvDoDaiChuKy = (TextView) mView.findViewById(R.id.tv_do_dai_chu_ky_fragment_conception_diary);
        TextView mTvStart1 = (TextView) mView.findViewById(R.id.tv_start_one_fragment_conception_diary);
        TextView mTvStart2 = (TextView) mView.findViewById(R.id.tv_start_two_fragment_conception_diary);
        TextView mTvStart3 = (TextView) mView.findViewById(R.id.tv_start_three_fragment_conception_diary);
        TextView mTvStart4 = (TextView) mView.findViewById(R.id.tv_start_four_fragment_conception_diary);
        TextView mTvStart5 = (TextView) mView.findViewById(R.id.tv_start_five_fragment_conception_diary);
        TextView mTvStart6 = (TextView) mView.findViewById(R.id.tv_start_six_fragment_conception_diary);
        TextView mTvStart7 = (TextView) mView.findViewById(R.id.tv_start_seven_fragment_conception_diary);
        TextView mTvStart8 = (TextView) mView.findViewById(R.id.tv_start_eight_fragment_conception_diary);
        TextView mTvStart9 = (TextView) mView.findViewById(R.id.tv_start_nine_fragment_conception_diary);
        TextView mTvStart10 = (TextView) mView.findViewById(R.id.tv_start_ten_fragment_conception_diary);
        TextView mTvEnd1 = (TextView) mView.findViewById(R.id.tv_end_one_fragment_conception_diary);
        TextView mTvEnd2 = (TextView) mView.findViewById(R.id.tv_end_two_fragment_conception_diary);
        TextView mTvEnd3 = (TextView) mView.findViewById(R.id.tv_end_three_fragment_conception_diary);
        TextView mTvEnd4 = (TextView) mView.findViewById(R.id.tv_end_four_fragment_conception_diary);
        TextView mTvEnd5 = (TextView) mView.findViewById(R.id.tv_end_five_fragment_conception_diary);
        TextView mTvEnd6 = (TextView) mView.findViewById(R.id.tv_end_six_fragment_conception_diary);
        TextView mTvEnd7 = (TextView) mView.findViewById(R.id.tv_end_seven_fragment_conception_diary);
        TextView mTvEnd8 = (TextView) mView.findViewById(R.id.tv_end_eight_fragment_conception_diary);
        TextView mTvEnd9 = (TextView) mView.findViewById(R.id.tv_end_nine_fragment_conception_diary);
        TextView mTvEnd10 = (TextView) mView.findViewById(R.id.tv_end_ten_fragment_conception_diary);

        RoundCornerProgressBar mRc = (RoundCornerProgressBar) mView.findViewById(R.id.rc_frag_conception_diary);
        TextView mTvLeftRc = (TextView) mView.findViewById(R.id.tv_left_rc_frag_conception_diary);
        TextView mTvRightRc = (TextView) mView.findViewById(R.id.tv_right_rc_frag_conception_diary);

        long mNgayBatDauChuKyKinhNguyet = Long.parseLong(Utils.readFromFile(Utils.FILE_NGAY_BAT_DAU_CHU_KY_KINH_NGUYET, getContext()));
        int mChuKyKinhNguyet = Integer.parseInt(Utils.readFromFile(Utils.FILE_CHU_KY_KINH_NGUYET, getContext()));
        int mChuKyHanhKinh = Integer.parseInt(Utils.readFromFile(Utils.FILE_CHU_KY_HANH_KINH, getContext()));
        int mSoNgayDenNgayThuThai = Utils.getDayLeftToDateEasyToConceive(mChuKyKinhNguyet);
        long mTimeToHanhKinh = mSoNgayDenNgayThuThai * Utils.mOneDay;

        mRc.setProgress(mSoNgayDenNgayThuThai);
        mRc.setSecondaryProgress(mChuKyKinhNguyet);
        mRc.setMax(mChuKyKinhNguyet);

        mTvLeftRc.setText(mSoNgayDenNgayThuThai+"");
        mTvRightRc.setText(mChuKyKinhNguyet+"");

        Calendar mCaGuessStart1 = Calendar.getInstance();
        Calendar mCaGuessStart2 = Calendar.getInstance();
        Calendar mCaGuessStart3 = Calendar.getInstance();
        Calendar mCaGuessStart4 = Calendar.getInstance();
        Calendar mCaGuessStart5 = Calendar.getInstance();
        Calendar mCaGuessStart6 = Calendar.getInstance();
        Calendar mCaGuessStart7 = Calendar.getInstance();
        Calendar mCaGuessStart8 = Calendar.getInstance();
        Calendar mCaGuessStart9 = Calendar.getInstance();
        Calendar mCaGuessStart10 = Calendar.getInstance();

        Calendar mCaGuessEnd1 = Calendar.getInstance();
        Calendar mCaGuessEnd2 = Calendar.getInstance();
        Calendar mCaGuessEnd3 = Calendar.getInstance();
        Calendar mCaGuessEnd4 = Calendar.getInstance();
        Calendar mCaGuessEnd5 = Calendar.getInstance();
        Calendar mCaGuessEnd6 = Calendar.getInstance();
        Calendar mCaGuessEnd7 = Calendar.getInstance();
        Calendar mCaGuessEnd8 = Calendar.getInstance();
        Calendar mCaGuessEnd9 = Calendar.getInstance();
        Calendar mCaGuessEnd10 = Calendar.getInstance();

        mCaGuessStart1.setTimeInMillis(mNgayBatDauChuKyKinhNguyet + mTimeToHanhKinh);
        mCaGuessStart2.setTimeInMillis(mCaGuessStart1.getTimeInMillis() + mChuKyKinhNguyet * Utils.mOneDay);
        mCaGuessStart3.setTimeInMillis(mCaGuessStart1.getTimeInMillis() + mChuKyKinhNguyet * Utils.mOneDay * 2);
        mCaGuessStart4.setTimeInMillis(mCaGuessStart1.getTimeInMillis() + mChuKyKinhNguyet * Utils.mOneDay * 3);
        mCaGuessStart5.setTimeInMillis(mCaGuessStart1.getTimeInMillis() + mChuKyKinhNguyet * Utils.mOneDay * 4);
        mCaGuessStart6.setTimeInMillis(mCaGuessStart1.getTimeInMillis() + mChuKyKinhNguyet * Utils.mOneDay * 5);
        mCaGuessStart7.setTimeInMillis(mCaGuessStart1.getTimeInMillis() + mChuKyKinhNguyet * Utils.mOneDay * 6);
        mCaGuessStart8.setTimeInMillis(mCaGuessStart1.getTimeInMillis() + mChuKyKinhNguyet * Utils.mOneDay * 7);
        mCaGuessStart9.setTimeInMillis(mCaGuessStart1.getTimeInMillis() + mChuKyKinhNguyet * Utils.mOneDay * 8);
        mCaGuessStart10.setTimeInMillis(mCaGuessStart1.getTimeInMillis() + mChuKyKinhNguyet * Utils.mOneDay * 9);

        mCaGuessEnd1.setTimeInMillis(mCaGuessStart1.getTimeInMillis() + mChuKyHanhKinh * Utils.mOneDay);
        mCaGuessEnd2.setTimeInMillis(mCaGuessStart2.getTimeInMillis() + mChuKyHanhKinh * Utils.mOneDay);
        mCaGuessEnd3.setTimeInMillis(mCaGuessStart3.getTimeInMillis() + mChuKyHanhKinh * Utils.mOneDay);
        mCaGuessEnd4.setTimeInMillis(mCaGuessStart4.getTimeInMillis() + mChuKyHanhKinh * Utils.mOneDay);
        mCaGuessEnd5.setTimeInMillis(mCaGuessStart5.getTimeInMillis() + mChuKyHanhKinh * Utils.mOneDay);
        mCaGuessEnd6.setTimeInMillis(mCaGuessStart6.getTimeInMillis() + mChuKyHanhKinh * Utils.mOneDay);
        mCaGuessEnd7.setTimeInMillis(mCaGuessStart7.getTimeInMillis() + mChuKyHanhKinh * Utils.mOneDay);
        mCaGuessEnd8.setTimeInMillis(mCaGuessStart8.getTimeInMillis() + mChuKyHanhKinh * Utils.mOneDay);
        mCaGuessEnd9.setTimeInMillis(mCaGuessStart9.getTimeInMillis() + mChuKyHanhKinh * Utils.mOneDay);
        mCaGuessEnd10.setTimeInMillis(mCaGuessStart10.getTimeInMillis() + mChuKyHanhKinh * Utils.mOneDay);

        mTvDoDaiKiKinh.setText(mSoNgayDenNgayThuThai + getString(R.string.day_the_average_length_of_the_period));
        mTvDoDaiChuKy.setText(mChuKyKinhNguyet + getString(R.string.day_average_cycle_length));

        mTvStart1.setText(mCaGuessStart1.get(Calendar.DAY_OF_MONTH) + " - " + (mCaGuessStart1.get(Calendar.MONTH) + 1) + ", " + mCaGuessStart1.get(Calendar.YEAR));
        mTvStart2.setText(mCaGuessStart2.get(Calendar.DAY_OF_MONTH) + " - " + (mCaGuessStart2.get(Calendar.MONTH) + 1) + ", " + mCaGuessStart2.get(Calendar.YEAR));
        mTvStart3.setText(mCaGuessStart3.get(Calendar.DAY_OF_MONTH) + " - " + (mCaGuessStart3.get(Calendar.MONTH) + 1) + ", " + mCaGuessStart3.get(Calendar.YEAR));
        mTvStart4.setText(mCaGuessStart4.get(Calendar.DAY_OF_MONTH) + " - " + (mCaGuessStart4.get(Calendar.MONTH) + 1) + ", " + mCaGuessStart4.get(Calendar.YEAR));
        mTvStart5.setText(mCaGuessStart5.get(Calendar.DAY_OF_MONTH) + " - " + (mCaGuessStart5.get(Calendar.MONTH) + 1) + ", " + mCaGuessStart5.get(Calendar.YEAR));
        mTvStart6.setText(mCaGuessStart6.get(Calendar.DAY_OF_MONTH) + " - " + (mCaGuessStart6.get(Calendar.MONTH) + 1) + ", " + mCaGuessStart6.get(Calendar.YEAR));
        mTvStart7.setText(mCaGuessStart7.get(Calendar.DAY_OF_MONTH) + " - " + (mCaGuessStart7.get(Calendar.MONTH) + 1) + ", " + mCaGuessStart7.get(Calendar.YEAR));
        mTvStart8.setText(mCaGuessStart8.get(Calendar.DAY_OF_MONTH) + " - " + (mCaGuessStart8.get(Calendar.MONTH) + 1) + ", " + mCaGuessStart8.get(Calendar.YEAR));
        mTvStart9.setText(mCaGuessStart9.get(Calendar.DAY_OF_MONTH) + " - " + (mCaGuessStart9.get(Calendar.MONTH) + 1) + ", " + mCaGuessStart9.get(Calendar.YEAR));
        mTvStart10.setText(mCaGuessStart10.get(Calendar.DAY_OF_MONTH) + " - " + (mCaGuessStart10.get(Calendar.MONTH) + 1) + ", " + mCaGuessStart10.get(Calendar.YEAR));

        mTvEnd1.setText(mCaGuessEnd1.get(Calendar.DAY_OF_MONTH) + " - " + (mCaGuessEnd1.get(Calendar.MONTH) + 1) + ", " + mCaGuessEnd1.get(Calendar.YEAR));
        mTvEnd2.setText(mCaGuessEnd2.get(Calendar.DAY_OF_MONTH) + " - " + (mCaGuessEnd2.get(Calendar.MONTH) + 1) + ", " + mCaGuessEnd2.get(Calendar.YEAR));
        mTvEnd3.setText(mCaGuessEnd3.get(Calendar.DAY_OF_MONTH) + " - " + (mCaGuessEnd3.get(Calendar.MONTH) + 1) + ", " + mCaGuessEnd3.get(Calendar.YEAR));
        mTvEnd4.setText(mCaGuessEnd4.get(Calendar.DAY_OF_MONTH) + " - " + (mCaGuessEnd4.get(Calendar.MONTH) + 1) + ", " + mCaGuessEnd4.get(Calendar.YEAR));
        mTvEnd5.setText(mCaGuessEnd5.get(Calendar.DAY_OF_MONTH) + " - " + (mCaGuessEnd5.get(Calendar.MONTH) + 1) + ", " + mCaGuessEnd5.get(Calendar.YEAR));
        mTvEnd6.setText(mCaGuessEnd6.get(Calendar.DAY_OF_MONTH) + " - " + (mCaGuessEnd6.get(Calendar.MONTH) + 1) + ", " + mCaGuessEnd6.get(Calendar.YEAR));
        mTvEnd7.setText(mCaGuessEnd7.get(Calendar.DAY_OF_MONTH) + " - " + (mCaGuessEnd7.get(Calendar.MONTH) + 1) + ", " + mCaGuessEnd7.get(Calendar.YEAR));
        mTvEnd8.setText(mCaGuessEnd8.get(Calendar.DAY_OF_MONTH) + " - " + (mCaGuessEnd8.get(Calendar.MONTH) + 1) + ", " + mCaGuessEnd8.get(Calendar.YEAR));
        mTvEnd9.setText(mCaGuessEnd9.get(Calendar.DAY_OF_MONTH) + " - " + (mCaGuessEnd9.get(Calendar.MONTH) + 1) + ", " + mCaGuessEnd9.get(Calendar.YEAR));
        mTvEnd10.setText(mCaGuessEnd10.get(Calendar.DAY_OF_MONTH) + " - " + (mCaGuessEnd10.get(Calendar.MONTH) + 1) + ", " + mCaGuessEnd10.get(Calendar.YEAR));

        return mView;
    }
}
