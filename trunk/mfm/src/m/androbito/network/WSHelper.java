package m.androbito.network;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import m.androbito.model.Emission;
import m.androbito.model.Event;
import android.app.Activity;
import android.net.ConnectivityManager;
import android.util.Log;

import com.androbito.web.WebException;
import com.androbito.web.WebListener;
import com.androbito.web.WebThread;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class WSHelper {

	private static WSHelper instance;
	private Gson gson = new Gson();

	public static WSHelper getInstance() {
		if (instance == null) {
			instance = new WSHelper();
		}
		return instance;
	}

	private Set<WSHelperListener> wsHelperListeners;

	private WSHelper() {
		wsHelperListeners = new HashSet<WSHelperListener>();
	}

	public void getEmissions(String url, ConnectivityManager manager,
			final Activity context) {

		Map<String, String> params = new HashMap<String, String>();
		params.put("page", "1");
		WebThread wt = new WebThread(url, WebThread.METHOD_POST, params,
				manager, WebThread.ENCODING_UTF_8, false);
		wt.setListener(new WebListener() {

			@Override
			public void onFinish(String url, String result) {
				Log.i("Response", "" + result);

				List<Emission> emissionsList = gson.fromJson(result,
						new TypeToken<ArrayList<Emission>>() {
						}.getType());
				for (WSHelperListener wsHelperListener : wsHelperListeners)
					wsHelperListener.onEmissionsLoaded(emissionsList);
			}

			@Override
			public void onError(WebException exception) {
				exception.printStackTrace();
				for (WSHelperListener wsHelperListener : wsHelperListeners)
					wsHelperListener.onErrorLoadingEmissions(exception
							.toString());
			}
		});
		wt.start();
	}

	public void getEvents(String url, ConnectivityManager manager,
			final Activity context) {

		Map<String, String> params = new HashMap<String, String>();
		params.put("tag", "getevents");
		WebThread wt = new WebThread(url, WebThread.METHOD_POST, params,
				manager, WebThread.ENCODING_UTF_8, false);
		wt.setListener(new WebListener() {

			@Override
			public void onFinish(String url, String result) {
				Log.i("Response", "" + result);

				List<Event> eventsList = gson.fromJson(result,
						new TypeToken<ArrayList<Event>>() {
						}.getType());
				for (WSHelperListener wsHelperListener : wsHelperListeners)
					wsHelperListener.onEventsLoaded(eventsList);
			}

			@Override
			public void onError(WebException exception) {
				exception.printStackTrace();
				for (WSHelperListener wsHelperListener : wsHelperListeners)
					wsHelperListener.onErrorLoadingEvents(exception.toString());
			}
		});
		wt.start();
	}

	public void addWSHelperListener(WSHelperListener listener) {
		wsHelperListeners.add(listener);
	}

	public void removeWSHelperListener(WSHelperListener listener) {
		wsHelperListeners.remove(listener);
	}
}
