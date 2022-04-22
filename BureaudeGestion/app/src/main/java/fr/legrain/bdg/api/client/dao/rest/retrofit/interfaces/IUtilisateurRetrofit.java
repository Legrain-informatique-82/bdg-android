package fr.legrain.bdg.api.client.dao.rest.retrofit.interfaces;

import fr.legrain.bdg.api.client.dao.rest.IConstRest;
import fr.legrain.bdg.api.client.dto.ArticleDTO;
import fr.legrain.bdg.api.client.dto.AutorisationDossierDTO;
import fr.legrain.bdg.api.client.dto.TiersDTO;
import fr.legrain.bdg.api.client.dto.UtilisateurDTO;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IUtilisateurRetrofit {

 @Headers({IConstRest.HEADER_ACCEPT_APPLICATION_JSON})
 @GET("v1/utilisateur/")
 public Call<UtilisateurDTO[]> allSync(/*@Header(IConstRest.HEADER_DOSSIER) String dossier*/);

 @Headers({IConstRest.HEADER_ACCEPT_APPLICATION_JSON})
 @GET("v1/utilisateur/")
 public Observable<UtilisateurDTO[]> all(/*@Header(IConstRest.HEADER_DOSSIER) String dossier*/);

 @Headers({IConstRest.HEADER_ACCEPT_APPLICATION_JSON})
 @GET("v1/utilisateur/{id}")
 public Call<UtilisateurDTO> select(/*@Header(IConstRest.HEADER_DOSSIER) String dossier,*/ @Path("id") int id);

 @Headers({IConstRest.HEADER_ACCEPT_APPLICATION_JSON})
 @POST("v1/utilisateur/")
 public Call<UtilisateurDTO> create(/*@Header(IConstRest.HEADER_DOSSIER) String dossier,*/ @Body UtilisateurDTO utilisateur);

 @Headers({IConstRest.HEADER_ACCEPT_APPLICATION_JSON})
 @PUT("v1/utilisateur/{id}")
 public Call<UtilisateurDTO> update(/*@Header(IConstRest.HEADER_DOSSIER) String dossier,*/ @Path("id") int id, @Body UtilisateurDTO utilisateur);

 @Headers({IConstRest.HEADER_ACCEPT_APPLICATION_JSON})
 @DELETE("v1/utilisateur/{id}")
 public Call<UtilisateurDTO> delete(/*@Header(IConstRest.HEADER_DOSSIER) String dossier,*/ @Path("id") int id);

 @Headers({IConstRest.HEADER_ACCEPT_APPLICATION_JSON})
 @GET("v1/utilisateur/autorisation-dossier")
 public Call<AutorisationDossierDTO> autorisationDossier();

 @Headers({IConstRest.HEADER_ACCEPT_APPLICATION_JSON/*, IConstRest.HEADER_CONTENT_TYPE_APPLICATION_X_WWW_FORM_URLENCODED*/})
 @POST("v1/auth/authenticate/")
 @FormUrlEncoded
 public Call<UtilisateurDTO> authenticate(@Field("login") String loginForm, @Field("password") String pwdForm);
}