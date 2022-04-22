package fr.legrain.bdg.api.client.dao.rest.retrofit;

import android.widget.ProgressBar;

import java.util.List;
import java.util.concurrent.ExecutionException;

import fr.legrain.bdg.MainActivity;
import fr.legrain.bdg.api.client.dao.IArticleBdgService;
import fr.legrain.bdg.api.client.dao.rest.retrofit.interfaces.IArticleRetrofit;
import fr.legrain.bdg.api.client.dao.rest.retrofit.tasks.RestTaskRetrofit;
import fr.legrain.bdg.api.client.dao.rest.retrofit.tasks.RestTaskRetrofitList;
import fr.legrain.bdg.api.client.dao.rest.retrofit.tasks.UnsafeOkHttpClient;
import fr.legrain.bdg.api.client.dto.ArticleDTO;
import fr.legrain.bdg.api.client.dto.RechercheParCodeBarreResult;
import fr.legrain.bdg.data.model.Parametre;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class ArticleBdgService implements IArticleBdgService {

    private RestTaskRetrofitList<ArticleDTO> t = new RestTaskRetrofitList<ArticleDTO>(MainActivity.getAppContext(),"");
    private RestTaskRetrofit<ArticleDTO> t2 = new RestTaskRetrofit<ArticleDTO>(MainActivity.getAppContext(),"");
    private RestTaskRetrofit<RechercheParCodeBarreResult> t3 = new RestTaskRetrofit<RechercheParCodeBarreResult>(MainActivity.getAppContext(),"");

    private ProgressBar progressBar;

    @Override
    public List<ArticleDTO> selectAllSync() {

        IArticleRetrofit articleCall = RestTaskRetrofitList.getRetrofitInstance().create(IArticleRetrofit.class);

        Call<ArticleDTO[]> call = articleCall.allSync(/*param.getDossier()*/);
        t.execute(call);

        List<ArticleDTO> l = null;
        try {
            l = t.get();
            System.out.println("ArticleBdgService.selectAll");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return l;
    }

    public Observable<ArticleDTO[]> selectAll() {

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


            IArticleRetrofit iArticleRetrofit = retrofit.create(IArticleRetrofit.class);


            return iArticleRetrofit.all()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    ;

        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public ArticleDTO findById(int id) {
        IArticleRetrofit articleCall = RestTaskRetrofit.getRetrofitInstance().create(IArticleRetrofit.class);

        Call<ArticleDTO> call = articleCall.select(/*param.getDossier()*/id);
        t2.execute(call);

        ArticleDTO l = null;
        try {
            l = t2.get();
            System.out.println("ArticleBdgService.findById");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return l;
    }

    public RechercheParCodeBarreResult findByCodebarreEAN128(String codebarre) {
        IArticleRetrofit articleCall = RestTaskRetrofit.getRetrofitInstance().create(IArticleRetrofit.class);

        Call<RechercheParCodeBarreResult> call = articleCall.findByCodebarreEAN128Query(codebarre);
        t3.execute(call);

        RechercheParCodeBarreResult l = null;
        try {
            l = t3.get();
            System.out.println("ArticleBdgService.findByCodebarreEAN128");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return l;
    }

    @Override
    public void persist(ArticleDTO tiers) {
        IArticleRetrofit articleCall = RestTaskRetrofit.getRetrofitInstance().create(IArticleRetrofit.class);

        Call<ArticleDTO> call = articleCall.create(/*param.getDossier()*/tiers);
        t2.execute(call);

        ArticleDTO l = null;
        try {
            l = t2.get();
            System.out.println("ArticleBdgService.persist");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //return l;
    }

    @Override
    public void merge(ArticleDTO tiers) {
        IArticleRetrofit articleCall = RestTaskRetrofit.getRetrofitInstance().create(IArticleRetrofit.class);

        Call<ArticleDTO> call = articleCall.update(/*param.getDossier()*/tiers.getId(), tiers);
        t2.execute(call);

        ArticleDTO l = null;
        try {
            l = t2.get();
            System.out.println("ArticleBdgService.merge");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //return l;
    }

    @Override
    public void remove(ArticleDTO tiers) {
        IArticleRetrofit articleCall = RestTaskRetrofit.getRetrofitInstance().create(IArticleRetrofit.class);

        Call<ArticleDTO> call = articleCall.delete(/*param.getDossier()*/tiers.getId());
        t2.execute(call);

        ArticleDTO l = null;
        try {
            l = t2.get();
            System.out.println("ArticleBdgService.remove");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //return l;
    }
}
