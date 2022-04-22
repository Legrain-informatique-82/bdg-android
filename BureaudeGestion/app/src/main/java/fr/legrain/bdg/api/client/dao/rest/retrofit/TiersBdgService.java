package fr.legrain.bdg.api.client.dao.rest.retrofit;

import java.util.List;
import java.util.concurrent.ExecutionException;

import fr.legrain.bdg.MainActivity;
import fr.legrain.bdg.api.client.dao.ITiersBdgService;
import fr.legrain.bdg.api.client.dao.rest.retrofit.interfaces.ITiersRetrofit;
import fr.legrain.bdg.api.client.dao.rest.retrofit.tasks.RestTaskRetrofit;
import fr.legrain.bdg.api.client.dao.rest.retrofit.tasks.RestTaskRetrofitList;
import fr.legrain.bdg.api.client.dao.rest.retrofit.tasks.UnsafeOkHttpClient;
import fr.legrain.bdg.api.client.dto.TiersDTO;
import fr.legrain.bdg.data.model.Parametre;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class TiersBdgService implements ITiersBdgService {

    private RestTaskRetrofitList<TiersDTO> t = new RestTaskRetrofitList<TiersDTO>(MainActivity.getAppContext(),"");
    private RestTaskRetrofit<TiersDTO> t2 = new RestTaskRetrofit<TiersDTO>(MainActivity.getAppContext(),"");

    @Override
    public List<TiersDTO> selectAllSync() {

        ITiersRetrofit tiersCall = RestTaskRetrofitList.getRetrofitInstance().create(ITiersRetrofit.class);

        Call<TiersDTO[]> call = tiersCall.allSync(/*param.getDossier()*/);
        t.execute(call);

        List<TiersDTO> l = null;
        try {
            l = t.get();
            System.out.println("TiersBdgService.selectAll");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return l;
    }

    @Override
    public Observable<TiersDTO[]> selectAll() {
/*************************************************************************************************************/

//        ITiersRetrofit tiersCall = RestTaskRetrofitList.getRetrofitInstance(progressBar).create(ITiersRetrofit.class);
//
//        Call<TiersDTO[]> call = tiersCall.all(/*param.getDossier()*/);
//        t.execute(call);
//
        List<TiersDTO> l = null;
//        try {
//            l = t.get();
//            System.out.println("TiersBdgService.selectAll");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        return l;
/*************************************************************************************************************/

//        TiersRetrofitTask r = new TiersRetrofitTask(Parametre.getInstance(), progressBar);
//        r.execute("");
//        try {
//            l = r.get();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return l;

/*************************************************************************************************************/
        try {
            OkHttpClient okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();

            String ENDPOINT_URL = Parametre.getInstance().getBaseUrlApi();
//            String ENDPOINT_URL = "http://dev.demo.promethee.biz:8080/";
//            String ENDPOINT_URL = "https://192.168.1.22:8443/";
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ENDPOINT_URL)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();


            ITiersRetrofit iTiersRetrofit = retrofit.create(ITiersRetrofit.class);


           return iTiersRetrofit.all()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    ;

        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    @Override
//    public List<TiersDTO> selectAllData() {
//        List<TiersDTO> tiersDTOList;
//        return new ArrayList<>(Arrays.asList(selectAll().blockingSingle()));
//    }

    @Override
    public TiersDTO findById(int id) {
        ITiersRetrofit tiersCall = RestTaskRetrofit.getRetrofitInstance().create(ITiersRetrofit.class);

        Call<TiersDTO> call = tiersCall.select(/*param.getDossier()*/id);
        t2.execute(call);

        TiersDTO l = null;
        try {
            l = t2.get();
            System.out.println("TiersBdgService.findById");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return l;
    }

    @Override
    public void persist(TiersDTO tiers) {
        ITiersRetrofit tiersCall = RestTaskRetrofit.getRetrofitInstance().create(ITiersRetrofit.class);

        Call<TiersDTO> call = tiersCall.create(/*param.getDossier()*/tiers);
        t2.execute(call);

        TiersDTO l = null;
        try {
            l = t2.get();
            System.out.println("TiersBdgService.persist");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //return l;
    }

    @Override
    public void merge(TiersDTO tiers) {
        ITiersRetrofit tiersCall = RestTaskRetrofit.getRetrofitInstance().create(ITiersRetrofit.class);

        Call<TiersDTO> call = tiersCall.update(/*param.getDossier()*/tiers.getId(), tiers);
        t2.execute(call);

        TiersDTO l = null;
        try {
            l = t2.get();
            System.out.println("TiersBdgService.merge");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //return l;
    }

    @Override
    public void remove(TiersDTO tiers) {
        ITiersRetrofit tiersCall = RestTaskRetrofit.getRetrofitInstance().create(ITiersRetrofit.class);

        Call<TiersDTO> call = tiersCall.delete(/*param.getDossier()*/tiers.getId());
        t2.execute(call);

        TiersDTO l = null;
        try {
            l = t2.get();
            System.out.println("TiersBdgService.remove");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //return l;
    }
}
