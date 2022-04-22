package fr.legrain.bdg.api.client.dao;

import java.util.List;

import fr.legrain.bdg.api.client.dto.BonlivDTO;

public interface IBonlivBdgService {

 public List<BonlivDTO> selectAll();

 public BonlivDTO findById(int id);

 public void persist(BonlivDTO tiers);

 public void merge(BonlivDTO tiers);

 public void remove(BonlivDTO tiers);
}