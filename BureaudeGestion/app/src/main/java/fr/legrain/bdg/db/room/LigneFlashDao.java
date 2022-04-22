package fr.legrain.bdg.db.room;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface LigneFlashDao {
    @Query("SELECT * FROM ligneFlash")
    List<LigneFlash> getAll();

    @Query("SELECT * FROM ligneFlash WHERE idFlash = :idFlash")
    List<LigneFlash> lignesSessionFlash(int idFlash);

    @Query("SELECT * FROM ligneFlash WHERE id IN (:userIds)")
    List<LigneFlash> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM ligneFlash WHERE codeArticle LIKE :first AND codeBarre LIKE :last LIMIT 1")
    LigneFlash findByName(String first, String last);

    @Insert
    void insertAll(LigneFlash... articles);

    @Delete
    void delete(LigneFlash articles);
}
