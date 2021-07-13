package com.example.tutorsapp.helper;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import com.example.tutorsapp.R;
import com.example.tutorsapp.interfaces.CallbackGen;

public class DialogHelper {

   public static CallbackGen callbackGen;



    public static Dialog showMessageDialog(Context context, String title, String message) {
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                        dialog.dismiss();
                        if (callbackGen != null)
                            callbackGen.returnCall(null, 1);
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setCancelable(true)
                .show();
        return dialog;
    }

    public static void dismiss(AlertDialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }

    }

    public static void dismiss(Dialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }


    public static Dialog showLoadingDialog(Context context) {
        if (context != null) {
            Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            if (dialog.getWindow() != null)
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.argb(50, 0, 0, 0)));
            dialog.setCancelable(true);
            dialog.setContentView(R.layout.dialog_loading);
            if (dialog != null)
                dialog.show();
            return dialog;
        }
        return null;
    }
}
