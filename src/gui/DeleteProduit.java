/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.mycompany.myapp.MyApplication;
import com.codename1.components.Switch;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.List;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import Models.Produit;
import Services.ProduitService;
import com.codename1.components.ToastBar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
/**
 *
 * @author NAD
 */
   public class DeleteProduit extends Form{
       private Form current;
           private Resources theme;
       
            public DeleteProduit(Resources theme) {
       
       
        setTitle("Supprimer Produit");
        setLayout(BoxLayout.y());
          getToolbar().addCommandToRightBar("back", null, ev->{
             new Home(theme).showBack();
        });
        
        
        TextField nom = new TextField("","Name");
        Button btnValider = new Button("Suppromer");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((nom.getText().length()==0))
                    Dialog.show("Alert", "Merci de renseigner tout les champs","OK","");
                else
                {
                    
                Produit p =new Produit() ;
                   
                        if(ProduitService.getInstance().deleProduit(p))
                        {
                                ToastBar.Status status = ToastBar.getInstance().createStatus();
                                status.setMessage("Produit Supprimer ");
                                status.setExpires(5000);  
                                status.show();
                       
//                                new ListeProduits(theme).show();
                        }else
                            Dialog.show("ERROR", "Server error", "OK","");
                    
                    
                }
                
                
            }
        });

         
    // Create a form and show it.
        addAll(nom,btnValider);
    
                
    }
        
}
