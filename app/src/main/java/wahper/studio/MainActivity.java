package wahper.studio;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String url = "https://r2---sn-2uuxa3vh-wvbz.googlevideo.com/videoplayback?expire=1580170973&ei=XQ4vXuHfMsu98wSizoPYAg&ip=62.210.211.209&id=7ee24f53a53779dc&itag=18&source=blogger&susc=bl&mime=video/mp4&dur=1434.319&lmt=1580033970568832&sparams=expire,ei,ip,id,itag,source,susc,mime,dur,lmt&sig=ALgxI2wwRQIgCAI5FO6OKstgU7noUThlorOEgxjObc2FcibavZGe4UACIQCcU2_0PjFvdFSrZdnx5f10G4vqkBguUKIVyBGa7TJzGA==&redirect_counter=1&rm=sn-25gks7l&req_id=ea54acd2ee5936e2&cms_redirect=yes&ipbypass=yes&mip=36.72.214.47&mm=31&mn=sn-2uuxa3vh-wvbz&ms=au&mt=1580142150&mv=m&mvi=1&pl=24&lsparams=ipbypass,mip,mm,mn,ms,mv,mvi,pl&lsig=AHylml4wRQIgWUBD6j14smCBL_GVhDWnigMNbhgfg6EuFcggvYJ4d1QCIQD3tB1LpBzfK2QZ_LA7eQZe635R1KMNL71Bq3uiHD8Vrw==";

    ProgressBar progressBar;
    private WebView webView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progresbar);
        webView = (WebView) findViewById(R.id.webView);
        textView = (TextView) findViewById(R.id.titleView);

        progressBar.setVisibility(View.VISIBLE);
        webView.setWebViewClient(new Browser_home());
        webView.setWebChromeClient(new MyChrome());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAppCacheEnabled(true);
        webView.loadUrl(url);
        textView.setText("Anime Boruto");

    }

    class Browser_home extends WebViewClient {

        Browser_home() {
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            progressBar.setVisibility(View.GONE);
            super.onPageFinished(view, url);

        }
    }

    private class MyChrome extends WebChromeClient {
        private View mCustomView;
        private WebChromeClient.CustomViewCallback mCustomViewCallback;
        protected FrameLayout mFullscreenContainer;
        private int mOriginalOrientation;
        private int mOriginalSystemUiVisibility;

        @Override
        public void onHideCustomView() {
            ((FrameLayout)getWindow().getDecorView()).removeView(this.mCustomView);
            this.mCustomView = null;
            getWindow().getDecorView().setSystemUiVisibility(this.mOriginalSystemUiVisibility);
            setRequestedOrientation(this.mOriginalOrientation);
            this.mCustomViewCallback.onCustomViewHidden();
            this.mCustomViewCallback = null;
        }

        @Override
        public void onShowCustomView(View view, WebChromeClient.CustomViewCallback callback) {
            if(this.mCustomView !=null){
                onHideCustomView();
                return;
            }
            this.mCustomView = view;
            this.mOriginalSystemUiVisibility = getWindow().getDecorView().getSystemUiVisibility();
            this.mOriginalOrientation = getRequestedOrientation();
            this.mCustomViewCallback = callback;
            ((FrameLayout)getWindow().getDecorView()).addView(this.mCustomView , new FrameLayout.LayoutParams(-1,-1));
            getWindow().getDecorView().setSystemUiVisibility(3846);

        }
    }
}
