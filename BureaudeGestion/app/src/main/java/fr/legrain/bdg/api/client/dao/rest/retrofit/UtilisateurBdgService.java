package fr.legrain.bdg.api.client.dao.rest.retrofit;

import java.util.List;
import java.util.concurrent.ExecutionException;

import fr.legrain.bdg.MainActivity;
import fr.legrain.bdg.api.client.dao.IUtilisateurBdgService;
import fr.legrain.bdg.api.client.dao.rest.retrofit.interfaces.IUtilisateurRetrofit;
import fr.legrain.bdg.api.client.dao.rest.retrofit.tasks.RestTaskRetrofit;
import fr.legrain.bdg.api.client.dao.rest.retrofit.tasks.RestTaskRetrofitList;
import fr.legrain.bdg.api.client.dao.rest.retrofit.tasks.UnsafeOkHttpClient;
import fr.legrain.bdg.api.client.dto.ArticleDTO;
import fr.legrain.bdg.api.client.dto.AutorisationDossierDTO;
import fr.legrain.bdg.api.client.dto.UtilisateurDTO;
import fr.legrain.bdg.data.model.Parametre;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class UtilisateurBdgService implements IUtilisateurBdgService {

    private RestTaskRetrofitList<UtilisateurDTO> t = new RestTaskRetrofitList<UtilisateurDTO>(MainActivity.getAppContext(),"");
    private RestTaskRetrofit<UtilisateurDTO> t2 = new RestTaskRetrofit<UtilisateurDTO>(MainActivity.getAppContext(),"");
    private RestTaskRetrofit<AutorisationDossierDTO> t3 = new RestTaskRetrofit<AutorisationDossierDTO>(MainActivity.getAppContext(),"");

    @Override
    public List<UtilisateurDTO> selectAllSync() {

        IUtilisateurRetrofit utilisateurCall = RestTaskRetrofitList.getRetrofitInstance().create(IUtilisateurRetrofit.class);

        Call<UtilisateurDTO[]> call = utilisateurCall.allSync(/*param.getDossier()*/);
        t.execute(call);

        List<UtilisateurDTO> l = null;
        try {
            l = t.get();
            System.out.println("UtilisateurBdgService.selectAll");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return l;
    }

    public Observable<UtilisateurDTO[]> selectAll() {

        List<ArticleDTO> l = null;

        try {
            OkHttpClient okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();

            String ENDPOINT_URL = Parametre.getInstance().getBaseUrlApi();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ENDPOINT_URL)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();


            IUtilisateurRetrofit utilisateurRetrofit = retrofit.create(IUtilisateurRetrofit.class);


            return utilisateurRetrofit.all()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    ;

        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UtilisateurDTO findById(int id) {
        IUtilisateurRetrofit utilisateurCall = RestTaskRetrofit.getRetrofitInstance().create(IUtilisateurRetrofit.class);

        Call<UtilisateurDTO> call = utilisateurCall.select(/*param.getDossier()*/id);
        t2.execute(call);

        UtilisateurDTO l = null;
        try {
            l = t2.get();
            System.out.println("UtilisateurBdgService.findById");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return l;
    }

    @Override
    public void persist(UtilisateurDTO utilisateur) {
        IUtilisateurRetrofit utilisateurCall = RestTaskRetrofit.getRetrofitInstance().create(IUtilisateurRetrofit.class);

        Call<UtilisateurDTO> call = utilisateurCall.create(/*param.getDossier()*/utilisateur);
        t2.execute(call);

        UtilisateurDTO l = null;
        try {
            l = t2.get();
            System.out.println("UtilisateurBdgService.persist");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //return l;
    }

    @Override
    public void merge(UtilisateurDTO utilisateur) {
        IUtilisateurRetrofit utilisateurCall = RestTaskRetrofit.getRetrofitInstance().create(IUtilisateurRetrofit.class);

        Call<UtilisateurDTO> call = utilisateurCall.update(/*param.getDossier()*/utilisateur.getId(), utilisateur);
        t2.execute(call);

        UtilisateurDTO l = null;
        try {
            l = t2.get();
            System.out.println("UtilisateurBdgService.merge");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //return l;
    }

    @Override
    public void remove(UtilisateurDTO utilisateur) {
        IUtilisateurRetrofit utilisateurCall = RestTaskRetrofit.getRetrofitInstance().create(IUtilisateurRetrofit.class);

        Call<UtilisateurDTO> call = utilisateurCall.delete(/*param.getDossier()*/utilisateur.getId());
        t2.execute(call);

        UtilisateurDTO l = null;
        try {
            l = t2.get();
            System.out.println("UtilisateurBdgService.remove");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //return l;
    }

    @Override
    public AutorisationDossierDTO findAutorisationDossier() {
        IUtilisateurRetrofit utilisateurCall = RestTaskRetrofit.getRetrofitInstance().create(IUtilisateurRetrofit.class);

        Call<AutorisationDossierDTO> call = utilisateurCall.autorisationDossier();
        t3.execute(call);

        AutorisationDossierDTO l = null;
        try {
            l = t3.get();
            System.out.println("UtilisateurBdgService.findById");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return l;
    }

    public UtilisateurDTO authenticate(String loginForm, String pwdForm) {
        IUtilisateurRetrofit utilisateurCall = RestTaskRetrofit.getRetrofitInstance().create(IUtilisateurRetrofit.class);

        Call<UtilisateurDTO> call = utilisateurCall.authenticate(loginForm, pwdForm);
        t2 = new RestTaskRetrofit<UtilisateurDTO>(MainActivity.getAppContext(),"");
        t2.execute(call);

        UtilisateurDTO l = null;
        try {
            l = t2.get();
            System.out.println("UtilisateurBdgService.authenticate");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return l;
    }
}
