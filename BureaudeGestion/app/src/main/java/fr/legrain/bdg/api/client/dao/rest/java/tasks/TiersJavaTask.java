package fr.legrain.bdg.api.client.dao.rest.java.tasks;

import android.content.SharedPreferences;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import fr.legrain.bdg.BureauDeGestionApp;
import fr.legrain.bdg.LgrActivity;
import fr.legrain.bdg.MainActivity;
import fr.legrain.bdg.api.client.dao.rest.IConstRest;
import fr.legrain.bdg.api.client.dto.TiersDTO;
import fr.legrain.bdg.data.model.Parametre;

/*
 <interfaces>
        <interface name="management">
            <inet-address value="${jboss.bind.address.management:127.0.0.1}"/>
        </interface>
        <interface name="public">
<!--             <inet-address value="${jboss.bind.address:0.0.0.0}"/> -->
			<any-address/>
        </interface>
    </interfaces>
 */
public class TiersJavaTask extends AsyncTask<String, Void, List<TiersDTO>>{

//    private String mRestUrl;
//    private RestTaskCallback mCallback;
    private Parametre param;

//    public GetTask(String restUrl, RestTaskCallback callback){
//        this.mRestUrl = restUrl;
//        this.mCallback = callback;
//    }
    public TiersJavaTask(Parametre param){
        this.param = param;
//        this.mRestUrl = restUrl;
//        this.mCallback = callback;
    }

    @Override
    protected List<TiersDTO> doInBackground(String... params) {
        List<TiersDTO> r = null;
        //Use HTTP Client APIs to make the call.
        //Return the HTTP Response body here.

        try {
            System.out.println("tt");

            aa(0);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return r;
    }

    @Override
    protected void onPostExecute(List<TiersDTO> r) {
//        mCallback.onTaskComplete(result);
        super.onPostExecute(r);
    }

    public List<TiersDTO> aa(int id) {

        List<TiersDTO> r = null;
        String METHOD = "GET";

        try {

            HttpsTrustManager.allowAllSSL();

            URL QUERY  = new URL("https://dev.demo.promethee.biz:8443/rest/tiers/12");

           // System.out.println("LgrMailjet.sendSMS() To :"+to[0]+" To E164 : "+numberStr);

//            JSONObject b = new JSONObject();
//            b.put("charset", "UTF-8");
//            b.put("coding", "7bit");
//            b.put("receivers", to);
//            b.put("message", new String(text.getBytes("UTF-8"), "ISO-8859-1"));
//            //b.put("message",text);
//            b.put("priority", "high");
//            b.put("senderForResponse", true);
//            String BODY = b.toString();
            String BODY = "";

            HttpURLConnection req = (HttpURLConnection)QUERY.openConnection();
            req.setRequestMethod(METHOD);

            SharedPreferences prefs =  BureauDeGestionApp.getAppContext().getSharedPreferences(LgrActivity.MY_PREFS_NAME, LgrActivity.MODE_PRIVATE);
            String dossier = prefs.getString(LgrActivity.PARAM_KEY_DOSSIER, null);
            String login = prefs.getString(LgrActivity.PARAM_KEY_API_LOGIN, null);
            String password =  prefs.getString(LgrActivity.PARAM_KEY_API_PASSWORD, null);

            //pas d'interceptor avec HttpURLConnection
            req.setRequestProperty(IConstRest.HEADER_ACCEPT,  IConstRest.HEADER_APPLICATION_JSON);
            req.setRequestProperty(IConstRest.HEADER_DOSSIER, dossier);
            req.setRequestProperty(IConstRest.HEADER_LGR, Parametre.CONST_VALEUR_ACCES_API_LGR);
            req.setRequestProperty(IConstRest.HEADER_LOGIN, login);
            req.setRequestProperty(IConstRest.HEADER_PASSWORD, password);
//            req.setRequestProperty("X-Ovh-Consumer",    CK);
//            req.setRequestProperty("X-Ovh-Signature",   signature);
//            req.setRequestProperty("X-Ovh-Timestamp",   "" + TSTAMP);

            if(!BODY.isEmpty()) {
                req.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(req.getOutputStream());
                wr.writeBytes(BODY);
                wr.flush();
                wr.close();
            }

            String inputLine;
            BufferedReader in;
            int responseCode = req.getResponseCode();
            if ( responseCode == 200 ) {
                //Récupération du résultat de l'appel
                in = new BufferedReader(new InputStreamReader(req.getInputStream()));
            } else {
                in = new BufferedReader(new InputStreamReader(req.getErrorStream()));
            }
            StringBuffer response   = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

//            r = new ReponseSmsSendService();
////			if(response.getStatus() == 200) {
////				JSONObject jb = response.getData().getJSONObject(0).getJSONArray("To").getJSONObject(0);
////				r.setMessageID(String.valueOf(jb.getLong("MessageID")));
////				r.setErreur(false);
////			} else {
////				r.setErreur(true);
////			}
//            r.setReponseTxt(response.toString());
//            r.setData(response.toString());
//            r.initWithOvhJson(response.toString());
            //r.setStatus(LibConversion.integerToString(response.getStatus()));

            System.out.println(response.toString());

            return r;

        } catch (Exception e) {
            final String errmsg = "Exception: " + e;
            e.printStackTrace();
        }
        return null;
    }

}