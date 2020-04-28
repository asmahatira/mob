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
import com.mycompany.Entite.Evenement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mezri
 */
public class ServiceEvenement {
    public ArrayList<Evenement> parseListEvenementsJson(String json) {
        
        ArrayList<Evenement> listEvenements = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> evenements = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) evenements.get("root");
            for (Map<String, Object> obj : list) {
                Evenement e = new Evenement();
                float id = Float.parseFloat(obj.get("idevenement").toString());
                e.setIdEvenement((int) id);
                e.setNom(obj.get("nom").toString());
                e.setDescription(obj.get("description").toString());
                float prix = Float.parseFloat(obj.get("prix").toString());
                e.setPrix(prix);
                e.setTermine(Boolean.parseBoolean(obj.get("termine").toString()));
                e.setConfirme(Boolean.parseBoolean(obj.get("confirme").toString()));
                e.setDate(getDateEvenement((int) id));
                Map<String, Object> type = (Map<String, Object>) obj.get("Type");
                e.setType(type.get("type").toString());
                Map<String, Object> espace = (Map<String, Object>) obj.get("Espace");
                e.setEspace(espace.get("description").toString());
                Map<String, Object> user = (Map<String, Object>) obj.get("user");
                float iduser = Float.parseFloat(user.get("id").toString());
                e.setUser(new ServiceUser().getUserParId((int) iduser));
                e.setImage(obj.get("image").toString());
                System.out.println(e);
                listEvenements.add(e);
            }
        } catch (IOException ex) {
        }
        return listEvenements;
    
    }

    ArrayList<Evenement> listEvenements = new ArrayList();
    String date = new String();

    public String getDateEvenement(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/citedelacultureWeb/web/app_dev.php/webService/getDateEvenement/"+id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                date = new String(con.getResponseData());
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return date;
    }
    
    public ArrayList<Evenement> getListEvenements() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/citedelacultureWeb/web/app_dev.php/webService/getEvenementsConfirmes");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceEvenement ser = new ServiceEvenement();
                listEvenements = ser.parseListEvenementsJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEvenements;
    }
}
