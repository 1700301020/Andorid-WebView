package com.example.webviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/*
* 《第一行安卓代码》9.1 节webview用法
* Android 10 系统 在webview中可以显示www.baidu.com内容，但是点击链接会出现err_unknown_url_scheme
* 使用内部类重写shouldoverrideurlloading方法
* 参考：https://www.cnblogs.com/uudon/p/7873960.html
* 参考：https://blog.csdn.net/qq_32623363/article/details/79173485?tdsourcetag=s_pctim_aiomsg
*/

public class MainActivity extends AppCompatActivity {

    class MyWebViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {//返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
            String url=request.getUrl().toString();//request提取uri
            try {
                if(!url.startsWith("http:") || !url.startsWith("https:")){
                    Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                    return true;
                }
            }catch (Exception e){
                return false;
            }
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webView=findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);//支持JS脚本
        webView.setWebViewClient(new MyWebViewClient());
        webView.loadUrl("http://www.baidu.com");//传入网址
    }
}
