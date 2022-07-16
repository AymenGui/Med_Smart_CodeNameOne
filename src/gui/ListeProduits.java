/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import com.codename1.components.SpanLabel;
import com.codename1.ui.Component;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.util.List;
import Models.Produit;
import Services.ProduitService;
/**
 *
 * @author NAD
 */
public class ListeProduits extends Form{
    
    public ListeProduits(Resources theme){
        
        this.setLayout(BoxLayout.y());
        this.setTitle("Liste des produits");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new Home(theme).showBack();
        });
        
        List<Produit> Produits = ProduitService.getInstance().fetchPersonnes();
       //System.out.println("************* " + Produits.toString());
        for (Produit produit : Produits) {
            this.add(new mCellule(theme, produit));
        }
    }

}