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
import com.mycompany.Entite.Offre;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mezri
 */
public class ServiceOffre {

    public ArrayList<Offre> parseListOffresJson(String json) {

        ArrayList<Offre> listOffres = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> Offres = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) Offres.get("root");
            for (Map<String, Object> obj : list) {
                Offre o = new Offre();
                float id = Float.parseFloat(obj.get("id").toString());
                o.setId((int) id);
                Map<String, Object> evenement = (Map<String, Object>) obj.get("evenement");
                o.setEvenement(evenement.get("nom").toString());
                float remise = Float.parseFloat(obj.get("remise").toString());
                o.setRemise(remise);
                float prixDeb = Float.parseFloat(obj.get("prixDebut").toString());
                o.setPrixDebut(prixDeb);
                float prixFin = Float.parseFloat(obj.get("prixFinal").toString());
                o.setPrixFinal(prixFin);
                float like = Float.parseFloat(obj.get("likesnumber").toString());
                o.setLikesnumber((int) like);
                float dislike = Float.parseFloat(obj.get("dislikesnumber").toString());
                o.setDateDebut(getDateDebut((int) id));
                o.setDateFin(getDateFin((int) id));
                o.setDislikesnumber((int) dislike);
                o.setImage(obj.get("image").toString());
                System.out.println(o);
                listOffres.add(o);
            }
        } catch (IOException ex) {
        }
        return listOffres;

    }

    public String getDateDebut(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/citedelacultureWeb/web/app_dev.php/webService/getDateDebut/" + id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                dateDeb = new String(con.getResponseData());
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return dateDeb;
    }

    public String getDateFin(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/citedelacultureWeb/web/app_dev.php/webService/getDateFin/" + id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                dateFin = new String(con.getResponseData());
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return dateFin;
    }

    ArrayList<Offre> listOffres = new ArrayList();
    String dateDeb = new String();
    String dateFin = new String();

    public ArrayList<Offre> getListOffres() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/citedelacultureWeb/web/app_dev.php/webService/getOffres");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceOffre ser = new ServiceOffre();
                listOffres = ser.parseListOffresJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listOffres;
    }
    boolean succes = false;

    public boolean rating(boolean rating, int idOffre, int idUser) {

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/citedelacultureWeb/web/app_dev.php/webService/rating?idOffre=" + idOffre + "&idUser=" + idUser + "&like=" + rating);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String s = new String(con.getResponseData());
                if (s.equals("false")) {
                    succes = false;
                } else {
                    succes = true;
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return succes;
    }
}
