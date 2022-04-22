package fr.legrain.bdg.db.room;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ArticleDao {
    @Query("SELECT * FROM article")
    List<Article> getAll();

    @Query("SELECT * FROM article WHERE id IN (:userIds)")
    List<Article> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM article WHERE codeArticle LIKE :first AND codeBarre LIKE :last LIMIT 1")
    Article findByName(String first, String last);

    @Query("SELECT * FROM article WHERE id = :id")
    Article findById(int id);

    @Query("SELECT * FROM article WHERE codeBarre = :codeBarre")
    Article findByCodeBarre(String codeBarre);

    @Insert
    void insertAll(Article... articles);

    @Delete
    void delete(Article articles);

    @Query("DELETE FROM article")
    void deleteTable();
}
