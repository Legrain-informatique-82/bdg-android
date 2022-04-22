package fr.legrain.bdg.api.client.dao.rest.retrofit.tasks;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.legrain.bdg.api.client.dao.rest.retrofit.interfaces.IFlashRetrofit;
import fr.legrain.bdg.api.client.dto.FlashDTO;
import fr.legrain.bdg.data.model.Parametre;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class FlashRetrofitTask extends AsyncTask<String, Void, List<FlashDTO>>{

//    private String mRestUrl;
//    private RestTaskCallback mCallback;
    private Parametre param;

//    public GetTask(String restUrl, RestTaskCallback callback){
//        this.mRestUrl = restUrl;
//        this.mCallback = callback;
//    }
    public FlashRetrofitTask(Parametre param){
        this.param = param;
//        this.mRestUrl = restUrl;
//        this.mCallback = callback;
    }

    @Override
    protected List<FlashDTO> doInBackground(String... params) {
        List<FlashDTO> r = null;

        try {
            OkHttpClient okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();

            String ENDPOINT_URL = Parametre.getInstance().getBaseUrlApi();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ENDPOINT_URL)
                    .client(okHttpClient)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();

            IFlashRetrofit getTodos = retrofit.create(IFlashRetrofit.class);

            Call<FlashDTO[]> call = getTodos.all(/*param.getDossier()*/);
            Response<FlashDTO[]> response = call.execute();
            FlashDTO[] result = response.body();


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
    protected void onPostExecute(List<FlashDTO> r) {
//        mCallback.onTaskComplete(result);
        super.onPostExecute(r);
    }


}