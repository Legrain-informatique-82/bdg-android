package fr.legrain.bdg.ui.bonliv;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fr.legrain.bdg.R;
import fr.legrain.bdg.api.client.dao.IBonlivBdgService;
import fr.legrain.bdg.api.client.dao.rest.retrofit.BonlivBdgService;
import fr.legrain.bdg.api.client.dto.BonlivDTO;
import fr.legrain.bdg.api.client.dto.LigneBonlivDTO;
import fr.legrain.bdg.databinding.BonlivLigneItemBinding;
import fr.legrain.bdg.databinding.FragmentBonlivDetailBinding;

/**
 * A placeholder fragment containing a simple view.
 */
public class BonlivDetailFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    public static final String ARG_SECTION_NUMBER = "section_number";
    public static final String ARG_SECTION_CONTENT = "section_content";

    public BonlivDetailFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static BonlivDetailFragment newInstance(int sectionNumber, BonlivDTO kanji) {
        BonlivDetailFragment fragment = new BonlivDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putSerializable(ARG_SECTION_CONTENT, kanji);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_bonliv_detail, container, false);

        BonlivDTO data = (BonlivDTO) getArguments().getSerializable(ARG_SECTION_CONTENT);

        FragmentBonlivDetailBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bonliv_detail, container, false);
        binding.setBonliv(data);

        IBonlivBdgService dao = new BonlivBdgService();

        data = dao.findById(data.getId());

        initData(data,binding.getRoot());

        //return rootView;
        return binding.getRoot();
    }

    public void initData(final BonlivDTO data, final View rootView){
        try {

//            ListView mListView = (ListView) findViewById(R.id.listViewBonliv);
//            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                    Intent intent = new Intent(getBaseContext() , BonlivDetailActivity.class);
//                    intent.putExtra("parametre1",position);
//
//                    startActivity(intent);
//                }
//            });

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    List<LigneBonlivDTO> l =  data.getLignesDTO();
                    //ArrayAdapter<String> adapter = new ArrayAdapter<String>(KanjiActivity.this, android.R.layout.simple_list_item_1, cache.getKanjisTxt());
                    ArrayList<LigneBonlivDTO> l2 = new ArrayList<LigneBonlivDTO>();
                    l2.addAll(l);
                    BonlivDetailFragment.LigneBonlivAdapter adapter = new BonlivDetailFragment.LigneBonlivAdapter(getContext(),l2);

                   // getActivity().getLayoutInflater().inflate(R.id.listViewLigneBonliv,rootView);

                    ListView mListView = (ListView)  rootView.findViewById(R.id.listViewLigneBonliv);
                    mListView.setAdapter(adapter);
                    mListView.postInvalidate();

                    getActivity().findViewById(android.R.id.content).invalidate();
                    //findViewById(R.id.activity_main_frame_layout).invalidate();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
//            Log.e("Erreur lors de l'envoi de la requête : ", e.getMessage());
        }

    }

    public class LigneBonlivAdapter extends ArrayAdapter<LigneBonlivDTO> {

        public LigneBonlivAdapter(Context context, ArrayList<LigneBonlivDTO> kanjis) {
            super(context, 0, kanjis);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            // Get the data item for this position
            LigneBonlivDTO kanji = getItem(position);

            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.bonliv_ligne_item, parent, false);
            }

            LayoutInflater inflater = LayoutInflater.from(getContext());
            BonlivLigneItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.bonliv_ligne_item, null, false);
            //binding.setBonliv(getItem(position));
            binding.setLigneBonliv(kanji);

            return binding.getRoot();
        }
    }

}