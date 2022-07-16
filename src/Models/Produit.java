/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.ArrayList;

/**
 *
 * @author NAD
 */
public class Produit {
    
    //
    
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    private String name;
    private String Id_physique;
    private double qte;
    private double id_etap;
 

    public Produit() {
    }

      public Produit(String name, String Id_physique , double qte , double id_etap ) {
        
        this.name = name;
        this.Id_physique = Id_physique;
        this.qte = qte;
        this.id_etap = id_etap;
    }
    public Produit(long id,String name, String Id_physique , double qte , double id_etap ) {
        this.id=id;
        this.name = name;
        this.Id_physique = Id_physique;
        this.qte = qte;
        this.id_etap = id_etap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId_physique() {
        return Id_physique;
    }

    public void setId_physique(String Id_physique) {
        this.Id_physique = Id_physique;
    }

    public double getQte() {
        return qte;
    }

    public void setQte(Integer qte) {
        this.qte = qte;
    }

    public double getId_etap() {
        return id_etap;
    }

    public void setId_etap(Integer id_etap) {
        this.id_etap = id_etap;
    }

    @Override
    public String toString() {
        return "Produit{" +  "id=" + id +"name=" + name + ", Id_physique=" + Id_physique + ", qte=" + qte + ", id_etap=" + id_etap + '}';
    }


  
    
    
    
}
