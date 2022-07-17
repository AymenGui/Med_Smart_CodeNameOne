/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import Models.User;
import Services.UserService;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;

/**
 *
 * @author bureau
 */
public class AddUser extends Form{
    private Form current;
           private Resources theme;
       
            public AddUser(Resources theme) {
       
       
        setTitle("Add User");
        setLayout(BoxLayout.y());
          getToolbar().addCommandToRightBar("back", null, ev->{
             new Home(theme).showBack();
        });
        
        
        TextField nom = new TextField("","Nom");
        TextField prenom = new TextField("","Prénom");
        TextField email = new TextField("","Email");
        TextField cin = new TextField("","CIN");
        TextField numtel = new TextField("","Tel");
        TextField role = new TextField("","Role");


       
         

       
                
        Button btnValider = new Button("Ajouter");
        
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                if ((nom.getText().length()==0)||(prenom.getText().length()==0)|| (email.getText().length()==0)||
                        (cin.getText().length()==0)|| (numtel.getText().length()==0)|| (role.getText().length()==0))
                    Dialog.show("Alert", "Merci de renseigner tout les champs","OK","");
                else
                {
                    User us=new User(nom.getText(),prenom.getText() , email.getText(), cin.getText(), cin.getText(), numtel.getText(),role.getText());
                    
                   
                        if(UserService.getInstance().addUser(us))
                        {
                                ToastBar.Status status = ToastBar.getInstance().createStatus();
                                status.setMessage("user added successfully");
                                status.setExpires(5000);  
                                status.show();
                                
                       
                        try {
                            new ListUsers(theme).show();
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
                        }else
                            Dialog.show("ERROR", "Server error", "OK","");
                    
                    
                }
                
                
            }
        });

         
    // Create a form and show it.
        addAll(nom,prenom,email,cin,numtel,role,btnValider);
    
                
    }
    
    
}
