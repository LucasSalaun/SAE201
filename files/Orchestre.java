public class Orchestre extends Intervenant {


    public Orchestre(String nom) {
        super(nom);
    }

    @Override
    public String toString() {
        return getNom();
    }
}
