/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

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
import Models.Produit;
import Services.ProduitService;

///**
// *
// * @author NAD
// */
//public class AjouterProduit extends Form {
//
//    public AjouterProduit(Resources theme) {
//        this.setLayout(BoxLayout.y());
//        this.setTitle("Ajouter Produit");
//
//        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
//            new Home(theme).showBack();
//            
//            TextField nameTF = new TextField("", "Insert Nom Produit");
//            //2
//            TextField id_physique = new TextField("", "insert id physique");
//
//            //3
//            TextField qte = new TextField("", "insert qte");
//
//            //4
//            TextField id_etap = new TextField("", "insert Id etap");
//
//            Button btnAdd = new Button("Ajouter Produit");
//           
//           
//            btnAdd.addActionListener((evt) -> {
//                Produit p = new Produit();
//                 //java.util.List<Produit> Produits = ProduitService.;
//                 java.util.List<Produit> produits = ProduitService;
//            });
//        });
//            getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
//            new Home(theme).showBack();
//            });
//     this.addAll(nameTF, id_physique, qte, id_etap,btn);       
//    }
//    
//
//        }
public class AjouterProduit extends Form{
       private Form current;
           private Resources theme;
       
            public AjouterProduit(Resources theme) {
       
       
        setTitle("Ajouter Produit");
        setLayout(BoxLayout.y());
          getToolbar().addCommandToRightBar("back", null, ev->{
             new Home(theme).showBack();
        });
        
        
        TextField nom = new TextField("","Name");
        TextField id_physique= new TextField("", "ID physique");
        TextField qte= new TextField("", "qte");
        TextField id_etab = new TextField("", "id_etab");
       
         

       
                
        Button btnValider = new Button("Ajouter");
        
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((nom.getText().length()==0)||(id_physique.getText().length()==0)||(qte.getText().length()==0)||(id_etab.getText().length()==0))
                    Dialog.show("Alert", "Merci de renseigner tout les champs","OK","");
                else
                {
                    double doubleValue;
                    Produit of= new Produit(nom.getText(), id_physique.getText(),Double.parseDouble(qte.getText()),Double.parseDouble(id_etab.getText()));
                   
                        if(ProduitService.getInstance().addProduit(of))
                        {
                                ToastBar.Status status = ToastBar.getInstance().createStatus();
                                status.setMessage("Produit Ajouté");
                                status.setExpires(5000);  
                                status.show();
                       
//                                new ListeProduits(theme).show();
                        }else
                            Dialog.show("ERROR", "Server error", "OK","");
                    
                    
                }
                
                
            }
        });

         
    // Create a form and show it.
        addAll(nom,id_physique,qte,id_etab,btnValider);
    
                
    }
    
}
