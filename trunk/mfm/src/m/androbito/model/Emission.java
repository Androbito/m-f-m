package m.androbito.model;

import com.google.gson.annotations.SerializedName;

public class Emission {

	@SerializedName("item")
	private String title_emission;

	@SerializedName("animateur")
	private String auditeur_emission;

	@SerializedName("thumb")
	private String icon;

	@SerializedName("heure")
	private String rdv;

	@SerializedName("description")
	private String description;

	public Emission(String title_emission,
			String auditeur_emission, String icon, String rdv,
			String description) {
		super();
		this.title_emission = title_emission;
		this.auditeur_emission = auditeur_emission;
		this.icon = icon;
		this.rdv = rdv;
		this.description = description;
	}

	public String getTitle_emission() {
		return title_emission;
	}

	public void setTitle_emission(String title_emission) {
		this.title_emission = title_emission;
	}

	public String getAuditeur_emission() {
		return auditeur_emission;
	}

	public void setAuditeur_emission(String auditeur_emission) {
		this.auditeur_emission = auditeur_emission;
	}


	public String getIcon() {
		return "http://www.mfmradio.ma/uploads/animateurs/min/" + icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getRdv() {
		return rdv;
	}

	public void setRdv(String rdv) {
		this.rdv = rdv;
	}

	public String getDescription() {
		return ""+description;
	}

	public void setDescription(String description) {
		this.description = ""+description;
	}

}
