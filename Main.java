package com.company;

import Personne.*;

public class Main {

    public static void main(String[] args) throws CloneNotSupportedException {
	// write your code here

        /*Magasin SuperU = new Magasin("Super U Sarah Bernard", "14 Avenue Janvier, Bains-sur-Oust");
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

        Object copie = Chemise.clone();
        System.out.println("Copie = "+copie.toString());
        System.out.println(copie instanceof Article);
        Chemise.setQuantite(10);
        System.out.println("Copie = "+copie.toString());
        */

        Personne JCVD = new Personne("Jean-Claude", "Van Damme");
        JCVD.setNumSecu("160109932873406");
        CompteBanquaire compteJCVD = new CompteBanquaire("1", JCVD, "123", "456");
        System.out.println(JCVD.toString());
        System.out.println(JCVD.calculAge());
        compteJCVD.setDecouvertAutorise(100, "456");
        compteJCVD.versement(100, "123");

        Habit Kimono = new Habit("Kimono", 49.99, 0, "M");
        Primeur Magnesie = new Primeur("Magnesie", 18.40, 0);
        Electromenager Rameur = new Electromenager("Rameur", 299.00, 0, "220V");


        Magasin Decathlon = new Magasin("Decathlon", "Rue de l'Evêque 30, 1000 Bruxelles, Belgique");
        Decathlon.setNombreVendeurs(20);
        Decathlon.setCaisseMagasin(5000);
        Decathlon.addArticletoStock(Kimono);
        Decathlon.addArticletoStock(Magnesie);
        Decathlon.addArticletoStock(Rameur);
        Decathlon.addMoreToStock(Kimono, "4");
        Decathlon.addMoreToStock(Magnesie,"2");
        Decathlon.addMoreToStock(Rameur, "1");
        Decathlon.startSolde(Rameur, 30);
        Decathlon.publicite(Rameur);
        System.out.println(Decathlon.getStockArticle());


        compteJCVD.achatArticle(Decathlon, Kimono, "1");
        compteJCVD.achatArticle(Decathlon, Magnesie, "0.5");
        compteJCVD.achatArticle(Decathlon, Rameur, "2");

        System.out.println(Decathlon.getCaisseMagasin());
        System.out.println(compteJCVD.getSolde());
        System.out.println(compteJCVD.getPanier());

        Decathlon.stopSolde(Rameur);
        System.out.println(compteJCVD.getPanier());

        Alcool Whisky = new Alcool("Whisky Exception 18 ans d'age", 45.00,0, 0.5);
        Primeur Bonbon = new Primeur("Méli-Mélo de bonbons", 10, 0);

        Magasin CarrefourMarket = new Magasin("Carrefour Market", "Rue du Marché aux poulets");
        CarrefourMarket.setCaisseMagasin(1000);
        CarrefourMarket.addArticletoStock(Whisky);
        CarrefourMarket.addArticletoStock(Bonbon);
        CarrefourMarket.addMoreToStock(Whisky, "5");
        CarrefourMarket.addMoreToStock(Bonbon, "0.5");

        //JCVD boit un elexir de jouvence
        JCVD.setAge(15);
        System.out.println(JCVD.calculAge());

        compteJCVD.achatArticle(CarrefourMarket, Whisky, "1");

        //A défaut de pouvoir se détendre avec un doigt de whisky, JCVD force sur l'éléxir de jouvence.
        JCVD.setAge(9);
        //Soudain lui vient une folle envie de sucreries.
        compteJCVD.achatArticle(CarrefourMarket, Bonbon, "0.1");

        System.out.println(compteJCVD.getPanier());
        System.out.println(compteJCVD.getSolde());

    }
}
