package fr.legrain.bdg.ui.bonliv;

import android.content.Context;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fr.legrain.bdg.LgrActivity;
import fr.legrain.bdg.R;
import fr.legrain.bdg.api.client.dao.IBonlivBdgService;
import fr.legrain.bdg.api.client.dao.rest.retrofit.BonlivBdgService;
import fr.legrain.bdg.api.client.dto.BonlivDTO;
import fr.legrain.bdg.databinding.BonlivItemBinding;

public class BonlivActivity extends LgrActivity {

    private IBonlivBdgService dao = new BonlivBdgService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonliv);

        this.configureToolBar();

        this.configureDrawerLayout();

        this.configureNavigationView();

        FloatingActionButton btn = (FloatingActionButton) findViewById(R.id.btnFloatActionNouveauBl);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivityNouveauBL();
            }
        });

        initData();
    }

    public class BonlivAdapter extends ArrayAdapter<BonlivDTO> {

        public BonlivAdapter(Context context, ArrayList<BonlivDTO> kanjis) {
            super(context, 0, kanjis);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            // Get the data item for this position
            BonlivDTO kanji = getItem(position);

            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.bonliv_item, parent, false);
            }

            LayoutInflater inflater = LayoutInflater.from(getContext());
            BonlivItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.bonliv_item, null, false);
            //binding.setBonliv(getItem(position));
            binding.setBonliv(kanji);

            return binding.getRoot();
        }
    }

    public void initData(){
        try {

            ListView mListView = (ListView) findViewById(R.id.listViewBonliv);
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent intent = new Intent(getBaseContext() , BonlivDetailActivity.class);
                    intent.putExtra("parametre1",position);

                    startActivity(intent);
                }
            });

            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    List<BonlivDTO> l =  dao.selectAll();
                    //ArrayAdapter<String> adapter = new ArrayAdapter<String>(KanjiActivity.this, android.R.layout.simple_list_item_1, cache.getKanjisTxt());
                    ArrayList<BonlivDTO> l2 = new ArrayList<BonlivDTO>();
                    l2.addAll(l);
                    BonlivAdapter adapter = new BonlivAdapter(BonlivActivity.this,l2);


                    ListView mListView = (ListView) findViewById(R.id.listViewBonliv);
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
