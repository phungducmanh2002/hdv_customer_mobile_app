package com.example.hotel_customer.hook.retrofit;

import android.content.Context;

import com.example.hotel_customer.data.ResponseData;
import com.example.hotel_customer.ui.dialog.DialogNotify;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomeCallback implements Callback<ResponseData> {
    OnResponse actionSuccess;
    OnResponse actionFailed;
    OnFailure actionFailure;
    Context context;
    DialogNotify dialogNotify;
    int aceptCode;

    public CustomeCallback(Context context, int aceptCode, OnResponse actionResponse) {
        this.actionSuccess = actionResponse;
        this.context = context;
        this.aceptCode = aceptCode;
    }

    public CustomeCallback(Context context, int aceptCode, OnResponse actionSuccess, OnResponse actionFailed, OnFailure actionFailure) {
        this.actionSuccess = actionSuccess;
        this.actionFailed = actionFailed;
        this.actionFailure = actionFailure;
        this.context = context;
        this.aceptCode = aceptCode;
    }

    @Override
    public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
        try {
            if (!response.isSuccessful()) {
                throw new Exception("Response is not successfully");
            }
            if (response.body().getCode() != aceptCode) {
                throw new Exception(String.format("Response with code: %s", response.body().getCode()));
            }
            if (actionSuccess != null) {
                actionSuccess.action(call, response);
            }
        } catch (Exception ex) {
            showDialogNotify("Call api failed");
            actionFailed.action(call, response);
        }
    }

    @Override
    public void onFailure(Call call, Throwable t) {
        showDialogNotify(t.getMessage());
        if (actionFailure != null) {
            actionFailure.action(call, t);
        }
    }

    private void showDialogNotify(String content) {
        if (dialogNotify == null) {
            dialogNotify = new DialogNotify(context);
        }
        dialogNotify.setContent(content);
        dialogNotify.show();
    }
}
