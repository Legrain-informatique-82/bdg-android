package fr.legrain.bdg;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import androidx.annotation.NonNull;
import androidx.room.Room;
import fr.legrain.bdg.api.client.dao.IUtilisateurBdgService;
import fr.legrain.bdg.api.client.dao.rest.retrofit.UtilisateurBdgService;
import fr.legrain.bdg.api.client.dto.UtilisateurDTO;
import fr.legrain.bdg.db.room.AppDatabase;
import fr.legrain.bdg.db.room.Utilisateur;
import fr.legrain.bdg.db.room.UtilisateurMapper;
import fr.legrain.bdg.data.model.Parametre;
import fr.legrain.bdg.data.model.TDoc;
import fr.legrain.bdg.ui.article.ArticleActivity;
import fr.legrain.bdg.ui.tiers.TiersActivity;


public class MainActivity extends LgrActivity {

    private static Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivity.context = getApplicationContext();

        this.configureToolBar();

        this.configureDrawerLayout();

        this.configureNavigationView();

        SharedPreferences prefs = getSharedPreferences(LgrActivity.MY_PREFS_NAME, LgrActivity.MODE_PRIVATE);
        String loggedInUser = prefs.getString(LgrActivity.PARAM_KEY_LOGGED_IN_USER_NAME, null);

        if(loggedInUser==null) {
            startActivityLogin();
        }

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        loadParam();
//
////        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
////        fab.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
////
////                Intent intentTest = new Intent(getBaseContext() , DBTestActivity.class);
////                //intent.putExtra("parametre1",verbes[position]);
////                startActivity(intentTest);
////
////            }
////        });

//        Button btnNouveauBL = (Button) findViewById(R.id.btnNouveauBL);
//        btnNouveauBL.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v){
//                startActivityNouveauBL();
//            }
//        });
//
//        Button btnNouveauFlash = (Button) findViewById(R.id.btnNouveauFlash);
//        btnNouveauFlash.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v){
//                startActivityNouveauFlash();
//            }
//        });

        Button btnNouveauPreparation = (Button) findViewById(R.id.btnNouveauPreparation);
        btnNouveauPreparation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                //throw new RuntimeException("Test Crash"); // Force a crash
                startActivityNouveauFlash(TDoc.TYPE_PREPARATION);
            }
        });

        Button btnNouveauInventaire = (Button) findViewById(R.id.btnNouveauInventaire);
        btnNouveauInventaire.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                startActivityNouveauFlash(TDoc.TYPE_INVENTAIRE);
            }
        });

        Button btnNouveauFabrication = (Button) findViewById(R.id.btnNouveauFabrication);
        btnNouveauFabrication.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                startActivityNouveauFlash(TDoc.TYPE_FABRICATION);
            }
        });

        Button btnTiers = (Button) findViewById(R.id.btnTiers);
        btnTiers.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                Intent intentTiers = new Intent(getBaseContext(), TiersActivity.class);
                startActivity(intentTiers);
            }
        });

        Button btnArticle = (Button) findViewById(R.id.btnArticle);
        btnArticle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                Intent intentArticle = new Intent(getBaseContext(), ArticleActivity.class);
                startActivity(intentArticle);
            }
        });


//        final TextView tvLibelleBtnNouveauBL = (TextView) findViewById(R.id.tvLibelleBtnNouveauBL);
//        if(Parametre.getInstance().getModeTest()) {
//            tvLibelleBtnNouveauBL.setText("MODE TEST - NOUVEAU BL");
//        } else {
//            tvLibelleBtnNouveauBL.setText("Nouveau BL");
//        }
//        tvLibelleBtnNouveauBL.invalidate();
////        tvLibelleBtnNouveauBL.postInvalidate();

        /*
         Verification de la disponibilite de Google Play Service sur le terminal, notament pour accéder aux fonctionnalité de Firebase comme
         Firebase Cloud Messenging (FCM) pour les notification push.
         Si on souhaite utilisé uniquement le systeme de message de Firebase d'autre librairie peuvent le remplacer ou peut être directement des websocket ouvert depuis le serveur.
         */
        GoogleApiAvailability.getInstance().makeGooglePlayServicesAvailable(this);

        createNotificationChannel();
        findFireBaseCloudMessagingToken();
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*
         Verification de la disponibilite de Google Play Service sur le terminal, notament pour accéder aux fonctionnalité de Firebase comme
         Firebase Cloud Messenging (FCM) pour les notification push.
         */
        GoogleApiAvailability.getInstance().makeGooglePlayServicesAvailable(this);
    }

    public static Context getAppContext() {
        return MainActivity.context;
    }

    public String findFireBaseCloudMessagingToken() {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("Cle FCM", "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();


                        SharedPreferences prefs = MainActivity.this.getSharedPreferences(((LgrActivity)MainActivity.this).MY_PREFS_NAME, (MainActivity.this).MODE_PRIVATE);
                        String loggedInUser = prefs.getString(LgrActivity.PARAM_KEY_LOGGED_IN_USER_NAME, null);

                        if(loggedInUser!=null) {
                            AppDatabase db = Room.databaseBuilder(MainActivity.this,
                                    AppDatabase.class, Parametre.CONST_DB_NAME)
                                    .allowMainThreadQueries()
                                    .build();
                            Utilisateur u = db.utilisateurDao().findByEmail(loggedInUser);
                            if(u!=null) {
                                u.setAndroidRegistrationToken(token);
                                db.utilisateurDao().updateUtilisateur(u);
                                db.close();


                                UtilisateurDTO dto = UtilisateurMapper.INSTANCE.utilisateurToUtilisateurDto(u);
                                if(dto!=null) {
                                    IUtilisateurBdgService dao = new UtilisateurBdgService();
                                    dao.merge(dto);
                                } else {
                                    //TODO problème de connexion au serveur ou de "session"
                                    //Essayer de donner un message précis
                                    startActivityLogin();
                                }
                            } else {
                                //TODO problème de connexion au serveur ou de "session"
                                //Essayer de donner un message précis
                                startActivityLogin();
                            }
                        }




                        // Log and toast
//                        String msg = getString(R.string.msg_token_fmt, token);
//                        Log.d("", msg);
//                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                        Log.d("Cle FCM", token);
                        //Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();
                    }
                });
        return null;
    }

    public static final int CHANNEL_ID = 1;
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            CharSequence name = getString(R.string.channel_name);
//            String description = getString(R.string.channel_description);
            CharSequence name = "Nom chaine";
            String description = "Description chaine";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(getString(R.string.default_notification_channel_id), name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }



}
