package demo.mirror.com.myupdate;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import java.io.File;

/**
 * 监听安装完成的广播
 * Created by zhangzhuang on 17/10/20.
 */
public class ApkInstallReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
            //处理 下载完成
            long downloadApkId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);

            DownloadManager dManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            Cursor c = dManager.query(new DownloadManager.Query().setFilterById(downloadApkId));

            if (c != null) {

                c.moveToFirst();
                String downloadFileLocalUri = c.getString(c.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
                if (downloadFileLocalUri != null) {

                    File mFile = new File(Uri.parse(downloadFileLocalUri).getPath());
                    installApk(context, mFile);
                }
                c.close();
            }

        } else if (intent.getAction().equals(DownloadManager.ACTION_NOTIFICATION_CLICKED)) {
            //处理 如果还未完成下载，用户点击Notification
            Intent viewDownloadIntent = new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS);
            viewDownloadIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(viewDownloadIntent);
        }
    }

    /**
     * 安装apk
     *
     * @param context
     * @param file
     */
    private static void installApk(Context context, File file) {

        if (Build.VERSION.SDK_INT >= 24) {//判读版本是否在7.0以上

            String providerFile = SharedPrefsUtil.getValue(context, "PROVIDER_FILE", "");

            Uri apkUri = FileProvider.getUriForFile(context, providerFile, file);

            Intent install = new Intent(Intent.ACTION_VIEW);
            install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);//添加这一句表示对目标应用临时授权该Uri所代表的文件
            install.setDataAndType(apkUri, "application/vnd.android.package-archive");
            context.startActivity(install);
        } else {
            Intent install = new Intent(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(install);
        }
    }

}
