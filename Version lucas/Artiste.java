public class Artiste extends Intervenant {
    private String instrument;

    public Artiste(String nom, String instrument) {
        super(nom);
        this.instrument = instrument;
    }

    public String getInstrument() { return instrument; }
    public void setInstrument(String instrument) { this.instrument = instrument; }
}