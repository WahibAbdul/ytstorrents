package codeclobber.com.ytsbrowser.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import codeclobber.com.ytsbrowser.R;

public class WebViewActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton mBackButton, mForwardButton;
    WebView mWebView;
    ProgressBar mProgressBar;
    private String mUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        mUrl = getIntent().getStringExtra("url");
        if (mUrl == null || mUrl.isEmpty()) {
            finish();
            return;
        }

        initViews();

        setupWebView();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBtn_backArrow:
                if (mWebView != null && mWebView.canGoBack()) {
                    mProgressBar.setVisibility(View.VISIBLE);
                    mWebView.goBack();
                    changeNavigationButtonsState();
                }
                break;
            case R.id.imgBtn_forwardArrow:
                if (mWebView != null && mWebView.canGoForward()) {
                    mProgressBar.setVisibility(View.VISIBLE);
                    mWebView.goForward();
                    changeNavigationButtonsState();
                }
                break;
        }
    }

    // MARK: Helper Methods
    private void initViews() {

        String title = getIntent().getStringExtra("title");
        TextView titleView = (TextView) findViewById(R.id.tv_title);
        titleView.setText(title == null ? "" : title);

        mBackButton = (ImageButton) findViewById(R.id.imgBtn_backArrow);
        assert mBackButton != null;
        mBackButton.setOnClickListener(this);

        mForwardButton = (ImageButton) findViewById(R.id.imgBtn_forwardArrow);
        assert mForwardButton != null;
        mForwardButton.setOnClickListener(this);

        mWebView = (WebView) findViewById(R.id.webView);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

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
                view.scrollTo(0, 0);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mProgressBar.setVisibility(View.INVISIBLE);
                changeNavigationButtonsState();
            }
        });
    }

    private void changeNavigationButtonsState() {
        if (mWebView.canGoBack()) {
            mBackButton.setVisibility(View.VISIBLE);
            mBackButton.setEnabled(true);
        } else {
            mBackButton.setVisibility(View.GONE);
            mBackButton.setEnabled(false);
        }
        if (mWebView.canGoForward()) {
            mForwardButton.setVisibility(View.VISIBLE);
            mForwardButton.setEnabled(true);
        } else {
            mForwardButton.setVisibility(View.GONE);
            mForwardButton.setEnabled(false);
        }

    }

}
