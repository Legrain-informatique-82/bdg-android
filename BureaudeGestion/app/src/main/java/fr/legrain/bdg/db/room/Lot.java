package fr.legrain.bdg.db.room;


import java.io.Serializable;
import java.util.Date;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Lot implements Serializable {


    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo (name = "idLotBdg")
    public int idLotBdg;

    @ColumnInfo (name = "numLot")
    private String numLot;

    @ColumnInfo (name = "codeBarre")
    private String codeBarre;

    @ColumnInfo (name = "libelle")
    private String libelle;

    @ColumnInfo (name = "dluo")
    private Date dluo;

    @ColumnInfo (name = "dateLot")
    private Date dateLot;

    @ColumnInfo (name = "codeArticle")
    private String codeArticle;

    @ColumnInfo (name = "libelleArticle")
    private String libelleArticle;

    @ColumnInfo (name = "codeUnite1")
    private String codeUnite1;

    @ColumnInfo (name = "codeUnite2")
    private String codeUnite2;

    @ColumnInfo (name = "codeUniteRef")
    private String codeUniteRef;

    @ColumnInfo (name = "liblUnite1")
    private String liblUnite1;

    @ColumnInfo (name = "liblUnite2")
    private String liblUnite2;

    @ColumnInfo (name = "liblUniteRef")
    private String liblUniteRef;

    @ColumnInfo (name = "termine")
    private Boolean termine;

    @ColumnInfo (name = "matierePremiere")
    private Boolean matierePremiere=false;

    @ColumnInfo (name = "produitFini")
    private Boolean produitFini=false;

    @ColumnInfo (name = "virtuel")
    private Boolean virtuel = false;

    @ColumnInfo (name = "virtuelUnique")
    private Boolean virtuelUnique = false;

    @ColumnInfo (name = "lotConforme")
    private Boolean lotConforme = false;

    @ColumnInfo (name = "presenceDeNonConformite")
    private Boolean presenceDeNonConformite = false;

//    @ColumnInfo (name = "idStatusConformite")
//    private Integer idStatusConformite;

    @ColumnInfo (name = "codeStatusConformite")
    private String codeStatusConformite;

    @ColumnInfo (name = "libelleStatusConformiteLot")
    private String libelleStatusConformiteLot;

    /*
	private BigDecimal qteRef;
	private Boolean utiliseDlc;

	private Boolean sens;
	private Integer nbDecimal;
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdLotBdg() {
        return idLotBdg;
    }

    public void setIdLotBdg(int idLotBdg) {
        this.idLotBdg = idLotBdg;
    }

    public String getNumLot() {
        return numLot;
    }

    public void setNumLot(String numLot) {
        this.numLot = numLot;
    }

    public String getCodeBarre() {
        return codeBarre;
    }

    public void setCodeBarre(String codeBarre) {
        this.codeBarre = codeBarre;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Date getDluo() {
        return dluo;
    }

    public void setDluo(Date dluo) {
        this.dluo = dluo;
    }

    public Date getDateLot() {
        return dateLot;
    }

    public void setDateLot(Date dateLot) {
        this.dateLot = dateLot;
    }

    public String getCodeArticle() {
        return codeArticle;
    }

    public void setCodeArticle(String codeArticle) {
        this.codeArticle = codeArticle;
    }

    public String getLibelleArticle() {
        return libelleArticle;
    }

    public void setLibelleArticle(String libelleArticle) {
        this.libelleArticle = libelleArticle;
    }

    public String getCodeUnite1() {
        return codeUnite1;
    }

    public void setCodeUnite1(String codeUnite1) {
        this.codeUnite1 = codeUnite1;
    }

    public String getCodeUnite2() {
        return codeUnite2;
    }

    public void setCodeUnite2(String codeUnite2) {
        this.codeUnite2 = codeUnite2;
    }

    public String getCodeUniteRef() {
        return codeUniteRef;
    }

    public void setCodeUniteRef(String codeUniteRef) {
        this.codeUniteRef = codeUniteRef;
    }

    public String getLiblUnite1() {
        return liblUnite1;
    }

    public void setLiblUnite1(String liblUnite1) {
        this.liblUnite1 = liblUnite1;
    }

    public String getLiblUnite2() {
        return liblUnite2;
    }

    public void setLiblUnite2(String liblUnite2) {
        this.liblUnite2 = liblUnite2;
    }

    public String getLiblUniteRef() {
        return liblUniteRef;
    }

    public void setLiblUniteRef(String liblUniteRef) {
        this.liblUniteRef = liblUniteRef;
    }

    public Boolean getTermine() {
        return termine;
    }

    public void setTermine(Boolean termine) {
        this.termine = termine;
    }

    public Boolean getMatierePremiere() {
        return matierePremiere;
    }

    public void setMatierePremiere(Boolean matierePremiere) {
        this.matierePremiere = matierePremiere;
    }

    public Boolean getProduitFini() {
        return produitFini;
    }

    public void setProduitFini(Boolean produitFini) {
        this.produitFini = produitFini;
    }

    public Boolean getVirtuel() {
        return virtuel;
    }

    public void setVirtuel(Boolean virtuel) {
        this.virtuel = virtuel;
    }

    public Boolean getVirtuelUnique() {
        return virtuelUnique;
    }

    public void setVirtuelUnique(Boolean virtuelUnique) {
        this.virtuelUnique = virtuelUnique;
    }

    public Boolean getLotConforme() {
        return lotConforme;
    }

    public void setLotConforme(Boolean lotConforme) {
        this.lotConforme = lotConforme;
    }

    public Boolean getPresenceDeNonConformite() {
        return presenceDeNonConformite;
    }

    public void setPresenceDeNonConformite(Boolean presenceDeNonConformite) {
        this.presenceDeNonConformite = presenceDeNonConformite;
    }

    public String getCodeStatusConformite() {
        return codeStatusConformite;
    }

    public void setCodeStatusConformite(String codeStatusConformite) {
        this.codeStatusConformite = codeStatusConformite;
    }

    public String getLibelleStatusConformiteLot() {
        return libelleStatusConformiteLot;
    }

    public void setLibelleStatusConformiteLot(String libelleStatusConformiteLot) {
        this.libelleStatusConformiteLot = libelleStatusConformiteLot;
    }
}
