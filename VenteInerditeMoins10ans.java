package Personne;

public class VenteInerditeMoins10ans extends Exception {
    public VenteInerditeMoins10ans(){
        System.out.println("La maman du petit Donacien est attendu à la caisse centrale, merci ! La maman du petit Donacien");
    }
    public VenteInerditeMoins10ans(String message){
        super(message);
        message="\"La maman du petit Donacien est attendu à la caisse centrale, merci ! La maman du petit Donacien\"";
    }
}
