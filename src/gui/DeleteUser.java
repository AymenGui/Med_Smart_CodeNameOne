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

/**
 *
 * @author bureau
 */
public class DeleteUser extends Form {
    
    private Form current;
    private Resources theme;
       
    public DeleteUser(Resources theme) {
       
       
        setTitle("Delete User");
        setLayout(BoxLayout.y());
          getToolbar().addCommandToRightBar("back", null, ev->{
             new Home(theme).showBack();
        });
        
        
        TextField nom = new TextField("","nom");
        TextField prenom = new TextField("","prenom");
        TextField email = new TextField("","email");
        TextField cin = new TextField("","cin");
        TextField hashedPwd = new TextField("","hashedPwd");
        TextField numtel = new TextField("","numtel");
        TextField role = new TextField("","role");
        
        Button btnValider = new Button("Delete");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((nom.getText().length()==0) || (prenom.getText().length()==0) || (email.getText().length()==0)
                        || (cin.getText().length()==0) || (hashedPwd.getText().length()==0) || (numtel.getText().length()==0)
                        || (role.getText().length()==0))
                    Dialog.show("Alert", "Merci de renseigner tout les champs","OK","");
                else
                {
                    
                User us =new User() ;
                   
                        if(UserService.getInstance().deleteUser(us))
                        {
                                ToastBar.Status status = ToastBar.getInstance().createStatus();
                                status.setMessage("User Deleted Successfully ");
                                status.setExpires(5000);  
                                status.show();
                       
                        }else
                            Dialog.show("ERROR", "Server error", "OK","");
                    
                    
                }
                
                
            }
        });

         
    // Create a form and show it.
        addAll(nom,prenom,email,cin,hashedPwd,numtel,role,btnValider);
    
                
    }
        
    
}
