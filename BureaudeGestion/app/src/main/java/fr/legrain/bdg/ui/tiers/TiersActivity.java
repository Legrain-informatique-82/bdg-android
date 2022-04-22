package fr.legrain.bdg.ui.tiers;

import android.content.Context;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import androidx.room.Room;
import fr.legrain.bdg.LgrActivity;
import fr.legrain.bdg.R;
import fr.legrain.bdg.api.client.dao.ITiersBdgService;
import fr.legrain.bdg.api.client.dao.rest.retrofit.TiersBdgService;
import fr.legrain.bdg.api.client.dto.TiersDTO;
import fr.legrain.bdg.databinding.TiersItemBinding;
import fr.legrain.bdg.db.room.AppDatabase;
import fr.legrain.bdg.db.room.Tiers;
import fr.legrain.bdg.db.room.TiersMapper;
import fr.legrain.bdg.data.model.Parametre;

public class TiersActivity extends LgrActivity {

    private ITiersBdgService dao = new TiersBdgService();
    private ArrayList<Tiers> listeDonneesAdapter = new ArrayList<Tiers>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiers);

        this.configureToolBar();

        this.configureDrawerLayout();

        this.configureNavigationView();

        setTitle("Tiers");

        initData();

    }

    public class TiersAdapter extends ArrayAdapter<Tiers> {

        public TiersAdapter(Context context, ArrayList<Tiers> kanjis) {
            super(context, 0, kanjis);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            // Get the data item for this position
            Tiers tiers = getItem(position);

            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.tiers_item, parent, false);
            }

            LayoutInflater inflater = LayoutInflater.from(getContext());
            TiersItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.tiers_item, null, false);
            //binding.setTiers(getItem(position));
            binding.setTiers(tiers);
//            // Lookup view for data population
//            TextView tvName = (TextView) convertView.findViewById(R.id.textView);
//
//            // Populate the data into the template view using the data object
//            tvName.setText(kanji.getCodeTiers());

            // Return the completed view to render on screen
            //return convertView;
            return binding.getRoot();
        }
    }

    public void initData(){

        try {

            //android.R.layout.simple_list_item_1 est une vue disponible de base dans le SDK android,
            //Contenant une TextView avec comme identifiant "@android:id/text1"
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, verbes);
//            ListView mListView = (ListView) findViewById(R.id.maliste);
//            mListView.setAdapter(adapter);

            ListView mListView = (ListView) findViewById(R.id.listViewTiers);
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    //Toast.makeText(MainActivity.this, reponse[position], Toast.LENGTH_SHORT).show();

                    //Toast.makeText(KanjiActivity.this, cache.getKanjis()[position].getJapKanji(), Toast.LENGTH_SHORT).show();

                    //Intent intent = new Intent(getBaseContext() , KanjiDetailActivity.class);
                    //intent.putExtra("parametre1",mapping(cache.getReponse().get(position)));

//********************************************
                    Intent intent = new Intent(getBaseContext() , TiersDetailActivity.class);
                    intent.putExtra("parametre1",position);

                    startActivity(intent);
                }
            });

            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    boolean utiliseCache = true;
                    listeDonneesAdapter = new ArrayList<Tiers>();
                    List<Tiers> l = null;

                    if(utiliseCache) {
                        AppDatabase db = Room.databaseBuilder(TiersActivity.this.getBaseContext(),
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

                    listeDonneesAdapter.addAll(l);
                    TiersAdapter adapter = new TiersAdapter(TiersActivity.this,listeDonneesAdapter);

                    ////
//                    CacheTiers ct = new CacheTiers(getBaseContext());
//                    ct.cacheBdd(l2);
                    /////

                    ListView mListView = (ListView) findViewById(R.id.listViewTiers);
                    mListView.setAdapter(adapter);
                    mListView.postInvalidate();

                    findViewById(android.R.id.content).invalidate();
                    //findViewById(R.id.activity_main_frame_layout).invalidate();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
//            Log.e("Erreur lors de l'envoi de la requête : ", e.getMessage());
        }

    }


}
