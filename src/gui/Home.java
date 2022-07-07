/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author AGuizani
 */
public class Home extends Form {
     public Home(Resources theme){
        
        this.setLayout(BoxLayout.y());
        this.setTitle("Acceuil");
        
        this.getToolbar().addCommandToLeftSideMenu("Manage Files", null, (evt) -> {
            new ManageFiles(theme).show();
        });


        
        this.getToolbar().addCommandToOverflowMenu("Settings", null, (evt) -> {
        });
        this.getToolbar().addCommandToOverflowMenu("Logout", null, (evt) -> {
        });
    }
}