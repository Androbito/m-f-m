package m.hiddentalent.mfm;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class TabsActivity extends TabActivity {
	/** Called when the activity is first created. */
	TabHost tabHost;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs_layout);

		tabHost = getTabHost();

		TabSpec radiospec = tabHost.newTabSpec("Radio");
		radiospec.setIndicator("Radio",
				getResources().getDrawable(R.drawable.radio));
		Intent radioIntent = new Intent(this, RadioActivity.class);
		radiospec.setContent(radioIntent);

		TabSpec emissionspec = tabHost.newTabSpec("Emissions");
		emissionspec.setIndicator("Emissions",
				getResources().getDrawable(R.drawable.emmisions));
		Intent emissionIntent = new Intent(this, EmissionsActivity.class);
		emissionspec.setContent(emissionIntent);

		TabSpec podcastspec = tabHost.newTabSpec("Podcast");
		podcastspec.setIndicator("Podcast",
				getResources().getDrawable(R.drawable.podcast));
		Intent podcastIntent = new Intent(this, PodcastActivity.class);
		podcastspec.setContent(podcastIntent);

		TabSpec evenementspec = tabHost.newTabSpec("Evenement");
		evenementspec.setIndicator("Evenement",
				getResources().getDrawable(R.drawable.evenement));
		Intent evenementIntent = new Intent(this, EvenementActivity.class);
		evenementspec.setContent(evenementIntent);

		TabSpec contactspec = tabHost.newTabSpec("Contact");
		contactspec.setIndicator("Contact",
				getResources().getDrawable(R.drawable.enveloppe));
		Intent contactIntent = new Intent(this, ContactActivity.class);
		contactspec.setContent(contactIntent);

		setupTab(R.drawable.icon_radio_tab, "Radio", RadioActivity.class);
		setupTab(R.drawable.icon_emissions_tab, "Emissions",
				EmissionsActivity.class);
		setupTab(R.drawable.icon_podcast_tab, "Podcast", PodcastActivity.class);
		setupTab(R.drawable.icon_evenement_tab, "Evenement",
				EvenementActivity.class);
		setupTab(R.drawable.icon_contact_tab, "Contact", ContactActivity.class);
	}

	private void setupTab(final int imgId, final String title,
			final Class<?> cls) {
		View tabview = createTabView(tabHost.getContext(), title, imgId);
		Intent tabIntent = new Intent(this, cls);
		TabSpec setContent = tabHost.newTabSpec(title).setIndicator(tabview)
				.setContent(tabIntent);
		tabHost.addTab(setContent);

	}

	private static View createTabView(final Context context, final String text,
			final int imgID) {
		View view = LayoutInflater.from(context)
				.inflate(R.layout.tabs_bg, null);
		TextView tv = (TextView) view.findViewById(R.id.tabsText);
		ImageView imgv = (ImageView) view.findViewById(R.id.tabsIcon);
		imgv.setImageResource(imgID);
		tv.setText(text);
		return view;
	}
}