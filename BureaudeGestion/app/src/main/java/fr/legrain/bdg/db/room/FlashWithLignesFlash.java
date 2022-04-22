package fr.legrain.bdg.db.room;

import java.util.List;

import androidx.room.Embedded;
import androidx.room.Relation;

public class FlashWithLignesFlash {
    @Embedded
    public Flash flash;
    @Relation(
         parentColumn = "id", //flash
         entityColumn = "id" //ligne flash
    )
    public List<LigneFlash> lignes;
}
