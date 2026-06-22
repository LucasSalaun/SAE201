public class Artiste extends Intervenant {
    
    public Artiste(String nom) {
        super(nom);
    }

    @Override
    public String toString() {
        return getNom();
    }
}
