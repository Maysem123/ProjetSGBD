package com.example.revisionsgbd;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Etudiant {
    private final StringProperty nom;
    private final DoubleProperty moyenne;

    public Etudiant() {
        this("", 0.0);
    }

    public Etudiant(String nom, double moyenne) {
        this.nom = new SimpleStringProperty(nom);
        this.moyenne = new SimpleDoubleProperty(moyenne);
    }

    // Getters et setters pour 'nom'
    public String getNom() {
        return nom.get();
    }
    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public StringProperty nomProperty() {
        return nom;
    }

    // Getters et setters pour 'moyenne'
    public double getMoyenne() {
        return moyenne.get();
    }
    public void setMoyenne(double moyenne) {
        this.moyenne.set(moyenne);
    }
    public DoubleProperty moyenneProperty() {
        return moyenne;
    }



}



