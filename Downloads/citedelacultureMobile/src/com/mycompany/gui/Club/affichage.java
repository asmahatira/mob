/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.Club;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.Entite.Club;
import com.mycompany.Entite.Offre;
import com.mycompany.Service.ServiceClub;
import com.mycompany.gui.Offre.DetailOffre;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author nader
 */
public class affichage {

    Form f;
    SpanLabel lb;

    public affichage(Form back) {

        f = new Form();

        ServiceClub serviceClub = new ServiceClub();
        List<Club> lc = serviceClub.getList2();
        for (int i = 0; i < lc.size(); i++) {
            addItem(lc.get(i));
        }
        f.getToolbar().addCommandToRightBar("retour", null, (ev) -> {
            back.show();
        });

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public void addItem(Club o) {

        Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        Label l1 = new Label("Club : " + o.getNom());
        Label l2 = new Label("Description : ");
        Label l3 = new Label(o.getDescription());
        Label l4 = new Label("Cotisation annuelle :");
        Label l5 = new Label(o.getCotisationannuelle() + "dt");
        Label l6 = new Label("_______________________________");

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
    }
}
