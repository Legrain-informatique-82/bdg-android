package fr.legrain.bdg.db.room;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

@Dao
//public interface FlashDao {
public abstract class FlashDao {
    @Query("SELECT * FROM flash")
    public abstract List<Flash> getAll();

    @Query("SELECT * FROM flash where codeTDoc = :codeTypeDocument")
    public abstract List<Flash> getAll(String codeTypeDocument);

    @Query("SELECT * FROM flash WHERE id IN (:flashIds)")
    public abstract List<Flash> loadAllByIds(int[] flashIds);

    @Query("SELECT * FROM flash WHERE codeTiers LIKE :first AND nomTiers LIKE :last LIMIT 1")
    public abstract Flash findByName(String first, String last);

    @Query("SELECT * FROM flash WHERE dateTransfert IS NULL")
    public abstract List<Flash> findByNonTransfere();


    @Query("SELECT * FROM flash WHERE codeTDoc = :codeTypeDocument and dateTransfert IS NULL")
    public abstract List<Flash> findByNonTransfere(String codeTypeDocument);
    @Update
    public abstract void updateFlash(Flash... flashs);


    @Insert
    public abstract void insertAll(Flash... flashs);

    // If the @Insert method receives only 1 parameter, it can return a long,
    // which is the new rowId for the inserted item.
    // https://developer.android.com/training/data-storage/room/accessing-data
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract long insert(Flash flash);

    @Insert
    public abstract void insertLignesFlash(List<LigneFlash> liste);

    @Delete
    public abstract void delete(Flash flash);

    @Transaction
    @Query("SELECT * FROM flash")
    public abstract List<FlashWithLignesFlash> getFlashWithLignesFlash();

    @Transaction
    public void insertFlashWithLignesFlash(Flash f) {
        final long flashIdLocal = insert(f);
        List<LigneFlash> lignes = f.lignes;
        for (int i = 0; i < lignes.size(); i++) {
            lignes.get(i).idFlash = new Long(flashIdLocal).intValue();
        }
        insertLignesFlash(lignes);

    }

//    @Transaction
//    public List<Flash> getListeFlashNonTransfere() {
//        List<Flash> l = findByNonTransfere();
//        List<LigneFlash> lignes = f.lignes;
//        for (int i = 0; i < lignes.size(); i++) {
//            lignes.get(i).idFlash = new Long(flashIdLocal).intValue();
//        }
//        insertLignesFlash(lignes);
//
//    }

}
