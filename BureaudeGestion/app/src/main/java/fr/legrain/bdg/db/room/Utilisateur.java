package fr.legrain.bdg.db.room;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity
public class Utilisateur implements Serializable {


    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo (name = "idUtilisateurBdg")
    public int idUtilisateurBdg;

    @ColumnInfo (name = "username")
    private String username;

    @ColumnInfo (name = "passwd")
    private String passwd;

    @ColumnInfo (name = "nom")
    private String nom;

    @ColumnInfo (name = "prenom")
    private String prenom;

    @ColumnInfo (name = "email")
    private String email;

    @ColumnInfo (name = "dernierAcces")
    private Date dernierAcces;

    @ColumnInfo (name = "actif")
    private Boolean actif;

    @ColumnInfo (name = "autorisations")
    private String autorisations;

    @ColumnInfo (name = "androidRegistrationToken")
    private String androidRegistrationToken; //token Firebase pour l'instant

    @ColumnInfo (name = "dernierAccesMobile")
    private Date dernierAccesMobile;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUtilisateurBdg() {
        return idUtilisateurBdg;
    }

    public void setIdUtilisateurBdg(int idUtilisateurBdg) {
        this.idUtilisateurBdg = idUtilisateurBdg;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDernierAcces() {
        return dernierAcces;
    }

    public void setDernierAcces(Date dernierAcces) {
        this.dernierAcces = dernierAcces;
    }

    public Boolean getActif() {
        return actif;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    public String getAutorisations() {
        return autorisations;
    }

    public void setAutorisations(String autorisations) {
        this.autorisations = autorisations;
    }

    public String getAndroidRegistrationToken() {
        return androidRegistrationToken;
    }

    public void setAndroidRegistrationToken(String androidRegistrationToken) {
        this.androidRegistrationToken = androidRegistrationToken;
    }

    public Date getDernierAccesMobile() {
        return dernierAccesMobile;
    }

    public void setDernierAccesMobile(Date dernierAccesMobile) {
        this.dernierAccesMobile = dernierAccesMobile;
    }
}
