package com.sir.app.retrofit.common;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.pgyersdk.crash.PgyCrashManager;
import com.pgyersdk.feedback.PgyFeedbackShakeManager;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;
import com.sir.app.retrofit.R;

import org.json.JSONException;
import org.json.JSONObject;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by zhuyinan on 2017/8/2.
 * Contact by 445181052@qq.com
 */
public class UpdateAction {

    String TAG = UpdateAction.class.getSimpleName();

    SweetAlertDialog alertDialog;
    Activity mContext;


    public UpdateAction(Activity context) {
        mContext = context;
        PgyCrashManager.register(context);
        PgyFeedbackShakeManager.setShakingThreshold(1000);
    }

    public void startUpdates() {
        PgyUpdateManager.register(mContext, mContext.getPackageName(), new UpdateManagerListener() {
            @Override
            public void onUpdateAvailable(String result) {
                Log.e(TAG, result);
                JSONObject jsonData;
                try {
                    jsonData = new JSONObject(result);
                    if ("0".equals(jsonData.getString("code"))) {
                        String message = jsonData.getString("message");
                        JSONObject jsonObject = jsonData.getJSONObject("data");
                        String url = jsonObject.getString("downloadURL");
                        showNoticeDialog(message, url);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNoUpdateAvailable() {

            }
        });
    }

    public void checkUpdates() {
        alertDialog = new SweetAlertDialog(mContext, SweetAlertDialog.PROGRESS_TYPE)
                .setTitleText(mContext.getString(R.string.check_for_updates))
                .setCancelText(mContext.getString(R.string.cancel))
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                    }
                });
        alertDialog.show();

        PgyUpdateManager.register(mContext, mContext.getPackageName(), new UpdateManagerListener() {
            @Override
            public void onUpdateAvailable(String result) {
                alertDialog.dismiss();
                Log.e(TAG, result);
                JSONObject jsonData;
                try {
                    jsonData = new JSONObject(result);
                    if ("0".equals(jsonData.getString("code"))) {
                        String message = jsonData.getString("message");
                        JSONObject jsonObject = jsonData.getJSONObject("data");
                        String url = jsonObject.getString("downloadURL");
                        showNoticeDialog(message, url);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNoUpdateAvailable() {
                alertDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                alertDialog.showCancelButton(false);
                alertDialog.setTitleText(mContext.getString(R.string.latest_version));
            }
        });
    }


    /**
     * 显示更新对话框
     */
    private void showNoticeDialog(String msg, final String downloadURL) {
        //对话框,显示更新信息
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(mContext.getString(R.string.update_tip));
        builder.setMessage(msg);
        // 更新
        builder.setPositiveButton(mContext.getString(R.string.update_now), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                UpdateManagerListener.startDownloadTask(mContext,downloadURL);
            }
        });

        // 稍后更新
        builder.setNegativeButton(mContext.getString(R.string.update_later), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        Dialog noticeDialog = builder.create();
        noticeDialog.show();
    }
}
