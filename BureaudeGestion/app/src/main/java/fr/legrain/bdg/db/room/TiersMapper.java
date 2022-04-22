package fr.legrain.bdg.db.room;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import fr.legrain.bdg.api.client.dto.TiersDTO;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TiersMapper {

    TiersMapper INSTANCE = Mappers.getMapper( TiersMapper.class );

    @Mapping(source = "idTiersBdg", target = "id")
    TiersDTO tiersToTiersDto(Tiers tiers);

    @Mapping(source = "id", target = "idTiersBdg")
    Tiers tiersDtoToTiers(TiersDTO tiersDto);
}

