/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entite;

import java.util.Date;

/**
 *
 * @author asus
 */
public class Publication {

    @Override
    public String toString() {
        return "Publication{" + "idPublication=" + idPublication + ", titre=" + titre + ", contenu=" + contenu + ", date=" + date + ", image=" + image + ", fichier=" + fichier + ", idRedacteur=" + idRedacteur + '}';
    }

    private int idPublication;
    private String titre;
    private String contenu;
    private Date date;
    private String image;
    private String fichier;
    private int idRedacteur;
    private String type;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFichier() {
        return fichier;
    }

    public void setFichier(String fichier) {
        this.fichier = fichier;
    }

    public int getIdRedacteur() {
        return idRedacteur;
    }

    public void setIdRedacteur(int idRedacteur) {
        this.idRedacteur = idRedacteur;
    }
public Publication()
{
    
}
    public Publication(int idPublication, String titre, String contenu, Date date, String image, String fichier, int idRedacteur) {
        this.idPublication = idPublication;
        this.titre = titre;
        this.contenu = contenu;
        this.date = date;
        this.image = image;
        this.fichier = fichier;
        this.idRedacteur = idRedacteur;
    }

    public Publication(int idPublication, String titre, String contenu, Date date) {
        this.idPublication = idPublication;
        this.titre = titre;
        this.contenu = contenu;
        this.date = date;
    }

    public int getIdPublication() {
        return idPublication;
    }

    public void setIdPublication(int idPublication) {
        this.idPublication = idPublication;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
}
