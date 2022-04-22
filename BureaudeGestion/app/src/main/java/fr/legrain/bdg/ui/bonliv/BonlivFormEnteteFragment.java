package fr.legrain.bdg.ui.bonliv;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.room.Room;
import fr.legrain.bdg.ui.flash.scan.IFlashDataListener;
import fr.legrain.bdg.LgrFragment;
import fr.legrain.bdg.R;
import fr.legrain.bdg.RechercheTiersAdapter;
import fr.legrain.bdg.api.client.dao.ITiersBdgService;
import fr.legrain.bdg.api.client.dao.rest.retrofit.TiersBdgService;
import fr.legrain.bdg.api.client.dto.BonlivDTO;
import fr.legrain.bdg.api.client.dto.TiersDTO;
import fr.legrain.bdg.databinding.FragmentBonlivFormEnteteBinding;
import fr.legrain.bdg.db.room.AppDatabase;
import fr.legrain.bdg.db.room.Tiers;
import fr.legrain.bdg.db.room.TiersMapper;
import fr.legrain.bdg.data.model.Parametre;


public class BonlivFormEnteteFragment extends LgrFragment {

    public static final String ARG_SECTION_NUMBER = "section_number";
    public static final String ARG_SECTION_CONTENT = "section_content";

    private IFlashDataListener listener;
    private Tiers selectedEntreprise = null;
    private ITiersBdgService dao = new TiersBdgService();


    public BonlivFormEnteteFragment() {

    }

    public void setOnSomeDataListener(IFlashDataListener listener) {
        this.listener = listener;
    }
    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static BonlivFormEnteteFragment newInstance(int sectionNumber, BonlivDTO p) {
        BonlivFormEnteteFragment fragment = new BonlivFormEnteteFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putSerializable(ARG_SECTION_CONTENT, p);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_bonliv_form_entete, container, false);

        final FragmentBonlivFormEnteteBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bonliv_form_entete, container, false);

        final TextView tvTiersId = (TextView) binding.getRoot().findViewById(R.id.tvTiersId);
//        final TextView tvTiersCode = (TextView) rootView.findViewById(R.id.tvTiersCode);
//        final TextView tvTiersNom = (TextView) rootView.findViewById(R.id.tvTiersNom);
//        final TextView tvTiersAdresse = (TextView) rootView.findViewById(R.id.tvTiersAdresse);
//        final TextView tvTiersCodePostal = (TextView) rootView.findViewById(R.id.tvTiersCodePostal);
//        final TextView tvTiersVille = (TextView) rootView.findViewById(R.id.tvTiersVille);
//        final TextView tvTiersPays = (TextView) rootView.findViewById(R.id.tvTiersPays);
        final Button btnValiderTiers = (Button) binding.getRoot().findViewById(R.id.btnValiderTiers);

        btnValiderTiers.setEnabled(false);


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
        for (Tiers e: l) {
            TIERS[i] = e.getCodeTiers()!=null?e.getCodeTiers():"";
            listeTiers.add(TIERS[i]);
            if(e.getCodeTiers()!=null) {
                l2.put(e.getCodeTiers(),e);
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
               // selectedEntreprise = l2.get(position);



                AppDatabase db = Room.databaseBuilder(getActivity().getBaseContext(),
                        AppDatabase.class, Parametre.CONST_DB_NAME)
                        .allowMainThreadQueries()
                        .build();
                selectedEntreprise = db.tiersDao().findById(selectedEntreprise.getId());//dao.findById(selectedEntreprise.getId());
//                FragmentBonlivFormEnteteBinding binding = DataBindingUtil.inflate(inflater1, R.layout.fragment_bonliv_form_entete, container1, false);
                binding.setTiers(selectedEntreprise);

                tvTiersId.setText(String.valueOf(selectedEntreprise.getId()));
//                tvTiersCode.setText("");
//                tvTiersNom.setText(selectedEntreprise.getNomTiers());

//                tvTiersAdresse.setText(selectedEntreprise.getAdresse());
//                tvTiersCodePostal.setText(selectedEntreprise.getCodePostal());
//                tvTiersVille.setText(selectedEntreprise.getVille());
//                tvTiersPays.setText(selectedEntreprise.getPays());

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
                if(selectedEntreprise!=null) {
                    if (listener != null) {
                        listener.valdeTiers(selectedEntreprise);
                    }
                    Toast.makeText(getContext(), "Valdier Tiers", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //return rootView;
        return binding.getRoot();
    }

}

