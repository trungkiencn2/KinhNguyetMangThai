package com.skyfree.kinhnguyetmangthai.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skyfree.kinhnguyetmangthai.R;
import com.skyfree.kinhnguyetmangthai.adapter.RecycleViewCalendarAdapter;
import com.skyfree.kinhnguyetmangthai.custom_interface.UpdateListCaItem;
import com.skyfree.kinhnguyetmangthai.model.CalendarItem;
import com.skyfree.kinhnguyetmangthai.utils.Utils;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by KienBeu on 3/16/2018.
 */

public class FragmentCalendar extends Fragment{
    int mNum;
    private static int mMonth, mYear;
    private Calendar mCa = Calendar.getInstance();
    private ArrayList<CalendarItem> mListItem = new ArrayList<>();

    private static String[] cheeses = {"Cheddar", "Jack", "Gamonedo", "Lancashire",
            "Limburger", "Pepperjack", "Skyr", "Feta", "Asiago"};

    /**
     * Create a new instance of CountingFragment, providing "num"
     * as an argument.
     */
    public static FragmentCalendar newInstance(int num, int month, int year) {
        FragmentCalendar f = new FragmentCalendar();

        mMonth = month;
        mYear = year;

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("num", num);
        f.setArguments(args);

        return f;
    }

    /**
     * When creating, retrieve this instance's number from its arguments.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNum = getArguments() != null ? getArguments().getInt("num") : 1;
    }

    public int getShownIndex() {
        return getArguments().getInt("num", 0);
    }

    /**
     * The Fragment's UI is just a simple text view showing its
     * instance number.
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        String dayWeek = Utils.getThuMayLaMung1(mMonth, mYear);

        int numberOfDataNull = Utils.getNumberDataNull(dayWeek);
        int maxDayInMonth = Utils.getSoNgayTrong1Thang(mMonth, mYear);

        View v = inflater.inflate(R.layout.fragment_month_calendar, container, false);
        RecyclerView mRcv = v.findViewById(R.id.rcv_fragment_month_calendar);

        RecycleViewCalendarAdapter mAdapter = new RecycleViewCalendarAdapter(Utils.createListCaItem(numberOfDataNull, maxDayInMonth), getContext());
        mRcv.setLayoutManager(new GridLayoutManager(getContext(), 7));
        mRcv.setHasFixedSize(true);
        mRcv.setAdapter(mAdapter);

        return v;
    }

}
