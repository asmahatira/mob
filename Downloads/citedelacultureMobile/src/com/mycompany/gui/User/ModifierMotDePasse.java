/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.User;

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
public class ModifierMotDePasse {

    Form f;

    public ModifierMotDePasse(User u, Form precedent) {

        f = new Form("Modifier mot de passe", BoxLayout.y());
        Toolbar tb = f.getToolbar();
        Label lbl1 = new Label("Mot de passe actuelle : ");
        TextField tfPasswordAct = new TextField();
        Label lbl2 = new Label("Mot de passe : ");
        TextField tfPassword1 = new TextField(null, "", 0, TextField.PASSWORD);
        Label lbl3 = new Label("réécrire mot de passe : ");
        TextField tfPassword2 = new TextField(null, "", 0, TextField.PASSWORD);

        Button btModifier = new Button("Modifier");

        f.add(lbl1);
        f.add(tfPasswordAct);
        f.add(lbl2);
        f.add(tfPassword1);
        f.add(lbl3);
        f.add(tfPassword2);
        f.add(btModifier);

        btModifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (!tfPasswordAct.getText().equals(u.getPassword())) {
                    Dialog.show("Alerte", "verifier votre mot de passe actuelle ! ", "ok", null);
                } else if (!tfPassword1.getText().equals(tfPassword2.getText())) {
                    Dialog.show("Alerte", "verifier votre nouvelle mot de passe ! ", "ok", null);
                } else if (tfPassword1.getText().length()<6 || tfPassword2.getText().length()<=6) {
                    Dialog.show("Alerte", "Taille mot de passe < 6 ! ", "ok", null);
                } else {
                    boolean yes = Dialog.show("Alerte", "Voulez-vous vraiment enregistrer les modifications ?", "oui", "non");
                    if (yes) {
                        ServiceUser su = new ServiceUser();
                        su.modifierMotDePasse(u.getId(), tfPassword1.getText());
                        Dialog.show("Succès", "mot de passe modifié ! ", "ok", null);
                        Profile p = new Profile(u, f);
                        p.getF().show();
                    }
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
