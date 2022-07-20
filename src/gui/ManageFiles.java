/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;

import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author AGuizani
 */
public class ManageFiles extends Form {

    public ManageFiles(Resources theme) {

        this.setLayout(BoxLayout.y());
        this.setTitle("Manage Files");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new Home(theme).showBack();
        });
        //1
        Label ddn = new Label("Please choose an option below");

        Button btn = new Button("Add Files");
        btn.setIcon(theme.getImage("addfile"));
        Button btn2 = new Button("list Files");
        // Button btn3 = new Button("Edit Files");
        this.addComponent(ddn);
        this.addComponent(btn);
        this.addComponent(btn2);
        //this.addComponent(btn3);

        btn.addActionListener((evt) -> {
            new AddFile(theme).show();
        });
        btn2.addActionListener((evt2) -> {
            new ListFiles(theme).show();
        });

    }
}
