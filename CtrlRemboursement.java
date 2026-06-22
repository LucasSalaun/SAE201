import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class CtrlRemboursement {

    @FXML private ComboBox<String> comboSpectacles;
    @FXML private ListView<Billet> listeBilletsARembourser;
    @FXML private Label labelModePaiement;
    @FXML private javafx.scene.layout.VBox zoneChoixRemboursement;
    @FXML private RadioButton radioEspece;
    @FXML private RadioButton radioCarte;
    @FXML private Label labelResultat;

    private Client client;
    private ToggleGroup toggleGroup;

    public void initialize() {
        toggleGroup = new ToggleGroup();
        radioEspece.setToggleGroup(toggleGroup);
        radioCarte.setToggleGroup(toggleGroup);
        radioEspece.setSelected(true);
    }

    public void setClient(Client client) {
        this.client = client;

        // Remplir le ComboBox avec les spectacles distincts
        List<String> spectacles = new ArrayList<>();
        for (Billet b : client.getBillets()) {
            String spectacle = b.getIntervenant().getNom() + " - " + b.getDate();
            if (!spectacles.contains(spectacle)) {
                spectacles.add(spectacle);
            }
        }
        comboSpectacles.setItems(FXCollections.observableArrayList(spectacles));
        comboSpectacles.setOnAction(e -> mettreAJourBillets());
    }

    private void mettreAJourBillets() {
        String spectacleChoisi = comboSpectacles.getValue();
        if (spectacleChoisi == null) return;

        String[] parts = spectacleChoisi.split(" - ");
        String nomIntervenant = parts[0];
        String date = parts[1];

        List<Billet> billetsSpectacle = new ArrayList<>();
        for (Billet b : client.getBillets()) {
            if (b.getIntervenant().getNom().equals(nomIntervenant)
                    && b.getDate().equals(date)) {
                billetsSpectacle.add(b);
            }
        }

        listeBilletsARembourser.setItems(FXCollections.observableArrayList(billetsSpectacle));

        boolean payeParCarte = false;
        for (Billet b : billetsSpectacle) {
            if (b.getModePaiement().equalsIgnoreCase("carte bancaire")) {
                payeParCarte = true;
                break;
            }
        }

        if (payeParCarte) {
            labelModePaiement.setText("Paiement effectué par carte bancaire.");
            zoneChoixRemboursement.setVisible(true);
        } else {
            labelModePaiement.setText("Paiement effectué en espèce. Remboursement en espèce.");
            zoneChoixRemboursement.setVisible(false);
        }
    }

    @FXML
    private void confirmerRemboursement() {
        String spectacleChoisi = comboSpectacles.getValue();
        if (spectacleChoisi == null) {
            labelResultat.setText("Sélectionnez un spectacle.");
            labelResultat.setStyle("-fx-text-fill: red;");
            return;
        }

        String[] parts = spectacleChoisi.split(" - ");
        String nomIntervenant = parts[0];
        String date = parts[1];

        List<Billet> aRembourser = new ArrayList<>();
        for (Billet b : client.getBillets()) {
            if (b.getIntervenant().getNom().equals(nomIntervenant)
                    && b.getDate().equals(date)) {
                aRembourser.add(b);
            }
        }

        String modeRemboursement;
        if (zoneChoixRemboursement.isVisible() && radioCarte.isSelected()) {
            modeRemboursement = "carte bancaire";
        } else {
            modeRemboursement = "espece";
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                "Rembourser " + aRembourser.size() + " billet(s) par " + modeRemboursement + " ?",
                ButtonType.YES, ButtonType.NO);
        confirm.showAndWait();

        if (confirm.getResult() == ButtonType.YES) {
            for (Billet b : aRembourser) {
                client.retirerBillet(b);
            }

            Principale.sauvegarder();

            labelResultat.setText("Remboursement effectué par " + modeRemboursement + ".");
            labelResultat.setStyle("-fx-text-fill: green;");
            comboSpectacles.getItems().remove(spectacleChoisi);
            listeBilletsARembourser.setItems(FXCollections.observableArrayList());
            zoneChoixRemboursement.setVisible(false);
            labelModePaiement.setText("");
        }
    }

    @FXML
    private void annuler() {
        Stage stage = (Stage) comboSpectacles.getScene().getWindow();
        stage.close();
    }
}
