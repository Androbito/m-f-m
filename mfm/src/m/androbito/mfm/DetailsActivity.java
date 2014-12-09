package m.androbito.mfm;

import m.androbito.utils.ImageLoader;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends Activity {

	private String detail;
	private TextView detailTxtView;
	private ImageView imageEmission;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_layout);
		detailTxtView = (TextView) findViewById(R.id.textView1);
		imageEmission = (ImageView) findViewById(R.id.imageView5);
		new ImageLoader(this).DisplayImage(getIntent().getStringExtra("image"),
				imageEmission);
		detail = getIntent().getStringExtra("detail");
		detailTxtView.setText(detail);
	}
}
