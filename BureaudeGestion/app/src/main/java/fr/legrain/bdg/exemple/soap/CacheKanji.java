//package fr.legrain.bdg.exemple.soap;
//
//import android.util.Log;
//
//import org.ksoap2.SoapEnvelope;
//import org.ksoap2.serialization.SoapObject;
//import org.ksoap2.serialization.SoapPrimitive;
//import org.ksoap2.serialization.SoapSerializationEnvelope;
//import org.ksoap2.transport.HttpTransportSE;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Vector;
//
//import ovh.sencha.jap.model.TaKanji;
//
///**
// * Created by nicolasmura on 08/10/2016.
// */
//
//public class CacheKanji extends AbstractWsSOAP {
//
//    private TaKanji[] kanjis = null;
//    private String[] kanjisTxt = null;
//    private List<TaKanji> listeKanji = null;
//    private String txt;
//
//    private static final CacheKanji INSTANCE = new CacheKanji();
//
//    public static CacheKanji getInstance() {
//        return INSTANCE;
//    }
//
//    private CacheKanji() {
//        this.URL = Const.URL_KANJI;
//    }
//
//    public float executionRequeteSOAP(String url, String namespace, String methodName, String[] parametres){
//
//        float resultat_float = 0f;
//
//        //Préparation de la requête
//        SoapObject requete = new SoapObject(namespace, methodName);
//
//
////Renseignement des valeurs des paramètres
//        try{
////            requete.addProperty(parametres[0], Integer.parseInt(txt_nombre1.getText().toString()));
////            requete.addProperty(parametres[1], Integer.parseInt(txt_nombre2.getText().toString()));
////            requete.addProperty(parametres[2], Integer.parseInt(txt_nombre3.getText().toString())) ;
//        }catch(Exception e){
//            Log.e("MainActivity", e.getMessage());
//        }
//
//        //Création de l'enveloppe
//        SoapSerializationEnvelope enveloppe = new SoapSerializationEnvelope(SoapEnvelope.VER11);
//
//        //Ajout de la requête dans l'enveloppe
//        enveloppe.setOutputSoapObject(requete);
//
//        //Envoi de la requête et traitement du résultat
//        HttpTransportSE http_transport = new HttpTransportSE(url,timeout);
//        try {
//            //http_transport.call(namespace + methodName, enveloppe);
//            http_transport.call("", enveloppe);
//            //SoapObject reponse = (SoapObject)enveloppe.getResponse();
//            reponse = (Vector<SoapObject>)enveloppe.getResponse();
//            kanjis = new TaKanji[reponse.size()];
//            kanjisTxt = new String[reponse.size()];
//            listeKanji = new ArrayList<TaKanji>();
//            int i = 0;
//            TaKanji v = null;
//            for (SoapObject o :reponse) {
//                //v = new TaKanji();
//                v = mapping(o);
//                txt = v.getJapKanji();
//                /*
//                txt = o.getPrimitivePropertySafelyAsString("japKanji");
//
//                v.setId(
//                        Integer.parseInt(
//                                (String)((SoapPrimitive)o.getPrimitiveProperty("id")).getValue()
//                        )
//                );
//                v.setJapKanji(o.getPrimitivePropertySafelyAsString("japKanji"));
//                v.setFrancais(o.getPrimitivePropertySafelyAsString("francais"));
//                v.setKunyomi(o.getPrimitivePropertySafelyAsString("kunyomi"));
//                */
//
//                kanjisTxt[i] = txt;
//                kanjis[i] = v;
//                i++;
//            }
//            listeKanji.addAll(Arrays.asList(kanjis));
//
//        } catch (Exception e) {
//            e.printStackTrace();
////            Log.e("Erreur lors de l'envoi de la requête : ", e.getMessage());
//        }
//        //Retourner le résultat du calcul
//        return resultat_float;
//    }
//
//    public TaKanji mapping(SoapObject o) {
//        TaKanji v = null;
//        v = new TaKanji();
//        v.setId(
//                Integer.parseInt(
//                        (String)((SoapPrimitive)o.getPrimitiveProperty("id")).getValue()
//                )
//        );
//        v.setKanaKunyomi(o.getPrimitivePropertySafelyAsString("kanaKunyomi"));
//        v.setKanaOnyomi(o.getPrimitivePropertySafelyAsString("kanaOnyomi"));
//        v.setKunyomi(o.getPrimitivePropertySafelyAsString("kunyomi"));
//        v.setOnyomi(o.getPrimitivePropertySafelyAsString("onyomi"));
//        //v.setNbTrait(o.getPrimitivePropertySafelyAsString("nbTrait"));
//        v.setNbTrait(0);
//
//        v.setFrancais(o.getPrimitivePropertySafelyAsString("francais"));
//        v.setJapKanji(o.getPrimitivePropertySafelyAsString("japKanji"));
//        return v;
//    }
//
//    public Vector<SoapObject> getReponse() {
//        return reponse;
//    }
//
//    public void setReponse(Vector<SoapObject> reponse) {
//        this.reponse = reponse;
//    }
//
//    public TaKanji[] getKanjis() {
//        return kanjis;
//    }
//
//    public void setKanjis(TaKanji[] kanjis) {
//        this.kanjis = kanjis;
//    }
//
//    public String[] getKanjisTxt() {
//        return kanjisTxt;
//    }
//
//    public void setKanjisTxt(String[] kanjisTxt) {
//        this.kanjisTxt = kanjisTxt;
//    }
//
//    public List<TaKanji> getListeKanji() {
//        return listeKanji;
//    }
//
//    public void setListeKanji(List<TaKanji> listeKanji) {
//        this.listeKanji = listeKanji;
//    }
//}
