/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entite;

/**
 *
 * @author Mezri
 */
public class Offre {
    int id;
    String evenement;
    float remise;
    float prixDebut;
    float prixFinal;
    String dateDebut;
    String dateFin;
    int likesnumber;
    int dislikesnumber;
    String image;

    public Offre() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEvenement() {
        return evenement;
    }

    public void setEvenement(String evenement) {
        this.evenement = evenement;
    }

    public float getRemise() {
        return remise;
    }

    public void setRemise(float remise) {
        this.remise = remise;
    }

    public float getPrixDebut() {
        return prixDebut;
    }

    public void setPrixDebut(float prixDebut) {
        this.prixDebut = prixDebut;
    }

    public float getPrixFinal() {
        return prixFinal;
    }

    public void setPrixFinal(float prixFinal) {
        this.prixFinal = prixFinal;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public int getLikesnumber() {
        return likesnumber;
    }

    public void setLikesnumber(int likesnumber) {
        this.likesnumber = likesnumber;
    }

    public int getDislikesnumber() {
        return dislikesnumber;
    }

    public void setDislikesnumber(int dislikesnumber) {
        this.dislikesnumber = dislikesnumber;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
}
