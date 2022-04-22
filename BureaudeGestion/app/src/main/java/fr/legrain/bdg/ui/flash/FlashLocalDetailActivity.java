package fr.legrain.bdg.ui.flash;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.room.Room;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import fr.legrain.bdg.LgrActivity;
import fr.legrain.bdg.R;
import fr.legrain.bdg.api.client.dao.IFlashBdgService;
import fr.legrain.bdg.api.client.dao.rest.retrofit.FlashBdgService;
import fr.legrain.bdg.db.room.AppDatabase;
import fr.legrain.bdg.db.room.Flash;
import fr.legrain.bdg.data.model.Parametre;
import fr.legrain.bdg.data.model.TDoc;

public class FlashLocalDetailActivity extends LgrActivity implements ViewPager.OnPageChangeListener {

    private IFlashBdgService dao = new FlashBdgService();
    private TDoc typeDoc = null;
    private Flash[] laListe;

    public static final String ARG_TYPE_DOC = "type_doc";
    
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

    private boolean transfere = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_local_detail);

        this.configureToolBar();

        this.configureDrawerLayout();

        this.configureNavigationView();

        //setTitle("Bonliv ");

        int data = getIntent().getIntExtra("parametre1",0);
        transfere = getIntent().getBooleanExtra("parametre2",true);
        typeDoc = (TDoc) getIntent().getSerializableExtra(FlashLocalDetailActivity.ARG_TYPE_DOC);

        if(typeDoc!=null) {
            setTitle(typeDoc.getLiblTDoc()+" • "+"Local");
        }

        initData();

        mViewPager.setCurrentItem(data);
        mViewPager.addOnPageChangeListener(this);
        onPageSelected(data);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
//        if(laListe!=null) {
//            if((data.codeFlash==null || data.codeFlash.equals("")) /*|| data.dateFlash!=null*/) {
//                btnEnvoyerUnFlashVersBdg.setEnabled(true);
//            } else {
//                btnEnvoyerUnFlashVersBdg.setEnabled(false); //deja transfere
//            }
//        }
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


        public SectionsPagerAdapter(FragmentManager fm, Flash[] laListe) {
            super(fm);
            FlashLocalDetailActivity.this.laListe = laListe;
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a KanjiCardFragment (defined as a static inner class below).

            return FlashLocalDetailFragment.newInstance(position + 1,laListe[position]);
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

            //return laListe[position].getUsername();
            return laListe[position].codeFlash;
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

            //données sqlite
            AppDatabase db = Room.databaseBuilder(getBaseContext(),
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
               // f.libelleFlash=f.nomTiers;
                liste.add(f);
            }
            db.close();
            /////////////////////////////////////////////////////////////////////////////

           // List<Flash> l =  dao.selectAll();
            final Flash[] d = new Flash[l.size()];
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

    public TDoc getTypeDoc() {
        return typeDoc;
    }
}
