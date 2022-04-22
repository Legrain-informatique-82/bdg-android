package fr.legrain.bdg.api.client.dao.rest.retrofit.interfaces;

import fr.legrain.bdg.api.client.dao.rest.IConstRest;
import fr.legrain.bdg.api.client.dto.FlashDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IFlashRetrofit {

  @Headers({IConstRest.HEADER_ACCEPT_APPLICATION_JSON})
  @GET("v1/flash/")
  public Call<FlashDTO[]> all(/*@Header(IConstRest.HEADER_DOSSIER) String dossier*/);

  @Headers({IConstRest.HEADER_ACCEPT_APPLICATION_JSON})
  @GET("v1/flash/")
  public Call<FlashDTO[]> all(String codeTypeDocument);

  @Headers({IConstRest.HEADER_ACCEPT_APPLICATION_JSON})
  @GET("v1/flash/{id}")
  public Call<FlashDTO> select(/*@Header(IConstRest.HEADER_DOSSIER) String dossier,*/ @Path("id") int id);

  @Headers({IConstRest.HEADER_ACCEPT_APPLICATION_JSON})
  @POST("v1/flash/")
  public Call<FlashDTO> create(/*@Header(IConstRest.HEADER_DOSSIER) String dossier,*/ @Body FlashDTO flash);

  @Headers({IConstRest.HEADER_ACCEPT_APPLICATION_JSON})
  @PUT("v1/flash/{id}")
  public Call<FlashDTO> update(/*@Header(IConstRest.HEADER_DOSSIER) String dossier,*/ @Path("id") int id, @Body FlashDTO flash);

  @Headers({IConstRest.HEADER_ACCEPT_APPLICATION_JSON})
  @DELETE("v1/flash/{id}")
  public Call<FlashDTO> delete(/*@Header(IConstRest.HEADER_DOSSIER) String dossier,*/ @Path("id") int id);
}