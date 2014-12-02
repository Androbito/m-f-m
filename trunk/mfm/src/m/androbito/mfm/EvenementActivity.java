package m.androbito.mfm;

import java.util.List;

import m.androbito.adapters.EventAdapter;
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
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class EvenementActivity extends Activity implements WSHelperListener,
		OnItemClickListener {
	private ConnectivityManager manager;
	private ListView eventsLv;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		manager = (ConnectivityManager) this
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		setContentView(R.layout.evenement_layout);
		eventsLv = (ListView) findViewById(R.id.listView1);
		eventsLv.setOnItemClickListener(this);
		WSHelper.getInstance().addWSHelperListener(this);
		WSHelper.getInstance().getEvents(Urls.apiUrl, manager, this);
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
	public void onEventsLoaded(final List<Event> events) {
		// TODO Auto-generated method stub
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				eventsLv.setAdapter(new EventAdapter(getApplicationContext(),
						events));
			}
		});
	}

	@Override
	public void onErrorLoadingEvents(String error) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Intent detailIntent = new Intent(this, DetailsActivity.class);
		detailIntent.putExtra("detail", ((Event) ((EventAdapter) eventsLv
				.getAdapter()).getItem(position)).getDetail());
		startActivity(detailIntent);
	}

	@Override
	public void onPodcastsLoaded(List<Podcast> podcasts) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onErrorLoadingPodcasts(String error) {
		// TODO Auto-generated method stub
		
	}
}