package m.androbito.network;

import java.util.List;

import m.androbito.model.Emission;
import m.androbito.model.Event;
import m.androbito.model.Podcast;

public interface WSHelperListener {

	void onEmissionsLoaded(List<Emission> emissions);
	void onErrorLoadingEmissions(String error);
	
	void onEventsLoaded(List<Event> events);
	void onErrorLoadingEvents(String error);
	
	void onPodcastsLoaded(List<Podcast> podcasts);
	void onErrorLoadingPodcasts(String error);
}
