/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.mycompany.gui.User.Profile;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.Entite.User;
import com.mycompany.Service.ServiceUser;
import com.mycompany.gui.Club.affichage;
import com.mycompany.gui.Evenement.ListeEvenements;
import com.mycompany.gui.Offre.ListeOffres;
import com.mycompany.gui.Publication.AffPub;

/**
 *
 * @author Mezri
 */
public class DashboardClient {

    Form f;

    public DashboardClient(User u, Form precedent) {

        f = new Form("Bonjour " + u.getUsername(), BoxLayout.y());

        Toolbar tb = f.getToolbar();
        tb.addMaterialCommandToSideMenu("Publications", FontImage.MATERIAL_PEOPLE, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                AffPub P = new AffPub(f);
                P.getF().show();
            }
        });
        tb.addMaterialCommandToSideMenu("Mon profil", FontImage.MATERIAL_PEOPLE, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new Profile(u, f).getF().show();
            }
        });
        tb.addMaterialCommandToSideMenu("Evenements", FontImage.MATERIAL_PEOPLE, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ListeEvenements e = new ListeEvenements(u, f);
                e.getF().show();
            }
        });
        tb.addMaterialCommandToSideMenu("Offres", FontImage.MATERIAL_PEOPLE, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ListeOffres lo = new ListeOffres(u, f);
                lo.getF().show();
            }
        });
        tb.addMaterialCommandToSideMenu("Clubs", FontImage.MATERIAL_PEOPLE, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                affichage P = new affichage(f);
                P.getF().show();
            }
        });

        tb.addCommandToOverflowMenu("Déconnecter", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                boolean yes = Dialog.show("Alerte", "Voulez-vous vraiment se déconnecter ?", "oui", "non");
                if (yes) {
                    precedent.show();
                }
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
