package m.androbito.model;

import com.google.gson.annotations.SerializedName;

public class Event {

	@SerializedName("id")
	private String id_event;

	@SerializedName("title")
	private String title_event;

	@SerializedName("city")
	private String city;

	@SerializedName("mytime")
	private String time;

	public String getId_event() {
		return id_event;
	}

	public void setId_event(String id_event) {
		this.id_event = id_event;
	}

	public String getTitle_event() {
		return title_event;
	}

	public void setTitle_event(String title_event) {
		this.title_event = title_event;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Event(String id_event, String title_event, String city, String time) {
		super();
		this.id_event = id_event;
		this.title_event = title_event;
		this.city = city;
		this.time = time;
	}

	
}
