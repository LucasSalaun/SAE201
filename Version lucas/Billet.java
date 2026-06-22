public class Billet {
    private int numero;
    private String date;
    private String heureDebut;
    private String modePaiement; // "Carte Bancaire" ou "Espèce"
    
    private Salle salle;
    private Intervenant intervenant;
    private Client client;

    public Billet(int numero, String date, String heureDebut, String modePaiement, Salle salle, Intervenant intervenant, Client client) {
        this.numero = numero;
        this.date = date;
        this.heureDebut = heureDebut;
        this.modePaiement = modePaiement;
        this.salle = salle;
        this.intervenant = intervenant;
        this.client = client;
    }

    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getHeureDebut() { return heureDebut; }
    public void setHeureDebut(String heureDebut) { this.heureDebut = heureDebut; }
    public String getModePaiement() { return modePaiement; }
    public void setModePaiement(String modePaiement) { this.modePaiement = modePaiement; }
    public Salle getSalle() { return salle; }
    public void setSalle(Salle salle) { this.salle = salle; }
    public Intervenant getIntervenant() { return intervenant; }
    public void setIntervenant(Intervenant intervenant) { this.intervenant = intervenant; }
    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }
}