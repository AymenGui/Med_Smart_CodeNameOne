/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Models.Fichier;
import Services.FichierService;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.util.List;

/**
 *
 * @author AGuizani
 */
public class ListFiles  extends Form{
   public ListFiles(Resources theme) {
     this.setLayout(BoxLayout.y());
        this.setTitle("List of Files");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new ManageFiles(theme).showBack();
        });  
        
        List <Fichier> fichiers=FichierService.getInstance().fetchFichier();
        
                for (Fichier f: fichiers) {
            this.add(new fichierCellule(theme, f));
        }
   } 
}

