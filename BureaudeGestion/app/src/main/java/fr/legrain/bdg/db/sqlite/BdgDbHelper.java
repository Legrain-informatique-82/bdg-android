package fr.legrain.bdg.db.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BdgDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "bdg.db";


    public BdgDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void recreerBaseVide(SQLiteDatabase db) {
        db.execSQL(BdgContract.TiersEntry.SQL_DELETE_ENTRIES);
        db.execSQL(BdgContract.ArticleEntry.SQL_DELETE_ENTRIES);
        db.execSQL(BdgContract.FlashEntry.SQL_DELETE_ENTRIES);
        db.execSQL(BdgContract.LigneFlashEntry.SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BdgContract.TiersEntry.SQL_CREATE_ENTRIES);
        db.execSQL(BdgContract.ArticleEntry.SQL_CREATE_ENTRIES);
        db.execSQL(BdgContract.FlashEntry.SQL_CREATE_ENTRIES);
        db.execSQL(BdgContract.LigneFlashEntry.SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(BdgContract.TiersEntry.SQL_DELETE_ENTRIES);
        db.execSQL(BdgContract.ArticleEntry.SQL_DELETE_ENTRIES);
        db.execSQL(BdgContract.TiersEntry.SQL_DELETE_ENTRIES);
        db.execSQL(BdgContract.ArticleEntry.SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


}