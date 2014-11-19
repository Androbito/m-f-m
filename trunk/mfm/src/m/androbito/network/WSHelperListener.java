package m.androbito.network;

import java.util.List;

import m.androbito.model.Emission;
import m.androbito.model.Event;

public interface WSHelperListener {

	void onEmissionsLoaded(List<Emission> emissions);
	void onErrorLoadingEmissions(String error);
	
	void onEventsLoaded(List<Event> events);
	void onErrorLoadingEvents(String error);
}
