package fr.legrain.bdg.db.room;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface LotDao {
    @Query("SELECT * FROM lot ORDER BY dateLot")
    List<Lot> getAll();

    @Query("SELECT * FROM lot WHERE id IN (:userIds)")
    List<Lot> lot(int[] userIds);

    @Query("SELECT * FROM lot WHERE id = :id")
    Lot findById(int id);

    @Query("SELECT * FROM lot WHERE numLot = :numLot")
    Lot findByNumLot(String numLot);

    @Insert
    void insertAll(Lot... lots);

    @Delete
    void delete(Lot lot);
}
