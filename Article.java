package com.company;

 public class Article implements toString, Cloneable{
    protected String nomArticle;
    protected double prixUnitaire;
    protected double Poids;
    protected int Quantite;

    protected Article(String nomarticle, double prixunitaire){
        this.nomArticle=nomarticle;
        this.prixUnitaire=prixunitaire;
        this.Poids=-1;
        this.Quantite=-1;
    }

    public String toString(){
        return this.nomArticle+" "+this.prixUnitaire+" "+this.Poids+" "+this.Quantite;
    }
    public String toStringArticle(){
        return this.nomArticle+" "+this.prixUnitaire;
    }

    public double getPrixUnitaire(){
        return this.prixUnitaire;
    }
    public double getPoids(){
        return this.Poids;
    }
    public int getQuantite(){
        return this.Quantite;
    }

    public void setPoids(double poids){
        this.Poids=poids;
    }
    public void setQuantite(int quantite){
        this.Quantite=quantite;
    }

    public Object clone() throws CloneNotSupportedException {
        Object article = null;
        article = (Object) super.clone();
        return article;
    }
}
