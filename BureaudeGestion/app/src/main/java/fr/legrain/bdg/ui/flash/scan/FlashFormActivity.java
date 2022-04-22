package fr.legrain.bdg.ui.flash.scan;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import fr.legrain.bdg.BureauDeGestionApp;
import fr.legrain.bdg.LgrActivity;
import fr.legrain.bdg.MainActivity;
import fr.legrain.bdg.R;
import fr.legrain.bdg.api.client.dao.IFlashBdgService;
import fr.legrain.bdg.api.client.dao.rest.retrofit.FlashBdgService;
import fr.legrain.bdg.api.client.dto.ArticleDTO;
import fr.legrain.bdg.api.client.dto.FlashDTO;
import fr.legrain.bdg.api.client.dto.LigneFlashDTO;
import fr.legrain.bdg.db.room.Tiers;
import fr.legrain.bdg.db.room.Utilisateur;
import fr.legrain.bdg.data.model.TDoc;

public class FlashFormActivity extends LgrActivity implements IFlashDataListener {

    private IFlashBdgService dao = new FlashBdgService();
    private TDoc typeDoc = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_form);

        this.configureToolBar();

        this.configureDrawerLayout();

        this.configureNavigationView();

        typeDoc = (TDoc) getIntent().getSerializableExtra("type");


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

    private final FlashDTO flashDTO = new FlashDTO();
    private final List<LigneFlashDTO> listeLigneFlashBrut = new ArrayList<>();
    private FrameLayout linLayout = null;
    private boolean fin = false;

    public FlashDTO getFlashDTO() {
        return flashDTO;
    }

    public void ajouteLigne(LigneFlashDTO data) {
        //return the result
//        Intent intent = new Intent();
//        intent.putExtra("data", data);
//        setResult(RESULT_OK, intent);
//        finish();
        if(flashDTO.getLignesDTO()==null) {
            flashDTO.setLignesDTO(new LinkedList<LigneFlashDTO>());
        }
        flashDTO.getLignesDTO().add(data);


        SharedPreferences prefs =  BureauDeGestionApp.getAppContext().getSharedPreferences(LgrActivity.MY_PREFS_NAME, LgrActivity.MODE_PRIVATE);
        int modeRegroupement = prefs.getInt(LgrActivity.PARAM_KEY_MODE_REGROUPEMENT, LgrActivity.PARAM_VALUE_MODE_REGROUPEMENT_AUCUN);
        if(modeRegroupement==LgrActivity.PARAM_VALUE_MODE_REGROUPEMENT_AUCUN) {
            flashDTO.regroupeLigne(null);
        } else if(modeRegroupement==LgrActivity.PARAM_VALUE_MODE_REGROUPEMENT_ARTICLE) {
            flashDTO.regroupeLigne(FlashDTO.REGROUPEMENT_ARTICLE);
        } else if(modeRegroupement==LgrActivity.PARAM_VALUE_MODE_REGROUPEMENT_LOT) {
            flashDTO.regroupeLigne(FlashDTO.REGROUPEMENT_LOT);
        }

        listeLigneFlashBrut.add(data);
    }

    public void termineFlash() {
        supprimeFragmentTiers(R.id.activity_main_frame_layout);
        //linLayout.invalidate();
        linLayout.postInvalidate();
        ajouteFragmentValideFlash(R.id.activity_main_frame_layout, flashDTO);
        //linLayout.invalidate();
        linLayout.postInvalidate();
    }

    public void valdeTiers(Tiers entreprise) {
        if(entreprise!=null) {
            flashDTO.setIdTiers(entreprise.getId());
            flashDTO.setNomTiers(entreprise.getNomTiers());
            flashDTO.setCodeTiers(entreprise.getCodeTiers());
//        flashDTO.setEntreprise(entreprise);
        }

        supprimeFragmentTiers(R.id.activity_main_frame_layout);
        linLayout.postInvalidate();

        final int[] i = new int[]{0};
        //final LigneBLFlash ligne = new LigneBLFlash();
        final LigneFlashDTO[] ligne = new LigneFlashDTO[1];
        ligne[0] = new LigneFlashDTO();
        ArticleDTO p = new ArticleDTO();
        p.setCodeArticle("aa "+i[0]);

        ajouteFragmentLigne(R.id.activity_main_frame_layout,ligne[0], flashDTO);

        linLayout.postInvalidate();
    }

    @Override
    public void valdeTiers(Tiers entreprise, TDoc typeDoc, Utilisateur utilisateur) {
        if(typeDoc!=null) {
            flashDTO.setCodeTDoc(typeDoc.getCodeTDoc());
            //flashDTO.setIdTDoc(typeDoc.getIdTDoc());

            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString(PARAM_KEY_LAST_TYPE_DOC_FLASH, typeDoc.getCodeTDoc());
            editor.apply();
        }
        if(utilisateur!=null) {
            flashDTO.setIdUtilisateur(utilisateur.getId());
            flashDTO.setNom(utilisateur.getEmail());

            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString(PARAM_KEY_LAST_UTILISATEUR_FLASH, utilisateur.getEmail());
            editor.apply();
        }
        valdeTiers(entreprise);
    }

    public void ajouteFragmentLigne(int idPlaceHolder, LigneFlashDTO p, FlashDTO blf) {
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
        bundle.putSerializable(FlashBarCodeFragment.ARG_TYPE_DOC, typeDoc);

        f.setArguments(bundle);

        // alternatively add it with a tag
        // trx.add(R.id.your_placehodler, new YourFragment(), "detail");
        ft.commit();

    }

    public void ajouteFragmentValideFlash(int idPlaceHolder, FlashDTO p) {
        // get fragment manager
        FragmentManager fm = getSupportFragmentManager();

        // add
        FragmentTransaction ft = fm.beginTransaction();
        ValidationFlashFragment f = new ValidationFlashFragment();

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
        FlashFormEnteteFragment f = new FlashFormEnteteFragment();

        f.setOnSomeDataListener(this);

        ft.add(idPlaceHolder, f);

        Bundle bundle = new Bundle();
        bundle.putSerializable(FlashFormEnteteFragment.ARG_TYPE_DOC, typeDoc);
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
