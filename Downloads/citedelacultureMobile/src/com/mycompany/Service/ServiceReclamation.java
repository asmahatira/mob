/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Service;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.mycompany.Entite.TypeEvenement;

/**
 *
 * @author Mezri
 */
public class ServiceReclamation {
    public void ajouterReclamation(int idUser, int idEvenement, String contenu, String titre) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/citedelacultureWeb/web/app_dev.php/webService/ajouterReclamation?idUser=" + idUser + "&idEvenement=" + idEvenement + "&contenu=" + contenu + "&titre=" + titre;// création de l'URL
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
}
