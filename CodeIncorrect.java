package Personne;

public class CodeIncorrect extends Exception {
    public CodeIncorrect(){
        System.out.println("Le code (1) est incorrect, transaction impossible");
    }
    public CodeIncorrect(String message){
        super(message);
        message="\"Le code (1) est incorrect, transaction impossible\"";
    }
}
