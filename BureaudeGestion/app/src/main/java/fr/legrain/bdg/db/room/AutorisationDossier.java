package fr.legrain.bdg.db.room;


import java.io.Serializable;
import java.util.Date;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class AutorisationDossier implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo (name = "fichier")
    private String fichier;


    @ColumnInfo (name = "dateDerSynchro")
    private Date dateDerSynchro;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFichier() {
        return fichier;
    }

    public void setFichier(String fichier) {
        this.fichier = fichier;
    }

    public Date getDateDerSynchro() {
        return dateDerSynchro;
    }

    public void setDateDerSynchro(Date dateDerSynchro) {
        this.dateDerSynchro = dateDerSynchro;
    }

    public static final String ID_MODULE_TIERS = "fr.legrain.Tiers";

    public boolean moduleAutorise(String idModule) {
        boolean  autorisation = false;
        if(fichier!=null) {
            autorisation = fichier.contains(idModule);
        }
        return autorisation;
    }
}
