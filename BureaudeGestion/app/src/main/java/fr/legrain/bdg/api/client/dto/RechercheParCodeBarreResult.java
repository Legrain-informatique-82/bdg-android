package fr.legrain.bdg.api.client.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "taArticleDTO",
        "taLotSelectionneDTO",
        "listeLotDisponible"
})
public class RechercheParCodeBarreResult {

    @JsonProperty("taArticleDTO")
    private ArticleDTO taArticleDTO;
    @JsonProperty("taLotSelectionneDTO")
    private ArticleLotEntrepotDTO taLotSelectionneDTO;
    @JsonProperty("listeLotDisponible")
    private List<ArticleLotEntrepotDTO> listeLotDisponible = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("taArticleDTO")
    public ArticleDTO getTaArticleDTO() {
        return taArticleDTO;
    }

    @JsonProperty("taArticleDTO")
    public void setTaArticleDTO(ArticleDTO taArticleDTO) {
        this.taArticleDTO = taArticleDTO;
    }

    @JsonProperty("taLotSelectionneDTO")
    public ArticleLotEntrepotDTO getTaLotSelectionneDTO() {
        return taLotSelectionneDTO;
    }

    @JsonProperty("taLotSelectionneDTO")
    public void setTaLotSelectionneDTO(ArticleLotEntrepotDTO taLotSelectionneDTO) {
        this.taLotSelectionneDTO = taLotSelectionneDTO;
    }

    @JsonProperty("listeLotDisponible")
    public List<ArticleLotEntrepotDTO> getListeLotDisponible() {
        return listeLotDisponible;
    }

    @JsonProperty("listeLotDisponible")
    public void setListeLotDisponible(List<ArticleLotEntrepotDTO> listeLotDisponible) {
        this.listeLotDisponible = listeLotDisponible;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}