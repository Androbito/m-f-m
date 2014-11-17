package m.androbito.mfm;

import net.droidlabs.audio.ogg.OggStreamPlayer;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class RadioActivity extends Activity {
	
	private MediaPlayer mMediaPlayer ;
	private OggStreamPlayer player;
	private boolean playing=  false;
	ImageView playbutton,stopbutton;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radio_layout);
        
        player = new OggStreamPlayer();
        playbutton = (ImageView) findViewById(R.id.imageView3);
        stopbutton = (ImageView) findViewById(R.id.imageView4);
        
        playbutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "play radio", Toast.LENGTH_SHORT).show();
				
				try {
					if (playing){
						player.stop();
						playing=  false;
					
					}
					else{
						player.playAsync("http://images.wikia.com/saintsrow/images/e/ec/SR2_cemetery_house_Ambients_00574.ogg");
						playing =  true;
					}
					} catch (Exception e) {
					Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();	
				}
				

			}
		});
        stopbutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "stopping radio", Toast.LENGTH_SHORT).show();
				player.stop();

			}
		});
    }
}
