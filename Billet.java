public class Billet {
    private int numero;
    private String date;
    private String heureDebut;
    private String modePaiement;
    private Intervenant intervenant;
    private Salle salle;
    private Client client;

    public Billet(int numero, String date, String heureDebut, String modePaiement,
                  Intervenant intervenant, Salle salle, Client client) {
        this.numero = numero;
        this.date = date;
        this.heureDebut = heureDebut;
        this.modePaiement = modePaiement;
        this.intervenant = intervenant;
        this.salle = salle;
        this.client = client;
    }


    // accesseurs
    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getHeureDebut() { return heureDebut; }
    public void setHeureDebut(String heureDebut) { this.heureDebut = heureDebut; }

    public String getModePaiement() { return modePaiement; }
    public void setModePaiement(String modePaiement) { this.modePaiement = modePaiement; }

    public Intervenant getIntervenant() { return intervenant; }
    public void setIntervenant(Intervenant intervenant) { this.intervenant = intervenant; }

    public Salle getSalle() { return salle; }
    public void setSalle(Salle salle) { this.salle = salle; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }

    //

    @Override
    public String toString() {
        return "Billet n°" + numero + " | " + date + " " + heureDebut
                + " | " + intervenant + " | " + salle
                + " | " + modePaiement;
    }
}