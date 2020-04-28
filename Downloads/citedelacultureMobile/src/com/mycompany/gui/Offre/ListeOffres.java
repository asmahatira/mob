/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.Offre;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.Entite.User;
import com.mycompany.Entite.Offre;
import com.mycompany.Service.ServiceOffre;
import com.mycompany.Service.ServiceUser;
import com.mycompany.gui.DashboardVisiteur;
import com.mycompany.gui.User.Profile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mezri
 */
public class ListeOffres {

    Form f;
    ImageViewer ip;
    List<Offre> lse = new ArrayList();
    User user;

    public ListeOffres(User u, Form precedent) {

        f = new Form("Liste des Offres", BoxLayout.y());
        user = u;
        Toolbar tb = f.getToolbar();
        lse = new ServiceOffre().getListOffres();
        Button btAbonner = new Button("Abonner");
        Button btDesabonner = new Button("Desabonner");
        //si connecté
        if (u != null) {
            f.add(btAbonner);
            f.add(btDesabonner);
            if (u.isSubscribe()) {
                btAbonner.setVisible(false);
                btDesabonner.setVisible(true);
            } else {
                btAbonner.setVisible(true);
                btDesabonner.setVisible(false);
            }
        }

        for (int i = 0; i < lse.size(); i++) {
            addItem(lse.get(i));
        }
        btAbonner.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //notification
                ToastBar.Status status = ToastBar.getInstance().createStatus();
                status.setMessage("Vous etes abonné");
                status.show();
                
                ServiceUser su = new ServiceUser();
                su.abonner(u.getId());
                btAbonner.setVisible(false);
                btDesabonner.setVisible(true);
                f.refreshTheme();
            }
        });
        btDesabonner.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ToastBar.Status status = ToastBar.getInstance().createStatus();
                status.setMessage("Vous etes désabonné");
                status.show();
                ServiceUser su = new ServiceUser();
                su.desabonner(u.getId());
                btAbonner.setVisible(true);
                btDesabonner.setVisible(false);
                f.refreshTheme();
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

    public void addItem(Offre o) {

        Image imgs;
        String url = "http://localhost/citedelacultureWEB/web/uploads/images/offre/" + o.getImage();

        try {
            EncodedImage enc = EncodedImage.create("/load.png");
            imgs = URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE);
            ip = new ImageViewer(imgs);
            Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

            Label l1 = new Label("Event : " + o.getEvenement());
            Label l2 = new Label("date deb : ");
            Label l3 = new Label(o.getDateDebut());
            Label l4 = new Label("date fin :");
            Label l5 = new Label(o.getDateFin());
            Label l6 = new Label("Remise : " + o.getRemise() + "%");
            l1.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    new DetailOffre(user, o, f).getF().show();
                }
            });
            c1.add(ip);
            c2.add(l1);
            c2.add(l2);
            c2.add(l3);
            c2.add(l4);
            c2.add(l5);
            c2.add(l6);
            c1.add(c2);
            c1.setLeadComponent(l1);
            f.add(c1);
            f.refreshTheme();
        } catch (IOException ex) {
        }
    }
}
