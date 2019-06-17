package codeclobber.com.ytsbrowser.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import codeclobber.com.ytsbrowser.R;

public class YoutubePlayerActivity extends AppCompatActivity {

    String mVideoCode;
    private WebView mWebView;
    private ProgressBar mProgressBar;
    private String mUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_player);

        mVideoCode = getIntent().getStringExtra("object");
        if (mVideoCode == null || mVideoCode.isEmpty()) {
            finish();
            return;
        }

        mUrl = "https://www.youtube.com/embed/" + mVideoCode + "?autoplay=1&controls=1";

        initViews();

        setupWebView();
    }

    // MARK: Helper Methods
    private void initViews() {
        mWebView = (WebView) findViewById(R.id.webView);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        String title = getIntent().getStringExtra("title");
        ((TextView) findViewById(R.id.tv_title)).setText(title == null ? "" : title);

    }

    @SuppressLint("SetJavaScriptEnabled")
    private void setupWebView() {
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(mUrl);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                mProgressBar.setVisibility(View.VISIBLE);
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mProgressBar.setVisibility(View.INVISIBLE);
            }
        });
    }



}
