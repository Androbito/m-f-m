package m.androbito.utils;

import m.androbito.mfm.LandVideoActivity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.webkit.WebChromeClient;

public class MyChromeClient extends WebChromeClient {

	private Context context;
	private String videoURL;

	public MyChromeClient(Context context, String videoURL) {
		super();
		this.context = context;
		this.videoURL = videoURL;
	}

	@Override
	public void onShowCustomView(View view, CustomViewCallback callback) {
		/**
		 * Remove super function, it stucks to play youtube video if you click
		 * fullscreen icon
		 **/
		// super.onShowCustomView(view, callback);
		Intent intent = new Intent(this.context, LandVideoActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra("video", videoURL);
		context.startActivity(intent);

	}

}