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
import com.mycompany.Entite.Club;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author nader
 */
public class ServiceClub {

    public void ajoutClub(Club C) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/citedelacultureWEB/web/app_dev.php/webService/ajouterClub?idUser=1" + C.getIdClub() + "&nom=" + C.getNom() + "&description=" + C.getDescription() + "&cotisationannuelle=" + C.getCotisationannuelle();
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public ArrayList<Club> parseListClubJson(String json) {

        ArrayList<Club> listclubs = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();

            Map<String, Object> clubs = j.parseJSON(new CharArrayReader(json.toCharArray()));
            if (!clubs.isEmpty()) {
                java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) clubs.get("root");
                for (Map<String, Object> obj : list) {

                    Club c = new Club();

                    float idClub = Float.parseFloat(obj.get("idclub").toString());
                    float cotisationannuelle = Float.parseFloat(obj.get("cotisationannuelle").toString());

                    c.setIdClub((int) idClub);
                    c.setNom(obj.get("nom").toString());
                    c.setDescription(obj.get("description").toString());
                    c.setCotisationannuelle((int) cotisationannuelle);
                    System.out.println(c);

                    listclubs.add(c);
                }
            }

        } catch (IOException ex) {
            System.err.println(ex);
        }

        System.out.println(listclubs);
        return listclubs;
    }

    ArrayList<Club> listclubs = new ArrayList<>();

    public ArrayList<Club> getList2() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/citedelacultureWEB/web/app_dev.php/webService/getClubs");
        con.addResponseListener((NetworkEvent evt) -> {
            ServiceClub ser = new ServiceClub();
            listclubs = ser.parseListClubJson(new String(con.getResponseData()));
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listclubs;
    }
}
