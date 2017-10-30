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
        /*调用更新方法*/
        UpdateManager.update(this
                , "demo.mirror.com.myupdate.fileprovider"
                , url);

    }
}
