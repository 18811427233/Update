package demo.mirror.com.test111;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import demo.mirror.com.myupdate.UpdateManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = "http://101.28.249.94/apk.r1.market.hiapk.com/data/upload/apkres/2017/4_11/15/com.baidu.searchbox_034250.apk";
        /*该值为：AndroidManifest内的provider，name值，务必填写一致*/
        String providerFile = "demo.mirror.com.myupdate.fileprovider";
        /*调用更新方法*/
        UpdateManager.update(this
                , providerFile
                , url);

    }
}
