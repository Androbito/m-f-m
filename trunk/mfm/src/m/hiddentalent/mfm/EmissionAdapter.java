package m.hiddentalent.mfm;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class EmissionAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<Emission> emissions;

	public EmissionAdapter(Context _context) {
		mInflater = LayoutInflater.from(_context);
		emissions = new ArrayList<Emission>();
		emissions.add(new Emission("ISSTIRAHAT", "Khalid NIZAR",
				R.drawable.khalidnizar, "18:00"));
		emissions.add(new Emission("TOMBILE", "DAVID Jeremy",
				R.drawable.davidjeremie, "09:00"));
		emissions.add(new Emission("DIN WA DOUNIA", "Hassan BELAID",
				R.drawable.elhassanaitbilaid, "15:00"));
		emissions.add(new Emission("Bonjour", "Khalid NIZAR",
				R.drawable.khalidnizar, "07:00"));
		emissions.add(new Emission("Rihat dwar", "Khalid NIZAR",
				R.drawable.rihatedouar, "10:00"));
		emissions.add(new Emission("ISSTIRAHAT", "Khalid NIZAR",
				R.drawable.khalidnizar, "18:00"));
		emissions.add(new Emission("TOMBILE", "DAVID Jeremy",
				R.drawable.davidjeremie, "09:00"));
		emissions.add(new Emission("DIN WA DOUNIA", "Hassan BELAID",
				R.drawable.elhassanaitbilaid, "15:00"));
		emissions.add(new Emission("Bonjour", "Khalid NIZAR",
				R.drawable.khalidnizar, "07:00"));
		emissions.add(new Emission("Rihat dwar", "Khalid NIZAR",
				R.drawable.rihatedouar, "10:00"));

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return emissions.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return emissions.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return emissions.size();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = mInflater.inflate(R.layout.emission_item, parent, false);
		((TextView) v.findViewById(R.id.textView1)).setText(emissions.get(
				position).getTitle_emission());
		((TextView) v.findViewById(R.id.textView2)).setText(emissions.get(
				position).getAuditeur_emission());
		((TextView) v.findViewById(R.id.textView3)).setText(emissions.get(
				position).getRdv());
		((ImageView) v.findViewById(R.id.imageView1))
				.setImageResource(emissions.get(position).getIcon());
		return v;
	}

}
