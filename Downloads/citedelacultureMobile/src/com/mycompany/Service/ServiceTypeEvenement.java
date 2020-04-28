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
import com.mycompany.Entite.TypeEvenement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceTypeEvenement {

    public void ajouterType(TypeEvenement te) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/citedelacultureWeb/web/app_dev.php/webService/ajouterTypeEvenement?type=" + te.getType() + "&description=" + te.getDescription();// création de l'URL
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public ArrayList<TypeEvenement> parseListTypesJson(String json) {
        
        ArrayList<TypeEvenement> listTypes = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> types = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) types.get("root");
            for (Map<String, Object> obj : list) {
                TypeEvenement t = new TypeEvenement();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                if((obj.get("type")!=null))
                    t.setType(obj.get("type").toString());
                if((obj.get("description")!=null))
                    t.setDescription(obj.get("description").toString());
                System.out.println(t);
                listTypes.add(t);
            }
        } catch (IOException ex) {
        }
        System.out.println(listTypes);
        return listTypes;
    
    }

    ArrayList<TypeEvenement> listTypes = new ArrayList();

    public ArrayList<TypeEvenement> getListTypes() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/citedelacultureWeb/web/app_dev.php/webService/getTypesEvenement");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceTypeEvenement ser = new ServiceTypeEvenement();
                listTypes = ser.parseListTypesJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTypes;
    }

}
