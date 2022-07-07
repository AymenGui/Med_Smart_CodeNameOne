/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Etablissements;
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
 * @author user
 */
public class ServiceEtab {
     public ArrayList<Etablissements> Etabs;  
    
    public static ServiceEtab instance=null; 
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceEtab() {
         req =  new ConnectionRequest();
    }

    public static ServiceEtab getInstance() {
        if (instance == null) {
            instance = new ServiceEtab();
        }
        return instance;
    }
    
     public ArrayList<Etablissements> parseOffres(String jsonText){
        try {
            Etabs  =new ArrayList<Etablissements>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksDestinationJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksDestinationJson.get("root");
            for(Map<String,Object> obj : list){
                Etablissements etab = new Etablissements ();
                
                //id
                float id = Float.parseFloat(obj.get("id").toString());
                etab.setId((int)id);
                //nom
                if (obj.get("name")==null)
                etab.setName("null");
                else
                etab.setName(obj.get("name").toString());
                
                //Adresse
                if (obj.get("adresse")==null)
                etab.setAdresse("null");
                else
                    etab.setAdresse(obj.get("adresse").toString());
                 
                //Type
                 if (obj.get("type")==null)
                etab.setType("null");
                else
                etab.setType(obj.get("type").toString());
                 
                 
                 
                Etabs.add(etab);
            }
            
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            
        }
        return Etabs;
    }
    
         public ArrayList<Etablissements> getAllEtabs(){
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statistics.BASE_URL+"/etab/showAll";
        req.setUrl(url);
    
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Etabs = parseOffres(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Etabs;
    }
         
         
      public boolean addEtab(Etablissements etab) {
        String url = Statistics.BASE_URL + "/etab/add/"+ etab.getName()+"/" + etab.getAdresse()+"/"+etab.getType();
        
        req.setUrl(url);
        req.setPost(true);
  
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
   }
      
     public boolean EtidEtab(Etablissements etab) {
        String url = Statistics.BASE_URL + "/etab/edit/"+etab.getId()+"/"+etab.getName()+"/" + etab.getAdresse()+"/"+etab.getType();
        
        req.setUrl(url);
        req.setPost(true);
  
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
   }
}
