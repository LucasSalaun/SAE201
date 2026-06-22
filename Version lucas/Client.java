import java.util.ArrayList;
import java.util.List;

public class Client {
    private String id;
    private String nom;
    private String prenom;
    private String email;
    
    private Adresse adresse;
    private List<Billet> billetsAchetes;

    public Client(String id, String nom, String prenom, String email, Adresse adresse) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adresse = adresse;
        this.billetsAchetes = new ArrayList<>();
    }

    public void ajouterBillet(Billet billet) {
        this.billetsAchetes.add(billet);
    }

    public void retirerBillet(Billet billet) {
        this.billetsAchetes.remove(billet);
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
    public List<Billet> getBilletsAchetes() { return billetsAchetes; }
}