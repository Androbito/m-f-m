package m.androbito.model;

import com.google.gson.annotations.SerializedName;

public class Podcast {
	@SerializedName("nom")
	private String nom;
	@SerializedName("fichier")
	private String url;
	@SerializedName("thumb")
	private String img;

	public Podcast(String nom, String url, String img) {
		super();
		this.nom = nom;
		this.url = url;
		this.img = img;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

}
