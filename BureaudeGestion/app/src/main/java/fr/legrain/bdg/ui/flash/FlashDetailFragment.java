package fr.legrain.bdg.ui.flash;

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
import fr.legrain.bdg.api.client.dao.IFlashBdgService;
import fr.legrain.bdg.api.client.dao.rest.retrofit.FlashBdgService;
import fr.legrain.bdg.api.client.dto.FlashDTO;
import fr.legrain.bdg.api.client.dto.LigneFlashDTO;
import fr.legrain.bdg.databinding.FlashLigneItemBinding;
import fr.legrain.bdg.databinding.FragmentFlashDetailBinding;

/**
 * A placeholder fragment containing a simple view.
 */
public class FlashDetailFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    public static final String ARG_SECTION_NUMBER = "section_number";
    public static final String ARG_SECTION_CONTENT = "section_content";

    public FlashDetailFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static FlashDetailFragment newInstance(int sectionNumber, FlashDTO kanji) {
        FlashDetailFragment fragment = new FlashDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putSerializable(ARG_SECTION_CONTENT, kanji);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_flash_detail, container, false);

        FlashDTO data = (FlashDTO) getArguments().getSerializable(ARG_SECTION_CONTENT);

        FragmentFlashDetailBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_flash_detail, container, false);
        binding.setFlash(data);

        IFlashBdgService dao = new FlashBdgService();

        data = dao.findById(data.getId());

        initData(data,binding.getRoot());

        //return rootView;
        return binding.getRoot();
    }

    public void initData(final FlashDTO data, final View rootView){
        try {

            ListView mListView = (ListView)  rootView.findViewById(R.id.listViewLigneFlash);

            View header = getActivity().getLayoutInflater().inflate(R.layout.flash_local_ligne_item_header, null);
            if(mListView.getHeaderViewsCount()==0) {
                mListView.addHeaderView(header);
            }

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    List<LigneFlashDTO> l =  data.getLignesDTO();
                    //ArrayAdapter<String> adapter = new ArrayAdapter<String>(KanjiActivity.this, android.R.layout.simple_list_item_1, cache.getKanjisTxt());
                    ArrayList<LigneFlashDTO> l2 = new ArrayList<LigneFlashDTO>();
                    l2.addAll(l);
                    FlashDetailFragment.LigneFlashAdapter adapter = new FlashDetailFragment.LigneFlashAdapter(getContext(),l2);

                   // getActivity().getLayoutInflater().inflate(R.id.listViewLigneBonliv,rootView);

                   

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

    public class LigneFlashAdapter extends ArrayAdapter<LigneFlashDTO> {

        public LigneFlashAdapter(Context context, ArrayList<LigneFlashDTO> kanjis) {
            super(context, 0, kanjis);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            // Get the data item for this position
            LigneFlashDTO kanji = getItem(position);

            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.flash_ligne_item, parent, false);
            }

            LayoutInflater inflater = LayoutInflater.from(getContext());
            FlashLigneItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.flash_ligne_item, null, false);
            //binding.setBonliv(getItem(position));
            binding.setLigneFlash(kanji);

            return binding.getRoot();
        }
    }

}