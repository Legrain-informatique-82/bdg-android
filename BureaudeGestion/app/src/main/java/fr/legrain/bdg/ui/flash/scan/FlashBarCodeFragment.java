package fr.legrain.bdg.ui.flash.scan;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.DialogFragment;
import androidx.room.Room;
import fr.legrain.bdg.BureauDeGestionApp;
import fr.legrain.bdg.LgrActivity;
import fr.legrain.bdg.LgrFragment;
import fr.legrain.bdg.MainActivity;
import fr.legrain.bdg.R;
import fr.legrain.bdg.api.client.dao.IArticleBdgService;
import fr.legrain.bdg.api.client.dao.rest.retrofit.ArticleBdgService;
import fr.legrain.bdg.api.client.dto.ArticleDTO;
import fr.legrain.bdg.api.client.dto.ArticleLotEntrepotDTO;
import fr.legrain.bdg.api.client.dto.IDocumentDTO;
import fr.legrain.bdg.api.client.dto.LigneFlashDTO;
import fr.legrain.bdg.api.client.dto.RechercheParCodeBarreResult;
import fr.legrain.bdg.db.room.AppDatabase;
import fr.legrain.bdg.db.room.Article;
import fr.legrain.bdg.lib.ecran.SmallCaptureActivity;
import fr.legrain.bdg.data.model.CodeBarreEAN128;
import fr.legrain.bdg.data.model.Parametre;
import fr.legrain.bdg.data.model.TDoc;
import fr.legrain.bdg.ui.flash.LigneFlashAdapter;
import fr.legrain.bdg.ui.flash.LigneFlashDialogFragment;


public class FlashBarCodeFragment extends LgrFragment implements LigneFlashAdapter.IModifLigneFlashListener,
        LigneFlashDialogFragment.LigneFlashDialogListener {

    private TDoc typeDoc = null;

    public static final int MODE_EAN_13 = 1;
    public static final int MODE_GS1 = 2;

    public static final String ARG_SECTION_NUMBER = "section_number";
    public static final String ARG_SECTION_CONTENT = "section_content";
    public static final String ARG_TYPE_DOC = "type_doc";

    private int modeCodeBarre = MODE_GS1;
    //private int modeCodeBarre = MODE_EAN_13;

    private EditText etCodeBarre = null;
    private EditText etQuantite = null;
//    private EditText etUnite = null;

    private TextView tvLot = null;
    private TextView tvNomDuClient = null;
    private TextView scanFormatCB = null;
    private TextView tvCodeArticle = null;
    private TextView tvCodeBarre = null;
    private TextView tvLibelleArticle = null;
    private TextView tvLibelleLongArticle = null;
    private TextView tvNumLot = null;
    private Button btnValiderLigne = null;

    private ProgressBar progressBar = null;

    private IFlashDataListener listener;

    private IDocumentDTO data = null;

    private ArticleDTO selectedProduitDTO = null;
    private Article selectedProduit = null;
    private List<ArticleLotEntrepotDTO> lotsProduit = null;
    private RechercheParCodeBarreResult rechercheParCodeBarreResult = null;
    private ArticleLotEntrepotDTO selectedLot = null;
    private View rootView = null;

    public FlashBarCodeFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static FlashBarCodeFragment newInstance(int sectionNumber, LigneFlashDTO p) {
        FlashBarCodeFragment fragment = new FlashBarCodeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putSerializable(ARG_SECTION_CONTENT, p);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_flash_barcode, container, false);
        final View finalRootView = rootView;

        SharedPreferences prefs =  BureauDeGestionApp.getAppContext().getSharedPreferences(LgrActivity.MY_PREFS_NAME, LgrActivity.MODE_PRIVATE);
        int modeCodeBarre = prefs.getInt(LgrActivity.PARAM_KEY_TYPE_CODE_BARRE, LgrActivity.PARAM_VALUE_TYPE_CODE_BARRE_EAN128);
        if(modeCodeBarre==LgrActivity.PARAM_VALUE_TYPE_CODE_BARRE_EAN128) {
            this.modeCodeBarre = MODE_GS1;
        } else if(modeCodeBarre==LgrActivity.PARAM_VALUE_TYPE_CODE_BARRE_EAN13) {
            this.modeCodeBarre = MODE_EAN_13;
        }

        etCodeBarre = (EditText) rootView.findViewById(R.id.etCodeBarre);
        tvCodeBarre = (TextView) rootView.findViewById(R.id.tvCodeBarre);
        tvNumLot = (TextView) rootView.findViewById(R.id.tvNumLot);
        tvLot = (TextView) rootView.findViewById(R.id.tvLot);

        tvNomDuClient = (TextView) rootView.findViewById(R.id.tvNomDuClient);

        etCodeBarre.requestFocus();

        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        tvCodeArticle = (TextView) rootView.findViewById(R.id.tvCodeArticle);

        tvLibelleArticle = (TextView) rootView.findViewById(R.id.tvLibelleArticle);
        tvLibelleLongArticle = (TextView) rootView.findViewById(R.id.tvLibelleArticle);

        TextView tvQte = (TextView) rootView.findViewById(R.id.tvQuantite);
        etQuantite = (EditText) rootView.findViewById(R.id.etQuantite);
//        TextView tvUnite = (TextView) rootView.findViewById(R.id.tvUnite);
//        etUnite = (EditText) rootView.findViewById(R.id.etUnite);

        //String data = getIntent().getStringExtra("parametre1");

        Parametre.getInstance().setClavierHardware(isHardwareKeyboardAvailable());

        data = (IDocumentDTO) getArguments().getSerializable(ARG_SECTION_CONTENT);
        typeDoc = (TDoc) getArguments().getSerializable(ARG_TYPE_DOC);
        if(data!=null) {

        }

        //LigneBLFlash data = (LigneBLFlash) getArguments().getSerializable(ARG_SECTION_CONTENT);

        //final LigneBLFlash ligne = new LigneBLFlash();
        final LigneFlashDTO[] ligne = new LigneFlashDTO[1];
        ligne[0] = new LigneFlashDTO();

        final int[] i = new int[]{0};
        final EditText etCodeBarre = (EditText) rootView.findViewById(R.id.etCodeBarre);
        btnValiderLigne = (Button) rootView.findViewById(R.id.btnValiderLigne);
        btnValiderLigne.setEnabled(false);
        btnValiderLigne.setAlpha(0.20f);
        if(data!=null) {
            tvNomDuClient.setText(data.getNomTiers());
        }
        etQuantite.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL /*TYPE_CLASS_NUMBER/* | InputType.TYPE_NUMBER_VARIATION_PASSWORD*/);

        Spinner spinnerLot = (Spinner) finalRootView.findViewById(R.id.spinnerLot);
        spinnerLot.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedLot =  (ArticleLotEntrepotDTO) parent.getItemAtPosition(position);
                etQuantite.requestFocus();
                etQuantite.performClick();
                etQuantite.setSelection(0,etQuantite.getText().length());

                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(etQuantite, InputMethodManager.SHOW_IMPLICIT);
                //imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                //imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

                etQuantite.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.showSoftInput(etQuantite, 0);
                    }
                },200);

                if(selectedLot!=null) {
                    Toast.makeText(getContext(), "Selection du lot " + selectedLot.getNumLot(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        etQuantite.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                etQuantite.setSelection(0,etQuantite.getText().length());
            }
        });

        if (modeCodeBarre == MODE_EAN_13) {
            tvNumLot.setVisibility(View.INVISIBLE);
        } else  if (modeCodeBarre == MODE_GS1){
            tvLot.setVisibility(View.GONE);
            spinnerLot.setVisibility(View.GONE);
        }


        final Runnable mFilterTask = new Runnable() {

            @Override
            public void run() {


                if (!etCodeBarre.getText().toString().equals("")) {
                    String cb = etCodeBarre.getText().toString();
                    //Toast.makeText(getContext(), "Recherche l'article avec le code barre *" + cb + "* dans Bureau de Gestion.", Toast.LENGTH_SHORT).show();

                    if (modeCodeBarre == MODE_EAN_13) {
//                        CodeBarreEAN128 gs1 = new CodeBarreEAN128(CodeBarreEAN128.TYPE_LTG, cb, getContext());
//                        cb=gs1.ean14;
                        selectedProduitDTO = findProduitDTO(cb);
                        if (selectedProduitDTO != null) {
                            lotsProduit = findLotsProduit(selectedProduitDTO.getCodeArticle());

                            //BigDecimal qte = findQteCondtionnement(((BonlivFormActivity) getActivity()).getFlashDTO().getIdTiers(), selectedProduit);
                            //if(qte!=null) {
                            //    etQuantite.setText(qte.toString());
                            // }

//                        if(selectedProduit.getUnite1()!=null) {
//                            etUnite.setText(selectedProduit.getUnite1());
//                        }

                            if (lotsProduit != null) {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        ArrayList<ArticleLotEntrepotDTO> l = new ArrayList<ArticleLotEntrepotDTO>();
                                        l.addAll(lotsProduit);
                                        FlashBarCodeFragment.LotsProduitAdapter adapter = new FlashBarCodeFragment.LotsProduitAdapter(getContext(), R.layout.ligne_lot_spinner_item, R.id.textView20, l);

                                        Spinner spinnerLot = (Spinner) finalRootView.findViewById(R.id.spinnerLot);

                                        spinnerLot.setAdapter(adapter);
                                        if (rechercheParCodeBarreResult != null && rechercheParCodeBarreResult.getTaLotSelectionneDTO() != null) {
                                            int i = 0;
                                            for (ArticleLotEntrepotDTO ale : lotsProduit) {
                                                if (ale.getIdALE() == rechercheParCodeBarreResult.getTaLotSelectionneDTO().getIdALE()) {
                                                    spinnerLot.setSelection(i);
                                                }
                                                i++;
                                            }
                                        } else {
                                            spinnerLot.performClick();
                                        }


                                    }
                                });
                            }

                            tvCodeArticle.setText(selectedProduitDTO.getCodeArticle());
                            tvLibelleArticle.setText(selectedProduitDTO.getLibellecArticle());
                            tvCodeBarre.setText(selectedProduitDTO.getCodeBarre());

                            //etCodeBarre.setSelection(0, etCodeBarre.getText().length());
                            //sélection par defaut sur la quantité directement modifiable et non plus sur le champ code barre
                            etQuantite.requestFocus();
                            etQuantite.performClick();
                            etQuantite.setSelection(0,etQuantite.getText().length());

                            btnValiderLigne.setEnabled(true);
                            btnValiderLigne.setAlpha(1f);
                        }
                    } else {
                        //mode code barre GS1

                        CodeBarreEAN128 gs1 = new CodeBarreEAN128(CodeBarreEAN128.TYPE_LTG, cb, getContext());
                        if(gs1.ean14!=null)
                            tvCodeBarre.setText(gs1.ean14);

                        if(gs1.poidsKiloString!=null)
                            etQuantite.setText(gs1.poidsKiloString);

                        if(gs1.numLot!=null)
                            tvNumLot.setText(gs1.numLot);

                        selectedProduit = findProduit(gs1.ean13);

                        if (selectedProduit != null) {
                            tvCodeArticle.setText(selectedProduit.getCodeArticle());
                            tvLibelleArticle.setText(selectedProduit.getLibellecArticle());
                            tvCodeBarre.setText(selectedProduit.getCodeBarre());

                            // lotsProduit = findLotsProduit(selectedProduitDTO.getCodeArticle());
                            //....
                        }

                        //etCodeBarre.setSelection(0, etCodeBarre.getText().length());
                        //sélection par defaut sur la quantité directement modifiable et non plus sur le champ code barre
                        etQuantite.requestFocus();
                        etQuantite.performClick();
                        etQuantite.setSelection(0,etQuantite.getText().length());

                        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.showSoftInput(etQuantite, InputMethodManager.SHOW_IMPLICIT);

                        etQuantite.postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                // TODO Auto-generated method stub
                                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.showSoftInput(etQuantite, 0);
                            }
                        },200);

                        btnValiderLigne.setEnabled(true);
                        btnValiderLigne.setAlpha(1f);

                        SharedPreferences prefs =  BureauDeGestionApp.getAppContext().getSharedPreferences(LgrActivity.MY_PREFS_NAME, LgrActivity.MODE_PRIVATE);
                        Boolean ligneSuivanteAuto = prefs.getBoolean(LgrActivity.PARAM_KEY_LIGNE_SUIVANTE_AUTO, false);
                        if(ligneSuivanteAuto!=null && ligneSuivanteAuto) {
                            btnValiderLigne.performClick();
                        }
                    }
                }


            }
        };

        final Handler mHandler = new Handler();

        TextWatcher watcher= new TextWatcher() {
            public void afterTextChanged(Editable s) {
                btnValiderLigne.setEnabled(false);
                btnValiderLigne.setAlpha(0.20f);

                mHandler.removeCallbacks(mFilterTask);
                mHandler.postDelayed(mFilterTask, 1000);

            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Do something or nothing.
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                           }
        };

        etCodeBarre.addTextChangedListener(watcher);

        btnValiderLigne.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (modeCodeBarre == MODE_EAN_13) {
                    if (selectedProduit != null) {

                        //remplir l'objet ligne à partir de l'interface
                        if (etCodeBarre.getText() != null) {
                            ligne[0].setCodeBarre(etCodeBarre.getText().toString());
                        }
                        if (etQuantite.getText() != null && !etQuantite.getText().toString().equals("")) {
                            ligne[0].setQteLDocument(new BigDecimal(etQuantite.getText().toString()));
                        }
                        //                    BigDecimal qte = findQteCondtionnement(((FlashBonLivActivity) getActivity()).getFlashDTO().getIdTiersAgrifact(), selectedProduit);
                        //                    if(qte!=null) {
                        //                        ligne[0].setQteCondtionnement(qte.intValue());
                        //                    } else {
                        //                        ligne[0].setQteCondtionnement(1);
                        //                    }


                        ligne[0].setIdArticle(Integer.valueOf(selectedProduitDTO.getId()));
                        ligne[0].setCodeArticle(selectedProduitDTO.getCodeArticle());
                        ligne[0].setLibelleLot(selectedProduitDTO.getLibellecArticle());

                        if (selectedLot != null) {
                            ligne[0].setNumLot(selectedLot.getNumLot());
                        }

                    }
                } else {
                    //mode code barre GS1
                    if (etCodeBarre.getText() != null) {
                        ligne[0].setCodeBarreLu(etCodeBarre.getText().toString());
                    }
                    if (etCodeBarre.getText() != null) {
                        CodeBarreEAN128 gs1 = new CodeBarreEAN128(CodeBarreEAN128.TYPE_LTG, etCodeBarre.getText().toString(), getContext());
                        ligne[0].setCodeBarre(gs1.ean13);
                    }
                    if (etQuantite.getText() != null && !etQuantite.getText().toString().equals("")) {
                        ligne[0].setQteLDocument(new BigDecimal(etQuantite.getText().toString()));
                    }
                    if (tvNumLot.getText() != null) {
                        ligne[0].setNumLot(tvNumLot.getText().toString());
                    }

                    if(selectedProduit!=null) {
                        ligne[0].setIdArticle(Integer.valueOf(selectedProduit.getId()));
                        ligne[0].setCodeArticle(selectedProduit.getCodeArticle());
                        ligne[0].setLibelleLot(selectedProduit.getLibellecArticle());
                        ligne[0].setLibLDocument(selectedProduit.getLibellecArticle());
                    }
                }

                //ajouter la ligne au bl en cours dans l'activité
                if (listener != null) {
                    listener.ajouteLigne(ligne[0]);
                }

                //nettoyer l'écran et préparer une nouvell ligne
                ligne[0] = new LigneFlashDTO();
                etCodeBarre.setText("");
                etQuantite.setText("");
                //                    etUnite.setText("");
                tvCodeArticle.setText("");
                tvLibelleArticle.setText("");
                tvCodeBarre.setText("");
                tvNumLot.setText("");
                selectedProduit = null;
                selectedLot = null;
                lotsProduit = null;

                if (modeCodeBarre == MODE_EAN_13) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ArrayList<ArticleLotEntrepotDTO> l = new ArrayList<ArticleLotEntrepotDTO>();
                            FlashBarCodeFragment.LotsProduitAdapter adapter = new FlashBarCodeFragment.LotsProduitAdapter(getContext(), R.layout.ligne_lot_spinner_item, R.id.textView20, l);

                            Spinner spinnerLot = (Spinner) finalRootView.findViewById(R.id.spinnerLot);
                            spinnerLot.setAdapter(adapter);
                            //finalRootView.invalidate();
                        }
                    });
                } //else, pas de combo pour les EAN128 GS1

                etCodeBarre.requestFocus();
                initDataLignes();
            }
        }
        );

        Button btnValiderBL = (Button) rootView.findViewById(R.id.btnValiderBL);
        btnValiderBL.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                if(listener != null) {
                    listener.termineFlash();
                }
            }
        });

        final FlashBarCodeFragment ff = this;
        Button btnScan = (Button) rootView.findViewById(R.id.btnScan);
        btnScan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                // http://tutorielsandroid.com/lire-un-qr-code-ou-un-code-barre-dans-son-application-android/
                // https://stackoverflow.com/questions/29159104/how-to-integrate-zxing-barcode-scanner-without-installing-the-actual-zxing-app
                // https://stackoverflow.com/questions/20013213/zxing-onactivityresult-not-called-in-fragment-only-in-activity

                //https://stackoverflow.com/questions/18850371/change-zxing-scanner-rectangle-dimensions
                //https://stackoverflow.com/questions/29789781/get-scan-content-using-zxing-android

                //new IntentIntegrator(getActivity()).initiateScan();

//                IntentIntegrator.forSupportFragment(ff).initiateScan(); //OK pour fragment

                //ok aussi
                    IntentIntegrator integrator = IntentIntegrator.forSupportFragment(ff);
                    integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                    integrator.setPrompt("Scan a barcode");
                    integrator.setCameraId(0);  // Use a specific camera of the device
                    integrator.setOrientationLocked(false);
                    integrator.setBeepEnabled(true);
                    integrator.setCaptureActivity(SmallCaptureActivity.class);
                    integrator.initiateScan();

                //ok aussi
//                IntentIntegrator integrator = IntentIntegrator.forSupportFragment(ff);
//                integrator.addExtra("SCAN_WIDTH", 800);
//                integrator.addExtra("SCAN_HEIGHT", 200);
//                integrator.addExtra("RESULT_DISPLAY_DURATION_MS", 3000L);
//                integrator.addExtra("PROMPT_MESSAGE", "Custom prompt to scan a product");
//                integrator.initiateScan(IntentIntegrator.PRODUCT_CODE_TYPES);
//                //integrator.initiateScan(IntentIntegrator.QR_CODE_TYPES);



            }
        });


        etCodeBarre.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
        etQuantite.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                  //  hideKeyboard(v);
                }
            }
        });


        return rootView;
    }




    public void onSupprimeLigneClick(LigneFlashDTO l) {
        if(data!=null) {
            data.getLignesDTO().remove(l);
        }
        initDataLignes();
    }

    public void setOnSomeDataListener(IFlashDataListener listener) {
        this.listener = listener;
    }


    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {

            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();

            Toast.makeText(getContext(), scanContent, Toast.LENGTH_SHORT).show();

            //scanFormatCB.setText(scanFormat);
            etCodeBarre.setText(scanContent);
            Toast toast = Toast.makeText(getContext(), scanFormat+"  "+scanContent, Toast.LENGTH_SHORT);
        }
        else{
            Toast toast = Toast.makeText(getContext(), "Aucune donnée reçu!", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    public class LotsProduitAdapter extends ArrayAdapter<ArticleLotEntrepotDTO> {

        private LayoutInflater flater;

        //public LotsProduitAdapter(Context context, ArrayList<LotDTO> lignes) {
        public LotsProduitAdapter(Context context, int resouceId, int textviewId, List<ArticleLotEntrepotDTO> list){
            super(context,resouceId,textviewId, list);
            flater = LayoutInflater.from(getContext());
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            // Get the data item for this position
            ArticleLotEntrepotDTO lot = getItem(position);

            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.ligne_lot_spinner_item, parent, false);
            }

            // Lookup view for data population
            TextView tvNumLot = (TextView) convertView.findViewById(R.id.textView20);
            TextView tvLibelleLot = (TextView) convertView.findViewById(R.id.textView21);
//            TextView tvHome5 = (TextView) convertView.findViewById(R.id.textView22);

            // Populate the data into the template view using the data object
            tvNumLot.setText(lot.getNumLot());
            tvLibelleLot.setText(lot.getLibelleLot());
            //tvHome5.setText(lot.getLibelleProduit());

            // Return the completed view to render on screen
            return convertView;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = flater.inflate(R.layout.ligne_lot_spinner_item,parent, false);
            }
            ArticleLotEntrepotDTO lot = getItem(position);
            TextView tvNumLot = (TextView) convertView.findViewById(R.id.textView20);
            TextView tvLibelleLot = (TextView) convertView.findViewById(R.id.textView21);
//            TextView tvHome5 = (TextView) convertView.findViewById(R.id.textView22);

            // Populate the data into the template view using the data object
            tvNumLot.setText(lot.getNumLot());
            tvLibelleLot.setText(lot.getLibelleLot());
            //tvHome5.setText(lot.getLibelleProduit());
            return convertView;
        }
    }

    public ArticleDTO findProduitDTO(String codeBarre) {
        IArticleBdgService dao = new ArticleBdgService();
        //codeBarre = "241BL|10LBR1800001_18/04/16_001|";
        //codeBarre = "241BL|10LBR101_18/04/16_001|";

        rechercheParCodeBarreResult = dao.findByCodebarreEAN128(codeBarre);
        return rechercheParCodeBarreResult.getTaArticleDTO();
    }

    public Article findProduit(String codeBarre) {
        AppDatabase db = Room.databaseBuilder(getActivity().getBaseContext(),
                AppDatabase.class, Parametre.CONST_DB_NAME)
                .allowMainThreadQueries()
                .build();
        Article article = db.articleDao().findByCodeBarre(codeBarre);
//        rechercheParCodeBarreResult = dao.findByCodebarreEAN128(codeBarre);
//        return rechercheParCodeBarreResult.getTaArticleDTO();
        return article;
    }

    public List<ArticleLotEntrepotDTO> findLotsProduit(String codeProduit) {

        return rechercheParCodeBarreResult.getListeLotDisponible();
    }

    public BigDecimal findQteCondtionnement(int idEntreprise, ArticleDTO p) {

        return null;
    }

    public void showNoticeDialog(LigneFlashDTO ligne) {
        // Create an instance of the dialog fragment and show it
        //DialogFragment dialog = new LigneFlashDialogFragment();
        LigneFlashDialogFragment dialog = new LigneFlashDialogFragment(ligne);
//        dialog.mListener = this;

        //dialog.show(getSupportFragmentManager(), "LigneFlashDialogFragment");
        dialog.show(getFragmentManager(), "LigneFlashDialogFragment");
    }

    private ArrayList<LigneFlashDTO> l = null;
    private int pos = -1;

    public void initDataLignes(){
        try {
            ListView mListView = (ListView) rootView.findViewById(R.id.lvLignesBL);
            View header = getActivity().getLayoutInflater().inflate(R.layout.ligne_bl_liste_header_flash, null);
            if(mListView.getHeaderViewsCount()==0) {
                mListView.addHeaderView(header);
            }

            l = new ArrayList<>();
            l.addAll( (List<LigneFlashDTO>)data.getLignesDTO());

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    LigneFlashAdapter adapter = new LigneFlashAdapter(getContext(),l);
                    adapter.setmListener(FlashBarCodeFragment.this);

                    ListView mListView = (ListView)  rootView.findViewById(R.id.lvLignesBL);
                    mListView.setAdapter(adapter);



                }
            });

            mListView.setItemsCanFocus(false);
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //Toast.makeText(getContext(), "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", Toast.LENGTH_SHORT).show();
                    pos = position-1;
                    showNoticeDialog(l.get(position-1));
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // The dialog fragment receives a reference to this Activity through the
    // Fragment.onAttach() callback, which it uses to call the following methods
    // defined by the LigneFlashDialogFragment.LigneFlashDialogListener interface
    @Override
    public void onDialogPositiveClick(DialogFragment dialog, LigneFlashDTO ligne) {
        // User touched the dialog's positive button
        l.set(pos,ligne);
        ((List<LigneFlashDTO>)data.getLignesDTO()).set(pos,ligne);
        initDataLignes();
       // Toast.makeText(getContext(), "OK", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog, LigneFlashDTO ligne) {
        // User touched the dialog's negative button
        //Toast.makeText(getContext(), "PAS OK", Toast.LENGTH_SHORT).show();
    }


}
