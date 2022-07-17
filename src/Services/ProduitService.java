/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Utilities.Statistics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import Models.Produit;

/**
 *
 * @author NAD
 */
public class ProduitService {

    public ArrayList<Produit> Prod;

    //prefix
    private String produitPrefix = "/produit";

    //var
    static ProduitService instance = null;
    boolean resultOK = false;
    ConnectionRequest req;
    List<Produit> produit = new ArrayList<Produit>();

    //constructor
    private ProduitService() {
        req = new ConnectionRequest();
    }

    //Get
    public static ProduitService getInstance() {
        if (instance == null) {
            instance = new ProduitService();
        }

        return instance;
    }

    //Ajout
    public boolean addProduit(Produit p) {

        //build URL
        String addURL = Statistics.BASE_URL + produitPrefix + "/add/" + p.getName() + "/" + p.getId_physique() + "/" + p.getQte() + "/" + p.getId_etap();

        //2
        req.setUrl(addURL);

        //3
        req.setPost(true);

        //4 : Transfert data
        req.addArgument("Nom", p.getName());
        req.addArgument("id_physique_img", p.getId_physique());
        req.addArgument("qte", String.valueOf(p.getQte()));
        req.addArgument("id_etap", String.valueOf(p.getId_etap()));

        //5
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return resultOK;
    }

    //Select
    public ArrayList<Produit> getAllProduit() {
        //String url = Statics.BASE_URL+"/tasks/";
        String selectURL = Statistics.BASE_URL + produitPrefix + "/showAll";
        req.setUrl(selectURL);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Prod = (ArrayList<Produit>) parsePro(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println(Prod);
        return Prod;
    }

    //PARSING JSON
    public List<Produit> parsePro(String jsonText) {

        //parser
        produit = new ArrayList<>();
        JSONParser jp = new JSONParser();

        try {
            //2
            Map<String, Object> jsonList = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) jsonList.get("root");

            for (Map<String, Object> item : list) {
                Produit p = new Produit();
                float id = Float.parseFloat(item.get("id").toString());
                p.setId((int) id);
                p.setName((String) item.get("Nom"));
                p.setId_physique((String) item.get("id_physique_img"));
                p.setQte((int) ((double) item.get("qte")));
                p.setId_etap((int) ((double) item.get("id_etab")));

                produit.add(p);

            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return produit;
    }

    public boolean deleProduit(Produit p) {

        //build URL
        String deleteURL = Statistics.BASE_URL + produitPrefix + "/delete";

        //2
        req.setUrl(deleteURL);

        //3
        req.setPost(true);

        //4 : Transfert data
        req.removeAllArguments();

        //5
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });

        //NetworkManager.getInstance().addToQueueAndWait(req);
        NetworkManager.getInstance().removeErrorListener((ActionListener<NetworkEvent>) req);

        return resultOK;
    }

}
