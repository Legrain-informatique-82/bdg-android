package fr.legrain.bdg.api.client.dao.rest.retrofit.tasks;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import fr.legrain.bdg.api.client.dto.TiersDTO;
import fr.legrain.bdg.api.client.dao.rest.retrofit.interfaces.ITiersRetrofit;
import fr.legrain.bdg.data.model.Parametre;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/*
 <interfaces>
        <interface name="management">
            <inet-address value="${jboss.bind.address.management:127.0.0.1}"/>
        </interface>
        <interface name="public">
<!--             <inet-address value="${jboss.bind.address:0.0.0.0}"/> -->
			<any-address/>
        </interface>
    </interfaces>
 */
public class TiersRetrofitTask extends AsyncTask<String, Void, List<TiersDTO>>{

//    private String mRestUrl;
//    private RestTaskCallback mCallback;
    private Parametre param;
    private ProgressBar progressBar;

//    public GetTask(String restUrl, RestTaskCallback callback){
//        this.mRestUrl = restUrl;
//        this.mCallback = callback;
//    }
    public TiersRetrofitTask(Parametre param){
        this.param = param;
//        this.mRestUrl = restUrl;
//        this.mCallback = callback;
    }

    public TiersRetrofitTask(Parametre param, ProgressBar progressBar){
        this.param = param;
        this.progressBar = progressBar;
//        this.mRestUrl = restUrl;
//        this.mCallback = callback;
    }

    protected void onPreExecute() {
        //dialog.setMessage("Loading corresponding destinations...");
        //dialog.show();
        if(progressBar!=null) {
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setIndeterminate(true);
        }
    }

    @Override
    protected List<TiersDTO> doInBackground(String... params) {
        List<TiersDTO> r = null;
        //Use HTTP Client APIs to make the call.
        //Return the HTTP Response body here.

        try {
            OkHttpClient okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();

            String ENDPOINT_URL = param.getBaseUrl();
            ENDPOINT_URL = Parametre.getInstance().getBaseUrlApi();
//            String ENDPOINT_URL = "http://dev.demo.promethee.biz:8080/";
//            String ENDPOINT_URL = "https://192.168.1.22:8443/";
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ENDPOINT_URL)
                    .client(okHttpClient)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();


            ITiersRetrofit iTiersRetrofit = retrofit.create(ITiersRetrofit.class);


//            TiersDTO tiers = new TiersDTO();
//            tiers.setId(104);
//            tiers.setNomTiers("azerty");
//            Call<TiersDTO> call = iTiersRetrofit.update(param.getDossier(),104,tiers);
//            Response<TiersDTO> response = call.execute();


           // Call<TiersDTO[]> call = iTiersRetrofit.all(/*param.getDossier()*/);
          //  Response<TiersDTO[]> response = call.execute();

//            call.enqueue(new Callback<TiersDTO[]>() {
//
//                @Override
//                public void onResponse(Call<TiersDTO[]> call, Response<TiersDTO[]> response) {
//                    if (response.isSuccessful()) {
//                        TiersDTO[] apiResponse = response.body();
//
//                        //API response
//                        System.out.println(apiResponse);
//                    } else {
//                        System.out.println("Request Error :: " + response.errorBody());
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<TiersDTO[]> call, Throwable t) {
//                    System.out.println("Network Error :: " + t.getLocalizedMessage());
//                }
//            });


         //   TiersDTO[] result = response.body();

         //   r = new ArrayList<>(Arrays.asList(result));


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

//    @Override
//    protected void onPostExecute(List<TiersDTO> r) {
////        mCallback.onTaskComplete(result);
//        super.onPostExecute(r);
//    }

    @Override
    protected void onPostExecute(List<TiersDTO> result) {
        super.onPostExecute(result);
        if(progressBar!=null) {
            progressBar.setVisibility(View.GONE);
        }
    }


}