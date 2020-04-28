/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.Publication;

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.io.Util;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.Entite.Publication;
import com.mycompany.Service.ServicePublication;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author asus
 */
public class AffPub {

    Resources res;
    private Resources theme;
    Form f, form;
    SpanLabel lb;
    String url = "http://localhost/citedelacultureWEB/web/uploads";
    EncodedImage enc;
    Label Titre, Contenu, Type;
    Button particip, modif, supp;
    private EncodedImage placeholder;

    public AffPub(Form frmBack) {
        //theme = UIManager.initFirstTheme("/theme_1");
        f = new Form(BoxLayout.y());

        Toolbar ta = f.getToolbar();

        ta.addCommandToLeftBar("< Retour", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                frmBack.show();
            }
        });

        Style s = UIManager.getInstance().getComponentStyle("Title");

        FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, s);

        lb = new SpanLabel("");
        f.add(lb);
        ServicePublication SP = new ServicePublication();
        ArrayList<Publication> lis = SP.getList2();
        for (Publication li : lis) {
            Container cc = new Container(BoxLayout.x());
            Container c = new Container(BoxLayout.y());

            Image placeholder = Image.createImage(500, 500);
            EncodedImage enc = EncodedImage.createFromImage(placeholder, false);
            URLImage urlim = URLImage.createToStorage(enc, li.getImage(), url + "/" + li.getImage());
            ImageViewer imgV = new ImageViewer();
            imgV.setImage(urlim);

            //  TextField a = new TextField(li.getId_event());
            Label aa = new Label("Titre  : " + li.getTitre().toString());
            Label desc = new Label("contenu : " + li.getContenu().toString());
            c.add(imgV);
            c.add(aa);
            c.setLeadComponent(aa);
            c.add(desc);
            //c.add(d);
            f.add(c);
            aa.addPointerPressedListener((l) -> {

                form = new Form(BoxLayout.x());
                Toolbar tb = form.getToolbar();
                tb.addCommandToLeftBar("< Retour", null, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        f.show();
                    }
                });

                Label lbser = new Label();
                Container F3 = new Container(BoxLayout.y());
                //Container x=new Container(BoxLayout.x());

                F3.add(lbser);
                // form.add(share);

                Image placeholder1 = Image.createImage(1000, 700);
                EncodedImage en = EncodedImage.createFromImage(placeholder1, false);
                URLImage urli = URLImage.createToStorage(en, li.getImage(), url + "/" + li.getImage());
                ImageViewer img = new ImageViewer();
                img.setImage(urli);

                ConnectionRequest con = new ConnectionRequest();

                String url = "http://localhost/citedelacultureWEB/web/app_dev.php/findP/" + li.getIdPublication();
                con.setUrl(url);

                con.addResponseListener((le) -> {
                    String reponse = new String(con.getResponseData());
                    //lbser.setText(reponse);
                    Titre = new Label("Titre :" + li.getTitre());
                    Contenu = new Label("Contenu :" + li.getContenu());
                    Type = new Label("Type :" + li.getType());
//                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    //String dateS = format.format(li.getDate());
//                    dateD = new Label("Date :" + li.getDate());

                });
                NetworkManager.getInstance().addToQueueAndWait(con);
                F3.add(img);
                F3.add(Titre);
                F3.add(Contenu);
                F3.add(Type);

                Button b = new Button("Commenter la publication");
                b.addActionListener((e) -> {

                    Form hi = new Form("Capture", BoxLayout.y());

                    hi.setToolbar(new Toolbar());
                    FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_MIC, s);

                    FileSystemStorage fs = FileSystemStorage.getInstance();
                    String recordingsDir = fs.getAppHomePath() + "recordings/";
                    fs.mkdir(recordingsDir);
                    try {
                        for (String file : fs.listFiles(recordingsDir)) {
                            MultiButton mb = new MultiButton(file.substring(file.lastIndexOf("/") + 1));
                            mb.addActionListener((ee) -> {
                                try {
                                    Media m = MediaManager.createMedia(recordingsDir + file, false);
                                    m.play();
                                } catch (IOException err) {
                                    Log.e(err);
                                }
                            });
                            hi.add(mb);
                        }

                        hi.getToolbar().addCommandToRightBar("", icon, (ev) -> {
                            try {
                                String file = Capture.captureAudio();
                                if (file != null) {
                                    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MMM-dd-kk-mm");
                                    String fileName = sd.format(new Date());
                                    String filePath = recordingsDir + fileName;
                                    Util.copy(fs.openInputStream(file), fs.openOutputStream(filePath));
                                    MultiButton mb = new MultiButton(fileName);
                                    mb.addActionListener((et) -> {
                                        try {
                                            Media m = MediaManager.createMedia(filePath, false);
                                            m.play();
                                        } catch (IOException err) {
                                            Log.e(err);
                                        }
                                    });
                                    hi.add(mb);
                                    hi.revalidate();
                                }
                            } catch (IOException err) {
                                Log.e(err);
                            }
                        });
                    } catch (IOException err) {
                        Log.e(err);
                    }
                    Toolbar tc = hi.getToolbar();
                    tc.addCommandToLeftBar("< Retour", null, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            form.show();
                        }
                    });
                    hi.show();

                });
                F3.add(b);
                //F3.add(dateD);
                form.add(F3);

                form.show();

                c.setLeadComponent(aa);

            });
        }
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
