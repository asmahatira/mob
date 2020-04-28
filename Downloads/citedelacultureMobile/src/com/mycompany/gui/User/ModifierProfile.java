/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.User;

import com.codename1.components.OnOffSwitch;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.Entite.User;
import com.mycompany.Service.ServiceUser;

/**
 *
 * @author Mezri
 */
public class ModifierProfile {

    Form f;

    public ModifierProfile(User u, Form precedent) {

        f = new Form("Modifier mon profile", BoxLayout.y());
        Toolbar tb = f.getToolbar();
        Label lbl1 = new Label("Nom d'utilisateur (username) : ");
        TextField tfUsername = new TextField(u.getUsername());
        Label lbl2 = new Label("E-Mail : ");
        TextField tfEmail = new TextField(u.getEmail());
        Label lbl5 = new Label("Numero de téléphone : ");
        TextField tfTel = new TextField(u.getTel() + "");
        Label lbl6 = new Label("Abonné au offres : ");
        OnOffSwitch swSubscribe = new OnOffSwitch();
        if (u.isSubscribe()) {
            swSubscribe.setValue(true);
        } else {
            swSubscribe.setValue(false);
        }
        Button btModifier = new Button("Modifier");

        f.add(lbl1);
        f.add(tfUsername);
        f.add(lbl2);
        f.add(tfEmail);
        f.add(lbl5);
        f.add(tfTel);
        f.add(lbl6);
        f.add(swSubscribe);
        f.add(btModifier);

        btModifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                boolean yes = Dialog.show("Alerte", "Voulez-vous vraiment enregistrer les modifications ?", "oui", "non");
                if (yes) {
                    ServiceUser su = new ServiceUser();
                    su.modifierProfil(u.getId(), tfUsername.getText(), tfEmail.getText(), Integer.parseInt(tfTel.getText()), swSubscribe.isValue());
                    u.setUsername(tfUsername.getText());
                    u.setEmail(tfEmail.getText());
                    u.setTel(Integer.parseInt(tfTel.getText()));
                    u.setSubscribe(swSubscribe.isValue());
                    Profile p = new Profile(u, f);
                    p.getF().show();
                }
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
