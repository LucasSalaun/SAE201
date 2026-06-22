import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Principale extends Application {

    static List<Client> lesClients = new ArrayList<>();
    private static FenGestionClients fenetreClients;

    @Override
    public void start(Stage stage) throws Exception {
        // Chargement des données depuis le fichier
        lesClients = Persistance.chargerClients();

        fenetreClients = new FenGestionClients();
        fenetreClients.show();
    }

    static void ajouterClient(Client c) {
        lesClients.add(c);
    }

    static void supprimerClient(Client c) {
        lesClients.remove(c);
    }

    static void sauvegarder() {
        Persistance.sauvegarderClients(lesClients);
    }

    static void fermerFenetreClients() {
        fenetreClients.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
