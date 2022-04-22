package fr.legrain.bdg.ui.flash.scan;

import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.room.Room;
import fr.legrain.bdg.LgrActivity;
import fr.legrain.bdg.LgrFragment;
import fr.legrain.bdg.R;
import fr.legrain.bdg.RechercheTiersAdapter;
import fr.legrain.bdg.api.client.dao.ITiersBdgService;
import fr.legrain.bdg.api.client.dao.IUtilisateurBdgService;
import fr.legrain.bdg.api.client.dao.rest.retrofit.TiersBdgService;
import fr.legrain.bdg.api.client.dao.rest.retrofit.UtilisateurBdgService;
import fr.legrain.bdg.api.client.dto.BonlivDTO;
import fr.legrain.bdg.api.client.dto.TiersDTO;
import fr.legrain.bdg.api.client.dto.UtilisateurDTO;
import fr.legrain.bdg.databinding.FragmentFlashFormEnteteBinding;
import fr.legrain.bdg.db.room.AppDatabase;
import fr.legrain.bdg.db.room.Tiers;
import fr.legrain.bdg.db.room.TiersMapper;
import fr.legrain.bdg.db.room.Utilisateur;
import fr.legrain.bdg.db.room.UtilisateurMapper;
import fr.legrain.bdg.data.model.Parametre;
import fr.legrain.bdg.data.model.TDoc;


public class FlashFormEnteteFragment extends LgrFragment {

    private TDoc typeDoc = null;

    public static final String ARG_SECTION_NUMBER = "section_number";
    public static final String ARG_SECTION_CONTENT = "section_content";
    public static final String ARG_TYPE_DOC = "type_doc";

    private IFlashDataListener listener;
    private Tiers selectedEntreprise = null;
    private ITiersBdgService dao = new TiersBdgService();
    private IUtilisateurBdgService daoUtilisateur = new UtilisateurBdgService();
    private TDoc selectedTDoc = null;
    private Utilisateur selectedUtilisateur = null;


    public FlashFormEnteteFragment() {

    }

    public void setOnSomeDataListener(IFlashDataListener listener) {
        this.listener = listener;
    }
    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static FlashFormEnteteFragment newInstance(int sectionNumber, BonlivDTO p) {
        FlashFormEnteteFragment fragment = new FlashFormEnteteFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putSerializable(ARG_SECTION_CONTENT, p);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_flash_form_entete, container, false);

        final FragmentFlashFormEnteteBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_flash_form_entete, container, false);

        final TextView tvTiersId = (TextView) binding.getRoot().findViewById(R.id.tvTiersId);
        final Button btnValiderTiers = (Button) binding.getRoot().findViewById(R.id.btnValiderTiers);

        final Spinner spinnerTypeDocument = (Spinner) binding.getRoot().findViewById(R.id.spinnerTypeDocument);
        final Spinner spinnerUtilsateur = (Spinner) binding.getRoot().findViewById(R.id.spinnerUtilsateur);

        typeDoc = (TDoc) getArguments().getSerializable(ARG_TYPE_DOC);

        //btnValiderTiers.setEnabled(false);
        btnValiderTiers.setEnabled(true);
        //btnValiderTiers.setText("Suivant");


        boolean utiliseCache = true;
        ArrayList<Tiers> listeDonneesAdapter = new ArrayList<Tiers>();
        List<Tiers> l = null;

        if(utiliseCache) {
            AppDatabase db = Room.databaseBuilder(getActivity().getBaseContext(),
                    AppDatabase.class, Parametre.CONST_DB_NAME)
                    .allowMainThreadQueries()
                    .build();
            l = db.tiersDao().getAll();

        } else {
            List<TiersDTO> serveur = null;
            l = new ArrayList<Tiers>();
            for(TiersDTO t : serveur) {
                Tiers tiers = TiersMapper.INSTANCE.tiersDtoToTiers(t);
                l.add(tiers);
            }

        }

        final Map<String,Tiers> l2 = new HashMap<>();
        final String[] TIERS = new String[l.size()];
        List<String> listeTiers = new ArrayList<>();
        int i = 0;
        String cle = null;
        for (Tiers e: l) {
            cle = (e.getCodeTiers()!=null?e.getCodeTiers():"")+" • "+(e.getNomTiers()!=null?e.getNomTiers():"");
            TIERS[i] = cle;
            listeTiers.add(TIERS[i]);
            if(e.getCodeTiers()!=null) {
                l2.put(cle,e);
            }
            i++;
        }

//        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, TIERS);
        final RechercheTiersAdapter adapter = new RechercheTiersAdapter(getActivity(), R.layout.ligne_recherche_tiers,R.id.textView, listeTiers);
        //AutoCompleteTextView textView = (AutoCompleteTextView) rootView.findViewById(R.id.autoTiers);
        AutoCompleteTextView textView = (AutoCompleteTextView) binding.getRoot().findViewById(R.id.autoTiers);
        textView.setAdapter(adapter);
        textView.setThreshold(1);
        textView.requestFocus();

        textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                selectedEntreprise = l2.get((String) parent.getItemAtPosition(position));

                AppDatabase db = Room.databaseBuilder(getActivity().getBaseContext(),
                        AppDatabase.class, Parametre.CONST_DB_NAME)
                        .allowMainThreadQueries()
                        .build();
                selectedEntreprise = db.tiersDao().findById(selectedEntreprise.getId());//dao.findById(selectedEntreprise.getId());
//                FragmentBonlivFormEnteteBinding binding = DataBindingUtil.inflate(inflater1, R.layout.fragment_bonliv_form_entete, container1, false);
                binding.setTiers(selectedEntreprise);

               // tvTiersId.setText(String.valueOf(selectedEntreprise.getId()));
//                tvTiersCode.setText("");
//                tvTiersNom.setText(selectedEntreprise.getNomTiers());

                btnValiderTiers.setEnabled(true);

                //hideKeyboard(view);
            }
        });

        textView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        btnValiderTiers.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                //if(selectedEntreprise!=null) {
                    if (listener != null) {
                        listener.valdeTiers(selectedEntreprise,selectedTDoc,selectedUtilisateur);
                    }
                    //Toast.makeText(getContext(), "Valider Tiers", Toast.LENGTH_SHORT).show();
                //}
            }
        });

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                List<TDoc> l = new ArrayList<TDoc>();
                l.add(0, new TDoc(-1,"","","")); //ligne pour ne sélectionner aucun type
                l.addAll(TDoc.getListeDocFlash());
                FlashFormEnteteFragment.TDocAdapter adapter = new FlashFormEnteteFragment.TDocAdapter(getContext(), R.layout.ligne_type_doc_spinner_item, R.id.textView20, l);

                Spinner spinnerTypeDocument = (Spinner) binding.getRoot().findViewById(R.id.spinnerTypeDocument);
                spinnerTypeDocument.setAdapter(adapter);

                //Présélection
                SharedPreferences prefs = getActivity().getSharedPreferences(((LgrActivity)getActivity()).MY_PREFS_NAME, ((LgrActivity)getActivity()).MODE_PRIVATE);
                String dernier = prefs.getString(((LgrActivity)getActivity()).PARAM_KEY_LAST_TYPE_DOC_FLASH, null);
                if(typeDoc!=null) {
                    dernier = typeDoc.getCodeTDoc();
                    spinnerTypeDocument.setEnabled(false);
                }
                if(dernier!=null) {
                    int i = 0;
                    int pos = 0;
                    for (TDoc t : l) {
                        if (t.getCodeTDoc().equals(dernier)){
                            pos = i;
                        }
                        i++;
                    }
                    spinnerTypeDocument.setSelection(pos);
                }


            }
        });

        spinnerTypeDocument.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedTDoc =  (TDoc) parent.getItemAtPosition(position);
                if(selectedTDoc!=null) {
                   // Toast.makeText(getContext(), "Selection du type de document " + selectedTDoc.getLiblTDoc(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                ArrayList<Utilisateur> listeDonneesAdapter = new ArrayList<Utilisateur>();
                List<Utilisateur> l = null;

                if(utiliseCache) {
                    AppDatabase db = Room.databaseBuilder(getActivity().getBaseContext(),
                            AppDatabase.class, Parametre.CONST_DB_NAME)
                            .allowMainThreadQueries()
                            .build();
                    l = db.utilisateurDao().getAll();

                } else {
                    List<UtilisateurDTO> serveur = daoUtilisateur.selectAllSync();
                    l = new ArrayList<Utilisateur>();
                    for(UtilisateurDTO t : serveur) {
                        Utilisateur utilisateur = UtilisateurMapper.INSTANCE.utilisateurDtoToUtilisateur(t);
                        l.add(utilisateur);
                    }

                }
                Utilisateur vide = new Utilisateur();
                vide.setId(-1);
                listeDonneesAdapter.add(vide); //ligne pour ne sélectionner aucun utilisateur
                listeDonneesAdapter.addAll(l);

                FlashFormEnteteFragment.UtilisateurAdapter adapter = new FlashFormEnteteFragment.UtilisateurAdapter(getContext(), R.layout.ligne_utilisateur_spinner_item, R.id.textView20, listeDonneesAdapter);

                Spinner spinnerUtilsateur = (Spinner) binding.getRoot().findViewById(R.id.spinnerUtilsateur);
                spinnerUtilsateur.setAdapter(adapter);

                //Présélection
                SharedPreferences prefs = getActivity().getSharedPreferences(((LgrActivity)getActivity()).MY_PREFS_NAME, ((LgrActivity)getActivity()).MODE_PRIVATE);
                String dernier = prefs.getString(((LgrActivity)getActivity()).PARAM_KEY_LAST_UTILISATEUR_FLASH, null);
                String loggedInUser = prefs.getString(LgrActivity.PARAM_KEY_LOGGED_IN_USER_NAME, null);

                if(loggedInUser!=null) {
                    spinnerUtilsateur.setEnabled(false);
                    dernier = loggedInUser; //utilisation de l'utilisateur connecté (à la place du dernier utilisateur sélectionné)
                }

                if(dernier!=null) {
                    int i = 1;
                    int pos = 0;
                    for (Utilisateur t : l) {
                        if (t.getEmail()!=null && t.getEmail().equals(dernier)){
                            pos = i;
                        }
                        i++;
                    }
                    spinnerUtilsateur.setSelection(pos);
                }


            }
        });

        spinnerUtilsateur.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedUtilisateur =  (Utilisateur) parent.getItemAtPosition(position);
                if(selectedUtilisateur!=null) {
                   // Toast.makeText(getContext(), "Selection de l'utilisateur " + selectedUtilisateur.getEmail(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //return rootView;
        return binding.getRoot();
    }

    public class TDocAdapter extends ArrayAdapter<TDoc> {

        private LayoutInflater flater;

        //public TDocAdapter(Context context, ArrayList<TDoc> lignes) {
        public TDocAdapter(Context context, int resouceId, int textviewId, List<TDoc> list){
            super(context,resouceId,textviewId, list);
            flater = LayoutInflater.from(getContext());
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            // Get the data item for this position
            TDoc lot = getItem(position);

            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.ligne_type_doc_spinner_item, parent, false);
            }

            // Lookup view for data population
            TextView tvName = (TextView) convertView.findViewById(R.id.textView20);
            TextView tvHome = (TextView) convertView.findViewById(R.id.textView21);

            // Populate the data into the template view using the data object
            //tvName.setText(String.valueOf(lot.getnTarif()));
            tvName.setText("");
            tvHome.setText(lot.getLiblTDoc());

            // Return the completed view to render on screen
            return convertView;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = flater.inflate(R.layout.ligne_type_doc_spinner_item,parent, false);
            }
            TDoc lot = getItem(position);
            TextView tvName = (TextView) convertView.findViewById(R.id.textView20);
            TextView tvHome = (TextView) convertView.findViewById(R.id.textView21);

            // Populate the data into the template view using the data object
            //tvName.setText(String.valueOf(lot.getnTarif()));
            tvName.setText("");
            tvHome.setText(lot.getLiblTDoc());

            return convertView;
        }
    }

    public class UtilisateurAdapter extends ArrayAdapter<Utilisateur> {

        private LayoutInflater flater;

        //public TDocAdapter(Context context, ArrayList<TDoc> lignes) {
        public UtilisateurAdapter(Context context, int resouceId, int textviewId, List<Utilisateur> list){
            super(context,resouceId,textviewId, list);
            flater = LayoutInflater.from(getContext());
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            // Get the data item for this position
            Utilisateur lot = getItem(position);

            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.ligne_utilisateur_spinner_item, parent, false);
            }

            // Lookup view for data population
            TextView tvName = (TextView) convertView.findViewById(R.id.textView20);
            TextView tvHome = (TextView) convertView.findViewById(R.id.textView21);

            // Populate the data into the template view using the data object
            //tvName.setText(String.valueOf(lot.getnTarif()));
            tvName.setText("");
            tvHome.setText(lot.getEmail());

            // Return the completed view to render on screen
            return convertView;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = flater.inflate(R.layout.ligne_utilisateur_spinner_item,parent, false);
            }
            Utilisateur lot = getItem(position);
            TextView tvName = (TextView) convertView.findViewById(R.id.textView20);
            TextView tvHome = (TextView) convertView.findViewById(R.id.textView21);

            // Populate the data into the template view using the data object
            //tvName.setText(String.valueOf(lot.getnTarif()));
            tvName.setText("");
            tvHome.setText(lot.getEmail());

            return convertView;
        }
    }

}

