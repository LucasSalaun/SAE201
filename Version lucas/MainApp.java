package packet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage fenPrincipale) throws Exception {
        GestionDonnees.chargerDonnees();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("vue_principale.fxml"));
        Parent root = loader.load();
        
        fenPrincipale.setTitle("SAE 2.01 - Gestion des Ventes de Billets");
        fenPrincipale.setScene(new Scene(root, 800, 600));
        
        fenPrincipale.setOnCloseRequest(event -> {
            GestionDonnees.sauvegarderDonnees();
            System.out.println("Données sauvegardées en fin de session.");
        });

        fenPrincipale.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}