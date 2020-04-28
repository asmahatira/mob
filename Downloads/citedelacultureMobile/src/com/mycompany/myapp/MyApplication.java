package com.mycompany.myapp;

import com.mycompany.gui.DashboardClient;
import com.mycompany.gui.DashboardVisiteur;
import com.codename1.components.ImageViewer;
import static com.codename1.ui.CN.*;

import com.codename1.ui.Form;
import com.codename1.ui.Dialog;

import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.Entite.User;
import com.mycompany.Service.ServiceEvenement;
import com.mycompany.Service.ServiceUser;

/**
 * This file was generated by <a href="https://www.codenameone.com/">Codename
 * One</a> for the purpose of building native mobile applications using Java.
 */
public class MyApplication {

    private Form hi;
    private Resources theme;

    public void init(Object context) {
        // use two network threads instead of one
        updateNetworkThreadCount(2);

        theme = UIManager.initFirstTheme("/theme");

        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);

        // Pro only feature
        Log.bindCrashProtection(true);

        addNetworkErrorListener(err -> {
            // prevent the event from propagating
            err.consume();
            if (err.getError() != null) {
                Log.e(err.getError());
            }
            Log.sendLogAsync();
            Dialog.show("Connection Error", "There was a networking error in the connection to " + err.getConnectionRequest().getUrl(), "OK", null);
        });
    }

    public void start() {

        hi = new Form("S'authentifier", BoxLayout.y());

        Button btConnecter = new Button("Connecter");
        Button btVisiter = new Button("Continuer sans authentification");
        TextField tfUsername = new TextField(null, "nom d'utilisateur");
        TextField tfPassword = new TextField(null, "mot de passe", 0, TextField.PASSWORD);
        ImageViewer i = new ImageViewer(theme.getImage("logo.png"));
        hi.add(i);
        hi.add(tfUsername);
        hi.add(tfPassword);
        hi.add(btConnecter);
        hi.add(btVisiter);
        hi.show();
        btConnecter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                User u = new ServiceUser().login(tfUsername.getText(), tfPassword.getText());
                if (u == null) {
                    Dialog.show("Alerte", "username et/ou mot de passe incorrect ! ", "ok", null);
                } else if (u != null && u.getRoles().get(0).equals("ROLE_USER")) {
                    u.setPassword(tfPassword.getText());
                    tfUsername.setText("");
                    tfPassword.setText("");
                    DashboardClient dc = new DashboardClient(u, hi);
                    dc.getF().show();
                } else {
                    Dialog.show("Désolé", "Vous ne pouvez pas accéder à notre application en tant que " + u.getUsername() + " ! ", "ok", null);
                }
            }
        });

        btVisiter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                tfUsername.setText("");
                tfPassword.setText("");
                DashboardVisiteur dv = new DashboardVisiteur(hi);
                dv.getF().show();
            }
        });
    }

    public void stop() {
        hi = getCurrentForm();
        if (hi instanceof Dialog) {
            ((Dialog) hi).dispose();
            hi = getCurrentForm();
        }
    }

    public void destroy() {
    }

    public Form getF() {
        return hi;
    }

    public void setF(Form hi) {
        this.hi = hi;
    }
}