/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;

/**
 *
 * @author AGuizani
 */
public class Home extends Form {
     public Home(Resources theme){
        
        this.setLayout(BoxLayout.y());
        this.setTitle("Acceuil");
        
        this.getToolbar().addCommandToLeftSideMenu("Liste Utilisateurs", null, (evt) -> {
            try {
                new ListUsers(theme).show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage()); 
           }
        });
        
        this.getToolbar().addCommandToLeftSideMenu("Ajouter Produit", null, (evt) -> {
               new AddUser(theme).show();
        });
        
        this.getToolbar().addCommandToLeftSideMenu("Manage Files", null, (evt) -> {
            new ManageFiles(theme).show();
        });
         this.getToolbar().addCommandToLeftSideMenu("Liste etablissements", null, (evt) -> {
            try {
                new ListeEtabs(theme).show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage()); 
           }
        });
         
           this.getToolbar().addCommandToLeftSideMenu("Ajouter etablissement", null, (evt) -> {
               new AddTablissement(theme).show();
        });
           this.getToolbar().addCommandToLeftSideMenu("Ajouter Produit", null, (evt) -> {
               new AddProduit(theme).show();
        });
             this.getToolbar().addCommandToLeftSideMenu("Liste Produits", null, (evt) -> {
            try {
                new ListeProduits(theme).show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage()); 
           }
        });


        
        this.getToolbar().addCommandToOverflowMenu("Settings", null, (evt) -> {
        });
        this.getToolbar().addCommandToOverflowMenu("Logout", null, (evt) -> {
        });
    }
}
