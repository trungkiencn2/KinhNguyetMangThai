package com.skyfree.kinhnguyetmangthai.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.skyfree.kinhnguyetmangthai.R;
import com.skyfree.kinhnguyetmangthai.activity.MainActivity;
import com.skyfree.kinhnguyetmangthai.utils.Utils;

/**
 * Created by KienBeu on 3/14/2018.
 */

public class AlarmNotifyReceiver extends BroadcastReceiver {

    private RemoteViews mContentView;
    private NotificationCompat.Builder mNotify;
    private NotificationCompat.BigTextStyle mBigText = new NotificationCompat.BigTextStyle();
    private NotificationManager mNotifyManager;
    public static final int NOTIFY_ID = 5;
    public static int mNotificationId = 0;
    private Calendar mCaNow = Calendar.getInstance();
    private Calendar mCaEndOfCycle = Calendar.getInstance();
    private Calendar mCaStartEasyToConceive = Calendar.getInstance();
    private Calendar mCaStartGiaiDoanHoangThe = Calendar.getInstance();

    @Override
    public void onReceive(Context context, Intent intent) {

        long a = Long.parseLong(Utils.readFromFile(Utils.FILE_NGAY_BAT_DAU_CHU_KY_KINH_NGUYET, context));
        long b = Long.parseLong(Utils.readFromFile(Utils.FILE_CHU_KY_KINH_NGUYET, context));
        long c = Long.parseLong(Utils.readFromFile(Utils.FILE_REPORT_SO_NGAY_GIAI_DOAN_HOANG_THE, context));

        long mTimeEndOfCycle = a + b * Utils.mOneDay;
        long mTimeStartEasyToConceive = a + Utils.getDayLeftToDateEasyToConceive((int) b) * Utils.mOneDay;
        long mTimeStartGiaiDoanHoangThe = a + (b - c) * Utils.mOneDay;

        mCaEndOfCycle.setTimeInMillis(mTimeEndOfCycle);
        mCaStartEasyToConceive.setTimeInMillis(mTimeStartEasyToConceive);
        mCaStartGiaiDoanHoangThe.setTimeInMillis(mTimeStartGiaiDoanHoangThe);

        if(Utils.readFromFile(Utils.FILE_REPORT_CYCLE, context).equals(Utils.TRUE)){
            if(mCaNow.get(Calendar.DAY_OF_MONTH) == mCaEndOfCycle.get(Calendar.DAY_OF_MONTH)){
                pushNotify(context.getString(R.string.app_name), context, context.getString(R.string.alert_report_cycle));
            }
        }

        if(Utils.readFromFile(Utils.FILE_REPORT_EASY_TO_CONCEIVE, context).equals(Utils.TRUE)){
            if(mCaNow.get(Calendar.DAY_OF_MONTH) == mCaStartEasyToConceive.get(Calendar.DAY_OF_MONTH)){
                pushNotify(context.getString(R.string.app_name), context, context.getString(R.string.alert_report_easy_to_conceive));
            }
        }

        if(Utils.readFromFile(Utils.FILE_REPORT_SO_NGAY_GIAI_DOAN_HOANG_THE, context).equals(Utils.TRUE)){
            if(mCaNow.get(Calendar.DAY_OF_MONTH) == mCaStartGiaiDoanHoangThe.get(Calendar.DAY_OF_MONTH)){
                pushNotify(context.getString(R.string.app_name), context, context.getString(R.string.alert_report_ovulation));
            }
        }

    }

    private void pushNotify(String title, Context context, String msg) {
        NotificationCompat.Builder mBuilder;
        mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(msg)
                .setDefaults(
                        Notification.DEFAULT_SOUND
                                | Notification.DEFAULT_VIBRATE)
                .setOngoing(false);

        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(contentIntent);

        NotificationManager mNotifyMgr = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        mNotifyMgr.notify(mNotificationId, mBuilder.build());
    }
}
