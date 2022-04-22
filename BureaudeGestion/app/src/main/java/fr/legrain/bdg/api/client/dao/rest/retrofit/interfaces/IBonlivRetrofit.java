package fr.legrain.bdg.api.client.dao.rest.retrofit.interfaces;

import fr.legrain.bdg.api.client.dao.rest.IConstRest;
import fr.legrain.bdg.api.client.dto.BonlivDTO;
import fr.legrain.bdg.api.client.dto.TiersDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IBonlivRetrofit {

  @Headers({IConstRest.HEADER_ACCEPT_APPLICATION_JSON})
  @GET("v1/bonliv/")
  public Call<BonlivDTO[]> all(/*@Header(IConstRest.HEADER_DOSSIER) String dossier*/);

  @Headers({IConstRest.HEADER_ACCEPT_APPLICATION_JSON})
  @GET("v1/bonliv/{id}")
  public Call<BonlivDTO> select(/*@Header(IConstRest.HEADER_DOSSIER) String dossier,*/ @Path("id") int id);

  @Headers({IConstRest.HEADER_ACCEPT_APPLICATION_JSON})
  @POST("v1/bonliv/")
  public Call<BonlivDTO> create(/*@Header(IConstRest.HEADER_DOSSIER) String dossier,*/ @Body BonlivDTO bonliv);

  @Headers({IConstRest.HEADER_ACCEPT_APPLICATION_JSON})
  @PUT("v1/bonliv/{id}")
  public Call<BonlivDTO> update(/*@Header(IConstRest.HEADER_DOSSIER) String dossier,*/ @Path("id") int id, @Body BonlivDTO bonliv);

  @Headers({IConstRest.HEADER_ACCEPT_APPLICATION_JSON})
  @DELETE("v1/bonliv/{id}")
  public Call<BonlivDTO> delete(/*@Header(IConstRest.HEADER_DOSSIER) String dossier,*/ @Path("id") int id);
}