/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.Evenement;

import com.codename1.components.ImageViewer;
import com.codename1.components.ShareButton;
import com.codename1.components.ToastBar;
import com.codename1.db.Database;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.mycompany.Entite.AvisEvenement;
import com.mycompany.Entite.Evenement;
import com.mycompany.Entite.User;
import com.mycompany.Service.FacebookShare;
import com.mycompany.Service.ServiceAvisEvenement;
import com.mycompany.Service.ServiceEvenement;
import com.mycompany.gui.Reclamation.AjouterReclamation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mezri
 */
public class DetailEvenement {

    Form f;
    ImageViewer ip;
    List<Evenement> lse = new ArrayList();

    public DetailEvenement(User u, Evenement e, Form precedent) {

        try {
            f = new Form("Detail evenements", BoxLayout.y());
            Toolbar tb = f.getToolbar();
            Image imgs;
            String url = "http://localhost/citedelacultureWEB/web/uploads/images/offre/" + e.getImage();
            EncodedImage enc = EncodedImage.create("/load.png");
            imgs = URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE);
            ip = new ImageViewer(imgs);

            Label lbl1 = new Label("nom : ");
            TextField nom = new TextField(e.getNom());
            Label lbl2 = new Label("description : ");
            TextField description = new TextField(e.getDescription());
            Label lbl3 = new Label("prix : ");
            TextField prix = new TextField(e.getPrix() + "");
            Label lbl4 = new Label("termine : ");
            TextField termine = new TextField();
            if (e.isTermine()) {
                termine.setText("oui");
            } else {
                termine.setText("non");
            }
            Label lbl5 = new Label("date : ");
            TextField date = new TextField(e.getDate());
            Label lbl6 = new Label("espace : ");
            TextField espace = new TextField(e.getEspace());
            Label lbl7 = new Label("type : ");
            TextField type = new TextField(e.getType());
            Label lbl8 = new Label("Avis : ");

            AvisEvenement a = null;
            if (u != null) {
                a = new ServiceAvisEvenement().getMonAvis(e.getIdEvenement(), u.getId());
            }

            Slider s = new Slider();
            s.setMinValue(0);
            s.setMaxValue(6);
            s.setEditable(true);
            if (a != null) {
                lbl8.setText("Avis : " + a.getValeur());
                s.setProgress(new ServiceAvisEvenement().getMonAvis(e.getIdEvenement(), u.getId()).getValeur());
            }

            Button btReclamer = new Button("Reclamer");
            nom.setEnabled(false);
            description.setEnabled(false);
            prix.setEnabled(false);
            termine.setEnabled(false);
            date.setEnabled(false);
            espace.setEnabled(false);
            type.setEnabled(false);

            ShareButton sb = new ShareButton();
            f.add(sb);
            sb.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    try {
                        FacebookShare fb = new FacebookShare("67e88003f8a56f36dfe98e69974a71e0");
                        fb.share("test cite de la culture");
                    } catch (IOException ex) {
                    }
                }
            });

            f.add(ip);
            f.add(lbl1);
            f.add(nom);
            f.add(lbl2);
            f.add(description);
            f.add(lbl3);
            f.add(prix);
            f.add(lbl4);
            f.add(termine);
            f.add(lbl5);
            f.add(date);
            f.add(lbl6);
            f.add(espace);
            f.add(lbl7);
            f.add(type);
            if (u != null) {
                f.add(lbl8);
                f.add(s);
                f.add(btReclamer);
            }

            tb.addCommandToLeftBar(
                    "< Retour", null, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt
                ) {
                    precedent.show();
                }
            }
            );

            s.addDataChangedListener(new DataChangedListener() {
                @Override
                public void dataChanged(int type, int index) {
                    new ServiceAvisEvenement().ajouterAvisEvenement(e.getIdEvenement(), u.getId(), s.getProgress(), "");
                    f.refreshTheme();
                }
            }
            );

            btReclamer.addActionListener(
                    new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt
                ) {
                    AjouterReclamation ar = new AjouterReclamation(e, u, f);
                    ar.getF().show();
                }
            }
            );
        } catch (IOException ex) {
        }
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
