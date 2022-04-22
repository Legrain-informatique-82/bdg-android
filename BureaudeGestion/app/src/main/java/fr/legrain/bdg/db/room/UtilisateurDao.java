package fr.legrain.bdg.db.room;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UtilisateurDao {
    @Query("SELECT * FROM utilisateur ORDER BY username")
    List<Utilisateur> getAll();

    @Query("SELECT * FROM utilisateur WHERE id IN (:userIds)")
    List<Utilisateur> utilisateur(int[] userIds);

    @Query("SELECT * FROM utilisateur WHERE username LIKE :first AND email LIKE :last LIMIT 1")
    Utilisateur findByName(String first, String last);

    @Query("SELECT * FROM utilisateur WHERE id = :id")
    Utilisateur findById(int id);

    @Query("SELECT * FROM utilisateur WHERE email = :email")
    Utilisateur findByEmail(String email);

    @Insert
    void insertAll(Utilisateur... users);

    @Delete
    void delete(Utilisateur user);

    @Update
    public void updateUtilisateur(Utilisateur... utilisateurs);

    @Query("DELETE FROM utilisateur")
    void deleteTable();
}
