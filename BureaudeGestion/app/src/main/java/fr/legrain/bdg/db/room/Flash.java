package fr.legrain.bdg.db.room;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity
public class Flash implements Serializable {


    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo (name = "idFlashBdg")
    public int idFlashBdg;

    @ColumnInfo (name = "codeFlash")
    public String codeFlash;

    @ColumnInfo(name = "dateFlash")
    public Date dateFlash;

    @ColumnInfo(name = "dateTransfert")
    public Date dateTransfert = null;

    @ColumnInfo(name = "libelleFlash")
    public String libelleFlash;

    @ColumnInfo(name = "idTiers")
    public int idTiers;

    @ColumnInfo(name = "codeTiers")
    public String codeTiers;

    @ColumnInfo(name = "nomTiers")
    public String nomTiers;

    @ColumnInfo(name = "ttc")
    public String ttc;

    @ColumnInfo(name = "codeEtat")
    public String codeEtat;

    @ColumnInfo(name = "liblEtat")
    public String liblEtat;

    @ColumnInfo(name = "numeroCommandeFournisseur")
    public String numeroCommandeFournisseur;

    @ColumnInfo(name = "gestionLot")
    public String gestionLot;

    @ColumnInfo(name = "idUtilisateur")
    public int idUtilisateur;

    @ColumnInfo(name = "nom")
    public String nom;

    @ColumnInfo(name = "email")
    public String email;

    @ColumnInfo(name = "username")
    public String username;

    @ColumnInfo(name = "idGroupePreparation")
    public int idGroupePreparation;

    @ColumnInfo(name = "codeGroupePreparation")
    public String codeGroupePreparation;

    @ColumnInfo(name = "idTDoc")
    public int idTDoc;

    @ColumnInfo(name = "codeTDoc")
    public String codeTDoc;

    @Ignore
    public List<LigneFlash> lignes;
}
