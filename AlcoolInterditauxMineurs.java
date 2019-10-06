package Personne;

public class AlcoolInterditauxMineurs extends Exception {
    public AlcoolInterditauxMineurs(){
        System.out.println("La vente d'alcool est interdit aux mineurs");
    }
    public AlcoolInterditauxMineurs(String message){
        super(message);
        message="La vente d'alcool est interdit aux mineurs";
    }
}
