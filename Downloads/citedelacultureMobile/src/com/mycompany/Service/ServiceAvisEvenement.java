/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.Entite.AvisEvenement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mezri
 */
public class ServiceAvisEvenement {

    public void ajouterAvisEvenement(int idEvenement, int idUser, int valeur, String commentaire) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/citedelacultureWeb/web/app_dev.php/webService/ajouterAvisEvenement?idEvenement=" + idEvenement + "&idUser=" + idUser + "&valeur=" + valeur + "&commentaire=" + commentaire;// création de l'URL
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    /*public ArrayList<AvisEvenement> parseListAvissJson(String json) {
        
        ArrayList<AvisEvenement> listAviss = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> Aviss = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) Aviss.get("root");
            for (Map<String, Object> obj : list) {
                AvisEvenement t = new AvisEvenement();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                if((obj.get("Avis")!=null))
                    t.setAvis(obj.get("Avis").toString());
                if((obj.get("description")!=null))
                    t.setDescription(obj.get("description").toString());
                System.out.println(t);
                listAviss.add(t);
            }
        } catch (IOException ex) {
        }
        System.out.println(listAviss);
        return listAviss;
    }*/
    public AvisEvenement parseAvissJson(String json) {

        AvisEvenement avis = null;
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> Aviss = j.parseJSON(new CharArrayReader(json.toCharArray()));
            AvisEvenement a = new AvisEvenement();
            if (!Aviss.isEmpty()) {
                float valeur = Float.parseFloat(Aviss.get("valeur").toString());
                a.setValeur((int) valeur);
                avis = a;
            }
        } catch (IOException ex) {
        }
        return avis;
    }

    AvisEvenement Avis = new AvisEvenement();

    public AvisEvenement getMonAvis(int idEvenement, int idUser) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/citedelacultureWeb/web/app_dev.php/webService/getMonAvisEvenement?idUser=" + idUser + "&idEvenement=" + idEvenement);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceAvisEvenement ser = new ServiceAvisEvenement();
                Avis = ser.parseAvissJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return Avis;
    }
}
