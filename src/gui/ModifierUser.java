/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Models.User;
import Utilities.Statistics;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.Map;

/**
 *
 * @author bureau
 */
public class ModifierUser extends Form {
    
    private Resources theme;
    ConnectionRequest cnxUpdatedest;
    
     public ModifierUser(User user) {

        setTitle("Update User");

        getToolbar().addCommandToRightBar("back", null, ev -> {
            new Home(theme).showBack();
        });

        Container updateUserContainer = new Container(BoxLayout.y());

        TextField nom = new TextField("");
        TextField prenom = new TextField("");
        TextField email = new TextField("");
        TextField cin = new TextField("");
        TextField hashedPwd = new TextField("");
        TextField numtel = new TextField("");
        TextField role = new TextField("");
        
        nom.setText(user.getNom());
        prenom.setText(user.getPrenom());
        email.setText(user.getEmail());
        cin.setText(user.getCin());
        hashedPwd.setText(user.getHashedPwd());
        numtel.setText(user.getNumtel());
        role.setText(user.getRole());

     
        
        Button modifUser = new Button("Update User");
        modifUser.addActionListener((ActionEvent l) -> {

            final String urlUpdatedest = Statistics.BASE_URL + "/user/edit/" + user.getId() + "/" + nom.getText() + "/" + prenom.getText() + "/" + email.getText() + "/" + cin.getText()+ "/" + hashedPwd.getText()+ "/" + numtel.getText()+ "/" + role.getText();

            cnxUpdatedest = new ConnectionRequest(urlUpdatedest);
            cnxUpdatedest.setPost(true);

            cnxUpdatedest.addResponseListener(ev -> {
                try {
                    Map<String, Object> result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(cnxUpdatedest.getResponseData()), "UTF-8"));

                    Dialog.show("updated", "User Updated successfully", "ok", "");
                                        System.out.println(user.getNumtel());

                    new ListUsers(theme).show();

                } catch (Exception e) {
                    Dialog.show("updated", "  update failed ", "ok", "non");

                }
            });
            NetworkManager.getInstance().addToQueueAndWait(cnxUpdatedest);

        });
        updateUserContainer.addAll(nom, prenom, email, cin, hashedPwd, numtel, role, modifUser);
        add(updateUserContainer);

    }

    
}

