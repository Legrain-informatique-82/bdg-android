package fr.legrain.bdg.ui.flash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import fr.legrain.bdg.R;
import fr.legrain.bdg.api.client.dao.IFlashBdgService;
import fr.legrain.bdg.api.client.dao.rest.retrofit.FlashBdgService;
import fr.legrain.bdg.api.client.dto.ArticleDTO;
import fr.legrain.bdg.api.client.dto.FlashDTO;
import fr.legrain.bdg.databinding.FlashItemBinding;
import fr.legrain.bdg.data.model.TDoc;

/**
     * A placeholder fragment containing a simple view.
     */
    public class ListeFlashBdgFragment extends Fragment {

        private TDoc typeDoc = null;
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        public static final String ARG_SECTION_NUMBER = "section_number";
        public static final String ARG_SECTION_CONTENT = "section_content";
        public static final String ARG_TYPE_DOC = "type_doc";

    private IFlashBdgService dao = new FlashBdgService();

        public ListeFlashBdgFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static ListeFlashBdgFragment newInstance(int sectionNumber, ArticleDTO kanji) {
            ListeFlashBdgFragment fragment = new ListeFlashBdgFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            args.putSerializable(ARG_SECTION_CONTENT, kanji);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_liste_flash_bdg, container, false);

            ArticleDTO data = (ArticleDTO) getArguments().getSerializable(ARG_SECTION_CONTENT);

            typeDoc = (TDoc) getArguments().getSerializable(ARG_TYPE_DOC);

//            FragmentArticleDetailBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_liste_flash_bdg, container, false);
//            binding.setArticle(data);

            initData(rootView);

            return rootView;
//            return binding.getRoot();
        }

    public void initData(final View rootView){
        try {

            ListView mListView = (ListView) rootView.findViewById(R.id.listViewFlash);

            View header = getActivity().getLayoutInflater().inflate(R.layout.ligne_liste_flash_bdg_header, null);
            if(mListView.getHeaderViewsCount()==0) {
                mListView.addHeaderView(header);
            }

            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent intent = new Intent(getContext() , FlashDetailActivity.class);
                    intent.putExtra("parametre1",position);

                    startActivity(intent);
                }
            });

            ListeFlashBdgFragment.this.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {

                        //données de BDG
                        List<FlashDTO> l = dao.selectAll();
                        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(KanjiActivity.this, android.R.layout.simple_list_item_1, cache.getKanjisTxt());
                        ArrayList<FlashDTO> l2 = new ArrayList<FlashDTO>();
                        l2.addAll(l);
                    ListeFlashBdgFragment.FlashAdapter adapter = new ListeFlashBdgFragment.FlashAdapter(getContext(), l2);


                        ListView mListView = (ListView) rootView.findViewById(R.id.listViewFlash);
                        mListView.setAdapter(adapter);
                        mListView.postInvalidate();

                    rootView.findViewById(android.R.id.content).invalidate();
                        //findViewById(R.id.activity_main_frame_layout).invalidate();
                    }

            });

        } catch (Exception e) {
            e.printStackTrace();
//            Log.e("Erreur lors de l'envoi de la requête : ", e.getMessage());
        }

    }

    public class FlashAdapter extends ArrayAdapter<FlashDTO> {

        public FlashAdapter(Context context, ArrayList<FlashDTO> kanjis) {
            super(context, 0, kanjis);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            // Get the data item for this position
            FlashDTO kanji = getItem(position);

            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.flash_item, parent, false);
            }

            LayoutInflater inflater = LayoutInflater.from(getContext());
            FlashItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.flash_item, null, false);
            //binding.setBonliv(getItem(position));
            binding.setFlash(kanji);

            return binding.getRoot();
        }
    }




    }