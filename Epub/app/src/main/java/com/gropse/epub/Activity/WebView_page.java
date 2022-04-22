package com.gropse.epub.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.gropse.epub.Activity.Utils.Prefs;
import com.macreel.epauthi.R;

public class WebView_page extends AppCompatActivity {
    WebView wb;
    String pdfPath;
 ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        wb= findViewById(R.id.web_view);

        //progress dialog
        pd = new ProgressDialog(WebView_page.this);
        pd.setMessage("Loading");
        pd.show();


        pdfPath = Prefs.instanceOf(getApplicationContext()).getUrl();
        wb.loadUrl("https://docs.google.com/gview?embedded=true&url="+pdfPath);
        wb.getSettings().setJavaScriptEnabled(true);
        wb.getSettings().setLoadWithOverviewMode(true);
        wb.getSettings().setUseWideViewPort(true);
        wb.getSettings().setSupportZoom(true);
        // wb.invokeZoomPicker();
        wb.zoomIn();
        wb.getSettings().setBuiltInZoomControls(true);
        wb.zoomOut();
        wb.getSettings().setDisplayZoomControls(true);
        wb.getSettings().setDisplayZoomControls(false);
        wb.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                wb.getSettings().setBuiltInZoomControls(true);
                wb.getSettings().setSupportZoom(true);


            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pd.dismiss();
            }
        });



    }
}
