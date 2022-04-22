package fr.legrain.bdg.ui.article;

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
import fr.legrain.bdg.api.client.dao.IArticleBdgService;
import fr.legrain.bdg.api.client.dao.rest.retrofit.ArticleBdgService;
import fr.legrain.bdg.api.client.dto.ArticleDTO;
import fr.legrain.bdg.databinding.ArticleItemBinding;
import fr.legrain.bdg.db.room.AppDatabase;
import fr.legrain.bdg.db.room.Article;
import fr.legrain.bdg.db.room.ArticleMapper;
import fr.legrain.bdg.data.model.Parametre;

public class ArticleActivity extends LgrActivity {

    private IArticleBdgService dao = new ArticleBdgService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        this.configureToolBar();

        this.configureDrawerLayout();

        this.configureNavigationView();

        setTitle("Articles");

        initData();
    }

    public class ArticleAdapter extends ArrayAdapter<Article> {

        public ArticleAdapter(Context context, ArrayList<Article> kanjis) {
            super(context, 0, kanjis);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            // Get the data item for this position
            Article article = getItem(position);

            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.article_item, parent, false);
            }

            LayoutInflater inflater = LayoutInflater.from(getContext());
            ArticleItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.article_item, null, false);
            //binding.setArticle(getItem(position));
            binding.setArticle(article);

            return binding.getRoot();
        }
    }

    public void initData(){
        try {

            ListView mListView = (ListView) findViewById(R.id.listViewArticle);
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent intent = new Intent(getBaseContext() , ArticleDetailActivity.class);
                    intent.putExtra("parametre1",position);

                    startActivity(intent);
                }
            });

            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    boolean utiliseCache = true;
                    ArrayList<Article> listeDonneesAdapter = new ArrayList<Article>();
                    List<Article> l = null;

                    if(utiliseCache) {
                        AppDatabase db = Room.databaseBuilder(ArticleActivity.this.getBaseContext(),
                                AppDatabase.class, Parametre.CONST_DB_NAME)
                                .allowMainThreadQueries()
                                .build();
                        l = db.articleDao().getAll();

                    } else {
                        List<ArticleDTO> serveur = dao.selectAllSync();
                        l = new ArrayList<Article>();
                        for(ArticleDTO a : serveur) {
                            Article article = ArticleMapper.INSTANCE.articleDtoToArticle(a);
                            l.add(article);
                        }

                    }

                    listeDonneesAdapter.addAll(l);
                    ArticleAdapter adapter = new ArticleAdapter(ArticleActivity.this,listeDonneesAdapter);

                    ////
//                    CacheArticle ca = new CacheArticle(getBaseContext());
//                    ca.cacheBdd(l2);
                    /////


                    ListView mListView = (ListView) findViewById(R.id.listViewArticle);
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
