/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Models.Fichier;
import com.codename1.components.ImageViewer;
import com.codename1.io.Log;
import com.codename1.ui.Container;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;

/**
 *
 * @author AGuizani
 */
public class fichierCellule extends Container {

    public fichierCellule(Resources theme, Fichier f) {
        this.setLayout(BoxLayout.x());
       try {
            Image img = Image.createImage("/file-download.png");
            ImageViewer imgView = new ImageViewer();
            imgView.setHeight(10);
            imgView.setWidth(10);
            imgView.setImage(img);
            Label lab1 = new Label(f.getType());
            Label lab2 = new Label(f.getIdPhysique());
            // Label lab3 = new Label(f.getUser().getCin());

            Container ctn = new Container();
            ctn.setLayout(BoxLayout.y());
            ctn.addAll(imgView,lab1, lab2);

            this.addAll(ctn);
        } catch (IOException ex) {
            Log.e(ex);
        }

    }
}
