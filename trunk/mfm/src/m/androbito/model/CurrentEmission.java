package m.androbito.model;

import com.google.gson.annotations.SerializedName;

public class CurrentEmission {

	@SerializedName("emission")
	private String title_emission;
	@SerializedName("par")
	private String personne_emission;
	@SerializedName("photo")
	private String url_img_emission;
	@SerializedName("debut")
	private String debut_emission;
	@SerializedName("fin")
	private String fin_emission;

	public String getTitle_emission() {
		return title_emission;
	}

	public void setTitle_emission(String title_emission) {
		this.title_emission = title_emission;
	}

	public String getPersonne_emission() {
		return personne_emission;
	}

	public void setPersonne_emission(String personne_emission) {
		this.personne_emission = personne_emission;
	}

	public String getUrl_img_emission() {
		return url_img_emission;
	}

	public void setUrl_img_emission(String url_img_emission) {
		this.url_img_emission = url_img_emission;
	}

	public String getDebut_emission() {
		return debut_emission;
	}

	public void setDebut_emission(String debut_emission) {
		this.debut_emission = debut_emission;
	}

	public String getFin_emission() {
		return fin_emission;
	}

	public void setFin_emission(String fin_emission) {
		this.fin_emission = fin_emission;
	}

}
