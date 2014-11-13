package m.hiddentalent.mfm;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class EmissionsActivity extends Activity {

	ListView emissionsLv;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.emissions_layout);
		emissionsLv = (ListView) findViewById(R.id.listView1);
		emissionsLv.setAdapter(new EmissionAdapter(this));
	}
}