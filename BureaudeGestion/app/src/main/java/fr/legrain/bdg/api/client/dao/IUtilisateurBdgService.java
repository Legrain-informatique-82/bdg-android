package fr.legrain.bdg.api.client.dao;

import java.util.List;

import fr.legrain.bdg.api.client.dto.AutorisationDossierDTO;
import fr.legrain.bdg.api.client.dto.UtilisateurDTO;
import io.reactivex.Observable;

public interface IUtilisateurBdgService {

 public List<UtilisateurDTO> selectAllSync();

 public Observable<UtilisateurDTO[]> selectAll();

 public UtilisateurDTO findById(int id);

 public void persist(UtilisateurDTO utilisateur);

 public void merge(UtilisateurDTO utilisateur);

 public void remove(UtilisateurDTO utilisateur);

 public AutorisationDossierDTO findAutorisationDossier();

 public UtilisateurDTO authenticate(String loginForm, String pwdForm);
}