package fr.legrain.bdg.db.room;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import fr.legrain.bdg.api.client.dto.ArticleDTO;
import fr.legrain.bdg.api.client.dto.TiersDTO;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ArticleMapper {

    ArticleMapper INSTANCE = Mappers.getMapper( ArticleMapper.class );

    @Mapping(source = "idArticleBdg", target = "id")
    ArticleDTO articleToaAticleDto(Article article);

    @Mapping(source = "id", target = "idArticleBdg")
    Article articleDtoToArticle(ArticleDTO articleDto);
}

