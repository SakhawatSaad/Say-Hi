package com.shsaad.sayhi;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class Utils {

    public static void showAlert (Context context, String sms){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(sms)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).create().show();
    }

}
