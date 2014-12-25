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
	private WebView myWebView;

	@SuppressWarnings("deprecation")
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setTheme(android.R.style.Theme_Light_NoTitleBar_Fullscreen);

		setContentView(R.layout.landfull);

		videoID = getIntent().getStringExtra("videoID");

		myWebView = (WebView) findViewById(R.id.fullwebview);
		myWebView.getSettings().setJavaScriptEnabled(true);
		myWebView.getSettings().setPluginState(PluginState.ON);
		myWebView.loadUrl("http://www.youtube.com/embed/" + videoID
				+ "?autoplay=1");
		Log.i("URL_Video", "http://www.youtube.com/embed/" + videoID
				+ "?autoplay=1");
		myWebView.setWebChromeClient(new WebChromeClient());
	}
	
	@Override
	public void onPause() {
	    super.onPause();
	    myWebView.onPause();
	    myWebView.pauseTimers(); //careful with this! Pauses all layout, parsing, and JavaScript timers for all WebViews.
	}

	@Override
	public void onResume() {
	    super.onResume();
	    myWebView.onResume();
	    myWebView.resumeTimers();
	}

	@Override
	public void onDestroy() {
	    super.onDestroy();
	    myWebView.loadUrl("about:blank"); 
	    myWebView.stopLoading();
	    myWebView.setWebChromeClient(null);
	    myWebView.setWebViewClient(null);
	    myWebView.destroy();
	    myWebView = null;
	}
}
