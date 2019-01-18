package application;
public class Article {
	private int id;
	 private String designation;
	 private double longueur;
	 private double largeur;
	 private int quantite;
	 private double prix1metre;
	 private String formule;
	 private double totalHorsTaxe;
	 private double totalTTC;
	 public Article() {
		 
	 }
	public Article(int id, String designation, double longueur, double largeur, int quantite, double prix1metre,
			String formule, double totalHorsTaxe, double totalTTC) {
		this.id = id;
		this.designation = designation;
		this.longueur = longueur;
		this.largeur = largeur;
		this.quantite = quantite;
		this.prix1metre = prix1metre;
		this.formule = formule;
		this.totalHorsTaxe = totalHorsTaxe;
		this.totalTTC = totalTTC;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public double getLongueur() {
		return longueur;
	}
	public void setLongueur(double longueur) {
		this.longueur = longueur;
	}
	public double getLargeur() {
		return largeur;
	}
	public void setLargeur(double largeur) {
		this.largeur = largeur;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public double getPrix1metre() {
		return prix1metre;
	}
	public void setPrix1metre(double prix1metre) {
		this.prix1metre = prix1metre;
	}
	public String getFormule() {
		return formule;
	}
	public void setFormule(String formule) {
		this.formule = formule;
	}
	public double getTotalHorsTaxe() {
		return totalHorsTaxe;
	}
	public void setTotalHorsTaxe(double totalHorsTaxe) {
		this.totalHorsTaxe = totalHorsTaxe;
	}
	public double getTotalTTC() {
		return totalTTC;
	}
	public void setTotalTTC(double totalTTC) {
		this.totalTTC = totalTTC;
	}

}
