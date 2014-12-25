package m.androbito.adapters;

import java.util.ArrayList;
import java.util.List;

import m.androbito.mfm.R;
import m.androbito.model.Emission;
import m.androbito.utils.ImageLoader;
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
	private ImageLoader imageLoader;

	public EmissionAdapter(Context _context, List<Emission> mEmissions) {
		mInflater = LayoutInflater.from(_context);
		emissions = new ArrayList<Emission>();
		emissions.addAll(mEmissions);
		imageLoader = new ImageLoader(_context);

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
				position).getTitle1_emission());
		((TextView) v.findViewById(R.id.TextView11)).setText(emissions.get(
				position).getTitle2_emission());
		//((TextView) v.findViewById(R.id.textView1)).setTextSize(10);
		
		((TextView) v.findViewById(R.id.textView2)).setText(emissions.get(
				position).getAuditeur_emission());
		((TextView) v.findViewById(R.id.textView3)).setText(emissions.get(
				position).getRdv());
		ImageView iconImg = ((ImageView) v.findViewById(R.id.imageView1));
		imageLoader.DisplayImage(emissions.get(position).getIcon(), iconImg);
		return v;
	}

}
