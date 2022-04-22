package fr.legrain.bdg.ui.bonliv;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


import fr.legrain.bdg.LgrFragment;
import fr.legrain.bdg.R;
import fr.legrain.bdg.api.client.dao.IBonlivBdgService;
import fr.legrain.bdg.api.client.dao.ITiersBdgService;
import fr.legrain.bdg.api.client.dao.rest.retrofit.BonlivBdgService;
import fr.legrain.bdg.api.client.dao.rest.retrofit.TiersBdgService;
import fr.legrain.bdg.api.client.dto.BonlivDTO;
import fr.legrain.bdg.api.client.dto.LigneBonlivDTO;
import fr.legrain.bdg.api.client.dto.TiersDTO;

/**
 * A placeholder fragment containing a simple view.
 */
public class ValidationBonLivFragment extends LgrFragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    public static final String ARG_SECTION_NUMBER = "section_number";
    public static final String ARG_SECTION_CONTENT = "section_content";

    private ITiersBdgService daoTiers = new TiersBdgService();
    private IBonlivBdgService dao = new BonlivBdgService();
    private TiersDTO tiersDTO = null;

    private IBonLivDataListener listener;

    private EditText etValideAdresse = null;
    private EditText etValideCodePostal = null;
    private EditText etValideVille = null;
    private EditText etValidePays = null;
    private EditText etDateBL = null;
    private TextView tvRemise = null;
    private TextView tvNbColis = null;
    private TextView tvTxCommision = null;
    private CheckBox cbTTC = null;

//    private Tarif selectedTarif = null;
//    private Personne selectedCommercial = null;

    private final Calendar myCalendar = Calendar.getInstance();

    public ValidationBonLivFragment() {
    }

    public void setOnSomeDataListener(IBonLivDataListener listener) {
        this.listener = listener;
    }
    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ValidationBonLivFragment newInstance(int sectionNumber, BonlivDTO p) {
        ValidationBonLivFragment fragment = new ValidationBonLivFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putSerializable(ARG_SECTION_CONTENT, p);
        fragment.setArguments(args);
        return fragment;
    }

    private BonlivDTO data = null;
    private View rootView = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_valide_bl, container, false);
        final View finalRootView = rootView;


        RelativeLayout lay = (RelativeLayout) rootView.findViewById(R.id.layout_valide_flash);

        this.rootView = rootView;

        //String data = getIntent().getStringExtra("parametre1");

        data = (BonlivDTO) getArguments().getSerializable(ARG_SECTION_CONTENT);

        if(data!=null) {

            data.regroupeLigne();

            final TiersDTO finalTiersDTO = daoTiers.findById(data.getIdTiers());


            LinearLayout.LayoutParams lpView = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            TextView tvValideNomTiers = (TextView) rootView.findViewById(R.id.tvValideNomTiers);

            etValideAdresse = (EditText) rootView.findViewById(R.id.etValideAdresse);
            etValideCodePostal = (EditText) rootView.findViewById(R.id.etValideCodePostal);
            etValideVille = (EditText) rootView.findViewById(R.id.etValideVille);
            etValidePays = (EditText) rootView.findViewById(R.id.etValidePays);
            etDateBL = (EditText) rootView.findViewById(R.id.etDateBL);
            tvRemise = (TextView) rootView.findViewById(R.id.tvRemise);
            tvNbColis = (TextView) rootView.findViewById(R.id.tvNbColis);
            tvTxCommision = (TextView) rootView.findViewById(R.id.tvTxCommision);
            cbTTC = (CheckBox) rootView.findViewById(R.id.cbTTC);
            Spinner spinnerTarif = (Spinner) finalRootView.findViewById(R.id.spinnerTarif);
            Spinner spinnerCommercial = (Spinner) finalRootView.findViewById(R.id.spinnerCommercial);


            List<View> lv = new ArrayList<>();
            lv.add(etValideAdresse);
            lv.add(etValideCodePostal);
            lv.add(etValideVille);
            lv.add(etValidePays);
            for (View v:lv) {
                v.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (!hasFocus) {
                            hideKeyboard(v);
                        }
                    }
                });
            }


            tvValideNomTiers.setText(data.getNomTiers());

//            if(finalTiersDTO.getAdresseLiv()!=null
//                    || data.getEntreprise().getCodePostalLiv()!=null
//                    || data.getEntreprise().getVilleLiv()!=null
//                    || data.getEntreprise().getPaysLiv()!=null) {
//                etValideAdresse.setText(data.getEntreprise().getAdresseLiv());
//                etValideCodePostal.setText(data.getEntreprise().getCodePostalLiv());
//                etValideVille.setText(data.getEntreprise().getVilleLiv());
//                etValidePays.setText(data.getEntreprise().getPaysLiv());
//            } else {
                etValideAdresse.setText(finalTiersDTO.getAdresse1Adresse());
                etValideCodePostal.setText(finalTiersDTO.getCodepostalAdresse());
                etValideVille.setText(finalTiersDTO.getVilleAdresse());
                etValidePays.setText(finalTiersDTO.getPaysAdresse());
//            }

//            if(data.getEntreprise().getRemise()!=null) {
//                tvRemise.setText(data.getEntreprise().getRemise().toString());
//            } else {
//                tvRemise.setText("0");
//            }
//            if(data.getEntreprise().getTxCommission()!=null) {
//                tvTxCommision.setText(data.getEntreprise().getTxCommission().toString());
//            } else {
//                tvTxCommision.setText("0");
//            }

            if(finalTiersDTO.getTtcTiers()!=null) {
                cbTTC.setChecked(finalTiersDTO.getTtcTiers());
            } else {
                cbTTC.setChecked(false);
            }


//            spinnerTarif.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                @Override
//                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                    selectedTarif =  (Tarif) parent.getItemAtPosition(position);
//                    if(selectedTarif!=null) {
//                        Toast.makeText(getContext(), "Selection du tarif " + selectedTarif.getnTarif(), Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> parent) {
//
//                }
//            });

//            spinnerCommercial.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                @Override
//                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                    selectedCommercial =  (Personne) parent.getItemAtPosition(position);
//                    if(selectedCommercial!=null) {
//                        Toast.makeText(getContext(), "Selection du commercial " + selectedCommercial.getnPersonne(), Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> parent) {
//
//                }
//            });

            updateLabel();

            final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, monthOfYear);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    updateLabel();
                }

            };
            etDateBL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new DatePickerDialog(getActivity(), date, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            });

//            getActivity().runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    List<Tarif> l = new ArrayList<Tarif>();
//                    l = findTarif();
//                    l.add(0, new Tarif(-1,"")); //ligne pour ne sélectionner aucun tarif
//                    ValidationBonLivFragment.TarifClientAdapter adapter = new ValidationBonLivFragment.TarifClientAdapter(getContext(), R.layout.ligne_tarif_spinner_item, R.id.textView20, l);
//
//                    Spinner spinnerTarif = (Spinner) finalRootView.findViewById(R.id.spinnerTarif);
//                    spinnerTarif.setAdapter(adapter);
//
//                    if(finalEntreprise.getnTarif()!=null) {
//                        int i = 0;
//                        int pos = 0;
//                        for (Tarif t : l) {
//                            if (t.getnTarif() == finalEntreprise.getnTarif().intValue()){
//                                pos = i;
//                            }
//                            i++;
//                        }
//                        spinnerTarif.setSelection(pos);
//                    }
//
//
//                }
//            });

//            getActivity().runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    List<Personne> l = new ArrayList<Personne>();
//                    l = findCommercial();
//                    l.add(0, new Personne("-1","","")); //ligne pour ne sélectionner aucun commercial
//                    ValidationBonLivFragment.CommercialAdapter adapter = new ValidationBonLivFragment.CommercialAdapter(getContext(), R.layout.ligne_tarif_spinner_item, R.id.textView20, l);
//
//                    Spinner spinnerCommercial = (Spinner) finalRootView.findViewById(R.id.spinnerCommercial);
//                    spinnerCommercial.setAdapter(adapter);
//
//                    if(finalEntreprise.getnTarif()!=null) {
//                        int i = 0;
//                        int pos = 0;
//                        for (Personne t : l) {
//                            if (Integer.parseInt(t.getnPersonne()) == finalEntreprise.getnCommercial().intValue()){
//                                pos = i;
//                            }
//                            i++;
//                        }
//                        spinnerCommercial.setSelection(pos);
//                    }
//
//
//                }
//            });

//            if(data.getLignesDTO()!=null) {
//                for (LigneBonlivDTO l : data.getLignesDTO()) {
//                    TextView t = new TextView(lay.getContext());
//                    t.setText(l.getCodeBarre());
//                    lay.addView(t, lpView);
//                }
//            }
        }
        //lay.invalidate();
        lay.postInvalidate();

        final Button btnEnvoyerBlVersAgrifact = (Button) rootView.findViewById(R.id.btnEnvoyerBLVersBdG);
        btnEnvoyerBlVersAgrifact.setEnabled(true);

//        VBonLivraison vBonLivraisonTemp = mapVBonLivraison(data, data.getLignes());
//        tvNbColis.setTypeface(null, Typeface.BOLD);
//        tvNbColis.setText(String.valueOf(vBonLivraisonTemp.getNbColis()));

        btnEnvoyerBlVersAgrifact.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                btnEnvoyerBlVersAgrifact.setEnabled(false);
                Toast.makeText(getContext(), "Enregistrer dans BdG", Toast.LENGTH_SHORT).show();

//                data.setAdresse(etValideAdresse.getText().toString());
//                data.setCodePostal(etValideCodePostal.getText().toString());
//                data.setVille(etValideVille.getText().toString());
//                data.setPays(etValidePays.getText().toString());
//                data.setDateBl(myCalendar.getTime());
//                data.setTtc(cbTTC.isChecked());
//                if(selectedCommercial.getnPersonne()!=null && !selectedCommercial.getnPersonne().equals("0") && !selectedCommercial.getnPersonne().equals("")) {
//                    data.setIdCommercialAgrifact(Integer.parseInt(selectedCommercial.getnPersonne()));
//                    data.setTauxCommission(data.getEntreprise().getTxCommission());
//                }
//                data.setIdTarifAgrifact(selectedTarif.getnTarif());
//                data.setRemise(data.getEntreprise().getRemise());
//
//                VBonLivraison vBonLivraison = null;
//                vBonLivraison = mapVBonLivraison(data, data.getLignes());
//
                //Log.d(vBonLivraison.toString(),"");
                //enregistreBLBdg(vBonLivraison);
                dao.persist(data);

                Intent returnIntent = new Intent();
                returnIntent.putExtra("result",data);
                returnIntent.putExtra("bool",true);
                getActivity().setResult(Activity.RESULT_OK,returnIntent);
                getActivity().finish();
            }
        });

        initData();


        return rootView;
    }

    //https://stackoverflow.com/questions/14933330/datepicker-how-to-popup-datepicker-when-click-on-edittext
    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.FRANCE);

        etDateBL.setText(sdf.format(myCalendar.getTime()));
    }

    public void initData(){

        try {
             ListView mListView = (ListView) rootView.findViewById(R.id.lvLignesBL);

            View header = getActivity().getLayoutInflater().inflate(R.layout.ligne_bl_liste_header, null);
            mListView.addHeaderView(header);
//            mListView.setAdapter(new android.support.v4.widget.SimpleCursorAdapter(this, R.layout.display, cursor, new String[]{
//                    "_id", "Name", "Address"
//            }, new int[]{R.id.id, R.id.name, R.id.add}, 0));

            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //Toast.makeText(MainActivity.this, reponse[position], Toast.LENGTH_SHORT).show();

//                    Intent intent = new Intent(getBaseContext() , VerbeCardActivity.class);
//                    intent.putExtra("parametre1",position);
//                    startActivity(intent);
                }
            });

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ArrayList<LigneBonlivDTO> l = new ArrayList<LigneBonlivDTO>();
                    l.addAll(data.getLignesDTO());
                    LigneBLAdapter adapter = new LigneBLAdapter(getContext(),l);

                    ListView mListView = (ListView)  rootView.findViewById(R.id.lvLignesBL);
                    mListView.setAdapter(adapter);

                    //rootView.findViewById(android.R.id.content).invalidate();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
//            Log.e("Erreur lors de l'envoi de la requête : ", e.getMessage());
        }
    }

//    public VBonLivraison enregistreBLAgrifact(VBonLivraison blAgrifact) {
//        EnregistreBLAgrifactTask t = new EnregistreBLAgrifactTask(blAgrifact/*,progressBar*/);
//        t.execute();
//
//        VBonLivraison l = null;
//        try {
//            l = t.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        return l;
//    }

//    public List<Tarif> findTarif() {
//        SelectTarifClientTask t = new SelectTarifClientTask(null);
//        t.execute();
//
//        List<Tarif> l = null;
//        try {
//            l = t.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        return l;
//    }

//    public List<Personne> findCommercial() {
//        SelectCommercialClientTask t = new SelectCommercialClientTask(null);
//        t.execute();
//
//        List<Personne> l = null;
//        try {
//            l = t.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        return l;
//    }

//    public VBonLivraison mapVBonLivraison(BonLivFlash bonLivFlash, List<LigneBLFlash> ligneBLFlash) {
//        MapVBonLivraisonTask t = new MapVBonLivraisonTask(bonLivFlash, ligneBLFlash, null);
//        t.execute();
//
//        VBonLivraison l = null;
//        try {
//            l = t.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        return l;
//    }

//    public class TarifClientAdapter extends ArrayAdapter<Tarif> {
//
//        private LayoutInflater flater;
//
//        //public TarifClientAdapter(Context context, ArrayList<Tarif> lignes) {
//        public TarifClientAdapter(Context context, int resouceId, int textviewId, List<Tarif> list){
//            super(context,resouceId,textviewId, list);
//            flater = LayoutInflater.from(getContext());
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//
//            // Get the data item for this position
//            Tarif lot = getItem(position);
//
//            // Check if an existing view is being reused, otherwise inflate the view
//            if (convertView == null) {
//                convertView = LayoutInflater.from(getContext()).inflate(R.layout.ligne_lot_spinner_item, parent, false);
//            }
//
//            // Lookup view for data population
//            TextView tvName = (TextView) convertView.findViewById(R.id.textView20);
//            TextView tvHome = (TextView) convertView.findViewById(R.id.textView21);
//            TextView tvHome5 = (TextView) convertView.findViewById(R.id.textView22);
//
//            // Populate the data into the template view using the data object
//            //tvName.setText(String.valueOf(lot.getnTarif()));
//            tvName.setText("");
//            tvHome.setText(lot.getLibelle());
//
//            // Return the completed view to render on screen
//            return convertView;
//        }
//
//        @Override
//        public View getDropDownView(int position, View convertView, ViewGroup parent) {
//            if(convertView == null){
//                convertView = flater.inflate(R.layout.ligne_lot_spinner_item,parent, false);
//            }
//            Tarif lot = getItem(position);
//            TextView tvName = (TextView) convertView.findViewById(R.id.textView20);
//            TextView tvHome = (TextView) convertView.findViewById(R.id.textView21);
//            TextView tvHome5 = (TextView) convertView.findViewById(R.id.textView22);
//
//            // Populate the data into the template view using the data object
//            //tvName.setText(String.valueOf(lot.getnTarif()));
//            tvName.setText("");
//            tvHome.setText(lot.getLibelle());
//
//            return convertView;
//        }
//    }

//    public class CommercialAdapter extends ArrayAdapter<Personne> {
//
//        private LayoutInflater flater;
//
//        //public CommercialAdapter(Context context, ArrayList<Personne> lignes) {
//        public CommercialAdapter(Context context, int resouceId, int textviewId, List<Personne> list){
//            super(context,resouceId,textviewId, list);
//            flater = LayoutInflater.from(getContext());
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//
//            // Get the data item for this position
//            Personne p = getItem(position);
//
//            // Check if an existing view is being reused, otherwise inflate the view
//            if (convertView == null) {
//                convertView = LayoutInflater.from(getContext()).inflate(R.layout.ligne_commercial_spinner_item, parent, false);
//            }
//
//            // Lookup view for data population
//            TextView tvName = (TextView) convertView.findViewById(R.id.textView20);
//            TextView tvHome = (TextView) convertView.findViewById(R.id.textView21);
//            TextView tvHome5 = (TextView) convertView.findViewById(R.id.textView22);
//
//            // Populate the data into the template view using the data object
//            tvName.setText(String.valueOf(p.getNom()));
//            tvHome.setText(p.getPrenom());
//
//            // Return the completed view to render on screen
//            return convertView;
//        }
//
//        @Override
//        public View getDropDownView(int position, View convertView, ViewGroup parent) {
//            if(convertView == null){
//                convertView = flater.inflate(R.layout.ligne_lot_spinner_item,parent, false);
//            }
//            Personne p = getItem(position);
//            TextView tvName = (TextView) convertView.findViewById(R.id.textView20);
//            TextView tvHome = (TextView) convertView.findViewById(R.id.textView21);
//            TextView tvHome5 = (TextView) convertView.findViewById(R.id.textView22);
//
//            // Populate the data into the template view using the data object
//            tvName.setText(String.valueOf(p.getNom()));
//            tvHome.setText(p.getPrenom());
//
//            return convertView;
//        }
//    }

}

//class EnregistreBLAgrifactTask extends AsyncTask<String, Void, VBonLivraison> {
//
//    private VBonLivraison blAgrifact = null;
//    private ProgressBar p = null;
//    private Exception exception;
//
//    public EnregistreBLAgrifactTask(VBonLivraison blAgrifact/*, ProgressBar p*/) {
//        this.blAgrifact = blAgrifact;
//        //this.p = p;
//    }
//
//    protected VBonLivraison doInBackground(String... urls) {
//        try {
//            VBonLivraisonDAO daoTiers = new VBonLivraisonDAO();
//            VBonLivraisonDetailDAO daoLignes = new VBonLivraisonDetailDAO();
//
//            VBonLivraison p = null;
//            if(Parametre.getInstance().getModeTest()) {
//               //
//            } else {
//
////                daoTiers.add(blAgrifact);
//                daoTiers.addComplet(blAgrifact);
//            }
//            return p;
//        } catch (Exception e) {
//            this.exception = e;
//
//            return null;
//        }
//    }
//
//    protected void onPostExecute(VBonLivraison feed) {
//        super.onPostExecute(feed);
////        p.setVisibility(View.INVISIBLE);
////        p.setIndeterminate(false);
//    }
//
//    @Override
//    protected void onPreExecute() {
//        super.onPreExecute();
////        p.setVisibility(View.VISIBLE);
////        p.setIndeterminate(true);
//    }
//
//    @Override
//    protected void onProgressUpdate(Void... values) {
//        super.onProgressUpdate(values);
//    }
//}

//class SelectTarifClientTask extends AsyncTask<String, Void, List<Tarif>> {
//
//    //private String codeProduit = null;
//    private ProgressBar p = null;
//    private Exception exception;
//
//    public SelectTarifClientTask(ProgressBar p) {
//        //this.codeProduit = codeProduit;
//        this.p = p;
//    }
//
//    protected List<Tarif> doInBackground(String... urls) {
//        try {
//            TarifDAO daoTiers = new TarifDAO();
//
//            List<Tarif> p = null;
//            if(Parametre.getInstance().getModeTest()) {
//                //p = daoTiers.selectAllLotProduitTest(codeProduit,false);
//            } else {
//                //p = daoTiers.selectAllLotProduitTest(codeProduit,false);
//                // p = daoTiers.selectAllLotProduit(codeProduit,false);
//                p = daoTiers.selectAll();
//            }
//            return p;
//        } catch (Exception e) {
//            this.exception = e;
//
//            return null;
//        }
//    }
//
//    protected void onPostExecute(List<Tarif> feed) {
//        super.onPostExecute(feed);
////        p.setVisibility(View.INVISIBLE);
////        p.setIndeterminate(false);
//    }
//
//    @Override
//    protected void onPreExecute() {
//        super.onPreExecute();
////        p.setVisibility(View.VISIBLE);
////        p.setIndeterminate(true);
//    }
//
//    @Override
//    protected void onProgressUpdate(Void... values) {
//        super.onProgressUpdate(values);
//    }
//}

//class MapVBonLivraisonTask extends AsyncTask<String, Void, VBonLivraison> {
//
//    //private String codeProduit = null;
//    private ProgressBar p = null;
//    private BonLivFlash bonLivFlash;
//    private List<LigneBLFlash> ligneBLFlash;
//    private Exception exception;
//
//    public MapVBonLivraisonTask(BonLivFlash bonLivFlash, List<LigneBLFlash> ligneBLFlash, ProgressBar p) {
//        this.bonLivFlash = bonLivFlash;
//        this.ligneBLFlash = ligneBLFlash;
//        this.p = p;
//    }
//
//    protected VBonLivraison doInBackground(String... urls) {
//        try {
//            UtilMapper utilMapper = new UtilMapper();
//            VBonLivraison vBonLivraison = utilMapper.creationBLagrifactComplet(bonLivFlash, ligneBLFlash);
//            return vBonLivraison;
//        } catch (Exception e) {
//            this.exception = e;
//
//            return null;
//        }
//    }
//
//    protected void onPostExecute(VBonLivraison feed) {
//        super.onPostExecute(feed);
////        p.setVisibility(View.INVISIBLE);
////        p.setIndeterminate(false);
//    }
//
//    @Override
//    protected void onPreExecute() {
//        super.onPreExecute();
////        p.setVisibility(View.VISIBLE);
////        p.setIndeterminate(true);
//    }
//
//    @Override
//    protected void onProgressUpdate(Void... values) {
//        super.onProgressUpdate(values);
//    }
//}

//class SelectCommercialClientTask extends AsyncTask<String, Void, List<Personne>> {
//
//    //private String codeProduit = null;
//    private ProgressBar p = null;
//    private Exception exception;
//
//    public SelectCommercialClientTask(ProgressBar p) {
//        //this.codeProduit = codeProduit;
//        this.p = p;
//    }
//
//    protected List<Personne> doInBackground(String... urls) {
//        try {
//            PersonneDAO daoTiers = new PersonneDAO();
//
//            List<Personne> p = null;
//            if(Parametre.getInstance().getModeTest()) {
//                //p = daoTiers.selectAllLotProduitTest(codeProduit,false);
//            } else {
//                //p = daoTiers.selectAllLotProduitTest(codeProduit,false);
//                // p = daoTiers.selectAllLotProduit(codeProduit,false);
//                p = daoTiers.selectAllCommercial();
//            }
//            return p;
//        } catch (Exception e) {
//            this.exception = e;
//
//            return null;
//        }
//    }
//
//    protected void onPostExecute(List<Personne> feed) {
//        super.onPostExecute(feed);
////        p.setVisibility(View.INVISIBLE);
////        p.setIndeterminate(false);
//    }
//
//    @Override
//    protected void onPreExecute() {
//        super.onPreExecute();
////        p.setVisibility(View.VISIBLE);
////        p.setIndeterminate(true);
//    }
//
//    @Override
//    protected void onProgressUpdate(Void... values) {
//        super.onProgressUpdate(values);
//    }
//}

