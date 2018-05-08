package app.lz.com.webviewflingscrolldemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements MyWebView.ScrollChangeListener {

	MyWebView webView;
	EditText editText;
	Button flingBtn, resetBtn;
	TextView scrollResult, chromeVersion;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		webView = findViewById(R.id.webview);
		editText = findViewById(R.id.edit_text);
		flingBtn = findViewById(R.id.fling_btn);
		resetBtn = findViewById(R.id.reset_btn);
		scrollResult = findViewById(R.id.scroll_result);
		chromeVersion = findViewById(R.id.chrome_version);

		webView.setWebViewClient(new WebViewClient());
		webView.loadUrl("https://www.coupang.com/");
		webView.setScrollChangeListener(this);

		flingBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				hideSoftKeyboard(MainActivity.this, v);
				editText.clearFocus();
				webView.flingScroll(0, Integer.parseInt(editText.getText().toString()));
			}
		});
		resetBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				hideSoftKeyboard(MainActivity.this, v);
				editText.clearFocus();
				webView.scrollTo(0, 0);
			}
		});

		showResult(0);
		String ua = webView.getSettings().getUserAgentString();
		String chrome = ua.substring(ua.indexOf("Chrome/") + 7);
		chromeVersion.setText(String.format(getResources().getString(R.string.chrome_version), chrome.substring(0, chrome.indexOf(" "))));
	}

	@Override
	public void onScrollYChanged(int scrollY) {
		showResult(scrollY);
	}

	private void showResult(int scrollY) {
		scrollResult.setText(String.format(getResources().getString(R.string.current_y), scrollY));
	}

	public static void hideSoftKeyboard(Activity activity, View view) {
		InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
		if (imm != null) {
			imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
		}
	}
}
