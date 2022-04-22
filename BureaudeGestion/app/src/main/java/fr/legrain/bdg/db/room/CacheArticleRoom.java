package fr.legrain.bdg.db.room;

import android.content.Context;
import android.widget.ProgressBar;

import java.util.List;

import androidx.room.Room;
import fr.legrain.bdg.api.client.dto.ArticleDTO;
import fr.legrain.bdg.db.sqlite.BdgCRUD;
import fr.legrain.bdg.data.model.Parametre;

public class CacheArticleRoom {

    private Context context;
    private ProgressBar progressBar;

    public CacheArticleRoom(Context context) {
        this.context = context;
        this.progressBar = null;
    }

    public CacheArticleRoom(Context context,ProgressBar progressBar) {
        this.context = context;
        this.progressBar = progressBar;
    }

    public void cacheBdd(List<ArticleDTO> liste) {
        BdgCRUD crud = new BdgCRUD(context/*getBaseContext()*/);
//        for (ArticleDTO t : liste) {
//            crud.createTiers(t);
//        }
//        context.deleteDatabase("bdg");
//        context.deleteDatabase("bdg.db");
        AppDatabase db = Room.databaseBuilder(context,
                AppDatabase.class, Parametre.CONST_DB_NAME)
                .allowMainThreadQueries()
                .build();

        db.articleDao().deleteTable();

        for (ArticleDTO t : liste) {
            Article tr = ArticleMapper.INSTANCE.articleDtoToArticle(t);
            db.articleDao().insertAll(tr);
        }

        db.close();
       // crud.exportDatabse("");
    }

}
