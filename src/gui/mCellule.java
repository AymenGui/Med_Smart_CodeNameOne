/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import Models.Produit;

/**
 *
 * @author khaledguedria
 */
public class mCellule extends Container{
    
    public mCellule(Resources theme, Produit p){
       
        this.setLayout(BoxLayout.x());
        //ImageViewer img = new ImageViewer(theme.getImage("user.png"));
        Label lab1 = new Label(p.getName());
        Label lab2 = new Label(p.getId_physique());
     
        
        
        
        Container ctn = new Container();
        ctn.setLayout(BoxLayout.y());
        ctn.addAll(lab1, lab2);
        
        this.addAll(ctn);
//       this.addPointerPressedListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//                new DetailsScreen(theme, p).show();
//            }
//        });
   }
    
    
}
   

