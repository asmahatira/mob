/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.Reclamation;

import com.codename1.components.OnOffSwitch;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.mycompany.Entite.Evenement;
import com.mycompany.Entite.User;
import com.mycompany.Service.ServiceReclamation;
import com.mycompany.Service.ServiceUser;
import com.mycompany.gui.User.Profile;

/**
 *
 * @author Mezri
 */
public class AjouterReclamation {

    Form f;

    public AjouterReclamation(Evenement e, User u, Form precedent) {

        f = new Form("Ajouter reclamtion", BoxLayout.y());
        Toolbar tb = f.getToolbar();
        Label lbl1 = new Label("Titre : ");
        TextField titre = new TextField();
        Label lbl2 = new Label("Contenu : ");
        TextField description = new TextField();

        Button btAjouter = new Button("Reclamer");

        f.add(lbl1);
        f.add(titre);
        f.add(lbl2);
        f.add(description);

        f.add(btAjouter);

        btAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                boolean yes = Dialog.show("Alerte", "Voulez-vous envoyer votre reclamtion ?", "oui", "non");
                if (yes) {
                    ServiceReclamation sr = new ServiceReclamation();
                    sr.ajouterReclamation(u.getId(), e.getIdEvenement(), description.getText(), titre.getText());
                    String email = "abdelaziz.mezri@esprit.tn";
                    Message message = new Message("test");
                    Display.getInstance().sendMessage(new String[]{email}, "reclamation", message);
                    precedent.show();
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
