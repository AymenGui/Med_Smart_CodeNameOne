/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Models.Produit;
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
 * @author NAD
 */

public class MofifProd extends Form {

    private Resources theme;
    ConnectionRequest cnxUpdatedest;

    public MofifProd(Produit Prod) {

        setTitle("Update Produit");

        getToolbar().addCommandToRightBar("back", null, ev -> {
            new Home(theme).showBack();
        });

        Container updateUserContainer = new Container(BoxLayout.y());

        TextField nom = new TextField("");
        TextField id_physique = new TextField("");
        TextField qte = new TextField("");
        TextField id_etab = new TextField("");

        nom.setText(Prod.getName());
        id_physique.setText(Prod.getId_physique());
        qte.setText(String.valueOf(Prod.getQte()));
        id_etab.setText(String.valueOf(Prod.getId_etap()));

        Button ModifProd = new Button("Update Produit");
        ModifProd.addActionListener((ActionEvent l) -> {

            final String urlUpdatedest = Statistics.BASE_URL + "/produit/edit/" + Prod.getId() + "/" + nom.getText() + "/" + id_physique.getText() + "/" + qte.getText() + "/" + id_etab.getText();

            cnxUpdatedest = new ConnectionRequest(urlUpdatedest);
            cnxUpdatedest.setPost(true);

            cnxUpdatedest.addResponseListener(ev -> {
                try {
                    Map<String, Object> result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(cnxUpdatedest.getResponseData()), "UTF-8"));

                    Dialog.show("updated", "Information Updated successfully", "ok", "");
                    new ListeProduits(theme).show();

                } catch (Exception e) {
                    Dialog.show("updated", "  update failed ", "ok", "non");

                }
            });
            NetworkManager.getInstance().addToQueueAndWait(cnxUpdatedest);

        });
        updateUserContainer.addAll(nom, id_physique, qte, id_etab, ModifProd);
        add(updateUserContainer);

    }

}
