package com.android.jcandroid;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.android.jcandroid.Database.DBHelper;

import java.io.File;

public class Utility {

    public static String PREF_APP = "Preferences_App";
    public static String SELECTED_CUSTOMER = "customer";

    public static String VIEW_FRAGMENT_TAG = "ViewFragment";
    public static String ADD_UPDATE_FRAGMENT_TAG = "AddUpdateFragment";

    public static boolean ifDatabaseExists(Context context) {
        File database = context.getDatabasePath(DBHelper.DATABASE_NAME);
        return database.exists();
    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static ProgressDialog getProgressDialog(Context context, String message) {
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage(message);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }
}