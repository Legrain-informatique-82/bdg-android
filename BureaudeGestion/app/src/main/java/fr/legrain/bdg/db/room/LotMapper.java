package fr.legrain.bdg.db.room;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import fr.legrain.bdg.api.client.dto.UtilisateurDTO;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LotMapper {

    LotMapper INSTANCE = Mappers.getMapper( LotMapper.class );

//    @Mapping(source = "idUtilisateurBdg", target = "id")
//    LotDTO lotToLotDto(Lot lot);
//
//    @Mapping(source = "id", target = "idUtilisateurBdg")
//    Lot lotDtoToLot(LotDTO lotDto);
}

