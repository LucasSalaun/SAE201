import java.util.ArrayList;
import java.util.List;

public class Client {
    private String id;
    private String nom;
    private String prenom;
    private String email;
    private Adresse adresse;
    private List<Billet> billets;

    public Client(String id, String nom, String prenom, String email, Adresse adresse) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adresse = adresse;
        this.billets = new ArrayList<>();
    }

    public void acheteBillet(Billet billet) {
        billets.add(billet);
    }

    public boolean retirerBillet(Billet billet) {
        return billets.remove(billet);
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Adresse getAdresse() { return adresse; }
    public void setAdresse(Adresse adresse) { this.adresse = adresse; }

    public List<Billet> getBillets() { return billets; }

    @Override
    public String toString() {
        return id + " - " + prenom + " " + nom + " (" + email + ")";
    }
}
