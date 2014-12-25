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
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class RadioActivity extends Activity implements WSHelperListener {
	private ConnectivityManager manager;
	private MediaPlayer mMediaPlayer;
	private OggStreamPlayer player;
	private boolean playing = false;
	ImageView playbutton, stopbutton;
	MediaPlayer mediaPlayer;
	private WebView browser;
	String m_Text;
	Button sendsms;
	Button makecall;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.radio_layout);
		browser = (WebView)findViewById(R.id.WebView1);
	    browser.setWebViewClient(new MyBrowser());
	    //browser.loadUrl("http://radio.mfmradio.ma/mfm.ogg");
	    browser.loadUrl("http://www.filestouch.com/mfm/radio.html");
		manager = (ConnectivityManager) this
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		WSHelper.getInstance().addWSHelperListener(this);
		WSHelper.getInstance().getCurrentEmission(Urls.apiUrl, manager, this);
		
		makecall= (Button)findViewById(R.id.ButtonTestApl);
		
		makecall.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(isTelephonyEnabled()){
				Intent intent = new Intent(Intent.ACTION_CALL);

				intent.setData(Uri.parse("tel:"+555));
				startActivity(intent);	
			
				}
				else
				{
					Toast.makeText(getApplicationContext(), "impossible  d'appler", Toast.LENGTH_LONG).show();
					
				}
			}
		});
		
		sendsms= (Button)findViewById(R.id.ButtonTest);
		sendsms.setOnClickListener(new OnClickListener() {
			
	
			@Override
			public void onClick(View v) {
			
				AlertDialog.Builder builder = new AlertDialog.Builder(RadioActivity.this);
				builder.setTitle("Envoyer un SMS");
			

				// Set up the input
				final EditText input = new EditText(v.getContext());
				 LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
	             LinearLayout.LayoutParams.MATCH_PARENT,
	             LinearLayout.LayoutParams.MATCH_PARENT);
				 input.setLayoutParams(lp);
				// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
				input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE );
				input.setHeight(200);
			
				builder.setView(input);

				// Set up the buttons
				builder.setPositiveButton("Envoyer", new DialogInterface.OnClickListener() { 
				    @Override
				    public void onClick(DialogInterface dialog, int which) {
				        m_Text = input.getText().toString();
				        SmsManager smsManager = SmsManager.getDefault();
				        smsManager.sendTextMessage("+212619725402", null, m_Text, null, null);
				    }
				});
				builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
				    @Override
				    public void onClick(DialogInterface dialog, int which) {
				        dialog.cancel();
				    }
				});

				builder.show();
				}
		});
		//player = new OggStreamPlayer();
		//playbutton = (ImageView) findViewById(R.id.imageView3);
		//stopbutton = (ImageView) findViewById(R.id.imageView4);

//		playbutton.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				Toast.makeText(getApplicationContext(), "play radio",
//						Toast.LENGTH_SHORT).show();
//				
//
//				try {
//					if (playing) {
//						player.stop();
//						playing = false;
//						//playbutton.setImageDrawable(null);
//						playbutton.setImageResource(R.drawable.play);
//						
//						//mediaPlayer.stop();
//					} else {
//						
//						playbutton.setImageResource(R.drawable.pause);
//						//mediaPlayer = new MediaPlayer();
//					//	mediaPlayer.setDataSource("http://streaming.domainepublic.net:8000/radiopanik.ogg?b1016d03-68d1-4d41-b556-f72bf8e65e71");
//						//mediaPlayer.prepareAsync();
//					//	mediaPlayer.start();
//						//http://inovatek.ma:8001/mfm.ogg
//						player.playAsync("http://radio.mfmradio.ma/mfm.ogg");
//						//player.playAsync("http://images.wikia.com/saintsrow/images/e/ec/SR2_cemetery_house_Ambients_00574.ogg");
//					//player.playAsync("http://streaming.domainepublic.net:8000/radiopanik.ogg?b1016d03-68d1-4d41-b556-f72bf8e65e71");
//						playing = true;
//					}
//				} catch (Exception e) {
//					Toast.makeText(getApplicationContext(), e.toString(),
//							Toast.LENGTH_SHORT).show();
//				}
//
//			}
//		});
		
	}
	private boolean isTelephonyEnabled(){
	    TelephonyManager tm = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
	    return tm != null && tm.getSimState()==TelephonyManager.SIM_STATE_READY;
	}
	@Override
	public void onEmissionsLoaded(List<Emission> emissions) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onErrorLoadingEmissions(String error) {
		// TODO Auto-generated method stub

	}
	 private class MyBrowser extends WebViewClient {
	      @Override
	      public boolean shouldOverrideUrlLoading(WebView view, String url) {
	         view.loadUrl(url);
	         return true;
	      }
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
