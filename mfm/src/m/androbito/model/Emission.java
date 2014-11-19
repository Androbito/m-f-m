package m.androbito.model;

import com.google.gson.annotations.SerializedName;

public class Emission {

	@SerializedName("id")
	private String id_emission;

	@SerializedName("title")
	private String title_emission;

	@SerializedName("body")
	private String auditeur_emission;

	@SerializedName("imagepath")
	private String icon;

	@SerializedName("mytime")
	private String rdv;

	@SerializedName("description")
	private String description;

	public Emission(String id_emission, String title_emission,
			String auditeur_emission, String icon, String rdv,
			String description) {
		super();
		this.id_emission = id_emission;
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

	public String getId_emission() {
		return id_emission;
	}

	public void setId_emission(String id_emission) {
		this.id_emission = id_emission;
	}

	public String getIcon() {
		return "http://" + icon;
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
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
