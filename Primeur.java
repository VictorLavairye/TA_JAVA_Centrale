package com.company;

import java.util.Scanner;

public class Primeur extends Article implements IPublicite, IVendreKilo, toString, IPrix, Cloneable {

    public Primeur(String nom, double prixunitaire, double poids){
        super(nom,prixunitaire);
        this.Poids=poids;
    }

    public String toString(){
        return this.nomArticle+" "+this.prixUnitaire+" "+this.Poids;
    }
    public String toStringArticle(){
        return this.nomArticle+" "+this.prixUnitaire;
    }

    public void publicite(){
        System.out.println("Mesdames Messieurs bonjour, aujourd'hui dans votre enseigne de distribution preferee " +
                "le kilo de "+this.nomArticle+" est à "+this.prixUnitaire+" euros. Une aubaine comme rarement" +
                "on en a vu ailleurs");
    }

    public void vendreKilo(double poidsVente) {
        if (poidsVente > this.Poids) {
            System.out.println("Il n'y a que " + this.Poids + " kilo(s) de disponible(s), je vous le met quand-même ?");
            Scanner sc = new Scanner(System.in);
            String reponse =sc.nextLine();
            if(reponse.compareTo("oui")==0){
                System.out.println("Et voila pour vous, bonne journée");
                this.Poids=0;
            }
            else{
                System.out.println("Pas de probleme, bonne journée");
            }
        } else {
            this.Poids -= poidsVente;
        }
    }
    public void rembourserKilo(double poidsRemboursement){
        System.out.println("Les produits du rayon primeur ne sont pas remboursables");
    }

    public double prix(String quantite){
        double poids = Double.parseDouble(quantite);
        return this.prixUnitaire * poids;
    }

    //public Primeur clone() throws CloneNotSupportedException{
        //Primeur article = (Primeur) super.clone();
        //return article;
    //}
}
