package fr.legrain.bdg.db.room;


import java.math.BigDecimal;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class LigneFlash {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo (name = "idFlash")
    public int idFlash;

    @ColumnInfo (name = "idFlashBdg")
    public int idLFlashBdg;

    @ColumnInfo (name = "idLDocumentBdg")
    public int idLDocument;

    @ColumnInfo(name = "codeDocument")
    public String codeDocument;

    @ColumnInfo(name = "numLigneLDocument")
    public String numLigneLDocument;

    @ColumnInfo(name = "codeEtat")
    public String codeEtat;

    @ColumnInfo(name = "idArticle")
    public int idArticle;

    @ColumnInfo(name = "codeArticle")
    public String codeArticle;

    @ColumnInfo(name = "libLDocument")
    public String libLDocument;

    @ColumnInfo(name = "qteLDocument", typeAffinity = ColumnInfo.TEXT)
    public BigDecimal qteLDocument;

    @ColumnInfo(name = "qte2LDocument", typeAffinity = ColumnInfo.TEXT)
    public BigDecimal qte2LDocument;

    @ColumnInfo(name = "u1LDocument")
    public String u1LDocument;

    @ColumnInfo(name = "u2LDocument")
    public String u2LDocument;

    @ColumnInfo(name = "codeEntrepot")
    public String codeEntrepot;

    @ColumnInfo(name = "emplacement")
    public String emplacement;

    @ColumnInfo(name = "dluo")
    public String dluo;

    @ColumnInfo(name = "numLot")
    public String numLot;

    @ColumnInfo(name = "libelleLot")
    public String libelleLot;

    @ColumnInfo(name = "codeBarre")
    public String codeBarre;

    @ColumnInfo(name = "codeBarreLu")
    public String codeBarreLu;
}
