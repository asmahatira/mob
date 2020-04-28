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
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.mycompany.Entite.Publication;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mezri
 */
public class ServicePublication {

    public ArrayList<Publication> getList2() {
        ArrayList<Publication> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/citedelacultureWEB/web/app_dev.php/publication_all");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(tasks);
                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) {
                        Publication task = new Publication();

                        task.setIdPublication((int) Float.parseFloat(obj.get("idpublication").toString()));
                        task.setTitre(obj.get("titre").toString());
                        task.setContenu(obj.get("contenu").toString());
                        task.setType(obj.get("type").toString());

                        task.setImage(obj.get("image").toString());

                        listTasks.add(task);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }

    public void supprimerPub(Publication e) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/citedelacultureWEB/web/app_dev.php/deleteP/" + e.getIdPublication();
        con.setUrl(Url);
        con.addResponseListener((ee) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
            Dialog.show("Succ�s", "Publication supprim�e", "Terminer", null);

//            Affichage a = new Affichage();
//            a.getF().show();
        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }

    public ArrayList<Publication> getListEval(int idbp) {
        ArrayList<Publication> listEvaluations = new ArrayList<>();
        ConnectionRequest con1 = new ConnectionRequest();
        con1.setUrl("http://localhost:8080/3A/forsafinal/web/app_dev.php/mobile/evals/idbp/" + idbp);
        con1.addResponseListener(e -> {
            JSONParser jsonp = new JSONParser();
            try {
                Map<String, Object> evaluations = jsonp.parseJSON(new CharArrayReader(new String(con1.getResponseData()).toCharArray()));

                List<Map<String, Object>> list = (List<Map<String, Object>>) evaluations.get("root");
                for (Map<String, Object> obj : list) {

                    Publication task = new Publication();

                    task.setIdPublication((int) Float.parseFloat(obj.get("idpublication").toString()));
                    task.setTitre(obj.get("titre").toString());
                    task.setContenu(obj.get("contenu").toString());
                    task.setType(obj.get("type").toString());

                    task.setImage(obj.get("image").toString());

                    listEvaluations.add(task);

                }
            } catch (IOException ex) {
                System.out.println("probleme fetch eval");
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(con1);
        return listEvaluations;
    }
}
