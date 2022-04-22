package fr.legrain.bdg.ui.article;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.room.Room;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

import fr.legrain.bdg.LgrActivity;
import fr.legrain.bdg.R;
import fr.legrain.bdg.api.client.dao.IArticleBdgService;
import fr.legrain.bdg.api.client.dao.rest.retrofit.ArticleBdgService;
import fr.legrain.bdg.db.room.AppDatabase;
import fr.legrain.bdg.db.room.Article;
import fr.legrain.bdg.data.model.Parametre;

public class ArticleDetailActivity extends LgrActivity implements ViewPager.OnPageChangeListener {

    private IArticleBdgService dao = new ArticleBdgService();
    private Article[] laListe;
    
    /**
     * The {@link PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiers_detail);

        this.configureToolBar();

        this.configureDrawerLayout();

        this.configureNavigationView();

        setTitle("Article");

        initData();

        int data = getIntent().getIntExtra("parametre1",0);
        mViewPager.setCurrentItem(data);
        mViewPager.addOnPageChangeListener(this);
        onPageSelected(data);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if(laListe!=null) {
            setTitle(laListe[position].getCodeArticle()+" • "+laListe[position].getLibellecArticle());
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
////        getMenuInflater().inflate(R.menu.menu_kanji, menu);
//        return true;
//    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {


        public SectionsPagerAdapter(FragmentManager fm, Article[] laListe) {
            super(fm);
            ArticleDetailActivity.this.laListe = laListe;
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a KanjiCardFragment (defined as a static inner class below).

            return ArticleDetailFragment.newInstance(position + 1,laListe[position]);
            //return TiersDetailFragment.newInstance(position,mapping(cache.getReponse().get(position)));
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            //return 3;
            return laListe.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return laListe[position].getCodeArticle();
//            switch (position) {
//                case 0:
//                    return "SECTION 1";
//                case 1:
//                    return "SECTION 2";
//                case 2:
//                    return "SECTION 3";
//            }
//            return null;
        }
    }


    public void initData(){

        try {

            //List<ArticleDTO> l =  dao.selectAll();
            AppDatabase db = Room.databaseBuilder(getBaseContext(),
                    AppDatabase.class, Parametre.CONST_DB_NAME)
                    .allowMainThreadQueries()
                    .build();
            List<Article> l = db.articleDao().getAll();
            final Article[] d = new Article[l.size()];
            l.toArray(d);

            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // Create the adapter that will return a fragment for each of the three
                    // primary sections of the activity.
                    mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),d);

                    // Set up the ViewPager with the sections adapter.
                    mViewPager = (ViewPager) findViewById(R.id.container);
                    mViewPager.setAdapter(mSectionsPagerAdapter);
                    //mViewPager.setCurrentItem(5);
                    findViewById(android.R.id.content).invalidate();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
//            Log.e("Erreur lors de l'envoi de la requête : ", e.getMessage());
        }
    }

}
