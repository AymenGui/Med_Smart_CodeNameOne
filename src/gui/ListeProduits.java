/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Models.Produit;
import Services.ProduitService;
import Utilities.Statistics;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.SpanButton;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.Resources;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author user
 */
public class ListeProduits extends Form {

    ArrayList ListProduit = ProduitService.getInstance().getAllProduit();
    private Resources theme;

    public ListeProduits(Resources theme) throws IOException {

        setTitle("Liste des Produits");
        setScrollableY(true);
        getToolbar().addCommandToRightBar("back", null, ev -> {
            new Home(theme).showBack();
        });
        for (int i = 0; i < ListProduit.size(); i++) {
            Container ProdList = new Container(BoxLayout.y());

            ProdList.getUnselectedStyle().setPadding(50, 50, 250, 250);
            ProdList.getUnselectedStyle().setBorder(Border.createLineBorder(1));
            Produit prod = (Produit) ListProduit.get(i); 
            Label nom = new Label("Name: " + prod.getName());
            Label idPhy = new Label("Id_phy: " + prod.getId_physique());
            Label qte = new Label("Qte: " + prod.getQte());
            Label idEtab = new Label("idEtab: " + prod.getId_etap());

            ProdList.addAll(nom, idPhy, qte, idEtab);

            Container modifydestContainer = new Container(BoxLayout.x());
            SpanButton updateButton = new SpanButton("Update");
            updateButton.getTextAllStyles().setFgColor(0xF37217);
            updateButton.getAllStyles().setBorder(Border.createEmpty());

            updateButton.addActionListener(updatedest -> {
                new InfiniteProgress().showInifiniteBlocking();
                Produit prod1 = new Produit();
                prod1.setId(prod.getId());
                prod1.setName(prod.getName());
                prod1.setId_physique(prod.getId_physique());
                prod1.setQte((int)prod.getQte());
                prod1.setId_etap((int)prod.getId_etap());

                new MofifProd(prod1).show();
            });

            modifydestContainer.add(updateButton);
            String url = Statistics.BASE_URL + "/produit/delete/" +prod.getId();
            SpanButton deleteButton = new SpanButton("Delete");
            deleteButton.getTextAllStyles().setFgColor(0xFB0000);
            deleteButton.getAllStyles().setBorder(Border.createEmpty());

            deleteButton.addActionListener((delete) -> {

                ConnectionRequest cnxDelteDest = new ConnectionRequest(url);
                cnxDelteDest.setPost(true);

                cnxDelteDest.addResponseListener(deleteEvent -> {

                    try {
                        Map<String, Object> deleteResult = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(cnxDelteDest.getResponseData()), "UTF-8"));

                        Dialog.show("Deleted", "Produit Deleted", "OK", "");
                        new ListeProduits(theme).show();

                    } catch (Exception err) {
                        Dialog.show("Error", "Error parsing result", "OK", "");
                        System.out.println(err);
                    }
                });

                NetworkManager.getInstance().addToQueueAndWait(cnxDelteDest);
            });

            modifydestContainer.add(deleteButton);

            ProdList.add(modifydestContainer);

            add(ProdList);

        }

    }
}
