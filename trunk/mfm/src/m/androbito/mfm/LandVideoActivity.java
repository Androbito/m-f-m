package m.androbito.mfm;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;

public class LandVideoActivity extends Activity {
	WebView mWebView, fullweb;
	String videoID;

	@SuppressWarnings("deprecation")
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setTheme(android.R.style.Theme_Light_NoTitleBar_Fullscreen);

		setContentView(R.layout.landfull);

		videoID = getIntent().getStringExtra("videoID");

		WebView myWebView = (WebView) findViewById(R.id.fullwebview);
		myWebView.getSettings().setJavaScriptEnabled(true);
		myWebView.getSettings().setPluginState(PluginState.ON);
		myWebView.loadUrl("http://www.youtube.com/embed/" + videoID
				+ "?autoplay=1");
		Log.i("URL_Video", "http://www.youtube.com/embed/" + videoID
				+ "?autoplay=1");
		myWebView.setWebChromeClient(new WebChromeClient());
	}
}
