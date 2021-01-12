package com.example.tutorsapp.network;

import android.app.Dialog;

import com.example.tutorsapp.GeneralCallback;
import com.example.tutorsapp.TutorApp;
import com.example.tutorsapp.helper.DialogHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseCallService<T> {
    public static <V> void makeServiceCall(Call<V> call, final GeneralCallback callback) {
        Dialog dialog = DialogHelper.showLoadingDialog(TutorApp.getInstance().getmCurrentActivity());
        if (dialog != null)
            dialog.show();

        final CommunicationModel communicationModel = new CommunicationModel();
        call.enqueue(new Callback<V>() {
            @Override
            public void onResponse(Call<V> call, Response<V> response) {
                if (dialog != null)
                    dialog.dismiss();
                callback.successCall(response);
            }

            @Override
            public void onFailure(Call<V> call, Throwable t) {
                if (dialog != null)
                    dialog.dismiss();
                callback.errorCall(t);
            }
        });
    }
}

class CommunicationModel<T> {
    String code;
    String message;
    Boolean isSuccess;
    T data;
}
