package fr.legrain.bdg.db.room;

import android.content.Context;
import android.widget.ProgressBar;

import java.util.List;

import androidx.room.Room;
import fr.legrain.bdg.api.client.dto.UtilisateurDTO;
import fr.legrain.bdg.db.sqlite.BdgCRUD;
import fr.legrain.bdg.data.model.Parametre;

public class CacheUtilisateurRoom {

    private Context context;
    private ProgressBar progressBar;

    public CacheUtilisateurRoom(Context context) {
        this.context = context;
        this.progressBar = null;
    }

    public CacheUtilisateurRoom(Context context, ProgressBar progressBar) {
        this.context = context;
        this.progressBar = progressBar;
    }

    public void cacheBdd(List<UtilisateurDTO> liste) {
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

        db.utilisateurDao().deleteTable();

        for (UtilisateurDTO t : liste) {
            Utilisateur tr = UtilisateurMapper.INSTANCE.utilisateurDtoToUtilisateur(t);
            db.utilisateurDao().insertAll(tr);
        }

        db.close();
       // crud.exportDatabse("");
    }
}
