import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestionDonnees {
    private static final String FICHIER_CLIENTS = "clients.csv";
    private static final String FICHIER_BILLETS = "billets.csv";

    private static List<Client> listeClients = new ArrayList<>();
    private static List<Billet> listeBillets = new ArrayList<>();

    public static List<Client> getListeClients() { return listeClients; }
    public static List<Billet> getListeBillets() { return listeBillets; }

    public static void chargerDonnees() {
        listeClients.clear();
        listeBillets.clear();
        
        // 1. Chargement des clients
        File fClients = new File(FICHIER_CLIENTS);
        if (fClients.exists()) {
            try (Scanner scanner = new Scanner(fClients)) {
                while (scanner.hasNextLine()) {
                    String ligne = scanner.nextLine();
                    String[] t = ligne.split(";");
                    if (t.length == 8) {
                        Adresse adr = new Adresse(Integer.parseInt(t[4]), t[5], t[6], t[7]);
                        Client c = new Client(t[0], t[1], t[2], t[3], adr);
                        listeClients.add(c);
                    }
                }
            } catch (Exception e) {
                System.out.println("Erreur chargement clients: " + e.getMessage());
            }
        }

        // 2. Chargement des billets
        File fBillets = new File(FICHIER_BILLETS);
        if (fBillets.exists()) {
            try (Scanner scanner = new Scanner(fBillets)) {
                while (scanner.hasNextLine()) {
                    String ligne = scanner.nextLine();
                    String[] t = ligne.split(";");
                    if (t.length == 9) {
                        int numero = Integer.parseInt(t[0]);
                        String date = t[1];
                        String heure = t[2];
                        String modePaiement = t[3];
                        String nomSalle = t[4];
                        String typeIntervenant = t[5];
                        String nomIntervenant = t[6];
                        String detailIntervenant = t[7];
                        String idClient = t[8];

                        Client client = trouverClientParId(idClient);
                        if (client != null) {
                            Salle salle = new Salle(nomSalle);
                            Intervenant intervenant;
                            if (typeIntervenant.equals("Artiste")) {
                                intervenant = new Artiste(nomIntervenant, detailIntervenant);
                            } else {
                                intervenant = new Orchestre(nomIntervenant, detailIntervenant);
                            }
                            Billet b = new Billet(numero, date, heure, modePaiement, salle, intervenant, client);
                            client.ajouterBillet(b);
                            listeBillets.add(b);
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Erreur chargement billets: " + e.getMessage());
            }
        }
    }

    public static void sauvegarderDonnees() {
        // Sauvegarde des clients
        try (FileWriter writer = new FileWriter(FICHIER_CLIENTS)) {
            for (Client c : listeClients) {
                writer.write(c.getId() + ";" + c.getNom() + ";" + c.getPrenom() + ";" + c.getEmail() + ";" +
                             c.getAdresse().getNumero() + ";" + c.getAdresse().getRue() + ";" +
                             c.getAdresse().getCodePostal() + ";" + c.getAdresse().getVille() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Erreur sauvegarde clients: " + e.getMessage());
        }

        // Sauvegarde des billets
        try (FileWriter writer = new FileWriter(FICHIER_BILLETS)) {
            for (Billet b : listeBillets) {
                String typeIntervenant = (b.getIntervenant() instanceof Artiste) ? "Artiste" : "Orchestre";
                String detailIntervenant = (b.getIntervenant() instanceof Artiste) ? 
                        ((Artiste) b.getIntervenant()).getInstrument() : ((Orchestre) b.getIntervenant()).getType();
                
                writer.write(b.getNumero() + ";" + b.getDate() + ";" + b.getHeureDebut() + ";" +
                             b.getModePaiement() + ";" + b.getSalle().getNom() + ";" +
                             typeIntervenant + ";" + b.getIntervenant().getNom() + ";" +
                             detailIntervenant + ";" + b.getClient().getId() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Erreur sauvegarde billets: " + e.getMessage());
        }
    }

    public static Client trouverClientParId(String id) {
        for (Client c : listeClients) {
            if (c.getId().equals(id)) return c;
        }
        return null;
    }
}