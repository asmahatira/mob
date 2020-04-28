/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.Evenement;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
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
import com.mycompany.Entite.Evenement;
import com.mycompany.Service.ServiceEvenement;
import com.mycompany.Service.ServiceTypeEvenement;
import com.mycompany.Service.ServiceUser;
import com.mycompany.gui.User.Profile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mezri
 */
public class ListeEvenements {

    Form f;
    ImageViewer ip;
    List<Evenement> lse = new ArrayList();
    User user;

    public ListeEvenements(User u, Form precedent) {

        f = new Form("Liste des evenements", BoxLayout.y());
        user=u;
        Toolbar tb = f.getToolbar();
        lse = new ServiceEvenement().getListEvenements();
        for (int i = 0; i < lse.size(); i++) {
            addItem(lse.get(i));
        }
        
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

    public void addItem(Evenement e) {

        Image imgs;
        String url = "http://localhost/citedelacultureWEB/web/uploads/images/offre/" + e.getImage();

        try {
            EncodedImage enc = EncodedImage.create("/load.png");
            imgs = URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE);
            ip = new ImageViewer(imgs);
            Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

            Label l1 = new Label("nom : "+e.getNom());
            Label l2 = new Label("prix : "+e.getPrix() + " dt");
            Label l = new Label("Commance le :");
            Label l3 = new Label(e.getDate());
            Label l4 = new Label("salle : "+e.getEspace());
            l1.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    DetailEvenement de = new DetailEvenement(user, e, f);
                    de.getF().show();
                }
            });
            c1.add(ip);
            c2.add(l1);
            c2.add(l2);
            c2.add(l);
            c2.add(l3);
            c2.add(l4);
            c1.add(c2);
            c1.setLeadComponent(l1);
            f.add(c1);
            f.refreshTheme();
        } catch (IOException ex) {
        }    
    }
}
