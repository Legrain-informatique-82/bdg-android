package fr.legrain.bdg.api.client.dao.rest.retrofit.tasks;

import android.content.SharedPreferences;

import java.io.IOException;

import fr.legrain.bdg.BureauDeGestionApp;
import fr.legrain.bdg.LgrActivity;
import fr.legrain.bdg.MainActivity;
import fr.legrain.bdg.api.client.dao.rest.IConstRest;
import fr.legrain.bdg.data.model.Parametre;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class LgrInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();

        SharedPreferences prefs =  BureauDeGestionApp.getAppContext().getSharedPreferences(LgrActivity.MY_PREFS_NAME, LgrActivity.MODE_PRIVATE);
        String dossier = prefs.getString(LgrActivity.PARAM_KEY_DOSSIER, null);
        String login = prefs.getString(LgrActivity.PARAM_KEY_API_LOGIN, null);
        String password =  prefs.getString(LgrActivity.PARAM_KEY_API_PASSWORD, null);


        Request.Builder builder = originalRequest.newBuilder()
//                .header("Authorization", Credentials.basic("aUsername", "aPassword"))
                .header(IConstRest.HEADER_DOSSIER,dossier)
                .header(IConstRest.HEADER_LGR,Parametre.CONST_VALEUR_ACCES_API_LGR)
                .header(IConstRest.HEADER_LOGIN,login)
                .header(IConstRest.HEADER_PASSWORD,password);

        Request newRequest = builder.build();
        return chain.proceed(newRequest);
    }
}
