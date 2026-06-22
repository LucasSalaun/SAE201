public class Orchestre extends Intervenant {
    private String type;

    public Orchestre(String nom, String type) {
        super(nom);
        this.type = type;
    }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}