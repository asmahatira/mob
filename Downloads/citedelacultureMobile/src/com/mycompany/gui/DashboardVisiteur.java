/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.Entite.User;
import com.mycompany.gui.Club.affichage;
import com.mycompany.gui.Evenement.ListeEvenements;
import com.mycompany.gui.Offre.ListeOffres;
import com.mycompany.gui.Publication.AffPub;
import com.mycompany.gui.User.Profile;

/**
 *
 * @author Mezri
 */
public class DashboardVisiteur {

    Form f;

    public DashboardVisiteur(Form precedent) {

        f = new Form("Bienvenu au cite de la culture de Tunis ", BoxLayout.y());

        Toolbar tb = f.getToolbar();
        tb.addMaterialCommandToSideMenu("Publications", FontImage.MATERIAL_PEOPLE, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                AffPub P = new AffPub(f);
                P.getF().show();
            }
        });
        tb.addMaterialCommandToSideMenu("Evenements", FontImage.MATERIAL_PEOPLE, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ListeEvenements e = new ListeEvenements(null, f);
                e.getF().show();
            }
        });
        tb.addMaterialCommandToSideMenu("Offres", FontImage.MATERIAL_PEOPLE, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ListeOffres lo = new ListeOffres(null, f);
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
        tb.addCommandToOverflowMenu("Se connecter", null, new ActionListener() {
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
