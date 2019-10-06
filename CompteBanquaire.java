package Personne;

import com.company.*;
import java.util.Scanner;

public class CompteBanquaire {

    private String num;
    private Personne client;
    private double solde;
    private double decouvertAutorise;
    private String code1;
    private String code2;
    private Article [] panierArticle;
    private int compteur;

    public CompteBanquaire(String num, Personne client, String code1, String code2){

        this.num = num;
        this.client = client;
        this.code1 = code1;
        this.code2 = code2;

        this.solde = 0;
        this.decouvertAutorise = 0;

        this.panierArticle = new Article[20];
        this.compteur = 0;
    }

    public String getNum() {
        return num;
    }

    public Personne getClient() {
        return client;
    }

    public double getSolde() {
        return solde;
    }

    public double getDecouvertAutorise() {
        return decouvertAutorise;
    }

    public void setSolde(double solde, String code) {
        if(this.code1 == code) {
            this.solde = solde;
        }
    }

    public void setDecouvertAutorise(double decouvertAutorise, String code) {
        if(this.code2 == code) {
            this.decouvertAutorise = decouvertAutorise;
        }
    }

    public void versement(double montant, String code){
        if (this.code1 == code && montant >= 0){
            this.solde += montant;
            System.out.println("Nouveau solde: " + this.solde);
        }
        else {
            System.out.println("Solde inchangé");
        }
    }

    public void retrait(double montant, String code){
        if (this.code1 == code && montant >= 0){
            if (montant <= this.solde + this.decouvertAutorise){
                this.solde -= montant;
                System.out.println("Opération effectué. Nouveau solde: " + this.solde);
            }
            else{
                System.out.println("Votre solde et votre niveau de découvert autorisé ne vous permet pas d'effectuer cette opération");
            }
        }
        else{
            System.out.println("Impossible d'effectuer l'opération");
        }
    }

    @Override
    public String toString() {
        return "Client:"+ this.client.getNom() + ',' + this.client.getPrenom()
                + ". Solde:" + this.getSolde() + "Decouvert autorisé:" + this.getDecouvertAutorise();
    }

    public String getPanier(){
        String strPanierArticle="\nPanier de "+this.client.getPrenom()+" "+this.client.getNom()+"\n";
        for(int i=0;i<=this.compteur-1;i++){
            strPanierArticle += this.panierArticle[i].toString()+"\n";
        }
        return strPanierArticle;
    }

    public void achatArticle(Magasin magasin, Article article,String quantiteAchat) throws CloneNotSupportedException{
        if (magasin.isArticleInStock(article) != - 1){
            try{
                if (this.client.calculAge()<10) {
                    throw new VenteInerditeMoins10ans();
                }
                if (article instanceof Alcool && this.client.calculAge()<18) {
                    throw new AlcoolInterditauxMineurs();
                }
                System.out.println("Veuillez introduire votre code (1) pour le paiement de "+article.toStringArticle());
                Scanner sc = new Scanner(System.in);
                String reponse = sc.nextLine();
                if (reponse.compareTo(this.code1) > 0) {
                    throw new CodeIncorrect();
                }
                double prixAchat = ((IPrix) article).prix(quantiteAchat);
                if (prixAchat>this.solde+this.decouvertAutorise){
                    throw new RessourceFinanciereInsuffisante();
                }
                double poidsMemo = magasin.getArticleInStock(magasin.isArticleInStock(article)).getPoids();
                int unitMemo = magasin.getArticleInStock(magasin.isArticleInStock(article)).getQuantite();
                magasin.venteArticle(article, quantiteAchat);
                double poidsTest = magasin.getArticleInStock(magasin.isArticleInStock(article)).getPoids();
                int unitTest = magasin.getArticleInStock(magasin.isArticleInStock(article)).getQuantite();
                //On regarde la distance produit sur IN x IR pour la norme 1
                if (poidsMemo - poidsTest == 0 && unitMemo - unitTest == 0) {
                    System.out.println("Achat annulé");
                }
                else{
                    String strQuantiteAchat;
                    if (poidsMemo - poidsTest == 0){
                        strQuantiteAchat = Integer.toString(unitMemo-unitTest);
                    }
                    else{
                        strQuantiteAchat = Double.toString(poidsMemo - poidsTest);
                    }
                    retrait(((IPrix) article).prix(strQuantiteAchat),this.code1);
                    if (magasin.getArticleInStock(magasin.isArticleInStock(article)).getQuantite() == -1){
                        magasin.getArticleInStock(magasin.isArticleInStock(article)).setPoids(poidsMemo - poidsTest);
                        Object articlePanier = magasin.getArticleInStock(magasin.isArticleInStock(article)).clone();
                        panierArticle[this.compteur] = (Article) articlePanier;
                        this.compteur += 1;
                        magasin.getArticleInStock(magasin.isArticleInStock(article)).setPoids(poidsTest);
                    }
                    else {
                        magasin.getArticleInStock(magasin.isArticleInStock(article)).setQuantite(unitMemo - unitTest);
                        Object articlePanier = magasin.getArticleInStock(magasin.isArticleInStock(article)).clone();
                        panierArticle[this.compteur] = (Article) articlePanier;
                        this.compteur += 1;
                        magasin.getArticleInStock(magasin.isArticleInStock(article)).setQuantite(unitTest);
                    }
                }
            }
            catch (RessourceFinanciereInsuffisante ressourceFinanciereInsuffisante){
                ressourceFinanciereInsuffisante.getMessage();
            }
            catch (CodeIncorrect codeIncorrect){
                codeIncorrect.getMessage();
            }
            catch (AlcoolInterditauxMineurs alcoolInterditauxMineurs){
                alcoolInterditauxMineurs.getMessage();
            }
            catch (VenteInerditeMoins10ans venteInerditeMoins10ans){
                venteInerditeMoins10ans.getMessage();
            }
        }
        else{
            System.out.println(article.toStringArticle()+" n'appartient pas au stock de" +magasin.toString());
        }

    }
}
