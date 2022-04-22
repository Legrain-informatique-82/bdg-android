package fr.legrain.bdg.db.room;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import fr.legrain.bdg.api.client.dto.AutorisationDossierDTO;
import fr.legrain.bdg.api.client.dto.UtilisateurDTO;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AutorisationDossierMapper {

    AutorisationDossierMapper INSTANCE = Mappers.getMapper( AutorisationDossierMapper.class );

    //@Mapping(source = "id", target = "id")
    AutorisationDossierDTO autorisationDossierToAutorisationDossierDto(AutorisationDossier autorisationDossier);

    //@Mapping(source = "id", target = "id")
    AutorisationDossier autorisationDossierDtoToAutorisationDossier(AutorisationDossierDTO autorisationDossierDto);
}

