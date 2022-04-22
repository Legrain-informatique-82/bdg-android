package fr.legrain.bdg;

import android.app.Application;
import android.content.Context;

import com.google.firebase.FirebaseApp;

public class BureauDeGestionApp extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
        BureauDeGestionApp.context = getApplicationContext();


    }

    public static Context getAppContext() {
        return BureauDeGestionApp.context;
    }

}