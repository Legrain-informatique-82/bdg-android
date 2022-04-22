package fr.legrain.bdg.db.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {
            Tiers.class,
            Article.class,
            Flash.class,
            LigneFlash.class,
            Utilisateur.class,
            Lot.class,
            AutorisationDossier.class
        },
        version = 1,
        exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract TiersDao tiersDao();
    public abstract ArticleDao articleDao();
    public abstract FlashDao flashDao();
    public abstract LigneFlashDao ligneFlashDao();
    public abstract UtilisateurDao utilisateurDao();
    public abstract LotDao lotDao();
    public abstract AutorisationDossierDao autorisationDossierDao();
}
