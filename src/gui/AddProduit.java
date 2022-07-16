/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Models.Produit;
import Services.ProduitService;
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
public class AddProduit extends Form{
       private Form current;
           private Resources theme;
       
            public AddProduit(Resources theme) {
       
       
        setTitle("Add Produit");
        setLayout(BoxLayout.y());
          getToolbar().addCommandToRightBar("back", null, ev->{
             new Home(theme).showBack();
        });
        
        
        TextField nom = new TextField("","Nom");
        TextField idPhy= new TextField("", "id_physique_img");
        TextField qte= new TextField("", "qte");
        TextField idEtab= new TextField("", "id_etab");

       
         

       
                
        Button btnValider = new Button("Ajouter");
        
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                      Integer quantite=  Integer.parseInt(qte.getText().toString());
                      Integer idEtabb=  Integer.parseInt(idEtab.getText().toString());
                if ((nom.getText().length()==0)||(idPhy.getText().length()==0)||(quantite<0) || (idEtabb<0) )
                    Dialog.show("Alert", "Merci de renseigner tout les champs","OK","");
                else
                {
                    Produit pr=new Produit(nom.getText(),idPhy.getText() , quantite, idEtabb);
                    
                   
                        if(ProduitService.getInstance().addProduit(pr))
                        {
                                ToastBar.Status status = ToastBar.getInstance().createStatus();
                                status.setMessage("produit Ajouté");
                                status.setExpires(5000);  
                                status.show();
                       
                        try {
                            new ListeProduits(theme).show();
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
                        }else
                            Dialog.show("ERROR", "Server error", "OK","");
                    
                    
                }
                
                
            }
        });

         
    // Create a form and show it.
        addAll(nom,idPhy,qte,idEtab,btnValider);
    
                
    }
    
}
