package fr.legrain.bdg.lib;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import fr.legrain.bdg.ParamActivity;

/*
https://stackoverflow.com/questions/1560788/how-to-check-internet-access-on-android-inetaddress-never-times-out
 */
public class UtilReseau {

    private Context context = null;
    boolean internetOK = false;
//    /*
//    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
//     */
//    public boolean isOnline() {
//        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo netInfo = cm.getActiveNetworkInfo();
//        return netInfo != null && netInfo.isConnectedOrConnecting();
//    }

    public UtilReseau(Context context) {
        this.context = context;
    }

    public boolean internetOK() {

//        return new InternetCheck( (Boolean internet) -> {
//            if(internet) {
//                Toast.makeText(context, "Internet OK", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(context, "Internet PAS OK", Toast.LENGTH_SHORT).show();
//            }
//        }).resultat;

        InternetCheck t = new InternetCheck();
        t.execute();

        Boolean s =  null;
        try {
            s = t.get();

            if(s!=null && s) {
                Toast.makeText(context, "Internet OK", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Internet PAS OK", Toast.LENGTH_SHORT).show();
            }
            return s;
        } catch (Exception e) {
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
        }
        return s;

    }

}

//
//  new InternetCheck(internet -> { /* do something with boolean response */ });
//
class InternetCheck extends AsyncTask<Void,Void,Boolean> {

    private Consumer mConsumer;
    public  interface Consumer { void accept(Boolean internet); }

    public  InternetCheck(/*Consumer consumer*/) { /*mConsumer = consumer; execute();*/ }

    @Override protected Boolean doInBackground(Void... voids) { try {
        Socket sock = new Socket();
        sock.connect(new InetSocketAddress("8.8.8.8", 53), 1500);
        sock.close();
        return true;
    } catch (IOException e) { return false; } }

    @Override protected void onPostExecute(Boolean internet) { /*mConsumer.accept(internet);*/ }
}
