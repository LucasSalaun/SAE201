import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Persistance {

    private static final String FICHIER_CLIENTS = "clients.dat";

    public static void sauvegarderClients(List<Client> clients) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(FICHIER_CLIENTS))) {
            oos.writeObject(clients);
        } catch (IOException e) {
            System.err.println("Erreur sauvegarde : " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Client> chargerClients() {
        File fichier = new File(FICHIER_CLIENTS);
        if (!fichier.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(FICHIER_CLIENTS))) {
            return (List<Client>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erreur chargement : " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
