package com.company;

public class PasEntre0et100 extends Exception {
    public PasEntre0et100(){
        System.out.println("Des produits moins chers que gratuit, ça c'est des soldes !!\nTu veux couler la boite ? " +
                "Non... Bah change moi-ça du coup.");
    }
    public PasEntre0et100(String message){
        super(message);
        message="Des produits moins chers que gratuit, ça c'est des soldes !!\\n Tu veux couler la boite ? \" +\n" +
                "                \"Non... Bah change moi-ça du coup.";
    }

}
