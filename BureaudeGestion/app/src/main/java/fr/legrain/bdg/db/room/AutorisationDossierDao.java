package fr.legrain.bdg.db.room;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface AutorisationDossierDao {
    @Query("SELECT * FROM autorisationDossier ORDER BY id")
    List<AutorisationDossier> getAll();

    @Insert
    void insertAll(AutorisationDossier... auths);

    @Delete
    void delete(AutorisationDossier auth);

    @Query("DELETE FROM autorisationDossier")
    void deleteTable();
}
