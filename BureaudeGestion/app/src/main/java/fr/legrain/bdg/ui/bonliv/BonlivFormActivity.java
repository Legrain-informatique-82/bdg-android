package fr.legrain.bdg.ui.bonliv;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.widget.FrameLayout;

import java.util.LinkedList;

import fr.legrain.bdg.api.client.dao.rest.retrofit.BonlivBdgService;
import fr.legrain.bdg.ui.flash.scan.FlashBarCodeFragment;
import fr.legrain.bdg.ui.flash.scan.IFlashDataListener;
import fr.legrain.bdg.LgrActivity;
import fr.legrain.bdg.R;
import fr.legrain.bdg.api.client.dao.IBonlivBdgService;
import fr.legrain.bdg.api.client.dto.ArticleDTO;
import fr.legrain.bdg.api.client.dto.BonlivDTO;
import fr.legrain.bdg.api.client.dto.LigneBonlivDTO;
import fr.legrain.bdg.api.client.dto.LigneFlashDTO;
import fr.legrain.bdg.db.room.Tiers;
import fr.legrain.bdg.db.room.Utilisateur;
import fr.legrain.bdg.data.model.TDoc;

public class BonlivFormActivity extends LgrActivity implements IFlashDataListener {

    private IBonlivBdgService dao = new BonlivBdgService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonliv_form);

        this.configureToolBar();

        this.configureDrawerLayout();

        this.configureNavigationView();

        linLayout = (FrameLayout) findViewById(R.id.activity_main_frame_layout);
        ajouteFragmentTiers(R.id.activity_main_frame_layout);
        linLayout.invalidate();

        initData();
    }

    public void initData(){
        try {


        } catch (Exception e) {
            e.printStackTrace();
//            Log.e("Erreur lors de l'envoi de la requête : ", e.getMessage());
        }

    }

    private final BonlivDTO bonliv = new BonlivDTO();
    private FrameLayout linLayout = null;
    private boolean fin = false;

    public BonlivDTO getBonliv() {
        return bonliv;
    }

    public LigneBonlivDTO convertion(LigneFlashDTO ligneDTO) {
        Log.println(Log.ERROR,"","A IMPLEMENTER : LigneBonlivDTO convertion(LigneFlashDTO ligneDTO)");
        return new LigneBonlivDTO();
    }

    public void ajouteLigne(LigneFlashDTO data) {
        //return the result
//        Intent intent = new Intent();
//        intent.putExtra("data", data);
//        setResult(RESULT_OK, intent);
//        finish();
        if(bonliv.getLignesDTO()==null) {
            bonliv.setLignesDTO(new LinkedList<LigneBonlivDTO>());
        }
        bonliv.getLignesDTO().add(convertion(data));
    }

    public void termineFlash() {
        supprimeFragmentTiers(R.id.activity_main_frame_layout);
        //linLayout.invalidate();
        linLayout.postInvalidate();
        ajouteFragmentValideBonLiv(R.id.activity_main_frame_layout,bonliv);
        //linLayout.invalidate();
        linLayout.postInvalidate();
    }

    public void valdeTiers(Tiers entreprise) {
        bonliv.setIdTiers(entreprise.getId());
        bonliv.setNomTiers(entreprise.getNomTiers());
//        bonliv.setEntreprise(entreprise);

        supprimeFragmentTiers(R.id.activity_main_frame_layout);
        linLayout.postInvalidate();

        final int[] i = new int[]{0};
        //final LigneBLFlash ligne = new LigneBLFlash();
        final LigneBonlivDTO[] ligne = new LigneBonlivDTO[1];
        ligne[0] = new LigneBonlivDTO();
        ArticleDTO p = new ArticleDTO();
        p.setCodeArticle("aa "+i[0]);

        ajouteFragmentLigne(R.id.activity_main_frame_layout,ligne[0],bonliv);

        linLayout.postInvalidate();
    }

    @Override
    public void valdeTiers(Tiers entreprise, TDoc typeDoc, Utilisateur utilisateur) {
        valdeTiers(entreprise);
    }

    public void ajouteFragmentLigne(int idPlaceHolder, LigneBonlivDTO p, BonlivDTO blf) {
        // get fragment manager
        FragmentManager fm = getSupportFragmentManager();

        // add
        FragmentTransaction ft = fm.beginTransaction();
        FlashBarCodeFragment f = new FlashBarCodeFragment();

        f.setOnSomeDataListener(this);

        ft.add(idPlaceHolder, f);

        Bundle bundle = new Bundle();
        //bundle.putSerializable(FlashBarCodeFragment.ARG_SECTION_CONTENT, p);
        bundle.putSerializable(FlashBarCodeFragment.ARG_SECTION_CONTENT, blf);
        f.setArguments(bundle);

        // alternatively add it with a tag
        // trx.add(R.id.your_placehodler, new YourFragment(), "detail");
        ft.commit();

    }


    public void ajouteFragmentValideBonLiv(int idPlaceHolder, BonlivDTO p) {
        // get fragment manager
        FragmentManager fm = getSupportFragmentManager();

        // add
        FragmentTransaction ft = fm.beginTransaction();
        ValidationBonLivFragment f = new ValidationBonLivFragment();

        //f.setOnSomeDataListener(this);

        ft.add(idPlaceHolder, f);

       Bundle bundle = new Bundle();
        bundle.putSerializable(FlashBarCodeFragment.ARG_SECTION_CONTENT, p);
        f.setArguments(bundle);

        // alternatively add it with a tag
        // trx.add(R.id.your_placehodler, new YourFragment(), "detail");
        ft.commit();
    }

    public void supprimeFragmentValideBonLiv(int idPlaceHolder) {
        // get fragment manager
        FragmentManager fm = getSupportFragmentManager();

        // replace
        //FragmentTransaction ft = fm.beginTransaction();
        //ft.replace(idPlaceHolder, new KanjiCardFragment());
        //ft.commit();

        // remove
        Fragment fragment = fm.findFragmentById(idPlaceHolder);
        FragmentTransaction ft = fm.beginTransaction();
        ft.remove(fragment);
        ft.commit();
    }

    //public void ajouteFragmentTiers(int idPlaceHolder, LigneBLFlash p) {
    public void ajouteFragmentTiers(int idPlaceHolder) {
        // get fragment manager
        FragmentManager fm = getSupportFragmentManager();

        // add
        FragmentTransaction ft = fm.beginTransaction();
        BonlivFormEnteteFragment f = new BonlivFormEnteteFragment();

        f.setOnSomeDataListener(this);

        ft.add(idPlaceHolder, f);

        Bundle bundle = new Bundle();
        //bundle.putSerializable(ChoisirTiersFragment.ARG_SECTION_CONTENT, p);
        f.setArguments(bundle);

        // alternatively add it with a tag
        // trx.add(R.id.your_placehodler, new YourFragment(), "detail");
        ft.commit();
    }

    public void supprimeFragmentTiers(int idPlaceHolder) {
        // get fragment manager
        FragmentManager fm = getSupportFragmentManager();

        // replace
        //FragmentTransaction ft = fm.beginTransaction();
        //ft.replace(idPlaceHolder, new KanjiCardFragment());
        //ft.commit();

        // remove
        Fragment fragment = fm.findFragmentById(idPlaceHolder);
        FragmentTransaction ft = fm.beginTransaction();
        ft.remove(fragment);
        ft.commit();
    }


}
