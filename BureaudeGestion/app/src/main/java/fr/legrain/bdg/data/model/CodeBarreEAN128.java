package fr.legrain.bdg.data.model;

import android.content.Context;
import android.widget.Toast;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CodeBarreEAN128 {
    /*
        Pour le scanner USB Zebra DS22008, pour du code128/EAN128 => USB Keystroke Delay : 20ms, sinon trop rapide (Galaxy Tab / Android 8); ds2208-prg-en.pdf ** page 97
     */
        /*
        Exemple de lecture : 011234567890123117200213310300100510AA/123/89/456
        (01)12345678901231(17)200213(3103)001005(10)AA/123/89/456
        Interprété comme ceci :
        01 (Code EAN 14 de l'article) : 12345678901231
        17 (DLC en Année Mois Jour) :   200213
        3103 (Poids en Kg) :            001005
        10 (N° de lot) :                AA/123/89/456
         */
        public static final int TYPE_LTG = 1;

        public String ean128;
        public String ean13;
        public String ean14;
        public String dlcString;
        public Date dlc;
        public String poidsKiloString;
        public BigDecimal poidsKilo;
        public String numLot;
        public String qteString;
        public BigDecimal qte;

        public int type;
        public Context context;

        public CodeBarreEAN128(int type, String ean128, Context context) {
            this.ean128 = ean128;
            this.type = type;
            this.context = context;
           // Toast.makeText(context, "**** ---- "+ean128+" "+this.ean128.length()+" ==== "+"011234567890123117200213310300100510".length(), Toast.LENGTH_LONG).show();
            if(this.ean128!=null && (this.type == TYPE_LTG) && this.ean128.length()>= "011234567890123117200213310300100510".length()) {
                analyseEAN128();
            }
        }

        private void analyseEAN128() {
            int position = 0;
            int nbDecimale = 0;
            if(type == TYPE_LTG) {
                Toast.makeText(context, "**** ---- "+ean128, Toast.LENGTH_SHORT).show();
                position = position+"01".length();
                ean14 = ean128.substring(position,position+14);
                position = position+14;
                position = position+"17".length();
                dlcString = ean128.substring(position,position+6);
                position = position+6;

                //position = position+"310x".length();
                position = position+"310".length();
                nbDecimale = Integer.valueOf(ean128.substring(position,position+1));
                position = position+1;

                poidsKiloString = ean128.substring(position,position+6);
                position = position+6;
                position = position+"10".length();
                numLot = ean128.substring(position);

                ean13 = ean14.substring(0,ean14.length()-1);

                String myFormat = "yyMMdd";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.FRANCE);
                try {
                    dlc = sdf.parse(dlcString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                poidsKiloString = poidsKiloString.replaceFirst("^0+(?!$)", ""); //supprime les 0 en début de chaine
                //if(poidsKiloString.startsWith(".")) {
                if(poidsKiloString.length()<=nbDecimale) {
                    do {
                        poidsKiloString = "0" + poidsKiloString;
                    } while (poidsKiloString.length()<=nbDecimale);
                }
                //nbDecimale = 3;
                String partieEntiere = poidsKiloString.substring(0,poidsKiloString.length()-nbDecimale);
                poidsKiloString = partieEntiere+"."+poidsKiloString.substring(partieEntiere.length());
//                DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
//                decimalFormat.setParseBigDecimal(true);
//                BigDecimal bigDecimal = (BigDecimal) decimalFormat.parse("1,234.56");
                poidsKilo = new BigDecimal(poidsKiloString);

            }
        }
    }