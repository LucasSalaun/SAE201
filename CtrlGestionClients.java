import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CtrlGestionClients {

    @FXML private TextField champRecherche;
    @FXML private TableView<Client> tableClients;
    @FXML private TableColumn<Client, String> colNom;
    @FXML private TableColumn<Client, String> colPrenom;
    @FXML private TableColumn<Client, String> colEmail;

    @FXML private TextField champId;
    @FXML private TextField champNom;
    @FXML private TextField champPrenom;
    @FXML private TextField champEmail;
    @FXML private TextField champNumero;
    @FXML private TextField champRue;
    @FXML private TextField champVille;
    @FXML private TextField champCodePostal;

    @FXML private ListView<Billet> listeBillets;
    @FXML private Label labelMessage;

    private ObservableList<Client> listeAffichee;

    public void initialize() {
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        listeAffichee = FXCollections.observableArrayList(Principale.lesClients);
        tableClients.setItems(listeAffichee);
    }

    @FXML
    private void selectionnerClient() {
        Client c = tableClients.getSelectionModel().getSelectedItem();
        if (c == null) return;

        champId.setText(c.getId());
        champNom.setText(c.getNom());
        champPrenom.setText(c.getPrenom());
        champEmail.setText(c.getEmail());
        champNumero.setText(String.valueOf(c.getAdresse().getNumero()));
        champRue.setText(c.getAdresse().getRue());
        champVille.setText(c.getAdresse().getVille());
        champCodePostal.setText(c.getAdresse().getCodePostal());

        listeBillets.setItems(FXCollections.observableArrayList(c.getBillets()));
        labelMessage.setText("");
    }

    @FXML
    private void creerClient() {
        if (!champsValides()) return;

        String id = champId.getText().trim();
        for (Client c : Principale.lesClients) {
            if (c.getId().equals(id)) {
                labelMessage.setText("Erreur : cet ID existe déjà.");
                return;
            }
        }

        Adresse adresse = new Adresse(
                Integer.parseInt(champNumero.getText().trim()),
                champRue.getText().trim(),
                champVille.getText().trim(),
                champCodePostal.getText().trim()
        );

        Client nouveau = new Client(
                id,
                champNom.getText().trim(),
                champPrenom.getText().trim(),
                champEmail.getText().trim(),
                adresse
        );

        Principale.ajouterClient(nouveau);
        listeAffichee.add(nouveau);
        labelMessage.setText("Client créé avec succès.");
        viderFormulaire();
    }

    @FXML
    private void modifierClient() {
        Client c = tableClients.getSelectionModel().getSelectedItem();
        if (c == null) {
            labelMessage.setText("Sélectionnez un client à modifier.");
            return;
        }
        if (!champsValides()) return;

        c.setNom(champNom.getText().trim());
        c.setPrenom(champPrenom.getText().trim());
        c.setEmail(champEmail.getText().trim());
        c.getAdresse().setNumero(Integer.parseInt(champNumero.getText().trim()));
        c.getAdresse().setRue(champRue.getText().trim());
        c.getAdresse().setVille(champVille.getText().trim());
        c.getAdresse().setCodePostal(champCodePostal.getText().trim());

        tableClients.refresh();
        labelMessage.setText("Client modifié avec succès.");
    }

    @FXML
    private void supprimerClient() {
        Client c = tableClients.getSelectionModel().getSelectedItem();
        if (c == null) {
            labelMessage.setText("Sélectionnez un client à supprimer.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                "Supprimer le client " + c.getPrenom() + " " + c.getNom() + " ?",
                ButtonType.YES, ButtonType.NO);
        confirm.showAndWait();

        if (confirm.getResult() == ButtonType.YES) {
            Principale.supprimerClient(c);
            listeAffichee.remove(c);
            viderFormulaire();
            labelMessage.setText("Client supprimé.");
        }
    }

    @FXML
    private void rembourserBillets() throws IOException {
        Client c = tableClients.getSelectionModel().getSelectedItem();
        if (c == null) {
            labelMessage.setText("Sélectionnez un client.");
            return;
        }
        if (c.getBillets().isEmpty()) {
            labelMessage.setText("Ce client n'a aucun billet.");
            return;
        }

        FenRemboursement fen = new FenRemboursement(c);
        fen.initOwner(tableClients.getScene().getWindow());
        fen.showAndWait();

        // Rafraîchir après remboursement
        listeBillets.setItems(FXCollections.observableArrayList(c.getBillets()));
    }

    @FXML
    private void rechercherClient() {
        String critere = champRecherche.getText().trim().toLowerCase();
        List<Client> resultats = new ArrayList<>();

        if (critere.isEmpty()) {
            listeAffichee.setAll(Principale.lesClients);
            return;
        }

        for (Client c : Principale.lesClients) {
            if (c.getNom().toLowerCase().contains(critere)
                    || c.getPrenom().toLowerCase().contains(critere)
                    || c.getEmail().toLowerCase().contains(critere)) {
                resultats.add(c);
            }
        }
        listeAffichee.setAll(resultats);
    }

    @FXML
    private void reinitialiser() {
        champRecherche.setText("");
        listeAffichee.setAll(Principale.lesClients);
        viderFormulaire();
        labelMessage.setText("");
    }

    private boolean champsValides() {
        if (champId.getText().trim().isEmpty()
                || champNom.getText().trim().isEmpty()
                || champPrenom.getText().trim().isEmpty()
                || champEmail.getText().trim().isEmpty()
                || champRue.getText().trim().isEmpty()
                || champVille.getText().trim().isEmpty()
                || champCodePostal.getText().trim().isEmpty()) {
            labelMessage.setText("Erreur : tous les champs sont obligatoires.");
            return false;
        }
        try {
            Integer.parseInt(champNumero.getText().trim());
        } catch (NumberFormatException e) {
            labelMessage.setText("Erreur : le numéro doit être un entier.");
            return false;
        }
        return true;
    }

    private void viderFormulaire() {
        champId.setText("");
        champNom.setText("");
        champPrenom.setText("");
        champEmail.setText("");
        champNumero.setText("");
        champRue.setText("");
        champVille.setText("");
        champCodePostal.setText("");
        listeBillets.setItems(FXCollections.observableArrayList());
    }
}
