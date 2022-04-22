package fr.legrain.bdg.db.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

import fr.legrain.bdg.api.client.dto.ArticleDTO;
import fr.legrain.bdg.api.client.dto.TiersDTO;

/**
 * Created by nicolas on 28/09/16.
 */

/*
 * Linux
 * cd /home/nicolas/Android/Sdk/platform-tools
 * Equivalent du PULL
 * ~/Android/Sdk/platform-tools$ ./adb exec-out run-as fr.legrain.bdg cat databases/bdg.db > /tmp/bdg.db
 *
 *
 * PUSH avec répertoire temporaire pour les droits
 * ./adb shell run-as fr.legrain.bdg chmod 666 databases/bdg.db
 * ./adb push /tmp/bdg2.db /sdcard/bdg.db
 * ./adb shell run-as fr.legrain.bdg mv /sdcard/bdg.db /data/data/fr.legrain.bdg/databases/bdg.db
 *
 *
 * sqlitebrowser
 *
 * OSX
 * ~/Library/
 */
public class BdgCRUD {

    //private Context context;
    private BdgDbHelper mDbHelper;

    public BdgCRUD(Context context) {
       // this.context = context;
        mDbHelper = new BdgDbHelper(context);
    }

    public void recreerBaseVide() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        mDbHelper.recreerBaseVide(db);
    }

    public void exportDatabse(String databaseName) {
        //http://cleancodedevelopment-qualityseal.blogspot.fr/2014/07/how-to-import-export-or-backup-sqlite.html
        try {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory(); //storage/emulated

            if (sd.canWrite()) {
                String currentDBPath = "//data//"+"fr.legrain.bdg"+"//databases//"+"bdg.db"+"";
                String backupDBPath = "backupname.db";
                File currentDB = new File(data, currentDBPath);
                File backupDB = new File(sd, backupDBPath);

                if (currentDB.exists()) {
                    FileChannel src = new FileInputStream(currentDB).getChannel();
                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /******************************************************************************************************************/
    /******************************************************************************************************************/
    /******************************************************************************************************************/
    public void createTiers(TiersDTO tiers) {

        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(BdgContract.TiersEntry.COLUMN_NAME_ID_TIERS,tiers.getId());
        values.put(BdgContract.TiersEntry.COLUMN_NAME_CODE_TIERS,tiers.getCodeTiers());
        values.put(BdgContract.TiersEntry.COLUMN_NAME_NOM_TIERS,tiers.getNomTiers());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(BdgContract.TiersEntry.TABLE_NAME, null, values);

       // Log.d("insertion OK","");
    }

    public TiersDTO readTiers(String id) {

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BdgContract.TiersEntry._ID,
                BdgContract.TiersEntry.COLUMN_NAME_ID_TIERS,
                BdgContract.TiersEntry.COLUMN_NAME_CODE_TIERS,
                BdgContract.TiersEntry.COLUMN_NAME_NOM_TIERS
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = BdgContract.TiersEntry._ID + " = ?";
        String[] selectionArgs = { id };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                BdgContract.TiersEntry.COLUMN_NAME_CODE_TIERS + " DESC";

        Cursor c = db.query(
                BdgContract.TiersEntry.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        c.moveToFirst();
        long itemId = c.getLong(c.getColumnIndexOrThrow(BdgContract.TiersEntry._ID));

        TiersDTO r = new TiersDTO();
        r.setId(c.getInt(c.getColumnIndexOrThrow(BdgContract.TiersEntry._ID)));
        //r.setIdTiers(c.getString(c.getColumnIndexOrThrow(BdgContract.TiersEntry.COLUMN_NAME_ID_TIERS)));
        r.setCodeTiers(c.getString(c.getColumnIndexOrThrow(BdgContract.TiersEntry.COLUMN_NAME_CODE_TIERS)));
        r.setNomTiers(c.getString(c.getColumnIndexOrThrow(BdgContract.TiersEntry.COLUMN_NAME_NOM_TIERS)));

        return r;
    }

    public void removeTiers(String id) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define 'where' part of query.
        //String selection = BdgContract.TiersEntry.COLUMN_NAME_TITLE + " LIKE ?";
        String selection = BdgContract.TiersEntry._ID + " = ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { id };
        // Issue SQL statement.
        db.delete(BdgContract.TiersEntry.TABLE_NAME, selection, selectionArgs);

    }

    public void updateTiers(String id, String champ, String newValue) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(BdgContract.TiersEntry.COLUMN_NAME_NOM_TIERS, newValue);

        // Which row to update, based on the title
        String selection = BdgContract.TiersEntry._ID + " = ?";
        String[] selectionArgs = { id };

        int count = db.update(
                BdgContract.TiersEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }
    /******************************************************************************************************************/
    /******************************************************************************************************************/
    /******************************************************************************************************************/
    public void createArticle(ArticleDTO article) {

        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(BdgContract.ArticleEntry.COLUMN_NAME_ID_ARTICLE,article.getId());
        values.put(BdgContract.ArticleEntry.COLUMN_NAME_CODE_ARTICLE,article.getCodeArticle());
        values.put(BdgContract.ArticleEntry.COLUMN_NAME_LIBELLE_C_ARTICLE,article.getLibellecArticle());
        values.put(BdgContract.ArticleEntry.COLUMN_NAME_LIBELLE_L_ARTICLE,article.getLibellelArticle());
        values.put(BdgContract.ArticleEntry.COLUMN_NAME_CODE_BARRE_ARTICLE,article.getCodeBarre());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(BdgContract.ArticleEntry.TABLE_NAME, null, values);

        // Log.d("insertion OK","");
    }

    public ArticleDTO readArticle(String id) {

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BdgContract.ArticleEntry._ID,
                BdgContract.ArticleEntry.COLUMN_NAME_ID_ARTICLE,
                BdgContract.ArticleEntry.COLUMN_NAME_CODE_ARTICLE,
                BdgContract.ArticleEntry.COLUMN_NAME_LIBELLE_C_ARTICLE,
                BdgContract.ArticleEntry.COLUMN_NAME_LIBELLE_L_ARTICLE,
                BdgContract.ArticleEntry.COLUMN_NAME_CODE_BARRE_ARTICLE
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = BdgContract.ArticleEntry._ID + " = ?";
        String[] selectionArgs = { id };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                BdgContract.ArticleEntry.COLUMN_NAME_CODE_ARTICLE + " DESC";

        Cursor c = db.query(
                BdgContract.ArticleEntry.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        c.moveToFirst();
        long itemId = c.getLong(c.getColumnIndexOrThrow(BdgContract.ArticleEntry._ID));

        ArticleDTO r = new ArticleDTO();
        r.setId(c.getInt(c.getColumnIndexOrThrow(BdgContract.ArticleEntry._ID)));
        //r.setIdTiers(c.getString(c.getColumnIndexOrThrow(BdgContract.ArticleEntry.COLUMN_NAME_ID_ARTICLE)));
        r.setCodeArticle(c.getString(c.getColumnIndexOrThrow(BdgContract.ArticleEntry.COLUMN_NAME_CODE_ARTICLE)));
        r.setLibellecArticle(c.getString(c.getColumnIndexOrThrow(BdgContract.ArticleEntry.COLUMN_NAME_LIBELLE_C_ARTICLE)));
        r.setLibellelArticle(c.getString(c.getColumnIndexOrThrow(BdgContract.ArticleEntry.COLUMN_NAME_LIBELLE_L_ARTICLE)));
        r.setCodeArticle(c.getString(c.getColumnIndexOrThrow(BdgContract.ArticleEntry.COLUMN_NAME_CODE_BARRE_ARTICLE)));

        return r;
    }

    public void removeArticle(String id) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define 'where' part of query.
        //String selection = BdgContract.ArticleEntry.COLUMN_NAME_TITLE + " LIKE ?";
        String selection = BdgContract.ArticleEntry._ID + " = ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { id };
        // Issue SQL statement.
        db.delete(BdgContract.ArticleEntry.TABLE_NAME, selection, selectionArgs);

    }

    public void updateArticle(String id, String champ, String newValue) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(BdgContract.ArticleEntry.COLUMN_NAME_CODE_ARTICLE, newValue);

        // Which row to update, based on the title
        String selection = BdgContract.ArticleEntry._ID + " = ?";
        String[] selectionArgs = { id };

        int count = db.update(
                BdgContract.ArticleEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }
    /******************************************************************************************************************/
    /******************************************************************************************************************/
    /******************************************************************************************************************/


}
