package fr.legrain.bdg.api.client.dao.rest.retrofit.tasks;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.legrain.bdg.api.client.dto.BonlivDTO;
import fr.legrain.bdg.api.client.dao.rest.retrofit.interfaces.IBonlivRetrofit;
import fr.legrain.bdg.data.model.Parametre;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class BonlivRetrofitTask extends AsyncTask<String, Void, List<BonlivDTO>>{

//    private String mRestUrl;
//    private RestTaskCallback mCallback;
    private Parametre param;

//    public GetTask(String restUrl, RestTaskCallback callback){
//        this.mRestUrl = restUrl;
//        this.mCallback = callback;
//    }
    public BonlivRetrofitTask(Parametre param){
        this.param = param;
//        this.mRestUrl = restUrl;
//        this.mCallback = callback;
    }

    @Override
    protected List<BonlivDTO> doInBackground(String... params) {
        List<BonlivDTO> r = null;

        try {
            OkHttpClient okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();

            String ENDPOINT_URL = Parametre.getInstance().getBaseUrlApi();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ENDPOINT_URL)
                    .client(okHttpClient)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();

            IBonlivRetrofit getTodos = retrofit.create(IBonlivRetrofit.class);

            Call<BonlivDTO[]> call = getTodos.all(/*param.getDossier()*/);
            Response<BonlivDTO[]> response = call.execute();
            BonlivDTO[] result = response.body();


            r = new ArrayList<>(Arrays.asList(result));


            int id = 12;
//            Call<TiersDTO> call = getTodos.select(id);
//            Response<TiersDTO> response = call.execute();
//            TiersDTO result = response.body();

            System.out.println("tt");

        } catch(Exception e) {
            e.printStackTrace();
        }

        return r;
    }

    @Override
    protected void onPostExecute(List<BonlivDTO> r) {
//        mCallback.onTaskComplete(result);
        super.onPostExecute(r);
    }


}