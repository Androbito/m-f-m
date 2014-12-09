package m.androbito.mfm;

import java.util.List;

import m.androbito.model.CurrentEmission;
import m.androbito.model.Emission;
import m.androbito.model.Event;
import m.androbito.model.Podcast;
import m.androbito.network.WSHelper;
import m.androbito.network.WSHelperListener;
import m.androbito.utils.ImageLoader;
import m.androbito.utils.Urls;
import net.droidlabs.audio.ogg.OggStreamPlayer;
import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RadioActivity extends Activity implements WSHelperListener {
	private ConnectivityManager manager;
	private MediaPlayer mMediaPlayer;
	private OggStreamPlayer player;
	private boolean playing = false;
	ImageView playbutton, stopbutton;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.radio_layout);
		manager = (ConnectivityManager) this
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		WSHelper.getInstance().addWSHelperListener(this);
		WSHelper.getInstance().getCurrentEmission(Urls.apiUrl, manager, this);
		player = new OggStreamPlayer();
		playbutton = (ImageView) findViewById(R.id.imageView3);
		stopbutton = (ImageView) findViewById(R.id.imageView4);

		playbutton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "play radio",
						Toast.LENGTH_SHORT).show();

				try {
					if (playing) {
						player.stop();
						playing = false;

					} else {
						player.playAsync("http://images.wikia.com/saintsrow/images/e/ec/SR2_cemetery_house_Ambients_00574.ogg");
						playing = true;
					}
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), e.toString(),
							Toast.LENGTH_SHORT).show();
				}

			}
		});
		stopbutton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "stopping radio",
						Toast.LENGTH_SHORT).show();
				player.stop();

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
	public void onPodcastsLoaded(List<Podcast> podcasts) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onErrorLoadingPodcasts(String error) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCurrentEmissionLoaded(final CurrentEmission currentEmission) {
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				new ImageLoader(getApplicationContext()).DisplayImage(
						currentEmission.getUrl_img_emission(),
						((ImageView) findViewById(R.id.imageView1)));
				
				((TextView) findViewById(R.id.textView1))
						.setText(currentEmission.getTitle_emission());
				
				((TextView) findViewById(R.id.textView2))
						.setText(currentEmission.getPersonne_emission());
				
				((TextView) findViewById(R.id.textView3)).setText("De: "
						+ currentEmission.getDebut_emission());
				
				((TextView) findViewById(R.id.textView4)).setText("à : "
						+ currentEmission.getFin_emission());
			}
		});
	}

	@Override
	public void onErrorLoadingCurrentEmission(String error) {
		// TODO Auto-generated method stub

	}
}
