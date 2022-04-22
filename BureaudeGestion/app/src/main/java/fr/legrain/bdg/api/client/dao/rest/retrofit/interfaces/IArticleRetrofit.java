package fr.legrain.bdg.api.client.dao.rest.retrofit.interfaces;

import fr.legrain.bdg.api.client.dao.rest.IConstRest;
import fr.legrain.bdg.api.client.dto.ArticleDTO;
import fr.legrain.bdg.api.client.dto.RechercheParCodeBarreResult;
import fr.legrain.bdg.api.client.dto.TiersDTO;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IArticleRetrofit {

 @Headers({IConstRest.HEADER_ACCEPT_APPLICATION_JSON})
 @GET("v1/articles/")
 public Call<ArticleDTO[]> allSync();

 @Headers({IConstRest.HEADER_ACCEPT_APPLICATION_JSON})
 @GET("v1/articles/")
 public Observable<ArticleDTO[]> all();

 @Headers({IConstRest.HEADER_ACCEPT_APPLICATION_JSON})
 @GET("v1/articles/{id}")
 public Call<ArticleDTO> select(/*@Header(IConstRest.HEADER_DOSSIER) String dossier,*/ @Path("id") int id);

 @Headers({IConstRest.HEADER_ACCEPT_APPLICATION_JSON})
 @POST("v1/articles/")
 public Call<ArticleDTO> create(/*@Header(IConstRest.HEADER_DOSSIER) String dossier,*/ @Body ArticleDTO tiers);

 @Headers({IConstRest.HEADER_ACCEPT_APPLICATION_JSON})
 @PUT("v1/articles/{id}")
 public Call<ArticleDTO> update(/*@Header(IConstRest.HEADER_DOSSIER) String dossier,*/ @Path("id") int id, @Body ArticleDTO tiers);

 @Headers({IConstRest.HEADER_ACCEPT_APPLICATION_JSON})
 @DELETE("v1/articles/{id}")
 public Call<ArticleDTO> delete(/*@Header(IConstRest.HEADER_DOSSIER) String dossier,*/ @Path("id") int id);

 @Headers({IConstRest.HEADER_ACCEPT_APPLICATION_JSON})
 @GET("v1/articles/barcode/{codeBarre}")
 public Call<RechercheParCodeBarreResult> findByCodebarreEAN128(@Path("codeBarre") String codeBarre);

 @Headers({IConstRest.HEADER_ACCEPT_APPLICATION_JSON})
 //@GET("rest/articles/barcode/{codeBarre}")
 @GET("v1/articles/barcode/")
 public Call<RechercheParCodeBarreResult> findByCodebarreEAN128Query(@Query("codeBarre") String codeBarre);
}