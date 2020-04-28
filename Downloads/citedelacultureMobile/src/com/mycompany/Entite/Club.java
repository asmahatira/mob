/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entite;

import com.codename1.components.ImageViewer;

/**
 *
 * @author nader
 */
public class Club {
    
    private int idClub;
    private String nom;
    private String description;
    private float cotisationannuelle;
    private String idPole;
    private String idResponsablePole;
    
    
    

    public Club() {
    }

    public Club(int idClub, String nom, String description, float cotisationannuelle, String idPole, String idResponsablePole) {
        this.idClub = idClub;
        this.nom = nom;
        this.description = description;
        this.cotisationannuelle = cotisationannuelle;
        this.idPole = idPole;
        this.idResponsablePole = idResponsablePole;
    }

    public Club(String nom, String description, float cotisationannuelle) {
        this.idClub = idClub;
        this.nom = nom;
        this.description = description;
        this.cotisationannuelle = cotisationannuelle;
    }

    

    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    
    public int getIdClub() {
        return idClub;
    }

    public String getIdPole() {
        return idPole;
    }

    public String getIdResponsablePole() {
        return idResponsablePole;
    }

    public String getDescription() {
        return description;
    }

    public void setIdClub(int idClub) {
        this.idClub = idClub;
    }

    public void setIdPole(String idPole) {
        this.idPole = idPole;
    }

    public void setIdResponsablePole(String idResponsablePole) {
        this.idResponsablePole = idResponsablePole;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getCotisationannuelle() {
        return cotisationannuelle;
    }

    public void setCotisationannuelle(float cotisationannuelle) {
        this.cotisationannuelle = cotisationannuelle;
    }

    @Override
    public String toString() {
        return "| id :" + idClub + ", nom :" + nom + ", Description :" + description + ", Cotisation annuelle DT :" + cotisationannuelle + " |";
    }
    
    

    
    
    
    
}
