package byui.cit360.employeereport;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class EmployeeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        WebView myWebView = findViewById(R.id.webView2);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl("http://192.168.0.15:8084/FinalServlets/List?q=employee");
    }
}
