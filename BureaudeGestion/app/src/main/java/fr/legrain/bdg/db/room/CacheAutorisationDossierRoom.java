package fr.legrain.bdg.db.room;

import android.content.Context;

import java.util.List;

import androidx.room.Room;
import fr.legrain.bdg.api.client.dto.AutorisationDossierDTO;
import fr.legrain.bdg.db.sqlite.BdgCRUD;
import fr.legrain.bdg.data.model.Parametre;

public class CacheAutorisationDossierRoom {

    private Context context;

    public CacheAutorisationDossierRoom(Context context) {
        this.context = context;
    }

    public void cacheBdd(List<AutorisationDossierDTO> liste) {
        BdgCRUD crud = new BdgCRUD(context/*getBaseContext()*/);
//        for (TiersDTO t : liste) {
//            crud.createTiers(t);
//        }
//        context.deleteDatabase("bdg");
//        context.deleteDatabase("bdg.db");
        AppDatabase db = Room.databaseBuilder(context,
                AppDatabase.class, Parametre.CONST_DB_NAME)
                .allowMainThreadQueries()
                .build();

        db.autorisationDossierDao().deleteTable();

        for (AutorisationDossierDTO t : liste) {
            AutorisationDossier tr = AutorisationDossierMapper.INSTANCE.autorisationDossierDtoToAutorisationDossier(t);
            db.autorisationDossierDao().insertAll(tr);
        }

        db.close();
       // crud.exportDatabse("");
    }
}
