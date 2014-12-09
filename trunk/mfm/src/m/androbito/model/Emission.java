package m.androbito.model;

import com.google.gson.annotations.SerializedName;

public class Emission {

	@SerializedName("item1")
	private String title_emission1;
	@SerializedName("item2")
	private String title_emission2;
	
	@SerializedName("animateur")
	private String auditeur_emission;

	@SerializedName("thumb")
	private String icon;

	@SerializedName("heure")
	private String rdv;

	@SerializedName("description")
	private String description;


	public String getTitle_emission() {
		return title_emission1+" "+title_emission2;
	}


	public String getAuditeur_emission() {
		return auditeur_emission;
	}

	public void setAuditeur_emission(String auditeur_emission) {
		this.auditeur_emission = auditeur_emission;
	}


	public String getIcon() {
		return icon;
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
