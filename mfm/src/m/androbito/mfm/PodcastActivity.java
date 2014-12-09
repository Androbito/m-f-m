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
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.ListView;

public class PodcastActivity extends Activity implements WSHelperListener {

	private ConnectivityManager manager;
	private ListView podsLv;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		manager = (ConnectivityManager) this
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		setContentView(R.layout.podcast_layout);
		podsLv = (ListView) findViewById(R.id.listView1);
		WSHelper.getInstance().addWSHelperListener(this);
		WSHelper.getInstance().getPodcasts(Urls.apiUrl, manager, this);
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
				// TODO Auto-generated method stub
				podsLv.setAdapter(new PodcastAdapter(getApplicationContext(),
						podcasts));
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