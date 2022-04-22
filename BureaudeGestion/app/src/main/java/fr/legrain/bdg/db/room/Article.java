package fr.legrain.bdg.db.room;


import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Article implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo (name = "idArticleBdg")
    private int idArticleBdg;

    @ColumnInfo (name = "codeArticle")
    private String codeArticle;

    @ColumnInfo(name = "libellecArticle")
    private String libellecArticle;

    @ColumnInfo(name = "libellelArticle")
    private String libellelArticle;

    @ColumnInfo(name = "codeBarre")
    private String codeBarre;

    @ColumnInfo(name = "codeFamille")
    private String codeFamille;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdArticleBdg() {
        return idArticleBdg;
    }

    public void setIdArticleBdg(int idArticleBdg) {
        this.idArticleBdg = idArticleBdg;
    }

    public String getCodeArticle() {
        return codeArticle;
    }

    public void setCodeArticle(String codeArticle) {
        this.codeArticle = codeArticle;
    }

    public String getLibellecArticle() {
        return libellecArticle;
    }

    public void setLibellecArticle(String libellecArticle) {
        this.libellecArticle = libellecArticle;
    }

    public String getLibellelArticle() {
        return libellelArticle;
    }

    public void setLibellelArticle(String libellelArticle) {
        this.libellelArticle = libellelArticle;
    }

    public String getCodeBarre() {
        return codeBarre;
    }

    public void setCodeBarre(String codeBarre) {
        this.codeBarre = codeBarre;
    }

    public String getCodeFamille() {
        return codeFamille;
    }

    public void setCodeFamille(String codeFamille) {
        this.codeFamille = codeFamille;
    }
}
