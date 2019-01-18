package application;

public class TableauTaxe {
private String  libele_taxe;
private double base;
private String taux;
private double montant;

public TableauTaxe() {
	
}
public String getLibele_taxe() {
	return libele_taxe;
}
public void setLibele_taxe(String libele_taxe) {
	this.libele_taxe = libele_taxe;
}
public double getBase() {
	return base;
}
public void setBase(double base) {
	this.base = base;
}
public String getTaux() {
	return taux;
}
public void setTaux(String string) {
	this.taux = string;
}
public double getMontant() {
	return montant;
}
public void setMontant(double montant) {
	this.montant = montant;
}


}
