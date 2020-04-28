/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.Offre;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.Entite.Evenement;
import com.mycompany.Entite.Offre;
import com.mycompany.Entite.User;
import com.mycompany.Service.ServiceOffre;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mezri
 */
public class DetailOffre {

    Form f;
    ImageViewer ip;
    List<Evenement> lse = new ArrayList();

    public DetailOffre(User u, Offre o, Form precedent) {

        try {
            f = new Form("Detail offres", BoxLayout.y());
            Toolbar tb = f.getToolbar();
            Image imgs;
            String url = "http://localhost/citedelacultureWEB/web/uploads/images/offre/" + o.getImage();
            EncodedImage enc = EncodedImage.create("/load.png");
            imgs = URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE);
            ip = new ImageViewer(imgs);

            Label lbl1 = new Label("evenement : ");
            TextField evenement = new TextField(o.getEvenement());
            Label lbl2 = new Label("remise : ");
            TextField remise = new TextField("" + o.getRemise());
            Label lbl3 = new Label("prixDebut : ");
            TextField prixDebut = new TextField(o.getPrixDebut() + "");
            Label lbl4 = new Label("prixFinal : ");
            TextField prixFinal = new TextField(o.getPrixFinal() + "");
            Label lbl5 = new Label("dateDebut : ");
            TextField dateDebut = new TextField(o.getDateDebut());
            Label lbl6 = new Label("dateFin : ");
            TextField dateFin = new TextField(o.getDateFin());
            Label lbl7 = new Label("likesnumber : ");
            TextField likesnumber = new TextField(o.getLikesnumber() + "");
            Label lbl8 = new Label("dislikesnumber : ");
            TextField dislikesnumber = new TextField(o.getDislikesnumber() + "");

            evenement.setEnabled(false);
            remise.setEnabled(false);
            prixDebut.setEnabled(false);
            prixFinal.setEnabled(false);
            dateDebut.setEnabled(false);
            dateFin.setEnabled(false);
            likesnumber.setEnabled(false);
            dislikesnumber.setEnabled(false);

            f.add(ip);
            f.add(lbl1);
            f.add(evenement);
            f.add(lbl2);
            f.add(remise);
            f.add(lbl3);
            f.add(prixDebut);
            f.add(lbl4);
            f.add(prixFinal);
            f.add(lbl5);
            f.add(dateDebut);
            f.add(lbl6);
            f.add(dateFin);
            f.add(lbl7);
            f.add(likesnumber);
            f.add(lbl8);
            f.add(dislikesnumber);
            //si connecté
            if (u != null) {
                Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                Label l = new Label("Rating : ");
                Label nblike = new Label(o.getLikesnumber() + "");
                Button like = new Button(":) like");
                Button dislike = new Button("Dislike :(");
                Label nbdislike = new Label(o.getDislikesnumber() + "");
                c1.add(nblike);
                c1.add(like);
                c1.add(dislike);
                c1.add(nbdislike);
                f.removeComponent(lbl7);
                f.removeComponent(likesnumber);
                f.removeComponent(lbl8);
                f.removeComponent(dislikesnumber);
                f.add(l);
                f.add(c1);
                f.refreshTheme();
                like.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (new ServiceOffre().rating(true, o.getId(), u.getId())) {
                            o.setLikesnumber(o.getLikesnumber() + 1);
                            nblike.setText(o.getLikesnumber() + "");
                            o.setDislikesnumber(o.getDislikesnumber() - 1);
                            nbdislike.setText(o.getDislikesnumber() + "");
                            f.refreshTheme();
                        }
                    }
                });
                dislike.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (new ServiceOffre().rating(false, o.getId(), u.getId())) {
                            o.setDislikesnumber(o.getDislikesnumber() + 1);
                            nbdislike.setText(o.getDislikesnumber() + "");
                            o.setLikesnumber(o.getLikesnumber() - 1);
                            nblike.setText(o.getLikesnumber() + "");
                            f.refreshTheme();
                        }
                    }
                });
            }

            tb.addCommandToLeftBar("< Retour", null, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    precedent.show();
                }
            });

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
