/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.components.Switch;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.File;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.List;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;

/**
 *
 * @author AGuizani
 */
public class AddFile extends Form {

    public AddFile(Resources theme) {
        
        this.setLayout(BoxLayout.y());
        this.setTitle("Add File");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new ManageFiles(theme).showBack();
        });
         Button validate = new Button("Validate");
      this.getToolbar().addComponent(this.add(validate));
        Label Uid = new Label("Please enter a User Id");
        TextField userId = new TextField("", "User Id");

        String[] type = {"RADIO", "SCANNER", "IRM", "ECHO", "ANALYSE_LABO", "ORDONNANCE", "LETTRE_DE_LIAISON"};
        Picker typeFile = new Picker();
        
        typeFile.setStrings(type);
        typeFile.setSelectedString(type[0]);

        Label cf = new Label("Please Choose A File :");
        TextField filename = new TextField("", "Chosen File Name");

        Button btn = new Button("Choose a file");
        ImageViewer imgview = new ImageViewer();
       
        
        this.addComponent(Uid);
        this.addComponent(userId);
        this.addComponent(typeFile);
        this.addComponent(cf);
        this.addComponent(filename);
        this.addComponent(btn);
        this.addComponent(validate);
        this.addComponent(imgview);
        

        btn.addActionListener(e -> {

            if (FileChooser.isAvailable()) {
                FileChooser.setOpenFilesInPlace(true);

                FileChooser.showOpenDialog(".pdf,application/pdf,.png,image/png,.jpg,image/jpg,.tif,image/tif,.jpeg", e2 -> {
                    try {
                        String filePath = (String) e2.getSource();
                        System.out.println(filePath);
                        File f = new File(filePath);
                        String fileName = f.getName();
                        filename.setText(fileName);
                        String extension = null;
                        if (filePath.lastIndexOf(".") > 0) {
                            extension = filePath.substring(filePath.lastIndexOf(".") + 1);
                        }
                        if ("png".equalsIgnoreCase(extension)
                                || "jpg".equalsIgnoreCase(extension)
                                || "jpeg".equalsIgnoreCase(extension)
                                || "tif".equalsIgnoreCase(extension)) {

                            try {
                                Image img = Image.createImage(filePath);
                                imgview.setImage(img);
                            } catch (IOException ex) {
                                Log.e(ex);
                            }
                        }

                    } catch (NullPointerException ex) {
                        filename.setText("No file was selected");

                        Log.e(ex);
                    }

                });

            }

        });
    }
    
    
    
}
