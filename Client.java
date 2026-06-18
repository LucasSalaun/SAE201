public class Client {
    private String identifiant;
    private String nom;
    private String prenom;
    private String email;
    private Adresse adresse;
    private Collection <Billet> listeBillet;

    public String getIdentifiant() {
        return identifiant;
    }  

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Client(String i, String n, String p, String e, Adresse a, Billet b){
        this.identifiant = i;
        this.nom = n;
        this.prenom = p;
        this.email = e;
        this.adresse = a;
        listeBillet = new ArrayList<Billet>();
        listeBillet.add(b);
    }

    
}