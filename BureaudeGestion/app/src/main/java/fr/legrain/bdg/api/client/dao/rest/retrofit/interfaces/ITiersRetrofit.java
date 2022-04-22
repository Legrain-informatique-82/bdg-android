package fr.legrain.bdg.api.client.dao.rest.retrofit.interfaces;

import fr.legrain.bdg.api.client.dao.rest.IConstRest;
import fr.legrain.bdg.api.client.dto.ArticleDTO;
import fr.legrain.bdg.api.client.dto.TiersDTO;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
 
public interface ITiersRetrofit {

 @Headers({IConstRest.HEADER_ACCEPT_APPLICATION_JSON})
 @GET("v1/tiers/")
 public Call<TiersDTO[]> allSync(/*@Header(IConstRest.HEADER_DOSSIER) String dossier*/);

 @Headers({IConstRest.HEADER_ACCEPT_APPLICATION_JSON})
 @GET("v1/tiers/")
 public Observable<TiersDTO[]> all(/*@Header(IConstRest.HEADER_DOSSIER) String dossier*/);

 @Headers({IConstRest.HEADER_ACCEPT_APPLICATION_JSON})
 @GET("v1/tiers/{id}")
 public Call<TiersDTO> select(/*@Header(IConstRest.HEADER_DOSSIER) String dossier,*/ @Path("id") int id);

 @Headers({IConstRest.HEADER_ACCEPT_APPLICATION_JSON})
 @POST("v1/tiers/")
 public Call<TiersDTO> create(/*@Header(IConstRest.HEADER_DOSSIER) String dossier,*/ @Body TiersDTO tiers);

 @Headers({IConstRest.HEADER_ACCEPT_APPLICATION_JSON})
 @PUT("v1/tiers/{id}")
 public Call<TiersDTO> update(/*@Header(IConstRest.HEADER_DOSSIER) String dossier,*/ @Path("id") int id, @Body TiersDTO tiers);

 @Headers({IConstRest.HEADER_ACCEPT_APPLICATION_JSON})
 @DELETE("v1/tiers/{id}")
 public Call<TiersDTO> delete(/*@Header(IConstRest.HEADER_DOSSIER) String dossier,*/ @Path("id") int id);
}