/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Fichier;
import Models.User;
import Utilities.Statistics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.File;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;


import java.util.ArrayList;

import java.util.List;
import java.util.Map;


/**
 *
 * @author AGuizani
 */
public class FichierService {
        //prefix
    private String fichierPrefix = "/fichier";

    //var
    static FichierService instance = null;
    boolean resultOK = false;
    ConnectionRequest req;
    List<Fichier> fichiers = new ArrayList<Fichier>();

    //constructor
    public FichierService() {
        req = new ConnectionRequest();
    }

    //Get
    public static FichierService getInstance() {
        if (instance == null) {
            instance = new FichierService();
        }

        return instance;
    }

    //Ajout
    public boolean addFichier(Fichier f) {

        //build URL
        String addURL = Statistics.BASE_URL + fichierPrefix + "/add";

        //2
        req.setUrl(addURL);

        //3
        req.setPost(true);

        //4 : Transfert data
        req.addArgument("type", f.getType());
        req.addArgument("id_physique", f.getIdPhysique());
        req.addArgument("id_user", String.valueOf(f.getUser().getId()));

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
    public List<Fichier> fetchFichier() {

        //URL
        String selectURL = Statistics.BASE_URL + fichierPrefix + "/showAll";

        //1
        req.setPost(false);
        //2
        req.setUrl(selectURL);
        //3
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                String response = new String(req.getResponseData());
                //parsing
                //..
                System.err.println("start Parsing");
                fichiers = parseFichiers(response);

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return fichiers;
    } 
    
    
    //PARSING JSON
    public List<Fichier> parseFichiers(String jsonText) {

        //parser
        fichiers = new ArrayList<>();
        JSONParser jp = new JSONParser();

        try {
            //2
            Map<String, Object> jsonList = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) jsonList.get("root");

            for (Map<String, Object> item : list) {
                Fichier f = new Fichier();
                f.setType((String) item.get("type"));
                f.setIdPhysique((String) item.get("id_physique"));
               /* User user = new User();
                user.setId(Integer.parseInt(item.get("id_user").toString()) );
                f.setUser(user);*/
                fichiers.add(f);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return fichiers;
    }

    public boolean checkUser(int number) {
        boolean check = false;
        UserService us = new UserService();
        ArrayList<User> userArray = us.getAllUsers();
        for (User u : userArray) {
            int id = u.getId();
            if (id == number) {
                check = true;
                System.out.println("User Exists");
                break;
            } 
        }
        return check;
    }
    
    
  
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}  
    
    
    

