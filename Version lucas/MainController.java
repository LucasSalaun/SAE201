package packet;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MainController {

    @FXML private TableView<Client> tableClients;
    @FXML private TableColumn<Client, String> colClientId;
    @FXML private TableColumn<Client, String> colClientNom;
    @FXML private TableColumn<Client, String> colClientPrenom;
    @FXML private TableColumn<Client, String> colClientEmail;
    @FXML private TableColumn<Client, String> colClientVille;

    @FXML private TextField filtreNom;
    @FXML private TextField filtreVille;

    @FXML private TextField champId;
    @FXML private TextField champNom;
    @FXML private TextField champPrenom;
    @FXML private TextField champEmail;
    @FXML private TextField champNum;
    @FXML private TextField champRue;
    @FXML private TextField champCP;
    @FXML private TextField champVille;

    @FXML private TableView<Billet> tableBillets;
    @FXML private TableColumn<Billet, Integer> colBilletNum;
    @FXML private TableColumn<Billet, String> colBilletDate;
    @FXML private TableColumn<Billet, String> colBilletHeure;
    @FXML private TableColumn<Billet, String> colBilletSpectacle;
    @FXML private TableColumn<Billet, String> colBilletSalle;
    @FXML private TableColumn<Billet, String> colBilletMode;
    @FXML private TableColumn<Billet, String> colBilletClient;

    @FXML private ComboBox<Client> comboClients;
    @FXML private TextField champBilletNum;
    @FXML private TextField champBilletDate;
    @FXML private TextField champBilletHeure;
    @FXML private TextField champBilletSalle;
    @FXML private ComboBox<String> comboTypeIntervenant;
    @FXML private TextField champBilletIntervenantNom;
    @FXML private TextField champBilletIntervenantDetail;
    @FXML private ComboBox<String> comboModePaiement;

    private ObservableList<Client> masterClients;
    private ObservableList<Billet> masterBillets;

    @FXML
    public void initialize() {
        masterClients = FXCollections.observableArrayList(GestionDonnees.getListeClients());
        masterBillets = FXCollections.observableArrayList(GestionDonnees.getListeBillets());

        colClientId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colClientNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colClientPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colClientEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colClientVille.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAdresse().getVille()));
        tableClients.setItems(masterClients);

        colBilletNum.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colBilletDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colBilletHeure.setCellValueFactory(new PropertyValueFactory<>("heureDebut"));
        colBilletSpectacle.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIntervenant().getNom()));
        colBilletSalle.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSalle().getNom()));
        colBilletMode.setCellValueFactory(new PropertyValueFactory<>("modePaiement"));
        colBilletClient.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClient().getNom() + " (" + cellData.getValue().getClient().getId() + ")"));
        tableBillets.setItems(masterBillets);

        comboClients.setItems(masterClients);
        comboTypeIntervenant.setItems(FXCollections.observableArrayList("Artiste", "Orchestre"));
        comboModePaiement.setItems(FXCollections.observableArrayList("Carte Bancaire", "Espèce"));
        
        comboTypeIntervenant.getSelectionModel().selectFirst();
        comboModePaiement.getSelectionModel().selectFirst();
    }

    @FXML
    public void ajouterClient() {
        try {
            int num = Integer.parseInt(champNum.getText());
            Adresse adr = new Adresse(num, champRue.getText(), champCP.getText(), champVille.getText());
            Client c = new Client(champId.getText(), champNom.getText(), champPrenom.getText(), champEmail.getText(), adr);
            
            GestionDonnees.getListeClients().add(c);
            masterClients.add(c);
            
            champId.clear(); champNom.clear(); champPrenom.clear(); champEmail.clear();
            champNum.clear(); champRue.clear(); champCP.clear(); champVille.clear();
        } catch (NumberFormatException e) {
            afficherAlerte("Erreur", "Le numéro de rue doit être un entier valide.");
        }
    }
    
    @FXML
    public void supprimerClient() {
        Client selectedClient = tableClients.getSelectionModel().getSelectedItem();
        
        if (selectedClient == null) {
            afficherAlerte("Erreur", "Veuillez sélectionner un client dans le tableau.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Vous êtes sur le point de supprimer : " + selectedClient.getNom());
        alert.setContentText("Êtes vous sur de vouloir continuer ?");

        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.isPresent() && result.get() == ButtonType.OK) {
            masterClients.remove(selectedClient);
            GestionDonnees.getListeClients().remove(selectedClient);
            GestionDonnees.sauvegarderDonnees();
            
            System.out.println("Client supprimé avec succès.");
        } else {
            System.out.println("Suppression annulée.");
        }
    }




    @FXML
    public void filtrerClients() {
        String nomFiltre = filtreNom.getText().toLowerCase();
        String villeFiltre = filtreVille.getText().toLowerCase();

        List<Client> resultat = new ArrayList<>();
        for (Client c : GestionDonnees.getListeClients()) {
            boolean correspondNom = nomFiltre.isEmpty() || c.getNom().toLowerCase().contains(nomFiltre);
            boolean correspondVille = villeFiltre.isEmpty() || c.getAdresse().getVille().toLowerCase().contains(villeFiltre);
            if (correspondNom && correspondVille) {
                resultat.add(c);
            }
        }
        tableClients.setItems(FXCollections.observableArrayList(resultat));
    }

    @FXML
    public void reinitialiserFiltre() {
        filtreNom.clear();
        filtreVille.clear();
        tableClients.setItems(masterClients);
    }

    @FXML
    public void acheterBillet() {
        try {
            Client c = comboClients.getSelectionModel().getSelectedItem();
            if (c == null) {
                afficherAlerte("Erreur", "Veuillez sélectionner un client.");
                return;
            }

            int numBillet = Integer.parseInt(champBilletNum.getText());
            String date = champBilletDate.getText();
            String heure = champBilletHeure.getText();
            Salle salle = new Salle(champBilletSalle.getText());
            
            Intervenant intervenant;
            String type = comboTypeIntervenant.getSelectionModel().getSelectedItem();
            if ("Artiste".equals(type)) {
                intervenant = new Artiste(champBilletIntervenantNom.getText(), champBilletIntervenantDetail.getText());
            } else {
                intervenant = new Orchestre(champBilletIntervenantNom.getText(), champBilletIntervenantDetail.getText());
            }

            String mode = comboModePaiement.getSelectionModel().getSelectedItem();

            Billet b = new Billet(numBillet, date, heure, mode, salle, intervenant, c);
            
            GestionDonnees.getListeBillets().add(b);
            c.ajouterBillet(b);
            masterBillets.add(b);

            champBilletNum.clear();
            champBilletIntervenantNom.clear();
            champBilletIntervenantDetail.clear();
        } catch (NumberFormatException e) {
            afficherAlerte("Erreur", "Le numéro de billet doit être un entier valide.");
        }
    }

    @FXML
    public void rembourserSpectacle() {
        Billet billetSelectionne = tableBillets.getSelectionModel().getSelectedItem();
        if (billetSelectionne == null) {
            afficherAlerte("Remboursement", "Veuillez sélectionner un billet dans le tableau pour identifier le spectacle.");
            return;
        }

        Client clientConcerne = billetSelectionne.getClient();
        String spectacleNom = billetSelectionne.getIntervenant().getNom();
        String spectacleDate = billetSelectionne.getDate();

        List<Billet> billetsARembourser = new ArrayList<>();
        boolean paiementParCarteTrouve = false;

        for (Billet b : clientConcerne.getBilletsAchetes()) {
            if (b.getIntervenant().getNom().equals(spectacleNom) && b.getDate().equals(spectacleDate)) {
                billetsARembourser.add(b);
                if ("Carte Bancaire".equals(b.getModePaiement())) {
                    paiementParCarteTrouve = true;
                }
            }
        }

        if (billetsARembourser.isEmpty()) {
            afficherAlerte("Erreur", "Aucun billet trouvé pour ce spectacle chez ce client.");
            return;
        }

        String modeRemboursementChoisi = "Espèce";

        if (paiementParCarteTrouve) {
            ChoiceDialog<String> dialog = new ChoiceDialog<>("Carte Bancaire", "Carte Bancaire", "Espèce");
            dialog.setTitle("Choix du remboursement");
            dialog.setHeaderText("Remboursement de " + billetsARembourser.size() + " billet(s) pour le spectacle de " + spectacleNom);
            dialog.setContentText("Le paiement ayant eu lieu par carte, choisissez le mode de remboursement :");
            
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                modeRemboursementChoisi = result.get();
            } else {
                return;
            }
        } else {
            afficherAlerte("Information", "Le paiement ayant eu lieu en espèce, le remboursement se fera obligatoirement en espèce.");
        }

        for (Billet b : billetsARembourser) {
            clientConcerne.retirerBillet(b);
            GestionDonnees.getListeBillets().remove(b);
            masterBillets.remove(b);
        }

        GestionDonnees.sauvegarderDonnees();

        afficherAlerte("Succès", "Remboursement effectué avec succès (" + modeRemboursementChoisi + ").\n" +
                billetsARembourser.size() + " billet(s) supprimé(s) du fichier.");
    }

    private void afficherAlerte(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}