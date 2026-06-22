import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        GestionDonnees.chargerDonnees();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("vue_principale.fxml"));
        Parent root = loader.load();
        
        primaryStage.setTitle("SAE 2.01 - Gestion des Ventes de Billets");
        primaryStage.setScene(new Scene(root, 800, 600));
        
        primaryStage.setOnCloseRequest(event -> {
            GestionDonnees.sauvegarderDonnees();
            System.out.println("Données sauvegardées en fin de session.");
        });

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}