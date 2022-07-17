/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import Models.User;

import Services.UserService;
import Utilities.Statistics;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.SpanButton;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.Resources;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author bureau
 */
public class ListUsers extends Form{
    
      ArrayList listUsers = UserService.getInstance().getAllUsers();
    private Resources theme;

    public ListUsers(Resources theme) throws IOException {

        setTitle("Liste des Utilisateurs");
        setScrollableY(true);
        getToolbar().addCommandToRightBar("back", null, ev -> {
            new Home(theme).showBack();
        });
        for (int i = 0; i < listUsers.size(); i++) {
            Container userList = new Container(BoxLayout.y());

            userList.getUnselectedStyle().setPadding(50, 50, 250, 250);
            userList.getUnselectedStyle().setBorder(Border.createLineBorder(1));
            
            User user = (User) listUsers.get(i); 
            
            Label nom = new Label("nom: " + user.getNom());
            Label prenom = new Label("prenom: " + user.getPrenom());
            Label email = new Label("email: " + user.getEmail());
            Label cin = new Label("cin: " + user.getCin());
            Label hashedPwd = new Label("hashedPwd: " + user.getHashedPwd());
            Label numtel = new Label("numtel: " + user.getNumtel());
            Label role = new Label("role: " + user.getRole());
            
            userList.addAll(nom, prenom, email, cin, hashedPwd, numtel, role);

            Container modifydestContainer = new Container(BoxLayout.x());
            SpanButton updateButton = new SpanButton("Update");
            updateButton.getTextAllStyles().setFgColor(0xF37217);
            updateButton.getAllStyles().setBorder(Border.createEmpty());

            updateButton.addActionListener(updatedest -> {
                new InfiniteProgress().showInifiniteBlocking();
                User user1 = new User();
                
                user1.setId(user.getId());
                user1.setNom(user.getNom());
                user1.setPrenom(user.getPrenom());
                user1.setEmail(user.getEmail());
                user1.setCin(user.getCin());
                user1.setHashedPwd(user.getHashedPwd());
                user1.setNumtel(user.getNumtel());
                user1.setRole(user.getRole());
                
                new ModifierUser(user1).show();
            });

            modifydestContainer.add(updateButton);
            String url = Statistics.BASE_URL + "/user/delete/" +user.getId();
            SpanButton deleteButton = new SpanButton("Delete");
            deleteButton.getTextAllStyles().setFgColor(0xFB0000);
            deleteButton.getAllStyles().setBorder(Border.createEmpty());

            deleteButton.addActionListener((delete) -> {

                ConnectionRequest cnxDelteDest = new ConnectionRequest(url);
                cnxDelteDest.setPost(true);

                cnxDelteDest.addResponseListener(deleteEvent -> {

                    try {
                        Map<String, Object> deleteResult = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(cnxDelteDest.getResponseData()), "UTF-8"));

                        Dialog.show("Deleted", "User Deleted", "OK", "");
                        new ListUsers(theme).show();

                    } catch (Exception err) {
                        Dialog.show("Error", "Error parsing result", "OK", "");
                        System.out.println(err);
                    }
                });

                NetworkManager.getInstance().addToQueueAndWait(cnxDelteDest);
            });

            modifydestContainer.add(deleteButton);

            userList.add(modifydestContainer);

            add(userList);

        }

    }
    
}

