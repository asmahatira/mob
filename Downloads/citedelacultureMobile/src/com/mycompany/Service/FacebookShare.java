/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Service;
import com.codename1.facebook.FaceBookAccess;
import com.codename1.io.NetworkEvent;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
/**
 *
 * @author boussandel
 */
public class FacebookShare {

    public FacebookShare(String token) {
        FaceBookAccess.setToken(token);
    }
    
    public void share(String text) throws IOException {
	FaceBookAccess.getInstance().addResponseCodeListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent evt) {
		NetworkEvent ne = (NetworkEvent) evt;
		int code = ne.getResponseCode();
                FaceBookAccess.setToken("67e88003f8a56f36dfe98e69974a71e0");
                FaceBookAccess.setClientId("472508983493659");
                FaceBookAccess.setClientSecret("eb29d484d18e78db40d46ec4695c234e");
		FaceBookAccess.getInstance().removeResponseCodeListener(this);
	    }
/*
            @Override
            public void actionPerformed(ActionEvent evt) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }*/
	});
	FaceBookAccess.getInstance().postOnWall("me", text);
    }
}