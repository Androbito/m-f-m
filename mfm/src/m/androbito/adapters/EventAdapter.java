package m.androbito.adapters;

import java.util.ArrayList;
import java.util.List;

import m.androbito.mfm.R;
import m.androbito.model.Event;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class EventAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<Event> events;

	public EventAdapter(Context _context, List<Event> mEvents) {
		mInflater = LayoutInflater.from(_context);
		events = new ArrayList<Event>();
		events.addAll(mEvents);

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return events.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return events.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return events.size();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = mInflater.inflate(R.layout.event_item, parent, false);
		((TextView) v.findViewById(R.id.textView1)).setText(events
				.get(position).getTitle_event());
		((TextView) v.findViewById(R.id.textView2)).setText(events
				.get(position).getTime());
		((TextView) v.findViewById(R.id.textView3)).setText(events
				.get(position).getCity());
		return v;
	}

}
