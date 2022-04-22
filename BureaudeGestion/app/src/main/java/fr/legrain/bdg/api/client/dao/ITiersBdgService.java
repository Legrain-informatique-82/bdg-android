package fr.legrain.bdg.api.client.dao;

import java.util.List;

import fr.legrain.bdg.api.client.dto.ArticleDTO;
import fr.legrain.bdg.api.client.dto.TiersDTO;
import io.reactivex.Observable;

public interface ITiersBdgService {

 public List<TiersDTO> selectAllSync();

 public Observable<TiersDTO[]> selectAll();

 //public List<TiersDTO> selectAllData();

 public TiersDTO findById(int id);

 public void persist(TiersDTO tiers);

 public void merge(TiersDTO tiers);

 public void remove(TiersDTO tiers);
}