/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Models.Etablissements;
import Utilities.Statistics;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.Map;

/**
 *
 * @author user
 */
public class ModifEtab extends Form {

    private Resources theme;
    ConnectionRequest cnxUpdatedest;

    public ModifEtab(Etablissements etab) {

        setTitle("Update Etablissement");

        getToolbar().addCommandToRightBar("back", null, ev -> {
            new Home(theme).showBack();
        });

        Container updateUserContainer = new Container(BoxLayout.y());

        TextField nom = new TextField("");
        TextField adresse = new TextField("");
        TextField type = new TextField("");

        nom.setText(etab.getName());
        adresse.setText(etab.getAdresse());
        type.setText(etab.getType());

        Button ModifEtab = new Button("Update Etablissement");
        ModifEtab.addActionListener((ActionEvent l) -> {

            final String urlUpdatedest = Statistics.BASE_URL + "/etab/edit/" + etab.getId() + "/" + nom.getText() + "/" + adresse.getText() + "/" + type.getText();

            cnxUpdatedest = new ConnectionRequest(urlUpdatedest);
            cnxUpdatedest.setPost(true);

            cnxUpdatedest.addResponseListener(ev -> {
                try {
                    Map<String, Object> result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(cnxUpdatedest.getResponseData()), "UTF-8"));

                    Dialog.show("updated", "Information Updated successfully", "ok", "");
                    new ListeEtabs(theme).show();

                } catch (Exception e) {
                    Dialog.show("updated", "  update failed ", "ok", "non");

                }
            });
            NetworkManager.getInstance().addToQueueAndWait(cnxUpdatedest);

        });
        updateUserContainer.addAll(nom, adresse, type, ModifEtab);
        add(updateUserContainer);

    }

}
