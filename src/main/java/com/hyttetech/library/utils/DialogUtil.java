package com.hyttetech.library.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

/**
 * Created by koushik on 18/8/17.
 */

public class DialogUtil {

    static boolean isOK;

    public static boolean showSimpleDialog(String title, String message, Context context)
    {

        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener()
        {

            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                isOK = true;
            }
        });
        alert.setNegativeButton("Cancel",new DialogInterface.OnClickListener()
        {

            @Override
            public void onClick(DialogInterface dialog, int which)
            {

            }
        });

        alert.show();

        return isOK;
    }


    public static void showToast(String message,int duration,Context context)
    {
        Toast.makeText(context,message,duration).show();
    }
}
