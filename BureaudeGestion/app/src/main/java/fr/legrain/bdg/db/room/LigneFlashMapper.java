package fr.legrain.bdg.db.room;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import fr.legrain.bdg.api.client.dto.FlashDTO;
import fr.legrain.bdg.api.client.dto.LigneFlashDTO;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LigneFlashMapper {

    LigneFlashMapper INSTANCE = Mappers.getMapper( LigneFlashMapper.class );

    //@Mapping(source = "idTiers", target = "id")
    LigneFlashDTO ligneFlashToLigneFlashDto(LigneFlash tiers);

    //@Mapping(source = "id", target = "idTiers")
    LigneFlash ligneFlashDtoToLigneFlash(LigneFlashDTO tiersDto);
}

