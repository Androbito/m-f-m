package m.hiddentalent.mfm;

public class Emission {

	private String title_emission;
	private String auditeur_emission;
	private int icon;
	private String rdv;

	public Emission(String title_emission, String auditeur_emission, int icon,
			String rdv) {
		super();
		this.title_emission = title_emission;
		this.auditeur_emission = auditeur_emission;
		this.icon = icon;
		this.rdv = rdv;
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

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public String getRdv() {
		return rdv;
	}

	public void setRdv(String rdv) {
		this.rdv = rdv;
	}

}
