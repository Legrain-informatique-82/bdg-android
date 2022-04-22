package fr.legrain.bdg.db.room;


import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Tiers implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo (name = "idTiersBdg")
    private int idTiersBdg;

    @ColumnInfo (name = "codeTiers")
    private String codeTiers;

    @ColumnInfo(name = "nomTiers")
    private String nomTiers;
    @ColumnInfo(name = "prenomTiers")
    private String prenomTiers;
    @ColumnInfo(name = "nomEntreprise")
    private String nomEntreprise;

    @ColumnInfo(name = "codeTTiers")
    private String codeTTiers;
    @ColumnInfo(name = "libelleTTiers")
    private String libelleTTiers;

    @ColumnInfo(name = "adresse1Adresse")
    private String adresse1Adresse;
    @ColumnInfo(name = "adresse2Adresse")
    private String adresse2Adresse;
    @ColumnInfo(name = "adresse3Adresse")
    private String adresse3Adresse;
    @ColumnInfo(name = "codepostalAdresse")
    private String codepostalAdresse;
    @ColumnInfo(name = "villeAdresse")
    private String villeAdresse;
    @ColumnInfo(name = "paysAdresse")
    private String paysAdresse;

    @ColumnInfo(name = "adresseEmail")
    private String adresseEmail;
    @ColumnInfo(name = "adresseWeb")
    private String adresseWeb;
    @ColumnInfo(name = "numeroTelephone")
    private String numeroTelephone;

    @ColumnInfo(name = "codeFamilleTiers")
    private String codeFamilleTiers;
    @ColumnInfo(name = "libelleFamilleTiers")
    private String libelleFamilleTiers;

    public String getAdresseComplete() {
        String adresse = "";
        adresse += getAdresse1Adresse()!=null?getAdresse1Adresse()+" ":"";
        adresse += getAdresse2Adresse()!=null?"\n"+getAdresse2Adresse()+" ":"";
        adresse += getAdresse3Adresse()!=null?"\n"+getAdresse3Adresse()+" ":"";
        adresse += getCodepostalAdresse()!=null?"\n"+getCodepostalAdresse()+" ":"";
        adresse += getVilleAdresse()!=null?getVilleAdresse()+" ":"";
        adresse += getPaysAdresse()!=null?"\n"+getPaysAdresse()+" ":"";
        adresse = adresse.trim();
        return adresse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTiersBdg() {
        return idTiersBdg;
    }

    public void setIdTiersBdg(int idTiersBdg) {
        this.idTiersBdg = idTiersBdg;
    }

    public String getCodeTiers() {
        return codeTiers;
    }

    public void setCodeTiers(String codeTiers) {
        this.codeTiers = codeTiers;
    }

    public String getNomTiers() {
        return nomTiers;
    }

    public void setNomTiers(String nomTiers) {
        this.nomTiers = nomTiers;
    }

    public String getPrenomTiers() {
        return prenomTiers;
    }

    public void setPrenomTiers(String prenomTiers) {
        this.prenomTiers = prenomTiers;
    }

    public String getNomEntreprise() {
        return nomEntreprise;
    }

    public void setNomEntreprise(String nomEntreprise) {
        this.nomEntreprise = nomEntreprise;
    }

    public String getCodeTTiers() {
        return codeTTiers;
    }

    public void setCodeTTiers(String codeTTiers) {
        this.codeTTiers = codeTTiers;
    }

    public String getLibelleTTiers() {
        return libelleTTiers;
    }

    public void setLibelleTTiers(String libelleTTiers) {
        this.libelleTTiers = libelleTTiers;
    }

    public String getAdresse1Adresse() {
        return adresse1Adresse;
    }

    public void setAdresse1Adresse(String adresse1Adresse) {
        this.adresse1Adresse = adresse1Adresse;
    }

    public String getAdresse2Adresse() {
        return adresse2Adresse;
    }

    public void setAdresse2Adresse(String adresse2Adresse) {
        this.adresse2Adresse = adresse2Adresse;
    }

    public String getAdresse3Adresse() {
        return adresse3Adresse;
    }

    public void setAdresse3Adresse(String adresse3Adresse) {
        this.adresse3Adresse = adresse3Adresse;
    }

    public String getCodepostalAdresse() {
        return codepostalAdresse;
    }

    public void setCodepostalAdresse(String codepostalAdresse) {
        this.codepostalAdresse = codepostalAdresse;
    }

    public String getVilleAdresse() {
        return villeAdresse;
    }

    public void setVilleAdresse(String villeAdresse) {
        this.villeAdresse = villeAdresse;
    }

    public String getPaysAdresse() {
        return paysAdresse;
    }

    public void setPaysAdresse(String paysAdresse) {
        this.paysAdresse = paysAdresse;
    }

    public String getAdresseEmail() {
        return adresseEmail;
    }

    public void setAdresseEmail(String adresseEmail) {
        this.adresseEmail = adresseEmail;
    }

    public String getAdresseWeb() {
        return adresseWeb;
    }

    public void setAdresseWeb(String adresseWeb) {
        this.adresseWeb = adresseWeb;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public String getCodeFamilleTiers() {
        return codeFamilleTiers;
    }

    public void setCodeFamilleTiers(String codeFamilleTiers) {
        this.codeFamilleTiers = codeFamilleTiers;
    }

    public String getLibelleFamilleTiers() {
        return libelleFamilleTiers;
    }

    public void setLibelleFamilleTiers(String libelleFamilleTiers) {
        this.libelleFamilleTiers = libelleFamilleTiers;
    }
}
