package fr.legrain.bdg.db.room;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import fr.legrain.bdg.api.client.dto.TiersDTO;
import fr.legrain.bdg.api.client.dto.UtilisateurDTO;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UtilisateurMapper {

    UtilisateurMapper INSTANCE = Mappers.getMapper( UtilisateurMapper.class );

    @Mapping(source = "idUtilisateurBdg", target = "id")
    UtilisateurDTO utilisateurToUtilisateurDto(Utilisateur utilisateur);

    @Mapping(source = "id", target = "idUtilisateurBdg")
    Utilisateur utilisateurDtoToUtilisateur(UtilisateurDTO utilisateurDto);
}

