//package fr.legrain.bdg.exemple.soap;
//
//import android.os.AsyncTask;
//
//import org.ksoap2.serialization.SoapObject;
//
//import java.util.Vector;
//import java.util.concurrent.ExecutionException;
//
///**
// * Created by nicolas on 08/10/16.
// */
//
//public abstract class AbstractWsSOAP {
//
//    protected Vector<SoapObject> reponse = null;
//
//    protected int timeout = 60000;
//
//    protected String URL = null;
//    protected String NAMESPACE = Const.NAMESPACE;
//    protected String METHODNAME = Const.METHODNAME_SELECT_ALL;
//    protected String[] PARAMETRES = {"a", "b", "c"};
//
//    public void initCache(boolean updateCahe) {
//        //http://stackoverflow.com/questions/14827532/waiting-till-the-async-task-finish-its-work
//        //http://stackoverflow.com/questions/12575068/how-to-get-the-result-of-onpostexecute-to-main-activity-because-asynctask-is-a
//        if (updateCahe) {
//            if (reponse == null) { //pas encore initialisé, donc on execute l'appel soap
//                AbstractWsSOAPTask t = new AbstractWsSOAPTask();
//                t.execute();
//                try {
//                    t.get();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    abstract protected float executionRequeteSOAP(String url, String namespace, String methodName, String[] parametres);
//
//
//    private class AbstractWsSOAPTask extends AsyncTask<String, String, Float> {
//
//
//        protected float executionRequete(String url, String namespace, String methodName, String[] parametres) {
//            return executionRequeteSOAP(url, namespace, methodName, parametres);
//        }
//
//        @Override
//        protected void onPreExecute() {
//            // TODO Auto-generated method stub
////            progression = new ProgressDialog(MainActivity.this);
////            progression.setTitle("Appel au service web");
////            progression.show();
//
//        }
//
//        @Override
//        protected void onProgressUpdate(String... values) {
//            // TODO Auto-generated method stub
////            progression.setMessage(values[0]);
//        }
//
//        @Override
//        protected Float doInBackground(String... params) {
//
//            //  publishProgress("Calcul en cours...");
//            float r = executionRequete(URL, NAMESPACE, METHODNAME, PARAMETRES);
//            return r;
//        }
//
//        @Override
//        protected void onPostExecute(Float result) {
//// Affichage du résultat dans le textView et disparition de la //progressDialog
////            lbl_resultat.setText("La moyenne donne : " + result);
////            progression.dismiss();
//        }
//
//
//    }
//
//}