package m.androbito.mfm;

import java.util.List;

import m.androbito.adapters.EmissionAdapter;
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
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

public class EmissionsActivity extends Activity implements WSHelperListener,
		OnItemClickListener {

	ListView emissionsLv;
	ImageView facebook; 
	ImageView youtube;
	ImageView twitter; 
	
	

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
		facebook = (ImageView) findViewById(R.id.imageView1);
		facebook.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Uri uri = Uri.parse("https://www.facebook.com/RADIOMFM.Officiel");

		        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		        startActivity(intent);
				
			}
		});
		
		youtube = (ImageView) findViewById(R.id.imageView2);
		youtube.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Uri uri = Uri.parse("https://www.youtube.com/channel/UCFGC3iySyVURobB8-49Iz6A");

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
		detailIntent.putExtra("image", ((Emission) ((EmissionAdapter) emissionsLv
				.getAdapter()).getItem(position)).getIcon());
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

	@Override
	public void onCurrentEmissionLoaded(CurrentEmission currentEmission) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onErrorLoadingCurrentEmission(String error) {
		// TODO Auto-generated method stub
		
	}
}