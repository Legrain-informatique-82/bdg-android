package fr.legrain.bdg.db.room;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import fr.legrain.bdg.api.client.dto.FlashDTO;
import fr.legrain.bdg.api.client.dto.TiersDTO;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FlashMapper {

    FlashMapper INSTANCE = Mappers.getMapper( FlashMapper.class );

    @Mapping(source = "idFlashBdg", target = "id")
    FlashDTO flashToFlashDto(Flash flash);

    @Mapping(source = "id", target = "idFlashBdg")
    Flash flashDtoToFlash(FlashDTO flashDto);
}

