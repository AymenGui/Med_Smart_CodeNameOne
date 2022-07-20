/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Models.Fichier;
import Models.User;
import Services.FichierService;
import com.codename1.components.ImageViewer;

import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.File;
import com.codename1.io.FileSystemStorage;

import com.codename1.io.Log;
import com.codename1.ui.Button;

import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;

import com.codename1.ui.TextField;

import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.ImageIO;
import static com.codename1.ui.util.ImageIO.FORMAT_PNG;
import com.codename1.ui.util.Resources;


import java.io.IOException;

import java.io.OutputStream;


/**
 *
 * @author AGuizani
 */
public class AddFile extends Form {

    public String s;
    File currentDir = new File("../");

    private final String fileout = currentDir.getAbsolutePath() + "\\backnameone\\backNodeCodeNameOne\\public\\fichiers\\";

    public AddFile(Resources theme) {

        this.setLayout(BoxLayout.y());
        this.setTitle("Add File");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new ManageFiles(theme).showBack();
        });
        /*
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_WARNING, s);
        getToolbar().addCommandToRightBar("Right", icon, (e) -> Log.p("Clicked"));*/
        Label Uid = new Label("Please enter a User Id");
        TextField userId = new TextField("", "User Id");

        String[] type = {"RADIO", "SCANNER", "IRM", "ECHO", "ANALYSE_LABO", "ORDONNANCE", "LETTRE_DE_LIAISON"};
        Picker typeFile = new Picker();

        typeFile.setStrings(type);
        typeFile.setSelectedString(type[0]);

        Label cf = new Label("Please Choose A File :");
        TextField filename = new TextField("", "Chosen File Name");
        filename.setEditable(false);
        Button btn = new Button("Choose a file");
        ImageViewer imgview = new ImageViewer();
        Button validate = new Button("Validate");

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

                FileChooser.showOpenDialog(".png,image/png,.jpg,image/jpg,.tif,image/tif,.jpeg", e2 -> {
                    try {
                        String filePath = (String) e2.getSource();

                        s = filePath;

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
        
        
        
        

        validate.addActionListener(l -> {
           
            if ((userId.getText().length() == 0) && (filename.getText().length() == 0)) {
                Dialog.show("Alert", "Please Update All Fields!", "OK", "");

            } else {
                try {
                    System.out.println(s);
                    boolean check = false;
                    FichierService fs = new FichierService();
                    int id = Integer.parseInt(userId.getText());
                    check = fs.checkUser(id);

                    if (check) {
                        String filePath = s;
                        File fsource = new File(filePath);
                        File ftarget = new File(fileout + fsource.getName());
                        try {
                            Image img = Image.createImage(filePath);
                            String imageFile = FileSystemStorage.getInstance().getAppHomePath() + fsource.getName();
                            OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile);
                            ImageIO.getImageIO().save(img, os, ImageIO.FORMAT_PNG, 1);
                        } catch (IOException err) {
                            Log.e(err);
                        }

                        Fichier f = new Fichier();
                        f.setType(typeFile.getValue().toString());
                        f.setIdPhysique(filename.getText());
                        User user = new User();
                        user.setId(Integer.parseInt(userId.getText()));
                        f.setUser(user);

                        if (FichierService.getInstance().addFichier(f)) {
                            Dialog.show("Succes", "Fichier ajoutée avec succes!", "OK", null);
                            /*try {
                            new ListeEtabs(theme).show();
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }*/
                        } else {
                            Dialog.show("Echec", "Re-essayer un peu plus tard!", "OK", null);
                        }

                    } else {
                        Dialog.show("Echec", "User not Found !", "OK", null);
                    }
                } catch (NumberFormatException ex) {
                    Dialog.show("Echec", "User Id must be Integer!", "OK", null);
                }

            }

        });

    }

}
