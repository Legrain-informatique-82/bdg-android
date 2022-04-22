//package fr.legrain.bdg.exemple;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.os.Handler;
//import android.text.Editable;
//import android.text.InputType;
//import android.text.TextWatcher;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ListView;
//import android.widget.ProgressBar;
//import android.widget.Spinner;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.zxing.integration.android.IntentIntegrator;
//import com.google.zxing.integration.android.IntentResult;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.ExecutionException;
//
//import fr.legrain.agrifact.local.model.BonLivFlash;
//import fr.legrain.agrifact.local.model.LigneBLFlash;
//import fr.legrain.agrifact.local.model.Parametre;
//import fr.legrain.agrifact.sqlserver.dao.EntrepriseDAO;
//import fr.legrain.agrifact.sqlserver.dao.LotDAO;
//import fr.legrain.agrifact.sqlserver.dao.ProduitDAO;
//import fr.legrain.agrifact.sqlserver.model.Lot;
//import fr.legrain.agrifact.sqlserver.model.Produit;
//import fr.legrain.android.lib.ecran.SmallCaptureActivity;
//
//
//public class FlashBarCodeFragment extends LgrFragment implements LigneFlashAdapter.IModifLigneFlashListener /*LigneFlashDialogFragment.LigneFlashDialogListener*/ {
//
//    public static final String ARG_SECTION_NUMBER = "section_number";
//    public static final String ARG_SECTION_CONTENT = "section_content";
//
//    private EditText etCodeBarre = null;
//    private EditText etQuantite = null;
////    private EditText etUnite = null;
//
//    private TextView tvNomDuClient = null;
//    private TextView scanFormatCB = null;
//    private TextView tvCodeArticle = null;
//    private TextView tvCodeBarre = null;
//    private TextView tvLibelleArticle = null;
//    private TextView tvLibelleLongArticle = null;
//    private Button btnValiderLigne = null;
//
//    private ProgressBar progressBar = null;
//
//    private IFlashBonLivDataListener listener;
//
//    private BonLivFlash data = null;
//
//    private Produit selectedProduit = null;
//    private List<Lot> lotsProduit = null;
//    private Lot selectedLot = null;
//    private View rootView = null;
//
//    public FlashBarCodeFragment() {
//    }
//
//    /**
//     * Returns a new instance of this fragment for the given section
//     * number.
//     */
//    public static FlashBarCodeFragment newInstance(int sectionNumber, LigneBLFlash p) {
//        FlashBarCodeFragment fragment = new FlashBarCodeFragment();
//        Bundle args = new Bundle();
//        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
//        args.putSerializable(ARG_SECTION_CONTENT, p);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        rootView = inflater.inflate(R.layout.fragment_flash_barcode, container, false);
//        final View finalRootView = rootView;
//
//        etCodeBarre = (EditText) rootView.findViewById(R.id.etCodeBarre);
//        tvCodeBarre = (TextView) rootView.findViewById(R.id.tvCodeBarre);
//        tvNomDuClient = (TextView) rootView.findViewById(R.id.tvNomDuClient);
//
//        etCodeBarre.requestFocus();
//
//        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
//        progressBar.setVisibility(View.INVISIBLE);
//
//        tvCodeArticle = (TextView) rootView.findViewById(R.id.tvCodeArticle);
//
//        tvLibelleArticle = (TextView) rootView.findViewById(R.id.tvLibelleArticle);
//        tvLibelleLongArticle = (TextView) rootView.findViewById(R.id.tvLibelleArticle);
//
//        TextView tvQte = (TextView) rootView.findViewById(R.id.tvQuantite);
//        etQuantite = (EditText) rootView.findViewById(R.id.etQuantite);
////        TextView tvUnite = (TextView) rootView.findViewById(R.id.tvUnite);
////        etUnite = (EditText) rootView.findViewById(R.id.etUnite);
//
//        //String data = getIntent().getStringExtra("parametre1");
//
//        Parametre.getInstance().setClavierHardware(isHardwareKeyboardAvailable());
//
//        data = (BonLivFlash) getArguments().getSerializable(ARG_SECTION_CONTENT);
//        if(data!=null) {
//
//        }
//
//        //LigneBLFlash data = (LigneBLFlash) getArguments().getSerializable(ARG_SECTION_CONTENT);
//
//        //final LigneBLFlash ligne = new LigneBLFlash();
//        final LigneBLFlash[] ligne = new LigneBLFlash[1];
//        ligne[0] = new LigneBLFlash();
//
//        final int[] i = new int[]{0};
//        final EditText etCodeBarre = (EditText) rootView.findViewById(R.id.etCodeBarre);
//        btnValiderLigne = (Button) rootView.findViewById(R.id.btnValiderLigne);
//        btnValiderLigne.setEnabled(false);
//        tvNomDuClient.setText(data.getNomTiers());
//        etQuantite.setInputType(InputType.TYPE_CLASS_NUMBER/* | InputType.TYPE_NUMBER_VARIATION_PASSWORD*/);
//
//        Spinner spinnerLot = (Spinner) finalRootView.findViewById(R.id.spinnerLot);
//        spinnerLot.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                selectedLot =  (Lot) parent.getItemAtPosition(position);
//                etQuantite.requestFocus();
//                etQuantite.performClick();
//                etQuantite.setSelection(0,etQuantite.getText().length());
//
//                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.showSoftInput(etQuantite, InputMethodManager.SHOW_IMPLICIT);
//                //imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
//                //imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
//
//                etQuantite.postDelayed(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        // TODO Auto-generated method stub
//                        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//                        imm.showSoftInput(etQuantite, 0);
//                    }
//                },200);
//
//                if(selectedLot!=null) {
//                    Toast.makeText(getContext(), "Selection du lot " + selectedLot.getnLot(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//        etQuantite.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                etQuantite.setSelection(0,etQuantite.getText().length());
//            }
//        });
//
//
//        final Runnable mFilterTask = new Runnable() {
//
//            @Override
//            public void run() {
//
//
//                if (!etCodeBarre.getText().toString().equals("")) {
//                    String cb = etCodeBarre.getText().toString();
//                    Toast.makeText(getContext(), "Recherche l'article avec le code barre *" + cb + "* dans Agrifact.", Toast.LENGTH_SHORT).show();
//
//                    selectedProduit = findProduitDTO(cb);
//                    if (selectedProduit != null) {
//                        lotsProduit = findLotsProduit(selectedProduit.getCodeProduit());
//
//                        BigDecimal qte = findQteCondtionnement(((FlashBonLivActivity) getActivity()).getFlashDTO().getIdTiersAgrifact(), selectedProduit);
//                        if(qte!=null) {
//                            etQuantite.setText(qte.toString());
//                        }
////                        if(selectedProduit.getUnite1()!=null) {
////                            etUnite.setText(selectedProduit.getUnite1());
////                        }
//
//                        if(lotsProduit!=null) {
//                            getActivity().runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    ArrayList<Lot> l = new ArrayList<Lot>();
//                                    l.addAll(lotsProduit);
//                                    FlashBarCodeFragment.LotsProduitAdapter adapter = new FlashBarCodeFragment.LotsProduitAdapter(getContext(), R.layout.ligne_lot_spinner_item, R.id.textView20, l);
//
//                                    Spinner spinnerLot = (Spinner) finalRootView.findViewById(R.id.spinnerLot);
//                                    spinnerLot.setAdapter(adapter);
//                                    spinnerLot.performClick();
//
//                                }
//                            });
//                        }
//
//                        tvCodeArticle.setText(selectedProduit.getCodeProduit());
//                        tvLibelleArticle.setText(selectedProduit.getLibelle());
//                        tvCodeBarre.setText(selectedProduit.getCodeBarre());
//
//                        etCodeBarre.setSelection(0,etCodeBarre.getText().length());
//
//                        btnValiderLigne.setEnabled(true);
//                    }
//                }
//
//
//            }
//        };
//
//        final Handler mHandler = new Handler();
//
//        TextWatcher watcher= new TextWatcher() {
//            public void afterTextChanged(Editable s) {
//                btnValiderLigne.setEnabled(false);
//
//                mHandler.removeCallbacks(mFilterTask);
//                mHandler.postDelayed(mFilterTask, 1000);
//
//            }
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                //Do something or nothing.
//            }
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                           }
//        };
//
//        etCodeBarre.addTextChangedListener(watcher);
//
//        btnValiderLigne.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v){
//
//                if(selectedProduit!=null) {
//
//                    //remplir l'objet ligne à partir de l'interface
//                    if(etCodeBarre.getText()!=null) {
//                        ligne[0].setCodeBarre(etCodeBarre.getText().toString());
//                    }
//                    if(etQuantite.getText()!=null && !etQuantite.getText().toString().equals("")) {
//                        ligne[0].setQte(new BigDecimal(etQuantite.getText().toString()).intValue());
//                    }
//                    BigDecimal qte = findQteCondtionnement(((FlashBonLivActivity) getActivity()).getFlashDTO().getIdTiersAgrifact(), selectedProduit);
//                    if(qte!=null) {
//                        ligne[0].setQteCondtionnement(qte.intValue());
//                    } else {
//                        ligne[0].setQteCondtionnement(1);
//                    }
//
////                    ligne[0].setUnite(etUnite.getText().toString());
//
//                    ligne[0].setIdProduitAgrifact(Integer.valueOf(selectedProduit.getnProduit()));
//                    ligne[0].setCodeProduit(selectedProduit.getCodeProduit());
//                    ligne[0].setLibelle(selectedProduit.getLibelle());
//
//                    if (selectedLot != null) {
//                        ligne[0].setnLot(selectedLot.getnLot());
//                    }
//
//                    //ajouter la ligne au bl en cours dans l'activité
//                    if (listener != null) {
//                        listener.ajouteLigne(ligne[0]);
//                    }
//
//                    //nettoyer l'écran et préparer une nouvell ligne
//                    ligne[0] = new LigneBLFlash();
//                    etCodeBarre.setText("");
//                    etQuantite.setText("");
////                    etUnite.setText("");
//                    tvCodeArticle.setText("");
//                    tvLibelleArticle.setText("");
//                    tvCodeBarre.setText("");
//                    selectedProduit = null;
//                    selectedLot = null;
//                    lotsProduit = null;
//
//                    getActivity().runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            ArrayList<Lot> l = new ArrayList<Lot>();
//                            FlashBarCodeFragment.LotsProduitAdapter adapter = new FlashBarCodeFragment.LotsProduitAdapter(getContext(), R.layout.ligne_lot_spinner_item, R.id.textView20, l);
//
//                            Spinner spinnerLot = (Spinner) finalRootView.findViewById(R.id.spinnerLot);
//                            spinnerLot.setAdapter(adapter);
//                            //finalRootView.invalidate();
//                        }
//                    });
//
//                    etCodeBarre.requestFocus();
//                    initDataLignes();
//                }
//
//            }
//        });
//
//        Button btnValiderBL = (Button) rootView.findViewById(R.id.btnValiderBL);
//        btnValiderBL.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v){
//                if(listener != null) {
//                    listener.termineBL();
//                }
//            }
//        });
//
//        final FlashBarCodeFragment ff = this;
//        Button btnScan = (Button) rootView.findViewById(R.id.btnScan);
//        btnScan.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v){
//                // http://tutorielsandroid.com/lire-un-qr-code-ou-un-code-barre-dans-son-application-android/
//                // https://stackoverflow.com/questions/29159104/how-to-integrate-zxing-barcode-scanner-without-installing-the-actual-zxing-app
//                // https://stackoverflow.com/questions/20013213/zxing-onactivityresult-not-called-in-fragment-only-in-activity
//
//                //https://stackoverflow.com/questions/18850371/change-zxing-scanner-rectangle-dimensions
//                //https://stackoverflow.com/questions/29789781/get-scan-content-using-zxing-android
//
//                //new IntentIntegrator(getActivity()).initiateScan();
//
////                IntentIntegrator.forSupportFragment(ff).initiateScan(); //OK pour fragment
//
//                //ok aussi
//                    IntentIntegrator integrator = IntentIntegrator.forSupportFragment(ff);
//                    integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
//                    integrator.setPrompt("Scan a barcode");
//                    integrator.setCameraId(0);  // Use a specific camera of the device
//                    integrator.setOrientationLocked(false);
//                    integrator.setBeepEnabled(true);
//                    integrator.setCaptureActivity(SmallCaptureActivity.class);
//                    integrator.initiateScan();
//
//                //ok aussi
////                IntentIntegrator integrator = IntentIntegrator.forSupportFragment(ff);
////                integrator.addExtra("SCAN_WIDTH", 800);
////                integrator.addExtra("SCAN_HEIGHT", 200);
////                integrator.addExtra("RESULT_DISPLAY_DURATION_MS", 3000L);
////                integrator.addExtra("PROMPT_MESSAGE", "Custom prompt to scan a product");
////                integrator.initiateScan(IntentIntegrator.PRODUCT_CODE_TYPES);
////                //integrator.initiateScan(IntentIntegrator.QR_CODE_TYPES);
//
//
//
//            }
//        });
//
//
//        etCodeBarre.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (!hasFocus) {
//                    hideKeyboard(v);
//                }
//            }
//        });
//        etQuantite.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (!hasFocus) {
//                  //  hideKeyboard(v);
//                }
//            }
//        });
//
//
//        return rootView;
//    }
//
//    public void onSupprimeLigneClick(LigneBLFlash l) {
//        if(data!=null) {
//            data.getLignesDTO().remove(l);
//        }
//        initDataLignes();
//    }
//
//    public void setOnSomeDataListener(IFlashBonLivDataListener listener) {
//        this.listener = listener;
//    }
//
//
//    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
//
//        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
//        if (scanningResult != null) {
//
//            String scanContent = scanningResult.getContents();
//            String scanFormat = scanningResult.getFormatName();
//
//            Toast.makeText(getContext(), scanContent, Toast.LENGTH_SHORT).show();
//
//            //scanFormatCB.setText(scanFormat);
//            etCodeBarre.setText(scanContent);
//            Toast toast = Toast.makeText(getContext(), scanFormat+"  "+scanContent, Toast.LENGTH_SHORT);
//        }
//        else{
//            Toast toast = Toast.makeText(getContext(), "Aucune donnée reçu!", Toast.LENGTH_SHORT);
//            toast.show();
//        }
//
//    }
//
//    public class LotsProduitAdapter extends ArrayAdapter<Lot> {
//
//        private LayoutInflater flater;
//
//        //public LotsProduitAdapter(Context context, ArrayList<Lot> lignes) {
//        public LotsProduitAdapter(Context context, int resouceId, int textviewId, List<Lot> list){
//            super(context,resouceId,textviewId, list);
//            flater = LayoutInflater.from(getContext());
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//
//            // Get the data item for this position
//            Lot lot = getItem(position);
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
//            tvName.setText(lot.getnLot());
//            tvHome.setText(lot.getCodeProduit());
//            //tvHome5.setText(lot.getLibelleProduit());
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
//            Lot lot = getItem(position);
//            TextView tvName = (TextView) convertView.findViewById(R.id.textView20);
//            TextView tvHome = (TextView) convertView.findViewById(R.id.textView21);
//            TextView tvHome5 = (TextView) convertView.findViewById(R.id.textView22);
//
//            // Populate the data into the template view using the data object
//            tvName.setText(lot.getnLot());
//            tvHome.setText(lot.getCodeProduit());
//            //tvHome5.setText(lot.getLibelleProduit());
//            return convertView;
//        }
//    }
//
//    public Produit findProduitDTO(String codeBarre) {
//        SelectUnProduitTask t = new SelectUnProduitTask(codeBarre,progressBar);
//        t.execute();
//
//        Produit l = null;
//        try {
//            l = t.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        return l;
//    }
//
//    public List<Lot> findLotsProduit(String codeProduit) {
//        SelectLotProduitTask t = new SelectLotProduitTask(codeProduit,progressBar);
//        t.execute();
//
//        List<Lot> l = null;
//        try {
//            l = t.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        return l;
//    }
//
//    public BigDecimal findQteCondtionnement(int idEntreprise, Produit p) {
//        FindQteCondtionnementTask t = new FindQteCondtionnementTask(idEntreprise, p, progressBar);
//        t.execute();
//
//        BigDecimal l = null;
//        try {
//            l = t.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        return l;
//    }
//
//    public void showNoticeDialog() {
////        // Create an instance of the dialog fragment and show it
////        //DialogFragment dialog = new LigneFlashDialogFragment();
////        LigneFlashDialogFragment dialog = new LigneFlashDialogFragment();
////        dialog.mListener = this;
////
////        //dialog.show(getSupportFragmentManager(), "LigneFlashDialogFragment");
////        dialog.show(getFragmentManager(), "LigneFlashDialogFragment");
//    }
//
//    public void initDataLignes(){
//        try {
//            ListView mListView = (ListView) rootView.findViewById(R.id.lvLignesBL);
//            View header = getLayoutInflater(Bundle.EMPTY).inflate(R.layout.ligne_bl_liste_header_flash, null);
//            if(mListView.getHeaderViewsCount()==0) {
//                mListView.addHeaderView(header);
//            }
//
//            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
//            {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    //Toast.makeText(MainActivity.this, reponse[position], Toast.LENGTH_SHORT).show();
//                    showNoticeDialog();
//                }
//            });
//
//            getActivity().runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    ArrayList<LigneBLFlash> l = new ArrayList<LigneBLFlash>();
//                    l.addAll(data.getLignesDTO());
//                    LigneFlashAdapter adapter = new LigneFlashAdapter(getContext(),l);
//                    adapter.setmListener(FlashBarCodeFragment.this);
//
//                    ListView mListView = (ListView)  rootView.findViewById(R.id.lvLignesBL);
//                    mListView.setAdapter(adapter);
//
//                }
//            });
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//}
//
//class SelectUnProduitTask extends AsyncTask<String, Void, Produit> {
//
//    private String codeBarre = null;
//    private ProgressBar p = null;
//    private Exception exception;
//
//    public SelectUnProduitTask(String codeBarre, ProgressBar p) {
//        this.codeBarre = codeBarre;
//        this.p = p;
//    }
//
//    protected Produit doInBackground(String... urls) {
//        try {
//            ProduitDAO dao = new ProduitDAO();
//
//            Produit p = null;
//            if(Parametre.getInstance().getModeTest()) {
//                //p = dao.findWithBarCode("3760012033836");
//                p = dao.findWithBarCodeTest(codeBarre);
////                p = dao.findWithId(26002);
//            } else {
//                if(Parametre.getInstance().isClavierHardware()) { //clavier ou douchette
//                    p = dao.findWithBarCode(codeBarre);
//                } else {
//                    //clavier virtuel, à supprimer pour la mise en production ou si on achète une douchette bluetooth pour le debugage
//                    p = dao.findWithBarCodeTest(codeBarre);
//                }
//            }
//            return p;
//        } catch (Exception e) {
//            this.exception = e;
//
//            return null;
//        }
//    }
//
//    protected void onPostExecute(Produit feed) {
////        super.onPostExecute(feed);
//        p.setVisibility(View.GONE);
//        p.setIndeterminate(false);
//    }
//
//    @Override
//    protected void onPreExecute() {
////        super.onPreExecute();
//        p.setVisibility(View.VISIBLE);
//        p.setIndeterminate(true);
//    }
//
//    @Override
//    protected void onProgressUpdate(Void... values) {
//        super.onProgressUpdate(values);
//    }
//}
//
//class SelectLotProduitTask extends AsyncTask<String, Void, List<Lot>> {
//
//    private String codeProduit = null;
//    private ProgressBar p = null;
//    private Exception exception;
//
//    public SelectLotProduitTask(String codeProduit, ProgressBar p) {
//        this.codeProduit = codeProduit;
//        this.p = p;
//    }
//
//    protected List<Lot> doInBackground(String... urls) {
//        try {
//            LotDAO dao = new LotDAO();
//
//            List<Lot> p = null;
//            if(Parametre.getInstance().getModeTest()) {
//                p = dao.selectAllLotProduitTest(codeProduit,false);
//            } else {
//                //p = dao.selectAllLotProduitTest(codeProduit,false);
//                // p = dao.selectAllLotProduit(codeProduit,false);
//                p = dao.selectAllLotProduit(codeProduit);
//            }
//            return p;
//        } catch (Exception e) {
//            this.exception = e;
//
//            return null;
//        }
//    }
//
//    protected void onPostExecute(List<Lot> feed) {
//        super.onPostExecute(feed);
//        p.setVisibility(View.INVISIBLE);
//        p.setIndeterminate(false);
//    }
//
//    @Override
//    protected void onPreExecute() {
//        //super.onPreExecute();
//        p.setVisibility(View.VISIBLE);
////        p.setMax(100);
////        p.setProgress(50);
//        p.setIndeterminate(true);
//    }
//
//    @Override
//    protected void onProgressUpdate(Void... values) {
//        super.onProgressUpdate(values);
//    }
//}
//
//class FindQteCondtionnementTask extends AsyncTask<String, Void, BigDecimal> {
//
//    private Produit produit = null;
//    private int idEntreprise = 0;
//    private ProgressBar p = null;
//    private Exception exception;
//
//    public FindQteCondtionnementTask(int idEntreprise, Produit produit, ProgressBar p) {
//        this.idEntreprise = idEntreprise;
//        this.produit = produit;
//        this.p = p;
//    }
//
//    protected BigDecimal doInBackground(String... urls) {
//        try {
//            EntrepriseDAO daoe = new EntrepriseDAO();
//
//            BigDecimal p = null;
//            if(Parametre.getInstance().getModeTest()) {
//                p = new BigDecimal(0);
//            } else {
//
//                p = daoe.findQteCondtionnement(idEntreprise, produit);
//            }
//            return p;
//        } catch (Exception e) {
//            this.exception = e;
//
//            return null;
//        }
//    }
//
//    protected void onPostExecute(BigDecimal feed) {
//        super.onPostExecute(feed);
//        p.setVisibility(View.INVISIBLE);
//        p.setIndeterminate(false);
//    }
//
//    @Override
//    protected void onPreExecute() {
//        super.onPreExecute();
//        p.setVisibility(View.VISIBLE);
//        p.setIndeterminate(true);
//    }
//
//    @Override
//    protected void onProgressUpdate(Void... values) {
//        super.onProgressUpdate(values);
//    }
//}
//
