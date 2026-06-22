import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class FenRemboursement extends Stage {

    private CtrlRemboursement ctrl;

    public FenRemboursement(Client client) throws IOException {
        this.setTitle("Remboursement de billets");
        this.setResizable(false);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setScene(new Scene(creerSceneGraph()));
        ctrl.setClient(client);
    }

    private VBox creerSceneGraph() throws IOException {
        File fichier = new File("Remboursement.fxml");
        FXMLLoader loader = new FXMLLoader(fichier.toURI().toURL());
        VBox racine = loader.load();
        ctrl = loader.getController();
        return racine;
    }
}
