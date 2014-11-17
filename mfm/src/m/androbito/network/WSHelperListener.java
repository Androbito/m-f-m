package m.androbito.network;

import java.util.List;

import m.androbito.model.Emission;

public interface WSHelperListener {

	void onEmissionsLoaded(List<Emission> emissions);
	void onErrorLoadingEmissions(String error);
}
