package m.androbito.mfm;

import java.util.List;

import m.androbito.adapters.EmissionAdapter;
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
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class EmissionsActivity extends Activity implements WSHelperListener,
		OnItemClickListener {

	ListView emissionsLv;
	private ConnectivityManager manager;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		manager = (ConnectivityManager) this
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		setContentView(R.layout.emissions_layout);
		emissionsLv = (ListView) findViewById(R.id.listView1);
		emissionsLv.setOnItemClickListener(this);
		WSHelper.getInstance().addWSHelperListener(this);
		WSHelper.getInstance().getEmissions(Urls.apiUrl, manager, this);

	}

	@Override
	public void onEmissionsLoaded(final List<Emission> emissions) {
		// TODO Auto-generated method stub
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				emissionsLv.setAdapter(new EmissionAdapter(
						getApplicationContext(), emissions));
			}
		});
	}

	@Override
	public void onErrorLoadingEmissions(String error) {
		// TODO Auto-generated method stub
		Log.e("error", error);
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
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Intent detailIntent = new Intent(this, DetailsActivity.class);
		detailIntent.putExtra("detail", ((Emission) ((EmissionAdapter) emissionsLv
				.getAdapter()).getItem(position)).getDescription());
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