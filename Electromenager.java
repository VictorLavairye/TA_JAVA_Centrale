package com.company;

import java.util.Scanner;

public class Electromenager extends Article implements IPublicite, ISolde, IVendrePiece, toString {
    private String Voltage;
    private boolean discounted;
    private int tauxSolde;

    public Electromenager(String nom, double prixunitaire, int quantite, String voltage) {
        super(nom, prixunitaire);
        this.Quantite = quantite;
        this.Voltage = voltage;
        this.tauxSolde=0;
    }

    public String toString(){
        return this.nomArticle+" "+this.prixUnitaire+" "+this.Quantite+" "+this.Voltage+" discounted="
                +this.discounted+" "+this.tauxSolde;
    }
    public String toStringArticle(){
        return this.nomArticle+" "+this.prixUnitaire;
    }

    public void publicite() {
        String strPub="Mesdames Messieurs bonjour, aujourd'hui dans votre enseigne de distribution preferee " +
                "les " + this.nomArticle + " sont à " + this.prixUnitaire + " euros l'unité. Une aubaine comme rarement" +
                "on en a vu ailleurs";
        if(this.discounted){
            strPub+="\nProfitez aujourd'hui d'une remise exeptionelle de "+this.tauxSolde+"%";
        }
        System.out.println(strPub);
    }

    public void setTauxSolde(int tauxsolde){
        this.tauxSolde=tauxsolde;
    }
    public int getTauxSolde(){
        return this.tauxSolde;
    }
    public void startSolde() {
        this.discounted=true;
    }
    public void stopSolde(){
        this.tauxSolde=0;
        this.discounted=false;
    }

    public void vendrePiece(int quantiteVente) {
        if (quantiteVente > this.Quantite){
            System.out.println("Il n'y a que " + this.Quantite + " pièce(s) de disponible(s), les prenez-vous tout de même ?");
            Scanner sc = new Scanner(System.in);
            String reponse = sc.nextLine();
            if (reponse.compareTo("oui") == 0) {
                System.out.println("Et voila pour vous, bonne journée");
                this.Quantite = 0;
            } else {
                System.out.println("Pas de probleme, bonne journée");
            }
        } else {
            this.Quantite -= quantiteVente;
        }
    }
    public void rembourserPiece(int quantiteVendu){
        setQuantite(this.Quantite+quantiteVendu);
    }

    public double prix(String quantite){
        int unit = Integer.parseInt(quantite);
        return this.prixUnitaire * (100 - this.tauxSolde) * unit  / 100;
    }
}

