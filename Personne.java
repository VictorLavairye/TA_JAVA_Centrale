package Personne;

import com.company.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class Personne {

    private String Nom;
    private String Prenom;
    private String NumSecu;
    private String adresse;
    private int anneeNaissance;
    private char sexe;
    private int deptNaissance;


    public Personne(String nom, String prenom){
        this();
        this.Nom = nom;
        this.Prenom = prenom;
    }

    public Personne(String nom, String prenom, String numerosecu, String adresse) {
        this.Nom = nom;
        this.Prenom = prenom;
        this.adresse = adresse;
        this.NumSecu = numerosecu;
        this.sexe = this.sexe(this.NumSecu);
        this.anneeNaissance = this.anneeNaissance(this.NumSecu);
        this.deptNaissance = this.deptNaissance(this.NumSecu);
    }
    private Personne(){
        NumSecu = "N/A";
        anneeNaissance = 0;
        sexe = '?';
        deptNaissance = 0;
    }

    private int anneeNaissance(String NumSecu){
        return 1900+Integer.parseInt(NumSecu.substring(1,3));
    }

    private char sexe(String NumSecu){
        int sexe = NumSecu.charAt(0);
        if (sexe == 1){return 'M';}
        if (sexe == 2){return 'F';}
        return '?';
    }

    private int deptNaissance(String NumSecu){
        return Integer.parseInt(NumSecu.substring(5,7));
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public String getNumSecu() {
        return NumSecu;
    }

    public void setNumSecu(String numSecu) {
        NumSecu = numSecu;
        sexe = sexe(NumSecu);
        deptNaissance = deptNaissance(NumSecu);
        anneeNaissance = anneeNaissance(NumSecu);
    }


    private String getDateTime(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }
    public int getMoisNaissance(){
        return Integer.parseInt(this.NumSecu.replaceAll("\\s", "").substring(3,5));
    }
    public int calculAge(){
        String dateTime=getDateTime();
        dateTime=dateTime.replaceAll("-","");
        int anneeActuelle = Integer.parseInt(dateTime.substring(0,4));
        int moisActuel = Integer.parseInt(dateTime.substring(4,6));
        if(moisActuel<getMoisNaissance()){
            return anneeActuelle-this.anneeNaissance-1;
        }
        else{
            return anneeActuelle-this.anneeNaissance;
        }
    }

    @Override
    public String toString() {
        String str = "Nom:"+Nom+", Prenom:"+Prenom;
        if (NumSecu != "N/A"){str += ", NumSecu:"+NumSecu;}
        if (anneeNaissance != 0){str += ", Année de Naissance:"+ anneeNaissance;}
        if (sexe != '?'){str += ", sexe:"+ sexe;}
        if (deptNaissance != 0){ str+= ", Département de naissance:" + deptNaissance;}
        return str;
    }
}
