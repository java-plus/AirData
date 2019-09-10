package fr.diginamic.entites;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class MesurePollution {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	double valeur;
	String typeDeDonnee;
	String date;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "StationDeMesure_id")
	StationDeMesure stationDeMesure;
	
	
	
	
	public MesurePollution(double valeur, String typeDeDonnee, String date, StationDeMesure stationDeMesure) {
		super();
		this.valeur = valeur;
		this.typeDeDonnee = typeDeDonnee;
		this.date = date;
		
		this.stationDeMesure = stationDeMesure;
	}
	
	
	public double getValeur() {
		return valeur;
	}
	public void setValeur(double valeur) {
		this.valeur = valeur;
	}
	public String getTypeDeDonnee() {
		return typeDeDonnee;
	}
	public void setTypeDeDonnee(String typeDeDonnee) {
		this.typeDeDonnee = typeDeDonnee;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	

}
