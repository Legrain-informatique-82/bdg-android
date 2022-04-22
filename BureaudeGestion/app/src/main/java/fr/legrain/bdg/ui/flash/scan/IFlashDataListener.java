package fr.legrain.bdg.ui.flash.scan;


import fr.legrain.bdg.api.client.dto.LigneFlashDTO;
import fr.legrain.bdg.db.room.Tiers;
import fr.legrain.bdg.db.room.Utilisateur;
import fr.legrain.bdg.data.model.TDoc;

public interface IFlashDataListener {

    public void valdeTiers(Tiers entreprise);
    public void valdeTiers(Tiers entreprise, TDoc typeDoc, Utilisateur utilisateur);
    public void ajouteLigne(LigneFlashDTO ligneFlash);
    public void termineFlash();

}