/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.User;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.Entite.User;
import com.mycompany.gui.DashboardClient;

/**
 *
 * @author Dell
 */
public class Profile {

    Form f;

    public Profile(User u, Form precedent) {

        f = new Form("Mon profile", BoxLayout.y());
        Toolbar tb = f.getToolbar();
        Label lbl1 = new Label("Nom d'utilisateur (username) : ");
        TextField tfUsername = new TextField(u.getUsername());
        Label lbl2 = new Label("E-Mail : ");
        TextField tfEmail = new TextField(u.getEmail());
        Label lbl3 = new Label("Activé : ");
        TextField tfEnable = new TextField();
        if (u.isEnabled()) {
            tfEnable.setText("oui");
        } else {
            tfEnable.setText("non");
        }
        Label lbl4 = new Label("Role : ");
        TextField tfRole = new TextField(u.getRoles().get(0));
        Label lbl5 = new Label("Numero de téléphone : ");
        TextField tfTel = new TextField(u.getTel() + "");
        Label lbl7 = new Label("Blockage de reclamations : ");
        TextField tfBan = new TextField();
        if (u.isBan()) {
            tfBan.setText("oui");
        } else {
            tfBan.setText("non");
        }
        Label lbl6 = new Label("Abonné au offres : ");
        TextField tfSubscribe = new TextField();
        if (u.isSubscribe()) {
            tfSubscribe.setText("oui");
        } else {
            tfSubscribe.setText("non");
        }
        Button btModifierProfil = new Button("Modifier mon profil");
        Button btModifierMotDePasse = new Button("Modifier mot de passe");

        tfUsername.setEnabled(false);
        tfEmail.setEnabled(false);
        tfEnable.setEnabled(false);
        tfRole.setEnabled(false);
        tfTel.setEnabled(false);
        tfSubscribe.setEnabled(false);
        tfBan.setEnabled(false);

        f.add(lbl1);
        f.add(tfUsername);
        f.add(lbl2);
        f.add(tfEmail);
        f.add(lbl3);
        f.add(tfEnable);
        f.add(lbl4);
        f.add(tfRole);
        f.add(lbl5);
        f.add(tfTel);
        f.add(lbl6);
        f.add(tfSubscribe);
        f.add(lbl7);
        f.add(tfBan);
        f.add(btModifierProfil);
        f.add(btModifierMotDePasse);

        btModifierProfil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ModifierProfile mp = new ModifierProfile(u, f);
                mp.getF().show();
            }
        });

        btModifierMotDePasse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ModifierMotDePasse mmp = new ModifierMotDePasse(u, f);
                mmp.getF().show();
            }
        });

        tb.addCommandToLeftBar("< Retour", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                precedent.show();
            }
        });

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
