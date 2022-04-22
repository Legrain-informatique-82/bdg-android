package fr.legrain.bdg.api.client.dao;

import java.util.List;

import fr.legrain.bdg.api.client.dto.FlashDTO;

public interface IFlashBdgService {

 public List<FlashDTO> selectAll();

 public List<FlashDTO> selectAll(String codeTypeDocument);

 public FlashDTO findById(int id);

 public FlashDTO persist(FlashDTO tiers);

 public FlashDTO merge(FlashDTO tiers);

 public void remove(FlashDTO tiers);
}