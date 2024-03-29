package m.androbito.model;

import com.google.gson.annotations.SerializedName;

public class Event {

	@SerializedName("id")
	private String id_event;

	@SerializedName("nom")
	private String title_event;

	@SerializedName("adresse")
	private String city;

	@SerializedName("date_debut")
	private String time;

	@SerializedName("description")
	private String detail;

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

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Event(String id_event, String title_event, String city, String time,
			String detail) {
		super();
		this.id_event = id_event;
		this.title_event = title_event;
		this.city = city;
		this.time = time;
		this.detail = detail;
	}

}
