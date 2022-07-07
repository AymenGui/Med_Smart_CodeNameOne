/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Models.Etablissements;
import Services.ServiceEtab;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;

/**
 *
 * @author user
 */
public class AddTablissement extends Form{
       private Form current;
           private Resources theme;
       
            public AddTablissement(Resources theme) {
       
       
        setTitle("Add Etab");
        setLayout(BoxLayout.y());
          getToolbar().addCommandToRightBar("back", null, ev->{
             new Home(theme).showBack();
        });
        
        
        TextField nom = new TextField("","Name");
        TextField adresse= new TextField("", "Adresse");
        TextField type= new TextField("", "Type");
       
         

       
                
        Button btnValider = new Button("Ajouter");
        
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((nom.getText().length()==0)||(adresse.getText().length()==0)||(type.getText().length()==0))
                    Dialog.show("Alert", "Merci de renseigner tout les champs","OK","");
                else
                {
                    Etablissements of= new Etablissements(nom.getText(), adresse.getText(),type.getText());
                   
                        if(ServiceEtab.getInstance().addEtab(of))
                        {
                                ToastBar.Status status = ToastBar.getInstance().createStatus();
                                status.setMessage("Etablissement Ajouté");
                                status.setExpires(5000);  
                                status.show();
                       
                        try {
                            new ListeEtabs(theme).show();
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
                        }else
                            Dialog.show("ERROR", "Server error", "OK","");
                    
                    
                }
                
                
            }
        });

         
    // Create a form and show it.
        addAll(nom,adresse,type,btnValider);
    
                
    }
    
}
