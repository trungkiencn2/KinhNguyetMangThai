package com.skyfree.kinhnguyetmangthai.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.skyfree.kinhnguyetmangthai.R;
import com.skyfree.kinhnguyetmangthai.utils.Utils;

import java.util.Calendar;

/**
 * Created by KienBeu on 3/21/2018.
 */

public class FragmentHanhKinh extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_menstruation_diary, container, false);

        TextView mTvTop = (TextView) mView.findViewById(R.id.tv_top_fragment_menstruation_diary);
        TextView mTvGuessOne = (TextView) mView.findViewById(R.id.tv_guess_one);
        TextView mTvGuessTwo = (TextView) mView.findViewById(R.id.tv_guess_two);
        TextView mTvGuessThree = (TextView) mView.findViewById(R.id.tv_guess_three);
        TextView mTvGuessFour = (TextView) mView.findViewById(R.id.tv_guess_four);
        TextView mTvGuessFive = (TextView) mView.findViewById(R.id.tv_guess_five);
        TextView mTvDoDaiKinhNguyet = (TextView) mView.findViewById(R.id.tv_bottom_do_dai_kinh_nguyet);
        TextView mTvDoDaiChuKy = (TextView) mView.findViewById(R.id.tv_bottom_do_dai_chu_ky);
        RoundCornerProgressBar mRcTop = (RoundCornerProgressBar) mView.findViewById(R.id.rc_top_frag_menstruation_diary);
        RoundCornerProgressBar mRcOne = (RoundCornerProgressBar) mView.findViewById(R.id.rc_guess_one);
        RoundCornerProgressBar mRcTwo = (RoundCornerProgressBar) mView.findViewById(R.id.rc_guess_two);
        RoundCornerProgressBar mRcThree = (RoundCornerProgressBar) mView.findViewById(R.id.rc_guess_three);
        RoundCornerProgressBar mRcFour = (RoundCornerProgressBar) mView.findViewById(R.id.rc_guess_four);
        RoundCornerProgressBar mRcFive = (RoundCornerProgressBar) mView.findViewById(R.id.rc_guess_five);
        TextView mTvLeftTopRc = (TextView) mView.findViewById(R.id.tv_left_pro_top_frag_menstruation_diary);
        TextView mTvRightTopRc = (TextView) mView.findViewById(R.id.tv_right_pro_top_frag_menstruation_diary);
        TextView mTvLeftOne = (TextView) mView.findViewById(R.id.tv_left_pro_one_frag_menstruation_diary);
        TextView mTvLeftTwo = (TextView) mView.findViewById(R.id.tv_left_pro_two_frag_menstruation_diary);
        TextView mTvLeftThree = (TextView) mView.findViewById(R.id.tv_left_pro_three_frag_menstruation_diary);
        TextView mTvLeftFour = (TextView) mView.findViewById(R.id.tv_left_pro_four_frag_menstruation_diary);
        TextView mTvLeftFive = (TextView) mView.findViewById(R.id.tv_left_pro_five_frag_menstruation_diary);
        TextView mTvRightOne = (TextView) mView.findViewById(R.id.tv_right_pro_one_frag_menstruation_diary);
        TextView mTvRightTwo = (TextView) mView.findViewById(R.id.tv_right_pro_two_frag_menstruation_diary);
        TextView mTvRightThree = (TextView) mView.findViewById(R.id.tv_right_pro_three_frag_menstruation_diary);
        TextView mTvRightFour = (TextView) mView.findViewById(R.id.tv_right_pro_four_frag_menstruation_diary);
        TextView mTvRightFive = (TextView) mView.findViewById(R.id.tv_right_pro_five_frag_menstruation_diary);

        long mNgayBatDauChuKyKinhNguyet = Long.parseLong(Utils.readFromFile(Utils.FILE_NGAY_BAT_DAU_CHU_KY_KINH_NGUYET, getContext()));
        int mChuKyHanhKinh = Integer.parseInt(Utils.readFromFile(Utils.FILE_CHU_KY_HANH_KINH, getContext()));
        int mChuKyKinhNguyet = Integer.parseInt(Utils.readFromFile(Utils.FILE_CHU_KY_KINH_NGUYET, getContext()));

        mTvLeftTopRc.setText(mChuKyHanhKinh+"");
        mTvLeftOne.setText(mChuKyHanhKinh+"");
        mTvLeftTwo.setText(mChuKyHanhKinh+"");
        mTvLeftThree.setText(mChuKyHanhKinh+"");
        mTvLeftFour.setText(mChuKyHanhKinh+"");
        mTvLeftFive.setText(mChuKyHanhKinh+"");

        mTvRightTopRc.setText(mChuKyKinhNguyet+"");
        mTvRightOne.setText(mChuKyKinhNguyet+"");
        mTvRightTwo.setText(mChuKyKinhNguyet+"");
        mTvRightThree.setText(mChuKyKinhNguyet+"");
        mTvRightFour.setText(mChuKyKinhNguyet+"");
        mTvRightFive.setText(mChuKyKinhNguyet+"");

        mRcTop.setProgress(mChuKyHanhKinh);
        mRcTop.setSecondaryProgress(mChuKyKinhNguyet);
        mRcTop.setMax(mChuKyKinhNguyet);

        mRcOne.setProgress(mChuKyHanhKinh);
        mRcOne.setSecondaryProgress(mChuKyKinhNguyet);
        mRcOne.setMax(mChuKyKinhNguyet);

        mRcTwo.setProgress(mChuKyHanhKinh);
        mRcTwo.setSecondaryProgress(mChuKyKinhNguyet);
        mRcTwo.setMax(mChuKyKinhNguyet);

        mRcThree.setProgress(mChuKyHanhKinh);
        mRcThree.setSecondaryProgress(mChuKyKinhNguyet);
        mRcThree.setMax(mChuKyKinhNguyet);

        mRcFour.setProgress(mChuKyHanhKinh);
        mRcFour.setSecondaryProgress(mChuKyKinhNguyet);
        mRcFour.setMax(mChuKyKinhNguyet);

        mRcFive.setProgress(mChuKyHanhKinh);
        mRcFive.setSecondaryProgress(mChuKyKinhNguyet);
        mRcFive.setMax(mChuKyKinhNguyet);

        Calendar mCalBatDauKinhNguyet = Calendar.getInstance();
        Calendar mCalBatDauGuessOne = Calendar.getInstance();
        Calendar mCalBatDauGuessTwo = Calendar.getInstance();
        Calendar mCalBatDauGuessThree = Calendar.getInstance();
        Calendar mCalBatDauGuessFour = Calendar.getInstance();
        Calendar mCalBatDauGuessFive = Calendar.getInstance();

        Calendar mCalKetThucKinhNguyet = Calendar.getInstance();
        Calendar mCalKetThucGuessOne = Calendar.getInstance();
        Calendar mCalKetThucGuessTwo = Calendar.getInstance();
        Calendar mCalKetThucGuessThree = Calendar.getInstance();
        Calendar mCalKetThucGuessFour = Calendar.getInstance();
        Calendar mCalKetThucGuessFive = Calendar.getInstance();

        mCalBatDauKinhNguyet.setTimeInMillis(mNgayBatDauChuKyKinhNguyet);
        mCalBatDauGuessOne.setTimeInMillis(mCalBatDauKinhNguyet.getTimeInMillis() + 30 * Utils.mOneDay);
        mCalBatDauGuessTwo.setTimeInMillis(mCalBatDauKinhNguyet.getTimeInMillis() + 60 * Utils.mOneDay);
        mCalBatDauGuessThree.setTimeInMillis(mCalBatDauKinhNguyet.getTimeInMillis() + 90 * Utils.mOneDay);
        mCalBatDauGuessFour.setTimeInMillis(mCalBatDauKinhNguyet.getTimeInMillis() + 120 * Utils.mOneDay);
        mCalBatDauGuessFive.setTimeInMillis(mCalBatDauKinhNguyet.getTimeInMillis() + 150 * Utils.mOneDay);

        mCalKetThucKinhNguyet.setTimeInMillis(mCalBatDauKinhNguyet.getTimeInMillis() + mChuKyHanhKinh * Utils.mOneDay);
        mCalKetThucGuessOne.setTimeInMillis(mCalBatDauGuessOne.getTimeInMillis() + mChuKyHanhKinh * Utils.mOneDay);
        mCalKetThucGuessTwo.setTimeInMillis(mCalBatDauGuessTwo.getTimeInMillis() + mChuKyHanhKinh * Utils.mOneDay);
        mCalKetThucGuessThree.setTimeInMillis(mCalBatDauGuessThree.getTimeInMillis() + mChuKyHanhKinh * Utils.mOneDay);
        mCalKetThucGuessFour.setTimeInMillis(mCalBatDauGuessFour.getTimeInMillis() + mChuKyHanhKinh * Utils.mOneDay);
        mCalKetThucGuessFive.setTimeInMillis(mCalBatDauGuessFive.getTimeInMillis() + mChuKyHanhKinh * Utils.mOneDay);


        int mDate = mCalBatDauKinhNguyet.get(Calendar.DAY_OF_MONTH);
        int mMonth = mCalBatDauKinhNguyet.get(Calendar.MONTH) + 1;
        int mYear = mCalBatDauKinhNguyet.get(Calendar.YEAR);

        int mDateEnd = mCalKetThucKinhNguyet.get(Calendar.DAY_OF_MONTH);
        int mMonthEnd = mCalKetThucKinhNguyet.get(Calendar.MONTH) + 1;
        int mYearEnd = mCalKetThucKinhNguyet.get(Calendar.YEAR);

        mTvTop.setText(mDate + "/" + mMonth + " - " + mDateEnd + "/" + mMonthEnd);

        mTvGuessOne.setText(mCalBatDauGuessOne.get(Calendar.DAY_OF_MONTH) + "/" + (mCalBatDauGuessOne.get(Calendar.MONTH) + 1)
                + " - " + mCalKetThucGuessOne.get(Calendar.DAY_OF_MONTH) + "/" + (mCalKetThucGuessOne.get(Calendar.MONTH) + 1));

        mTvGuessTwo.setText(mCalBatDauGuessTwo.get(Calendar.DAY_OF_MONTH) + "/" + (mCalBatDauGuessTwo.get(Calendar.MONTH) + 1)
                + " - " + mCalKetThucGuessTwo.get(Calendar.DAY_OF_MONTH) + "/" + (mCalKetThucGuessTwo.get(Calendar.MONTH) + 1));

        mTvGuessThree.setText(mCalBatDauGuessThree.get(Calendar.DAY_OF_MONTH) + "/" + (mCalBatDauGuessThree.get(Calendar.MONTH) + 1)
                + " - " + mCalKetThucGuessThree.get(Calendar.DAY_OF_MONTH) + "/" + (mCalKetThucGuessThree.get(Calendar.MONTH) + 1));

        mTvGuessFour.setText(mCalBatDauGuessFour.get(Calendar.DAY_OF_MONTH) + "/" + (mCalBatDauGuessFour.get(Calendar.MONTH) + 1)
                + " - " + mCalKetThucGuessFour.get(Calendar.DAY_OF_MONTH) + "/" + (mCalKetThucGuessFour.get(Calendar.MONTH) + 1));

        mTvGuessFive.setText(mCalBatDauGuessFive.get(Calendar.DAY_OF_MONTH) + "/" + (mCalBatDauGuessFive.get(Calendar.MONTH) + 1)
                + " - " + mCalKetThucGuessFive.get(Calendar.DAY_OF_MONTH) + "/" + (mCalKetThucGuessFive.get(Calendar.MONTH) + 1));

        mTvDoDaiKinhNguyet.setText("  •  " + getString(R.string.menstrual_period) + Utils.getDayLeftToDateEasyToConceive(Integer.parseInt(Utils.readFromFile(Utils.FILE_CHU_KY_KINH_NGUYET, getContext()))) + " " + getString(R.string.day));
        mTvDoDaiChuKy.setText("  •  " + getString(R.string.cycle_length) + Utils.readFromFile(Utils.FILE_CHU_KY_KINH_NGUYET, getContext()) + " " + getString(R.string.day));

        return mView;
    }
}
