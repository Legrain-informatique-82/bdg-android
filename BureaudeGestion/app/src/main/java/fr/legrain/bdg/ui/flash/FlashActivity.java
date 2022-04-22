package fr.legrain.bdg.ui.flash;

import androidx.annotation.NonNull;

import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import fr.legrain.bdg.LgrActivity;
import fr.legrain.bdg.R;
import fr.legrain.bdg.api.client.dao.IFlashBdgService;
import fr.legrain.bdg.api.client.dao.rest.retrofit.FlashBdgService;
import fr.legrain.bdg.data.model.TDoc;

public class FlashActivity extends LgrActivity {

    private IFlashBdgService dao = new FlashBdgService();
    private TDoc typeDoc = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);

        this.configureToolBar();

        this.configureDrawerLayout();

        this.configureNavigationView();

        typeDoc = (TDoc) getIntent().getSerializableExtra("type");

        if(typeDoc!=null) {
            setTitle(typeDoc.getLiblTDoc());
        }

        ViewPager2 viewPager;

        viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(new DemoCollectionAdapter(getSupportFragmentManager(), getLifecycle()));

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        if((position+1)==1) {
                            tab.setText("Local");
                        } else {
                            tab.setText("Serveur");
                        }
                    }
                }).attach();

        FloatingActionButton btn = (FloatingActionButton) findViewById(R.id.btnFloatActionNouveauFlash);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivityNouveauFlash(typeDoc);
            }
        });

    }

    public class DemoCollectionAdapter extends FragmentStateAdapter {
        public DemoCollectionAdapter(Fragment fragment) {
            super(fragment);
        }

        public DemoCollectionAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            if((position+1)==1) {
                // Return a NEW fragment instance in createFragment(int)
                Fragment fragment = new ListeFlashLocalFragment();
                Bundle args = new Bundle();
                // Our object is just an integer :-P
                //args.putInt(ListeFlashLocalFragment.ARG_OBJECT, position + 1);
                args.putSerializable(ListeFlashLocalFragment.ARG_TYPE_DOC, typeDoc);
                fragment.setArguments(args);
                return fragment;
            } else {
                // Return a NEW fragment instance in createFragment(int)
                Fragment fragment = new ListeFlashBdgFragment();
                Bundle args = new Bundle();
                // Our object is just an integer :-P
               // args.putInt(ListeFlashBdgFragment.ARG_OBJECT, position + 1);
                args.putSerializable(ListeFlashBdgFragment.ARG_TYPE_DOC, typeDoc);
                fragment.setArguments(args);
                return fragment;
            }
        }

        @Override
        public int getItemCount() {
            return 2;
        }
    }

    public TDoc getTypeDoc() {
        return typeDoc;
    }
}
