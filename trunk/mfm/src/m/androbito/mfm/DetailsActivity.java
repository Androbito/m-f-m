package m.androbito.mfm;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends Activity {

	private String detail;
	private TextView detailTxtView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_layout);
		detailTxtView = (TextView) findViewById(R.id.textView1);
		detail = getIntent().getStringExtra("detail");
		detailTxtView.setText(detail);
	}
}
