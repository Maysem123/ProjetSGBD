package com.example.revisionsgbd;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.Connection;
import java.sql.SQLException;

public class HelloController {

    @FXML private TextField nomField;
    @FXML private TextField moyenneField;
    @FXML private TableView<Etudiant> studentsTable;
    @FXML private TableColumn<Etudiant, String> nomColumn;
    @FXML private TableColumn<Etudiant, Double> moyenneColumn;

    private ObservableList<Etudiant> etudiants = FXCollections.observableArrayList();
    private Connection connection;

    public void initialize() throws SQLException {
        connection = ConnectionDB.getConnection();

        // Configuration des colonnes
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        moyenneColumn.setCellValueFactory(new PropertyValueFactory<>("moyenne"));

        studentsTable.setItems(etudiants);

        // Écouteur de sélection
        studentsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                nomField.setText(newVal.getNom());
                moyenneField.setText(String.valueOf(newVal.getMoyenne()));
            }
        });
    }

    @FXML
    public void handleAjouter(ActionEvent actionEvent) {
        try {
            String nom = nomField.getText();
            double moyenne = Double.parseDouble(moyenneField.getText());

            if (nom.isEmpty()) {
                showAlert("Erreur", "Le nom ne peut pas être vide");
                return;
            }

            if (moyenne < 0 || moyenne > 20) {
                showAlert("Erreur", "La moyenne doit être entre 0 et 20");
                return;
            }

            Etudiant nouvelEtudiant = new Etudiant(nom, moyenne);
            etudiants.add(nouvelEtudiant);
            clearFields();

        } catch (NumberFormatException e) {
            showAlert("Erreur de format", "La moyenne doit être un nombre valide");
        }
    }

    @FXML
    public void handleModifier(ActionEvent actionEvent) {
        Etudiant selected = studentsTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                String nouveauNom = nomField.getText();
                double nouvelleMoyenne = Double.parseDouble(moyenneField.getText());

                if (nouveauNom.isEmpty()) {
                    showAlert("Erreur", "Le nom ne peut pas être vide");
                    return;
                }

                if (nouvelleMoyenne < 0 || nouvelleMoyenne > 20) {
                    showAlert("Erreur", "La moyenne doit être entre 0 et 20");
                    return;
                }

                selected.setNom(nouveauNom);
                selected.setMoyenne(nouvelleMoyenne);
                studentsTable.refresh();
                clearFields();

            } catch (NumberFormatException e) {
                showAlert("Erreur de format", "La moyenne doit être un nombre valide");
            }
        } else {
            showAlert("Aucune sélection", "Veuillez sélectionner un étudiant à modifier");
        }
    }

    @FXML
    public void handleSupprimer(ActionEvent actionEvent) {
        Etudiant selected = studentsTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            etudiants.remove(selected);
            clearFields();
        } else {
            showAlert("Aucune sélection", "Veuillez sélectionner un étudiant à supprimer");
        }
    }

    private void clearFields() {
        nomField.clear();
        moyenneField.clear();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}