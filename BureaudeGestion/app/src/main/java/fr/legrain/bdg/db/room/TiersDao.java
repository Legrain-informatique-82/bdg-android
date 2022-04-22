package fr.legrain.bdg.db.room;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface TiersDao {
    @Query("SELECT * FROM tiers ORDER BY codeTiers")
    List<Tiers> getAll();

    @Query("SELECT * FROM tiers WHERE id IN (:userIds)")
    List<Tiers> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM tiers WHERE codeTiers LIKE :first AND nomTiers LIKE :last LIMIT 1")
    Tiers findByName(String first, String last);

    @Query("SELECT * FROM tiers WHERE id = :id")
    Tiers findById(int id);

    @Insert
    void insertAll(Tiers... users);

    @Delete
    void delete(Tiers user);

    @Query("DELETE FROM tiers")
    void deleteTable();

    //@Query("UPDATE SQLITE_SEQUENCE SET SEQ=0 WHERE NAME='tiers'")
    //Integer resetTable();

}
