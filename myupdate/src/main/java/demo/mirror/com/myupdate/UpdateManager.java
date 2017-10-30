package demo.mirror.com.myupdate;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * 下载工具类
 * Created by zhangzhuang on 17/10/30.
 */
public class UpdateManager {

    /**
     * 下载
     *
     * @param context      上下文对象
     * @param providerFile 值为在mainfest文件中指定的provider节点 android:authorities属性的值， 如果不兼容7.0，可以设置为null或者"".
     */
    public static void update(Context context, String providerFile, String url) {

        SharedPrefsUtil.putValue(context, "PROVIDER_FILE", providerFile);

        Intent serviceIntent = new Intent(context, DownloadService.class);
        serviceIntent.setData(Uri.parse(url));
        context.startService(serviceIntent);
    }

}
