package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Magasin SuperU = new Magasin("Super U Sarah Bernard", "14 Avenue Janvier, Bains-sur-Oust");
        SuperU.setNombreVendeurs(5);

        Primeur Carotte = new Primeur("Carotte", 2.45, 0);
        Habit Chemise = new Habit("Chemise", 15, 0, "M");
        Electromenager Bouilloire = new Electromenager("Bouilloir", 24.99, 0, "240V");

        SuperU.addArticletoStock(Carotte);
        SuperU.addArticletoStock(Chemise);
        SuperU.addArticletoStock(Bouilloire);

        System.out.println(SuperU.getStockArticle());

        SuperU.addArticletoStock(Carotte);

        SuperU.setCaisseMagasin(100);
        SuperU.addMoreToStock(Carotte,"4.5");
        SuperU.venteArticle(Carotte, "4.5");
        System.out.println(SuperU.getCaisseMagasin());

        SuperU.startSolde(Chemise,20);
        SuperU.publicite(Chemise);
        SuperU.startSolde(Bouilloire,40);
        SuperU.stopSolde(Chemise);
        System.out.println(SuperU.getStockArticle());
    }
}
