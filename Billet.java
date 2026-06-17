public class Billet{
    private String numero;
    private String date;
    private String heureDebut;
    private String modePaiement;
    private Client client;

    public String getNumero() {
    return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    public String getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement(String modePaiement) {
        this.modePaiement = modePaiement;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Billet(String n, String d, String hD, String mP, Client c){
        this.numero = n;
        this.date = d;
        this.heureDebut = hD;
        this.modePaiement = mP;
        this.client = c;
    }
}   