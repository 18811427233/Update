package demo.mirror.com.myupdate;

import android.app.DownloadManager;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

/**
 * 下载服务
 * Created by zhangzhuang on 17/10/18.
 */

public class DownloadService extends IntentService {

    public DownloadService() {
        super("DownloadService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        //获取下载地址
        Uri parse = Uri.parse(intent.getDataString());

        //获取DownloadManager对象
        DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(parse);

        String name = String.valueOf(System.currentTimeMillis());

        //指定APK缓存路径和应用名称，可在SD卡/Android/data/包名/file/Download文件夹中查看
        request.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DOWNLOADS, name);

        //设置网络下载环境为wifi
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
        //设置显示通知栏，下载完成后通知栏自动消失
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        //设置通知栏标题
        request.setTitle("该应用");
        request.setDescription("正在下载");
        request.setAllowedOverRoaming(false);

        /*开始下载*/
        downloadManager.enqueue(request);

    }
}