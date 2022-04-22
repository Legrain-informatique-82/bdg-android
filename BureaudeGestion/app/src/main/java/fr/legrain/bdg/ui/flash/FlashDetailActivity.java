package fr.legrain.bdg.ui.flash;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

import fr.legrain.bdg.LgrActivity;
import fr.legrain.bdg.R;
import fr.legrain.bdg.api.client.dao.IFlashBdgService;
import fr.legrain.bdg.api.client.dao.rest.retrofit.FlashBdgService;
import fr.legrain.bdg.api.client.dto.FlashDTO;

public class FlashDetailActivity extends LgrActivity {

    private IFlashBdgService dao = new FlashBdgService();

    
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
        setContentView(R.layout.activity_flash_detail);

        this.configureToolBar();

        this.configureDrawerLayout();

        this.configureNavigationView();

       // setTitle("Bonliv ");

        initData();

        int data = getIntent().getIntExtra("parametre1",0);
        mViewPager.setCurrentItem(data);

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
        private FlashDTO[] laListe;

        public SectionsPagerAdapter(FragmentManager fm, FlashDTO[] laListe) {
            super(fm);
            this.laListe = laListe;
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a KanjiCardFragment (defined as a static inner class below).

            return FlashDetailFragment.newInstance(position + 1,laListe[position]);
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
            return laListe[position].getCodeFlash();
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
            List<FlashDTO> l =  dao.selectAll();
            final FlashDTO[] d = new FlashDTO[l.size()];
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
