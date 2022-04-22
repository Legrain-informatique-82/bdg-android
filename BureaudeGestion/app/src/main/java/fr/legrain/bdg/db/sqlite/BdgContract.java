package fr.legrain.bdg.db.sqlite;

import android.provider.BaseColumns;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/*
https://developer.android.com/training/basics/data-storage/databases.html
 */
public final class BdgContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private BdgContract() {}

    //https://www.sqlite.org/datatype3.html
    public static final String TEXT_TYPE = " TEXT";
    public static final String INTEGER_TYPE = " INTEGER";
    public static final String DATE_TYPE = " DATE";
    public static final String DATETIME_TYPE = " DATETIME";
    public static final String NUMERIC_TYPE = " NUMERIC";
    public static final String BOOLEAN_TYPE = " INTEGER";
    public static final String COMMA_SEP = ",";

    /* Inner class that defines the table contents */
    public static class TiersEntry implements BaseColumns {
        public static final String TABLE_NAME = "tiers";
        public static final String COLUMN_NAME_ID_TIERS = "idTiers";
        public static final String COLUMN_NAME_CODE_TIERS = "codeTiers";
        public static final String COLUMN_NAME_NOM_TIERS = "nomTiers";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TiersEntry.TABLE_NAME + " (" +
                        TiersEntry._ID + " INTEGER PRIMARY KEY," +
                        TiersEntry.COLUMN_NAME_ID_TIERS + INTEGER_TYPE + COMMA_SEP +
                        TiersEntry.COLUMN_NAME_CODE_TIERS + TEXT_TYPE + COMMA_SEP +
                        TiersEntry.COLUMN_NAME_NOM_TIERS + TEXT_TYPE +
                        " )";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TiersEntry.TABLE_NAME;
    }

    public static class ArticleEntry implements BaseColumns {
        public static final String TABLE_NAME = "article";
        public static final String COLUMN_NAME_ID_ARTICLE = "idArticle";
        public static final String COLUMN_NAME_CODE_ARTICLE= "codeArticle";
        public static final String COLUMN_NAME_LIBELLE_C_ARTICLE = "libellecArticle";
        public static final String COLUMN_NAME_LIBELLE_L_ARTICLE = "libellelArticle";
        public static final String COLUMN_NAME_CODE_BARRE_ARTICLE = "codeBarre";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + ArticleEntry.TABLE_NAME + " (" +
                        ArticleEntry._ID + " INTEGER PRIMARY KEY," +
                        ArticleEntry.COLUMN_NAME_ID_ARTICLE + INTEGER_TYPE + COMMA_SEP +
                        ArticleEntry.COLUMN_NAME_CODE_ARTICLE + TEXT_TYPE + COMMA_SEP +
                        ArticleEntry.COLUMN_NAME_LIBELLE_C_ARTICLE + TEXT_TYPE + COMMA_SEP +
                        ArticleEntry.COLUMN_NAME_LIBELLE_L_ARTICLE + TEXT_TYPE + COMMA_SEP +
                        ArticleEntry.COLUMN_NAME_CODE_BARRE_ARTICLE + TEXT_TYPE +
                         " )";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + ArticleEntry.TABLE_NAME;
    }

    public static class FlashEntry implements BaseColumns {

        public static final String TABLE_NAME = "flash";
        public static final String COLUMN_NAME_ID_FLASH = "idFlash";
        public static final String COLUMN_NAME_CODE_FLASH= "codeFlash";
        public static final String COLUMN_NAME_DATE_FLASH = "dateFlash";
        public static final String COLUMN_NAME_DATE_TRANSFERT = "dateTransfert";
        public static final String COLUMN_NAME_LIBELLE_FLASH = "libelleFlash";
        public static final String COLUMN_NAME_ID_TIERS = "idTiers";
        public static final String COLUMN_NAME_CODE_TIERS = "codeTiers";
        public static final String COLUMN_NAME_NOM_TIERS = "nomTiers";
        public static final String COLUMN_NAME_TTC = "ttc";
        public static final String COLUMN_NAME_CODE_ETAT = "codeEtat";
        public static final String COLUMN_NAME_LIBL_ETAT = "liblEtat";
        public static final String COLUMN_NAME_NNUMERO_COMMANDE_FOURNISSEUR = "numeroCommandeFournisseur";
        public static final String COLUMN_NAME_GESTION_LOT = "gestionLot";
        public static final String COLUMN_NAME_ID_UTILISATEUR = "idUtilisateur";
        public static final String COLUMN_NAME_NOM = "nom";
        public static final String COLUMN_NAME_ID_GROUPE_PREPARATION = "idGroupePreparation";
        public static final String COLUMN_NAME_CODE_GROUPE_PREPARATION = "codeGroupePreparation";
        public static final String COLUMN_NAME_ID_T_DOC = "idTDoc";
        public static final String COLUMN_NAME_CODE_T_DOC = "codeTDoc";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + FlashEntry.TABLE_NAME + " (" +
                        FlashEntry._ID + " INTEGER PRIMARY KEY," +
                        FlashEntry.COLUMN_NAME_ID_FLASH + INTEGER_TYPE + COMMA_SEP +
                        FlashEntry.COLUMN_NAME_CODE_FLASH + TEXT_TYPE + COMMA_SEP +
                        FlashEntry.COLUMN_NAME_DATE_FLASH + DATE_TYPE + COMMA_SEP +
                        FlashEntry.COLUMN_NAME_DATE_TRANSFERT + DATE_TYPE + COMMA_SEP +
                        FlashEntry.COLUMN_NAME_LIBELLE_FLASH + TEXT_TYPE + COMMA_SEP +

                        FlashEntry.COLUMN_NAME_ID_TIERS + INTEGER_TYPE + COMMA_SEP +
                        FlashEntry.COLUMN_NAME_CODE_TIERS + TEXT_TYPE + COMMA_SEP +
                        FlashEntry.COLUMN_NAME_NOM_TIERS + TEXT_TYPE + COMMA_SEP +
                        FlashEntry.COLUMN_NAME_TTC + BOOLEAN_TYPE + COMMA_SEP +
                        FlashEntry.COLUMN_NAME_CODE_ETAT + TEXT_TYPE + COMMA_SEP +
                        FlashEntry.COLUMN_NAME_LIBL_ETAT + TEXT_TYPE + COMMA_SEP +
                        FlashEntry.COLUMN_NAME_NNUMERO_COMMANDE_FOURNISSEUR + TEXT_TYPE + COMMA_SEP +
                        FlashEntry.COLUMN_NAME_GESTION_LOT + BOOLEAN_TYPE + COMMA_SEP +
                        FlashEntry.COLUMN_NAME_ID_UTILISATEUR + INTEGER_TYPE + COMMA_SEP +
                        FlashEntry.COLUMN_NAME_NOM + TEXT_TYPE + COMMA_SEP +
                        FlashEntry.COLUMN_NAME_ID_GROUPE_PREPARATION + INTEGER_TYPE + COMMA_SEP +
                        FlashEntry.COLUMN_NAME_CODE_GROUPE_PREPARATION + TEXT_TYPE + COMMA_SEP +
                        FlashEntry.COLUMN_NAME_ID_T_DOC + INTEGER_TYPE + COMMA_SEP +
                        FlashEntry.COLUMN_NAME_CODE_T_DOC + TEXT_TYPE +
                        " )";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + FlashEntry.TABLE_NAME;
    }

    public static class LigneFlashEntry implements BaseColumns {

        public static final String TABLE_NAME = "ligneFlash";
        public static final String COLUMN_NAME_ID_L_FLASH = "idLFlash";
        public static final String COLUMN_NAME_ID_L_DOCUMENT = "idLDocument";
        public static final String COLUMN_NAME_CODE_DOCUMENT = "codeDocument";
        public static final String COLUMN_NAME_CODE_ETAT = "codeEtat";
        public static final String COLUMN_NAME_ID_ARTICLE = "idArticle";
        public static final String COLUMN_NAME_NUM_LIGNE_DOCUMENT = "numLigneLDocument";
        public static final String COLUMN_NAME_CODE_ARTICLE = "codeArticle";
        public static final String COLUMN_NAME_LIB_L_DOCUMENT = "libLDocument";
        public static final String COLUMN_NAME_QTE_L_DOCUMENT = "qteLDocument";
        public static final String COLUMN_NAME_QTE2_L_DOCUMENT = "qte2LDocument";
        public static final String COLUMN_NAME_U1_L_DOCUMENT = "u1LDocument";
        public static final String COLUMN_NAME_U2_L_DOCUMENT = "u2LDocument";
        public static final String COLUMN_NAME_CODE_ENTREPOT = "codeEntrepot";
        public static final String COLUMN_NAME_EMPLACEMENT = "emplacement";
        public static final String COLUMN_NAME_DLUO = "dluo";
        public static final String COLUMN_NAME_NUMLOT = "numLot";
        public static final String COLUMN_NAME_LIBELLE_LOT= "libelleLot";
        public static final String COLUMN_NAME_CODE_BARRE = "codeBarre";
        public static final String COLUMN_NAME_CODE_BARRE_LU = "codeBarreLu";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + LigneFlashEntry.TABLE_NAME + " (" +
                        LigneFlashEntry._ID + " INTEGER PRIMARY KEY," +
                        LigneFlashEntry.COLUMN_NAME_ID_L_FLASH + INTEGER_TYPE + COMMA_SEP +
                        LigneFlashEntry.COLUMN_NAME_ID_L_DOCUMENT + TEXT_TYPE + COMMA_SEP +
                        LigneFlashEntry.COLUMN_NAME_CODE_DOCUMENT + TEXT_TYPE + COMMA_SEP +
                        LigneFlashEntry.COLUMN_NAME_CODE_ETAT + TEXT_TYPE + COMMA_SEP +
                        LigneFlashEntry.COLUMN_NAME_ID_ARTICLE + TEXT_TYPE + COMMA_SEP +

                        LigneFlashEntry.COLUMN_NAME_NUM_LIGNE_DOCUMENT + INTEGER_TYPE + COMMA_SEP +
                        LigneFlashEntry.COLUMN_NAME_CODE_ARTICLE + TEXT_TYPE + COMMA_SEP +
                        LigneFlashEntry.COLUMN_NAME_LIB_L_DOCUMENT + TEXT_TYPE + COMMA_SEP +
                        LigneFlashEntry.COLUMN_NAME_QTE_L_DOCUMENT + NUMERIC_TYPE + COMMA_SEP +
                        LigneFlashEntry.COLUMN_NAME_QTE2_L_DOCUMENT + NUMERIC_TYPE + COMMA_SEP +
                        LigneFlashEntry.COLUMN_NAME_U1_L_DOCUMENT + TEXT_TYPE + COMMA_SEP +
                        LigneFlashEntry.COLUMN_NAME_U2_L_DOCUMENT + TEXT_TYPE + COMMA_SEP +
                        LigneFlashEntry.COLUMN_NAME_CODE_ENTREPOT + TEXT_TYPE + COMMA_SEP +
                        LigneFlashEntry.COLUMN_NAME_EMPLACEMENT + TEXT_TYPE + COMMA_SEP +
                        LigneFlashEntry.COLUMN_NAME_DLUO + DATE_TYPE + COMMA_SEP +
                        LigneFlashEntry.COLUMN_NAME_NUMLOT + TEXT_TYPE + COMMA_SEP +
                        LigneFlashEntry.COLUMN_NAME_LIBELLE_LOT + TEXT_TYPE + COMMA_SEP +
                        LigneFlashEntry.COLUMN_NAME_CODE_BARRE + TEXT_TYPE + COMMA_SEP +
                        LigneFlashEntry.COLUMN_NAME_CODE_BARRE_LU + TEXT_TYPE +
                        " )";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + LigneFlashEntry.TABLE_NAME;
    }

}

