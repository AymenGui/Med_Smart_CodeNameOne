/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Models.Etablissements;
import Services.ServiceEtab;
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
 * @author user
 */
public class ListeEtabs  extends Form{
      ArrayList ListEtab =ServiceEtab.getInstance().getAllEtabs();
     private Resources theme;
      public ListeEtabs(Resources theme) throws IOException{
            
        setTitle("Liste des etablissements");
        setScrollableY(true);
      getToolbar().addCommandToRightBar("back", null, ev->{
             new Home(theme).showBack();
        });
         for (int i=0;i<ListEtab.size();i++){
                Container Etabliss=new Container(BoxLayout.y());
                
    
                Etabliss.getUnselectedStyle().setPadding(50, 50, 250, 250);
                Etabliss.getUnselectedStyle().setBorder(Border.createLineBorder(1));
                Etablissements etab=(Etablissements)ListEtab.get(i);
                Label nom=new Label("Name: "+etab.getName());
                Label description=new Label("Adresse: "+etab.getAdresse());
                Label adresse=new Label("Type: "+etab.getType());
      
             Etabliss.addAll(nom,description,adresse);
          
             Container modifydestContainer=new Container(BoxLayout.x());
              SpanButton updateButton=new SpanButton("Update");
                 updateButton.getTextAllStyles().setFgColor(0xF37217);
                         updateButton.getAllStyles().setBorder(Border.createEmpty());

                 updateButton.addActionListener(updatedest->{
                    new InfiniteProgress().showInifiniteBlocking();
                    Etablissements etabl=new Etablissements();
                    etabl.setId(etab.getId());
                    etabl.setName(etab.getAdresse());
                      etabl.setAdresse(etab.getAdresse());
                         etabl.setType(etab.getType());
                  
                    new ModifEtab(etabl).show();
                });
                 
                modifydestContainer.add(updateButton);
                
              String url=Statistics.BASE_URL+"/etab/delete/"+etab.getId();
                SpanButton deleteButton=new SpanButton("Delete");
                 deleteButton.getTextAllStyles().setFgColor(0xFB0000);
                 deleteButton.getAllStyles().setBorder(Border.createEmpty());
  
                 deleteButton.addActionListener((delete)->{
                     
                      ConnectionRequest cnxDelteDest = new ConnectionRequest(url);
                      cnxDelteDest.setPost(true);
                    
                       cnxDelteDest.addResponseListener(deleteEvent->{
                     
                   try {
                    Map<String,Object> deleteResult = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(cnxDelteDest.getResponseData()), "UTF-8"));
          
        
                  
                    Dialog.show("Deleted", "etabs Deleted","OK","");
                    new ListeEtabs(theme).show();
                         
                        } catch(Exception err) {
                    Dialog.show("Error", "Error parsing result", "OK","");
                    System.out.println(err);
                      }
                               });
                       
                        NetworkManager.getInstance().addToQueueAndWait(cnxDelteDest);
                 });
                 
                modifydestContainer.add(deleteButton);

                
               Etabliss.add(modifydestContainer);

                add(Etabliss);
 


         }
         
       

         
        
        
            
            
    }
}
