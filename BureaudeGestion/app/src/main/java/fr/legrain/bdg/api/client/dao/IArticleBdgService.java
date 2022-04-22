package fr.legrain.bdg.api.client.dao;

import java.util.List;
import java.util.concurrent.ExecutionException;

import fr.legrain.bdg.api.client.dao.rest.retrofit.interfaces.IArticleRetrofit;
import fr.legrain.bdg.api.client.dao.rest.retrofit.tasks.RestTaskRetrofitList;
import fr.legrain.bdg.api.client.dto.ArticleDTO;
import fr.legrain.bdg.api.client.dto.RechercheParCodeBarreResult;
import fr.legrain.bdg.api.client.dto.TiersDTO;
import io.reactivex.Observable;
import retrofit2.Call;

public interface IArticleBdgService {

 public List<ArticleDTO> selectAllSync();

 public Observable<ArticleDTO[]> selectAll();

 public ArticleDTO findById(int id);

 public RechercheParCodeBarreResult findByCodebarreEAN128(String codebarre);

 public void persist(ArticleDTO tiers);

 public void merge(ArticleDTO tiers);

 public void remove(ArticleDTO tiers);
}