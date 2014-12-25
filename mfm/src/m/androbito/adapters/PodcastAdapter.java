package m.androbito.adapters;

import java.util.ArrayList;
import java.util.List;

import m.androbito.mfm.R;
import m.androbito.model.Podcast;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

public abstract class PodcastAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<Podcast> podcasts;
	private Context context;

	public PodcastAdapter(Context _context, List<Podcast> mPodcast) {
		context = _context;
		mInflater = LayoutInflater.from(context);
		podcasts = new ArrayList<Podcast>();
		podcasts.addAll(mPodcast);

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return podcasts.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return podcasts.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return podcasts.size();
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = mInflater.inflate(R.layout.podcast_item, parent, false);
		((TextView) v.findViewById(R.id.textView1)).setText(podcasts.get(
				position).getNom());
		FrameLayout fview = (FrameLayout) v.findViewById(R.id.frameLayout1);
		fview.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				clickHandle(Uri.parse(podcasts.get(position).getUrl())
						.getQueryParameter("v"));
			}
		});

		return v;
	}

	public abstract void clickHandle(String urlVideo);
}
