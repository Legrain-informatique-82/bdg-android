package fr.legrain.bdg.ui.tiers;

import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.legrain.bdg.R;
import fr.legrain.bdg.databinding.FragmentTiersDetailBinding;
import fr.legrain.bdg.db.room.Tiers;

/**
     * A placeholder fragment containing a simple view.
     */
    public class TiersDetailFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        public static final String ARG_SECTION_NUMBER = "section_number";
        public static final String ARG_SECTION_CONTENT = "section_content";

        public TiersDetailFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static TiersDetailFragment newInstance(int sectionNumber, Tiers tiers) {
            TiersDetailFragment fragment = new TiersDetailFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            args.putSerializable(ARG_SECTION_CONTENT, tiers);
            fragment.setArguments(args);
            return fragment;
        }

        public void onAdresseClick(View v) {
            //https://developers.google.com/maps/documentation/urls/android-intents#launch_turn-by-turn_navigation
//            String adresse = null;
//            adresse += data.getAdresse1Adresse()!=null?data.getAdresse1Adresse()+" ":"";
//            adresse += data.getAdresse2Adresse()!=null?data.getAdresse2Adresse()+" ":"";
//            adresse += data.getAdresse3Adresse()!=null?data.getAdresse3Adresse()+" ":"";
//            adresse += data.getCodepostalAdresse()!=null?data.getCodepostalAdresse()+" ":"";
//            adresse += data.getVilleAdresse()!=null?data.getVilleAdresse()+" ":"";
//            adresse += data.getPaysAdresse()!=null?data.getPaysAdresse()+" ":"";

            Uri gmmIntentUri = Uri.parse("geo:0,0?q="+data.getAdresseComplete());
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);

        }

        private Tiers data = null;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_tiers_detail, container, false);

            data = (Tiers) getArguments().getSerializable(ARG_SECTION_CONTENT);

            FragmentTiersDetailBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tiers_detail, container, false);
            binding.setTiers(data);
            binding.setFragment(this);

            //getActivity().setTitle(data.getCodeTiers());

//            User user = new User("Test", "User");
//            binding.setUser(user);


//            TextView tvKana = (TextView) rootView.findViewById(R.id.tvK_Kanji);
//            TextView tvFrancais = (TextView) rootView.findViewById(R.id.tvK_Francais);
//
//            TextView tvKanaKuyomi = (TextView) rootView.findViewById(R.id.tvK_kanaKuyomi);
//            TextView tvKanaOnyomi = (TextView) rootView.findViewById(R.id.tvK_kanaOnyomi);

//
//            //String data = getIntent().getStringExtra("parametre1");
//            tvKana.setText("Code : "+data.getCodeTiers());
//            tvFrancais.setText("Nom : "+data.getNomTiers());
//
////            tvKanaKuyomi.setText("Kunyomi : "+data.getKanaKunyomi());
////            tvKanaOnyomi.setText("Onyomi : "+data.getKanaOnyomi());


            //return rootView;
            return binding.getRoot();
        }

        /*
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            Bundle bundle = getArguments();
            if (bundle != null) {
                setText(bundle.getSerializable("kanji"));
            }
        }
        */
    }