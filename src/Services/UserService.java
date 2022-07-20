/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

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
 * @author bureau
 */
public class UserService {
    
    public ArrayList<User> user;

    //prefix
    private String userPrefix = "/user";

    //var
    static UserService instance = null;
    boolean resultOK = false;
    ConnectionRequest req;
    List<User> listUsers = new ArrayList<User>();

    //constructor
    UserService() {
        req = new ConnectionRequest();
    }

    //Get
    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }

        return instance;
    }

    //Ajout un utilisateur
    public boolean addUser(User user) {

        //build URL
        String addURL = Statistics.BASE_URL +"/user/add/" + user.getNom() + "/" + user.getPrenom() + "/" + user.getEmail() + "/" + user.getCin()+ "/" + user.getHashedPwd()+ "/" + user.getNumtel()+ "/" + user.getRole() ;

        //2
        req.setUrl(addURL);

        //3
        req.setPost(true);

        //4 : Transfert data
        req.addArgument("nom", user.getNom());
        req.addArgument("prenom", user.getPrenom());
        req.addArgument("email", user.getEmail());
        req.addArgument("cin", user.getCin());
        req.addArgument("hashedpwd", user.getHashedPwd());
        req.addArgument("numtel", user.getNumtel());
        req.addArgument("role", user.getRole());

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

    //Liste des utilisateurs
    public ArrayList<User> getAllUsers() {
        
        String selectURL = Statistics.BASE_URL +"/user/showAll";
        req.setUrl(selectURL);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                user = (ArrayList<User>) parseUser(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println(user);
        return user;
    }

     //PARSING JSON
    public List<User> parseUser(String jsonText) {

        //parser
        user = new ArrayList<>();
        JSONParser jp = new JSONParser();

        try {
            //2
            Map<String, Object> jsonList = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) jsonList.get("root");

            for (Map<String, Object> item : list) {
                User us = new User();
                float id = Float.parseFloat(item.get("id").toString());
                us.setId((int) id);
                us.setNom((String) item.get("nom"));
                us.setPrenom((String) item.get("prenom"));
                us.setEmail((String) item.get("email"));
                us.setCin((String) item.get("cin"));
                us.setHashedPwd((String) item.get("hashedpwd"));
                us.setNumtel((String) item.get("numtel"));
                us.setRole((String) item.get("role"));

                user.add(us);

            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return user;
    }

    public boolean deleteUser(User us) {

        //build URL
        String deleteURL = Statistics.BASE_URL +"/user/delete";

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
