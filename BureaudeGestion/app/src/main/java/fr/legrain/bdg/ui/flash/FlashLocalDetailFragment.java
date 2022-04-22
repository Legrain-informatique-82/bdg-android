package fr.legrain.bdg.ui.flash;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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
import fr.legrain.bdg.api.client.dto.FlashDTO;
import fr.legrain.bdg.api.client.dto.LigneFlashDTO;
import fr.legrain.bdg.databinding.FlashLocalLigneItemBinding;
import fr.legrain.bdg.databinding.FragmentFlashLocalDetailBinding;
import fr.legrain.bdg.db.room.AppDatabase;
import fr.legrain.bdg.db.room.Flash;
import fr.legrain.bdg.db.room.FlashMapper;
import fr.legrain.bdg.db.room.LigneFlash;
import fr.legrain.bdg.db.room.LigneFlashMapper;
import fr.legrain.bdg.data.model.Parametre;

/**
 * A placeholder fragment containing a simple view.
 */
public class FlashLocalDetailFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    public static final String ARG_SECTION_NUMBER = "section_number";
    public static final String ARG_SECTION_CONTENT = "section_content";

    private Flash data = null;

    public FlashLocalDetailFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static FlashLocalDetailFragment newInstance(int sectionNumber, Flash kanji) {
        FlashLocalDetailFragment fragment = new FlashLocalDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putSerializable(ARG_SECTION_CONTENT, kanji);
        fragment.setArguments(args);
        return fragment;
    }

    /*
    https://stackoverflow.com/questions/37105066/android-data-binding-pass-arguments-to-onclick-method
     */
    public void onSupprimerClick(View v) {

        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setTitle("Supression");
        alert.setMessage("Etes vous sur de vouloir supprimer ce 'Flash' ?");

        alert.setPositiveButton("Supprimer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                AppDatabase db = Room.databaseBuilder(getContext(),
                        AppDatabase.class, Parametre.CONST_DB_NAME)
                        .allowMainThreadQueries()
                        .build();

                db.runInTransaction(new Runnable(){
                    @Override
                    public void run(){
                        int idFlash = data.id;
                        List<LigneFlash> listeLigneFlash = db.ligneFlashDao().lignesSessionFlash(idFlash);
                        data.lignes = listeLigneFlash;
                        for (LigneFlash lf: data.lignes) {
                            db.ligneFlashDao().delete(lf);
                        }
                        db.flashDao().delete(data);
                        Toast toast = Toast.makeText(getContext(), "Flash supprimé", Toast.LENGTH_SHORT);
                    }
                });
                db.close();

                dialog.dismiss();
            }
        });

        alert.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alert.show();

    }

    public void onEnvoyerUnFlashVersBdgClick(View v) {
        IFlashBdgService dao = null;
        AppDatabase db = Room.databaseBuilder(getContext(),
                AppDatabase.class, Parametre.CONST_DB_NAME)
                .allowMainThreadQueries()
                .build();

        List<LigneFlash> listeLigne = null;

        listeLigne = db.ligneFlashDao().lignesSessionFlash(data.id);
        data.lignes = listeLigne;

        FlashDTO dto = FlashMapper.INSTANCE.flashToFlashDto(data);
        dto.setLignesDTO(new ArrayList<>());
        for (LigneFlash lf :data.lignes) {
            LigneFlashDTO ldto = LigneFlashMapper.INSTANCE.ligneFlashToLigneFlashDto(lf);
            ldto.setIdLDocument(null);
            dto.getLignesDTO().add(ldto);
        }
        dto.setDateTransfert(new Date());

        dao = new FlashBdgService();
        /*dto = */dao.persist(dto);

        data.dateTransfert = dto.getDateTransfert();
        //data.codeFlash = dto.getCodeFlash();
        //data.idFlashBdg = dto.getId();

        db.flashDao().updateFlash(data); //Mise à jour des infos locale à partir de ce que renvoi BDG

        db.close();
        Toast.makeText(getContext(), "Transfert terminé.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_flash_local_detail, container, false);

        data = (Flash) getArguments().getSerializable(ARG_SECTION_CONTENT);

        FragmentFlashLocalDetailBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_flash_local_detail, container, false);
        binding.setFlash(data);
        binding.setFragment(this);

        final Button btnSupprimerFlashLocal = (Button) binding.getRoot().findViewById(R.id.btnSupprimerFlashLocal);
        final Button btnEnvoyerUnFlashVersBdg = (Button) binding.getRoot().findViewById(R.id.btnEnvoyerUnFlashVersBdg);

        if(/*(data.codeFlash==null || data.codeFlash.equals("")) ||*/ data.dateTransfert==null) {
            btnEnvoyerUnFlashVersBdg.setEnabled(true);
//            Drawable d = getResources().getDrawable(R.drawable.ic_bt_transferer_vers_le_serveur);
//            btnEnvoyerUnFlashVersBdg.setBackground(d);
            btnEnvoyerUnFlashVersBdg.setAlpha(1f);
        } else {
            btnEnvoyerUnFlashVersBdg.setEnabled(false); //deja transfere
//            Drawable d = getResources().getDrawable(R.drawable.ic_bt_transferer_vers_le_serveur);
//            btnEnvoyerUnFlashVersBdg.setBackground(d);
            btnEnvoyerUnFlashVersBdg.setAlpha(0.20f);
        }

        //données sqlite
        AppDatabase db = Room.databaseBuilder(getContext(),
                AppDatabase.class, Parametre.CONST_DB_NAME)
                .allowMainThreadQueries()
                .build();
        //List<Flash> l = db.flashDao().loadAllByIds(new int[]{data.idFlash});


        //data = dao.findById(data.getId());
        //data = dao.findById(data.idFlash);


        db.runInTransaction(new Runnable(){
            @Override
            public void run(){
                int idFlash = data.id;
                List<Flash> l = db.flashDao().loadAllByIds(new int[]{idFlash});
                data = l.get(0);
                //data.libelleFlash=data.nomTiers;
                List<LigneFlash> listeLigneFlash = db.ligneFlashDao().lignesSessionFlash(idFlash);
                data.lignes = listeLigneFlash;
            }
        });


        db.close();

        initData(data,binding.getRoot());

        //return rootView;
        return binding.getRoot();
    }

    public void initData(final Flash data, final View rootView){
        try {
            ListView mListView = (ListView)  rootView.findViewById(R.id.listViewLigneFlash);

            View header = getActivity().getLayoutInflater().inflate(R.layout.flash_local_ligne_item_header, null);
            if(mListView.getHeaderViewsCount()==0) {
                mListView.addHeaderView(header);
               // mListView.setHeaderDividersEnabled(true);
            }

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //List<LigneFlash> l =  data.getLignes();
                    List<LigneFlash> l =  data.lignes;
                    //ArrayAdapter<String> adapter = new ArrayAdapter<String>(KanjiActivity.this, android.R.layout.simple_list_item_1, cache.getKanjisTxt());
                    ArrayList<LigneFlash> l2 = new ArrayList<LigneFlash>();
                    l2.addAll(l);
                    FlashLocalDetailFragment.LigneFlashAdapter adapter = new FlashLocalDetailFragment.LigneFlashAdapter(getContext(),l2);

                   // getActivity().getLayoutInflater().inflate(R.id.listViewLigneBonliv,rootView);



                    mListView.setAdapter(adapter);
                    mListView.postInvalidate();

                    getActivity().findViewById(android.R.id.content).invalidate();
                    //findViewById(R.id.activity_main_frame_layout).invalidate();

//                    if(((FlashLocalDetailActivity)getActivity()).getTypeDoc()!=null) {
//                        getActivity().setTitle(((FlashLocalDetailActivity)getActivity()).getTypeDoc().getLiblTDoc()+(data.codeFlash!=null?" : "+data.codeFlash:""));
//                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
//            Log.e("Erreur lors de l'envoi de la requête : ", e.getMessage());
        }

    }

    public class LigneFlashAdapter extends ArrayAdapter<LigneFlash> {

        public LigneFlashAdapter(Context context, ArrayList<LigneFlash> kanjis) {
            super(context, 0, kanjis);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            // Get the data item for this position
            LigneFlash kanji = getItem(position);

            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.flash_local_ligne_item, parent, false);
            }

            LayoutInflater inflater = LayoutInflater.from(getContext());
            FlashLocalLigneItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.flash_local_ligne_item, null, false);
            //binding.setBonliv(getItem(position));
            binding.setLigneFlash(kanji);

            return binding.getRoot();
        }
    }

}