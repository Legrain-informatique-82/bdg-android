package fr.legrain.bdg.db.sqlite;

import android.content.Context;

import java.util.List;

import fr.legrain.bdg.api.client.dto.TiersDTO;

public class CacheTiers {

    private Context context;

    public CacheTiers(Context context) {
        this.context = context;
    }

    public void cacheBdd(List<TiersDTO> liste) {
        BdgCRUD crud = new BdgCRUD(context/*getBaseContext()*/);
        for (TiersDTO t : liste) {
            crud.createTiers(t);
        }
        crud.exportDatabse("");
    }
}
