/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entite;

import java.util.Date;

/**
 *
 * @author Mezri
 */
public class Evenement {
    int idEvenement;
    String nom;
    String description;
    float prix;
    boolean termine;
    boolean confirme;
    String date;
    String type;
    String espace;
    User user;
    String image;

    public Evenement() {
    }

    public Evenement(int idEvenement, String nom, String description, float prix, boolean termine, boolean confirme, String date, String type, String espace, User user, String image) {
        this.idEvenement = idEvenement;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.termine = termine;
        this.confirme = confirme;
        this.date = date;
        this.type = type;
        this.espace = espace;
        this.user = user;
        this.image = image;
    }

    public int getIdEvenement() {
        return idEvenement;
    }

    public void setIdEvenement(int idEvenement) {
        this.idEvenement = idEvenement;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public boolean isTermine() {
        return termine;
    }

    public void setTermine(boolean termine) {
        this.termine = termine;
    }

    public boolean isConfirme() {
        return confirme;
    }

    public void setConfirme(boolean confirme) {
        this.confirme = confirme;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEspace() {
        return espace;
    }

    public void setEspace(String espace) {
        this.espace = espace;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Evenement{" + "idEvenement=" + idEvenement + ", nom=" + nom + ", description=" + description + ", prix=" + prix + ", termine=" + termine + ", confirme=" + confirme + ", date=" + date + ", type=" + type + ", espace=" + espace + ", user=" + user + ", image=" + image + '}';
    }
    
    
}
