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
import com.codename1.io.JSONParser;
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
    private String fichierPrefix = "/user";
    
    
     //var
    static FichierService instance = null;
    boolean resultOK = false;
    ConnectionRequest req;
    List<Fichier> fichiers = new ArrayList<Fichier>();
    

    
    //constructor
    private FichierService() {
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
    public boolean addPerson(Fichier f) {

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
  
    
    
       //PARSING JSON
    public List<Fichier> parsePersonnes(String jsonText) {

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
                User user = new User();
                user.setId((int)item.get("id_user"));
                f.setUser(user);
                fichiers.add(f);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return fichiers;
    } 
}
