package fr.legrain.bdg.data;

import android.content.SharedPreferences;

import androidx.room.Room;
import fr.legrain.bdg.BureauDeGestionApp;
import fr.legrain.bdg.LgrActivity;
import fr.legrain.bdg.MainActivity;
import fr.legrain.bdg.api.client.dao.IUtilisateurBdgService;
import fr.legrain.bdg.api.client.dao.rest.retrofit.UtilisateurBdgService;
import fr.legrain.bdg.api.client.dto.UtilisateurDTO;
import fr.legrain.bdg.data.model.LoggedInUser;
import fr.legrain.bdg.db.room.AppDatabase;
import fr.legrain.bdg.db.room.Utilisateur;
import fr.legrain.bdg.db.room.UtilisateurMapper;
import fr.legrain.bdg.data.model.Parametre;

import java.io.IOException;
import java.util.List;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    private boolean utiliseCache = false;
    private IUtilisateurBdgService daoUtilisateur = new UtilisateurBdgService();

    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication

            List<Utilisateur> l = null;
            Utilisateur u = null;

            if(utiliseCache) {
                AppDatabase db = Room.databaseBuilder(MainActivity.getAppContext(),
                        AppDatabase.class, Parametre.CONST_DB_NAME)
                        .allowMainThreadQueries()
                        .build();
                l = db.utilisateurDao().getAll();
                u = db.utilisateurDao().findByEmail(username);

            } else {
                UtilisateurDTO dto = daoUtilisateur.authenticate(username, password);
                if(dto!=null) {
                    u = UtilisateurMapper.INSTANCE.utilisateurDtoToUtilisateur(dto);
                }
            }
            LoggedInUser fakeUser =
//                    new LoggedInUser(
//                            java.util.UUID.randomUUID().toString(),
//                            "Jane Doe");
                new LoggedInUser(
                    String.valueOf(u.id),
                    u.getUsername());
            return new Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication

        //SharedPreferences prefs = getSharedPreferences((LgrActivity.MY_PREFS_NAME, LgrActivity.MODE_PRIVATE);
        //String dernier = prefs.getString(LgrActivity.PARAM_KEY_LOGGED_IN_USER_NAME, null);

        SharedPreferences.Editor editor = BureauDeGestionApp.getAppContext().getSharedPreferences(LgrActivity.MY_PREFS_NAME, LgrActivity.MODE_PRIVATE).edit();
        editor.putString(LgrActivity.PARAM_KEY_LOGGED_IN_USER_NAME, null);
        editor.apply();

    }
}
