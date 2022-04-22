package fr.legrain.bdg.db.sqlite;

import android.content.Context;

import java.util.List;

import fr.legrain.bdg.api.client.dto.ArticleDTO;

public class CacheArticle {

    private Context context;

    public CacheArticle(Context context) {
        this.context = context;
    }

    public void cacheBdd(List<ArticleDTO> liste) {
        BdgCRUD crud = new BdgCRUD(context/*getBaseContext()*/);
        for (ArticleDTO t : liste) {
            crud.createArticle(t);
        }
        crud.exportDatabse("");
    }
}
