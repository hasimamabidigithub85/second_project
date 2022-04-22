package com.gropse.epub.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.macreel.epauthi.R;

public class AppUtility {

    public static void showMessage(Context context, int internetMessage) {
        Toast.makeText(context, internetMessage, Toast.LENGTH_SHORT).show();
    }

    public static boolean isNetworkAvailable(Context context, boolean isToNotifyUser) {

        if (context == null)
            return false;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        @SuppressLint("MissingPermission")
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        if (isToNotifyUser && !isConnected)
            AppUtility.showMessage(context, R.string.internetMessage);
        return isConnected;
    }
}
