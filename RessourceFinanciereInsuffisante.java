package Personne;

public class RessourceFinanciereInsuffisante extends Exception {
    public RessourceFinanciereInsuffisante(){
        System.out.println("Ressource financière insuffisante pour cet achat");
    }
    public RessourceFinanciereInsuffisante(String message){
        super(message);
        message="\"Ressouce financière insuffisante pour cet achat\"";
    }
}
