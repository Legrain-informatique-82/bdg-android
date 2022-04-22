package fr.legrain.bdg.ui.flash;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.room.Room;
import fr.legrain.bdg.R;
import fr.legrain.bdg.api.client.dao.IFlashBdgService;
import fr.legrain.bdg.api.client.dao.rest.retrofit.FlashBdgService;
import fr.legrain.bdg.api.client.dto.ArticleDTO;
import fr.legrain.bdg.api.client.dto.FlashDTO;
import fr.legrain.bdg.api.client.dto.LigneFlashDTO;
import fr.legrain.bdg.databinding.FlashItemLocalBinding;
import fr.legrain.bdg.db.room.AppDatabase;
import fr.legrain.bdg.db.room.Flash;
import fr.legrain.bdg.db.room.FlashMapper;
import fr.legrain.bdg.db.room.LigneFlash;
import fr.legrain.bdg.db.room.LigneFlashMapper;
import fr.legrain.bdg.data.model.Parametre;
import fr.legrain.bdg.data.model.TDoc;

/**
     * A placeholder fragment containing a simple view.
     */
    public class ListeFlashLocalFragment extends Fragment {

    private TDoc typeDoc = null;
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        public static final String ARG_SECTION_NUMBER = "section_number";
        public static final String ARG_SECTION_CONTENT = "section_content";
        public static final String ARG_TYPE_DOC = "type_doc";

        public ListeFlashLocalFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static ListeFlashLocalFragment newInstance(int sectionNumber, ArticleDTO kanji) {
            ListeFlashLocalFragment fragment = new ListeFlashLocalFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            args.putSerializable(ARG_SECTION_CONTENT, kanji);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_liste_flash_local, container, false);

            ArticleDTO data = (ArticleDTO) getArguments().getSerializable(ARG_SECTION_CONTENT);

            typeDoc = (TDoc) getArguments().getSerializable(ARG_TYPE_DOC);

            Switch swListeFlashLocal = (Switch) rootView.findViewById(R.id.swListeFlashLocal);
            final Button btnToutEnvoyerVersBdg = (Button) rootView.findViewById(R.id.btnToutEnvoyerVersBdg);

            swListeFlashLocal.setChecked(false);
            swListeFlashLocal.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    initData(rootView, swListeFlashLocal.isChecked());
                }
            });

            btnToutEnvoyerVersBdg.setOnClickListener(new View.OnClickListener() {

                 @Override
                 public void onClick(View v) {
                     IFlashBdgService dao = null;
                     //données sqlite
                     AppDatabase db = Room.databaseBuilder(getContext(),
                             AppDatabase.class, Parametre.CONST_DB_NAME)
                             .allowMainThreadQueries()
                             .build();
                     List<Flash> l = db.flashDao().findByNonTransfere(typeDoc.getCodeTDoc());

                     List<LigneFlash> listeLigne = null;
                     for (Flash f : l) {
                         listeLigne = db.ligneFlashDao().lignesSessionFlash(f.id);
                         f.lignes = listeLigne;

                         FlashDTO dto = FlashMapper.INSTANCE.flashToFlashDto(f);
                         dto.setLignesDTO(new ArrayList<>());
                         for (LigneFlash lf :f.lignes) {
                             LigneFlashDTO ldto = LigneFlashMapper.INSTANCE.ligneFlashToLigneFlashDto(lf);
                             ldto.setIdLDocument(null);
                             dto.getLignesDTO().add(ldto);
                         }
                         dto.setDateTransfert(new Date());


                         dao = new FlashBdgService();
                         dao.persist(dto);

                         f.dateTransfert = dto.getDateTransfert();
                         db.flashDao().updateFlash(f); //TODO a récupérer au prés de BDG de préférence
                     }
                     db.close();
                     Toast.makeText(getContext(), "Transfert terminé.", Toast.LENGTH_SHORT).show();
                 }
             });

//            FragmentArticleDetailBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_liste_flash_local, container, false);
//            binding.setArticle(data);

            initData(rootView,swListeFlashLocal.isChecked());

            return rootView;
//            return binding.getRoot();
        }

    public void initData(final View rootView, boolean transfere){
        try {

            ListView mListView = (ListView) rootView.findViewById(R.id.listViewFlash);

            View header = getActivity().getLayoutInflater().inflate(R.layout.ligne_liste_flash_local_header, null);
            if(mListView.getHeaderViewsCount()==0) {
                mListView.addHeaderView(header);
            }

            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent intent = new Intent(getContext() , FlashLocalDetailActivity.class);
                    intent.putExtra("parametre1",position-1); //-1 à cause du header qui produit un décalage de 1
                    intent.putExtra("parametre2",transfere);
                    intent.putExtra(FlashLocalDetailActivity.ARG_TYPE_DOC,typeDoc);

                    startActivity(intent);
                }
            });

            ListeFlashLocalFragment.this.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {

                        //données sqlite
                        AppDatabase db = Room.databaseBuilder(getContext(),
                                AppDatabase.class, Parametre.CONST_DB_NAME)
                                .allowMainThreadQueries()
                                .build();
                    List<Flash> l = null;
                        if(transfere) {
                            l = db.flashDao().getAll();
                        } else {
                            l = db.flashDao().findByNonTransfere();
                        }
                        ArrayList<Flash> liste = new ArrayList<>();
                        for (Flash f : l) {
//                            FlashDTO dto = FlashMapper.INSTANCE.flashToFlashDto(f);
                          //  f.libelleFlash=f.nomTiers;
                           liste.add(f);
                        }
                        db.close();

                    ListeFlashLocalFragment.FlashAdapter adapter = new ListeFlashLocalFragment.FlashAdapter(getContext(), liste);
                        ListView mListView = (ListView) rootView.findViewById(R.id.listViewFlash);
                        mListView.setAdapter(adapter);
                        mListView.postInvalidate();
                    rootView.findViewById(android.R.id.content).invalidate();

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
//            Log.e("Erreur lors de l'envoi de la requête : ", e.getMessage());
        }

    }

    public class FlashAdapter extends ArrayAdapter<Flash> {

        public FlashAdapter(Context context, ArrayList<Flash> kanjis) {
            super(context, 0, kanjis);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            // Get the data item for this position
            Flash kanji = getItem(position);

            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.flash_item_local, parent, false);
            }

            LayoutInflater inflater = LayoutInflater.from(getContext());
            FlashItemLocalBinding binding = DataBindingUtil.inflate(inflater, R.layout.flash_item_local, null, false);
            //binding.setBonliv(getItem(position));
            binding.setFlash(kanji);

            return binding.getRoot();
        }
    }


    }