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
import com.mycompany.Entite.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceUser {

    User gUser = new User();

    public User parseUserJson(String json) {
        User u = null;
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
            if (!user.isEmpty()) {
                u = new User();
                List<String> rolesUser = new ArrayList();
                float id = Float.parseFloat(user.get("id").toString());
                u.setId((int) id);
                List<String> roles = (List<String>) user.get("roles");
                for (String role : roles) {
                    rolesUser.add(role);
                }
                u.setRoles(rolesUser);
                u.setUsername(user.get("username").toString());
                u.setEmail(user.get("email").toString());
                u.setEnabled(Boolean.parseBoolean(user.get("enabled").toString()));
                if (user.get("tel") != null) {
                    float tel = Float.parseFloat(user.get("tel").toString());
                    u.setTel((int) tel);
                }
                u.setSubscribe(Boolean.parseBoolean(user.get("subscribe").toString()));
                u.setBan(Boolean.parseBoolean(user.get("ban").toString()));
            }
        } catch (IOException ex) {

        }
        return u;

    }

    public User login(String username, String password) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/citedelacultureWeb/web/app_dev.php/webService/loginService?username=" + username + "&password=" + password);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceUser ser = new ServiceUser();
                gUser = ser.parseUserJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return gUser;
    }
    
    public User getUserParId(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/citedelacultureWeb/web/app_dev.php/webService/getUserParId/"+id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceUser ser = new ServiceUser();
                gUser = ser.parseUserJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return gUser;
    }
    
    public User modifierProfil(int id, String username, String email, int tel, boolean subscribe) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/citedelacultureWeb/web/app_dev.php/webService/modifierProfile?id="+id+"&username="+username+"&email="+email+"&tel="+tel+"&subscribe="+subscribe);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceUser ser = new ServiceUser();
                gUser = ser.parseUserJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return gUser;
    }
    
    public void desabonner(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/citedelacultureWeb/web/app_dev.php/webService/desabonner?idUser="+id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceUser ser = new ServiceUser();
                gUser = ser.parseUserJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    public void abonner(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/citedelacultureWeb/web/app_dev.php/webService/abonner?idUser="+id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceUser ser = new ServiceUser();
                gUser = ser.parseUserJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    public User modifierMotDePasse(int id, String password) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/citedelacultureWeb/web/app_dev.php/webService/modifierMotDePasse?id="+id+"&password="+password);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceUser ser = new ServiceUser();
                gUser = ser.parseUserJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return gUser;
    }

}
