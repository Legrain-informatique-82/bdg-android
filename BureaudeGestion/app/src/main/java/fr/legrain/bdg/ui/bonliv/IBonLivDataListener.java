package fr.legrain.bdg.ui.bonliv;


import fr.legrain.bdg.api.client.dto.LigneBonlivDTO;
import fr.legrain.bdg.api.client.dto.TiersDTO;

public interface IBonLivDataListener {

    public void valdeTiers(TiersDTO entreprise);
    public void ajouteLigne(LigneBonlivDTO ligneBLFlash);
    public void termineBL();

}