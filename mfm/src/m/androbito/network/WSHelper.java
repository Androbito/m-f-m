package m.androbito.network;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import m.androbito.model.CurrentEmission;
import m.androbito.model.Emission;
import m.androbito.model.Event;
import m.androbito.model.Podcast;
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
		params.put("page", "2");
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

	public void getPodcasts(String url, ConnectivityManager manager,
			final Activity context) {

		Map<String, String> params = new HashMap<String, String>();
		params.put("page", "3");
		WebThread wt = new WebThread(url, WebThread.METHOD_POST, params,
				manager, WebThread.ENCODING_UTF_8, false);
		wt.setListener(new WebListener() {

			@Override
			public void onFinish(String url, String result) {
				Log.i("Response", "" + result);

				List<Podcast> podcastsList = gson.fromJson(result,
						new TypeToken<ArrayList<Podcast>>() {
						}.getType());
				for (WSHelperListener wsHelperListener : wsHelperListeners)
					wsHelperListener.onPodcastsLoaded(podcastsList);
			}

			@Override
			public void onError(WebException exception) {
				exception.printStackTrace();
				for (WSHelperListener wsHelperListener : wsHelperListeners)
					wsHelperListener.onErrorLoadingPodcasts(exception
							.toString());
			}
		});
		wt.start();
	}

	public void getCurrentEmission(String url, ConnectivityManager manager,
			final Activity context) {

		Map<String, String> params = new HashMap<String, String>();
		params.put("page", "4");
		WebThread wt = new WebThread(url, WebThread.METHOD_POST, params,
				manager, WebThread.ENCODING_UTF_8, false);
		wt.setListener(new WebListener() {

			@Override
			public void onFinish(String url, String result) {
				Log.i("Response", "" + result);

				List<CurrentEmission> currentEmissionList = gson.fromJson(
						result, new TypeToken<ArrayList<CurrentEmission>>() {
						}.getType());
				for (WSHelperListener wsHelperListener : wsHelperListeners)
					wsHelperListener
							.onCurrentEmissionLoaded(currentEmissionList.get(0));
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
