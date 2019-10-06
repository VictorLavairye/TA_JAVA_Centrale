package com.company;

import static java.lang.Double.parseDouble;

public class Magasin {
    private String nomMagasin;
    private String adresseMagasin;
    private int nombreVendeurs;
    private double caisseMagasin;
    private Article[] stockArticle;
    private int compteur;

    public Magasin(String nom, String adresse) {
        this.nomMagasin = nom;
        this.adresseMagasin = adresse;
        this.nombreVendeurs = 1;
        this.caisseMagasin = 0;
        this.stockArticle = new Article[50];
        this.compteur = 0;
    }

    public void setNombreVendeurs(int nombreVendeurs) {
        this.nombreVendeurs = nombreVendeurs;
    }

    public void setCaisseMagasin(double caisseMagasin) {
        this.caisseMagasin = caisseMagasin;
    }

    public String toString(){
        return this.nomMagasin+" "+this.adresseMagasin;
    }
    public double getCaisseMagasin() {
        return this.caisseMagasin;
    }
    public String getStockArticle() {
        String strStockArticle="\nStock du "+this.nomMagasin+" "+this.adresseMagasin+"\n";
        for(int i=0;i<=this.compteur-1;i++){
            strStockArticle+=this.stockArticle[i].toString()+"\n";
        }
        return strStockArticle;
    }
    public Article getArticleInStock(int emplacement){
        return stockArticle[emplacement];
    }


    public void addArticletoStock(Article article) {
        if (this.compteur > 0) {
            try {
                for (int i = 0; i <= this.compteur - 1; i++) {
                    if (article.toStringArticle().compareTo(stockArticle[i].toStringArticle()) == 0) {
                        throw new ArticleDejaEnStock();
                    }
                }
                this.stockArticle[this.compteur] = article;
                this.compteur++;
            } catch (ArticleDejaEnStock articleDejaEnStock) {
                System.out.println("Article déjà en stock");
            }
        } else {
            stockArticle[compteur] = article;
            compteur++;
        }
    }

    public int isArticleInStock(Article article){
        int inStock = - 1;
        String strTest = article.toStringArticle();
        int iter = 0;
        while (iter <= this.compteur - 1){
            if (strTest.compareTo(this.stockArticle[iter].toStringArticle()) == 0){
                inStock = iter;
                break;
            }
            iter++;
        }
        return inStock;
    }


    public void addMoreToStock(Article article, String quantiteAjoutee) {
        String strTest = article.toStringArticle();
        int iter = 0;
        while (iter <= this.compteur - 1){
            if (strTest.compareTo(this.stockArticle[iter].toStringArticle()) == 0){
                if(article.getQuantite() == -1){
                    double poidsAjoute = Double.parseDouble(quantiteAjoutee);
                    if (this.caisseMagasin - 0.5 * article.getPrixUnitaire() * poidsAjoute < 0){
                        System.out.println(this.nomMagasin+" "+this.adresseMagasin+" n'a pas les fonds nécéssaires " +
                                "pour acheter "+poidsAjoute+" kilos de "+article.toStringArticle());
                        break;
                    }
                    else {
                        this.caisseMagasin -= 0.5 * article.getPrixUnitaire() * poidsAjoute;
                        this.stockArticle[iter].setPoids(this.stockArticle[iter].getPoids()+poidsAjoute);
                        System.out.println(poidsAjoute+ " kilos ont été ajouté au stock");
                        break;
                    }
                }
                else {
                    int unitAjoute = Integer.parseInt(quantiteAjoutee);
                    if (this.caisseMagasin - 0.5 * article.getPrixUnitaire() * unitAjoute < 0){
                        System.out.println(this.nomMagasin+" "+this.adresseMagasin+" n'a pas les fonds nécéssaires " +
                                "pour acheter "+unitAjoute+" unités de "+article.toStringArticle());
                        break;
                    }
                    else {
                        this.caisseMagasin -= 0.5 * article.getPrixUnitaire() * unitAjoute;
                        this.stockArticle[iter].setQuantite(this.stockArticle[iter].getQuantite() + unitAjoute);
                        System.out.println(unitAjoute + " unités ont été ajouté au stock");
                        break;
                    }
                }
            }
            iter++;
        }
        if (iter == this.compteur){
            System.out.println(article.toStringArticle()+" n'appartient pas au stock");
        }
    }


    public void publicite(Article article) {
        String strTest = article.toStringArticle();
        int iter = 0;
        while(iter <= this.compteur - 1) {
            if (strTest.compareTo(this.stockArticle[iter].toStringArticle()) == 0) {
                    ((IPublicite) article).publicite();
                    break;
            }
            iter++;
        }
        if(iter==this.compteur){
            System.out.println(article.toStringArticle()+" n'appartient pas au stock");
        }
    }

    public void startSolde(Article article,int tauxSolde){
        String strTest = article.toStringArticle();
        int iter = 0;
        while (iter<= this.compteur - 1) {
            if (strTest.compareTo(this.stockArticle[iter].toStringArticle()) == 0) {
                try {
                    if(article instanceof Primeur){
                        throw new Exception();
                    }
                    if(tauxSolde<0 || tauxSolde>100){
                        throw new PasEntre0et100();
                    }
                } catch (PasEntre0et100 pasEntre0et100) {
                    pasEntre0et100.getMessage();
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
                ((ISolde) this.stockArticle[iter]).setTauxSolde(tauxSolde);
                ((ISolde) this.stockArticle[iter]).startSolde();
                break;
            }
            iter++;
        }
        if(iter==this.compteur){
            System.out.println(article.toStringArticle()+" n'appartient pas au stock");
        }
    }

    public void stopSolde(Article article){
        String strTest = article.toStringArticle();
        int iter = 0;
        while (iter<= this.compteur - 1) {
            if (strTest.compareTo(this.stockArticle[iter].toStringArticle()) == 0) {
                try {
                    if(article instanceof Primeur){
                        throw new Exception();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
                ((ISolde) this.stockArticle[iter]).stopSolde();
                break;
            }
            iter++;
        }
        if(iter==this.compteur){
            System.out.println(article.toStringArticle()+" n'appartient pas au stock");
        }
    }

    public void venteArticle(Article article, String quantiteVente){
        String strTest = article.toStringArticle();
        int iter = 0;
        while (iter<= this.compteur - 1) {
            if (strTest.compareTo(this.stockArticle[iter].toStringArticle()) == 0) {
                if(article.getQuantite()==-1){
                    double poidsMemo = article.getPoids();
                    double poidsVente = Double.parseDouble(quantiteVente);
                    ((IVendreKilo) this.stockArticle[iter]).vendreKilo(poidsVente);
                    double poidsVendu = poidsMemo-article.getPoids();
                    if(article instanceof Primeur){
                        this.caisseMagasin += poidsVendu * article.getPrixUnitaire();
                        break;
                    }
                    else {
                        this.caisseMagasin += poidsVendu * article.getPrixUnitaire() * (100 - ((ISolde) article).getTauxSolde()) / 100;
                        break;
                    }
                }
                else{
                    int unitMemo = article.getQuantite();
                    int unitVente = Integer.parseInt(quantiteVente);
                    ((IVendrePiece) this.stockArticle[iter]).vendrePiece(unitVente);
                    int unitVendu = unitMemo-article.getQuantite();
                    this.caisseMagasin += unitVendu * article.getPrixUnitaire() * (100 - ((ISolde) article).getTauxSolde()) / 100 ;
                    break;
                }
            }
            iter++;
        }
        if(iter==this.compteur){
            System.out.println(article.toStringArticle()+"n'appartient pas au stock");
        }
    }

    public void remboursementArticle(Article article, String strRemboursement){
        String strTest = article.toStringArticle();
        int iter = 0;
        while (iter <= this.compteur - 1) {
            if (strTest.compareTo(this.stockArticle[iter].toStringArticle()) == 0) {
                if (article.getQuantite()==-1) {
                    double poidsRemboursement = Double.parseDouble(strRemboursement);
                    double poidsMemo = this.stockArticle[iter].getPoids();
                    ((IVendreKilo) this.stockArticle[iter]).rembourserKilo(poidsRemboursement);
                    this.caisseMagasin-=(this.stockArticle[iter].getPoids() - poidsMemo) * article.getPrixUnitaire();
                    break;
                }
                else {
                    int unitRemboursement = Integer.parseInt(strRemboursement);
                    int unitMemo = this.stockArticle[iter].getQuantite();
                    ((IVendrePiece) this.stockArticle[iter]).rembourserPiece(unitRemboursement);
                    this.caisseMagasin-=(this.stockArticle[iter].getQuantite() - unitMemo) * article.getPrixUnitaire()
                            * ((ISolde) article).getTauxSolde();
                    //On applique rembourser() à l'article en stock afin de ne pas avoir à traiter les soldes etc.
                    break;
                }
            }
            iter++;
        }
        if (iter == this.compteur) {
            System.out.println(article.toStringArticle()+" n'appartient pas au stock");
        }
    }
}

