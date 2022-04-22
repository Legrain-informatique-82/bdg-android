package fr.legrain.bdg.api.client.dto;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "idCodeBarre",
        "codeBarre",
        "numLot",
        "libelle",
        "dluo",
        "dateLot",
        "idArticle",
        "codeArticle",
        "libelleArticle",
        "codeUnite1",
        "codeUnite2",
        "idUnite1",
        "idUnite2",
        "rapport",
        "liblUnite1",
        "liblUnite2",
        "sens",
        "nbDecimal",
        "termine",
        "matierePremiere",
        "produitFini",
        "virtuel",
        "virtuelUnique",
        "lotConforme",
        "presenceDeNonConformite"
})
public class LotDTO {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("idCodeBarre")
    private Integer idCodeBarre;
    @JsonProperty("codeBarre")
    private Object codeBarre;
    @JsonProperty("numLot")
    private String numLot;
    @JsonProperty("libelle")
    private String libelle;
    @JsonProperty("dluo")
    private Object dluo;
    @JsonProperty("dateLot")
    private Object dateLot;
    @JsonProperty("idArticle")
    private Object idArticle;
    @JsonProperty("codeArticle")
    private String codeArticle;
    @JsonProperty("libelleArticle")
    private String libelleArticle;
    @JsonProperty("codeUnite1")
    private Object codeUnite1;
    @JsonProperty("codeUnite2")
    private Object codeUnite2;
    @JsonProperty("idUnite1")
    private Object idUnite1;
    @JsonProperty("idUnite2")
    private Object idUnite2;
    @JsonProperty("rapport")
    private Object rapport;
    @JsonProperty("liblUnite1")
    private Object liblUnite1;
    @JsonProperty("liblUnite2")
    private Object liblUnite2;
    @JsonProperty("sens")
    private Object sens;
    @JsonProperty("nbDecimal")
    private Object nbDecimal;
    @JsonProperty("termine")
    private Boolean termine;
    @JsonProperty("matierePremiere")
    private Boolean matierePremiere;
    @JsonProperty("produitFini")
    private Boolean produitFini;
    @JsonProperty("virtuel")
    private Boolean virtuel;
    @JsonProperty("virtuelUnique")
    private Boolean virtuelUnique;
    @JsonProperty("lotConforme")
    private Boolean lotConforme;
    @JsonProperty("presenceDeNonConformite")
    private Boolean presenceDeNonConformite;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("idCodeBarre")
    public Integer getIdCodeBarre() {
        return idCodeBarre;
    }

    @JsonProperty("idCodeBarre")
    public void setIdCodeBarre(Integer idCodeBarre) {
        this.idCodeBarre = idCodeBarre;
    }

    @JsonProperty("codeBarre")
    public Object getCodeBarre() {
        return codeBarre;
    }

    @JsonProperty("codeBarre")
    public void setCodeBarre(Object codeBarre) {
        this.codeBarre = codeBarre;
    }

    @JsonProperty("numLot")
    public String getNumLot() {
        return numLot;
    }

    @JsonProperty("numLot")
    public void setNumLot(String numLot) {
        this.numLot = numLot;
    }

    @JsonProperty("libelle")
    public String getLibelle() {
        return libelle;
    }

    @JsonProperty("libelle")
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @JsonProperty("dluo")
    public Object getDluo() {
        return dluo;
    }

    @JsonProperty("dluo")
    public void setDluo(Object dluo) {
        this.dluo = dluo;
    }

    @JsonProperty("dateLot")
    public Object getDateLot() {
        return dateLot;
    }

    @JsonProperty("dateLot")
    public void setDateLot(Object dateLot) {
        this.dateLot = dateLot;
    }

    @JsonProperty("idArticle")
    public Object getIdArticle() {
        return idArticle;
    }

    @JsonProperty("idArticle")
    public void setIdArticle(Object idArticle) {
        this.idArticle = idArticle;
    }

    @JsonProperty("codeArticle")
    public String getCodeArticle() {
        return codeArticle;
    }

    @JsonProperty("codeArticle")
    public void setCodeArticle(String codeArticle) {
        this.codeArticle = codeArticle;
    }

    @JsonProperty("libelleArticle")
    public String getLibelleArticle() {
        return libelleArticle;
    }

    @JsonProperty("libelleArticle")
    public void setLibelleArticle(String libelleArticle) {
        this.libelleArticle = libelleArticle;
    }

    @JsonProperty("codeUnite1")
    public Object getCodeUnite1() {
        return codeUnite1;
    }

    @JsonProperty("codeUnite1")
    public void setCodeUnite1(Object codeUnite1) {
        this.codeUnite1 = codeUnite1;
    }

    @JsonProperty("codeUnite2")
    public Object getCodeUnite2() {
        return codeUnite2;
    }

    @JsonProperty("codeUnite2")
    public void setCodeUnite2(Object codeUnite2) {
        this.codeUnite2 = codeUnite2;
    }

    @JsonProperty("idUnite1")
    public Object getIdUnite1() {
        return idUnite1;
    }

    @JsonProperty("idUnite1")
    public void setIdUnite1(Object idUnite1) {
        this.idUnite1 = idUnite1;
    }

    @JsonProperty("idUnite2")
    public Object getIdUnite2() {
        return idUnite2;
    }

    @JsonProperty("idUnite2")
    public void setIdUnite2(Object idUnite2) {
        this.idUnite2 = idUnite2;
    }

    @JsonProperty("rapport")
    public Object getRapport() {
        return rapport;
    }

    @JsonProperty("rapport")
    public void setRapport(Object rapport) {
        this.rapport = rapport;
    }

    @JsonProperty("liblUnite1")
    public Object getLiblUnite1() {
        return liblUnite1;
    }

    @JsonProperty("liblUnite1")
    public void setLiblUnite1(Object liblUnite1) {
        this.liblUnite1 = liblUnite1;
    }

    @JsonProperty("liblUnite2")
    public Object getLiblUnite2() {
        return liblUnite2;
    }

    @JsonProperty("liblUnite2")
    public void setLiblUnite2(Object liblUnite2) {
        this.liblUnite2 = liblUnite2;
    }

    @JsonProperty("sens")
    public Object getSens() {
        return sens;
    }

    @JsonProperty("sens")
    public void setSens(Object sens) {
        this.sens = sens;
    }

    @JsonProperty("nbDecimal")
    public Object getNbDecimal() {
        return nbDecimal;
    }

    @JsonProperty("nbDecimal")
    public void setNbDecimal(Object nbDecimal) {
        this.nbDecimal = nbDecimal;
    }

    @JsonProperty("termine")
    public Boolean getTermine() {
        return termine;
    }

    @JsonProperty("termine")
    public void setTermine(Boolean termine) {
        this.termine = termine;
    }

    @JsonProperty("matierePremiere")
    public Boolean getMatierePremiere() {
        return matierePremiere;
    }

    @JsonProperty("matierePremiere")
    public void setMatierePremiere(Boolean matierePremiere) {
        this.matierePremiere = matierePremiere;
    }

    @JsonProperty("produitFini")
    public Boolean getProduitFini() {
        return produitFini;
    }

    @JsonProperty("produitFini")
    public void setProduitFini(Boolean produitFini) {
        this.produitFini = produitFini;
    }

    @JsonProperty("virtuel")
    public Boolean getVirtuel() {
        return virtuel;
    }

    @JsonProperty("virtuel")
    public void setVirtuel(Boolean virtuel) {
        this.virtuel = virtuel;
    }

    @JsonProperty("virtuelUnique")
    public Boolean getVirtuelUnique() {
        return virtuelUnique;
    }

    @JsonProperty("virtuelUnique")
    public void setVirtuelUnique(Boolean virtuelUnique) {
        this.virtuelUnique = virtuelUnique;
    }

    @JsonProperty("lotConforme")
    public Boolean getLotConforme() {
        return lotConforme;
    }

    @JsonProperty("lotConforme")
    public void setLotConforme(Boolean lotConforme) {
        this.lotConforme = lotConforme;
    }

    @JsonProperty("presenceDeNonConformite")
    public Boolean getPresenceDeNonConformite() {
        return presenceDeNonConformite;
    }

    @JsonProperty("presenceDeNonConformite")
    public void setPresenceDeNonConformite(Boolean presenceDeNonConformite) {
        this.presenceDeNonConformite = presenceDeNonConformite;
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

