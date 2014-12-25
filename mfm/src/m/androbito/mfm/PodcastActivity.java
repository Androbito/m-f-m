package m.androbito.mfm;

import java.util.List;

import m.androbito.adapters.PodcastAdapter;
import m.androbito.model.CurrentEmission;
import m.androbito.model.Emission;
import m.androbito.model.Event;
import m.androbito.model.Podcast;
import m.androbito.network.WSHelper;
import m.androbito.network.WSHelperListener;
import m.androbito.utils.Urls;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;

public class PodcastActivity extends Activity implements WSHelperListener {

	private ConnectivityManager manager;
	private ListView podsLv;
	ImageView facebook;
	ImageView youtube;
	ImageView twitter;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		manager = (ConnectivityManager) this
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		setContentView(R.layout.podcast_layout);
		podsLv = (ListView) findViewById(R.id.listView1);
		WSHelper.getInstance().addWSHelperListener(this);
		WSHelper.getInstance().getPodcasts(Urls.apiUrl, manager, this);
		facebook = (ImageView) findViewById(R.id.imageView1);
		facebook.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Uri uri = Uri
						.parse("https://www.facebook.com/RADIOMFM.Officiel");

				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);

			}
		});

		youtube = (ImageView) findViewById(R.id.imageView2);
		youtube.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Uri uri = Uri
						.parse("https://www.youtube.com/channel/UCFGC3iySyVURobB8-49Iz6A");

				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);

			}
		});
		twitter = (ImageView) findViewById(R.id.imageView3);
		twitter.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Uri uri = Uri.parse("https://twitter.com/mfm_officiel");

				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);

			}
		});
	}

	@Override
	public void onEmissionsLoaded(List<Emission> emissions) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onErrorLoadingEmissions(String error) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onEventsLoaded(List<Event> events) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onErrorLoadingEvents(String error) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPodcastsLoaded(final List<Podcast> podcasts) {
		// TODO Auto-generated method stub
		runOnUiThread(new Runnable() {

			@Override
			public void run() {

				podsLv.setAdapter(new PodcastAdapter(getApplicationContext(),
						podcasts) {

					@Override
					public void clickHandle(String videoID) {
						Intent intent = new Intent(getApplicationContext(), LandVideoActivity.class);
						intent.putExtra("videoID", videoID);
						startActivity(intent);
					}
				});
			}
		});
	}

	@Override
	public void onErrorLoadingPodcasts(String error) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCurrentEmissionLoaded(CurrentEmission currentEmission) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onErrorLoadingCurrentEmission(String error) {
		// TODO Auto-generated method stub

	}
}