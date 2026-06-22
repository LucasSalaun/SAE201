import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class FenGestionClients extends Stage {

    private CtrlGestionClients ctrl;

    public FenGestionClients() throws IOException {
        this.setTitle("Gestion des clients");
        this.setResizable(true);
        this.setScene(new Scene(creerSceneGraph()));

        // Sauvegarde automatique à la fermeture
        this.setOnCloseRequest(e -> Principale.sauvegarder());
    }

    private BorderPane creerSceneGraph() throws IOException {
        File fichier = new File("GestionClients.fxml");
        FXMLLoader loader = new FXMLLoader(fichier.toURI().toURL());
        BorderPane racine = loader.load();
        ctrl = loader.getController();
        return racine;
    }

    public CtrlGestionClients getCtrl() {
        return ctrl;
    }
}
