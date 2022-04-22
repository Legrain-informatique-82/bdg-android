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
        "idALE",
        "idEntrepot",
        "codeEntrepot",
        "libelleEntrepot",
        "idLot",
        "numLot",
        "libelleLot",
        "emplacement",
        "idUnite",
        "codeUnite",
        "libelleUnite",
        "dateLot",
        "dluo",
        "gestionLot",
        "versionObj",
        "qteSelec",
        "qte1"
})
public class ArticleLotEntrepotDTO {

    @JsonProperty("idALE")
    private Integer idALE;
    @JsonProperty("idEntrepot")
    private Object idEntrepot;
    @JsonProperty("codeEntrepot")
    private Object codeEntrepot;
    @JsonProperty("libelleEntrepot")
    private Object libelleEntrepot;
    @JsonProperty("idLot")
    private Integer idLot;
    @JsonProperty("numLot")
    private String numLot;
    @JsonProperty("libelleLot")
    private String libelleLot;
    @JsonProperty("emplacement")
    private String emplacement;
    @JsonProperty("idUnite")
    private Object idUnite;
    @JsonProperty("codeUnite")
    private String codeUnite;
    @JsonProperty("libelleUnite")
    private Object libelleUnite;
    @JsonProperty("dateLot")
    private String dateLot;
    @JsonProperty("dluo")
    private String dluo;
    @JsonProperty("gestionLot")
    private Boolean gestionLot;
    @JsonProperty("versionObj")
    private Object versionObj;
    @JsonProperty("qteSelec")
    private Object qteSelec;
    @JsonProperty("qte1")
    private Double qte1;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("idALE")
    public Integer getIdALE() {
        return idALE;
    }

    @JsonProperty("idALE")
    public void setIdALE(Integer idALE) {
        this.idALE = idALE;
    }

    @JsonProperty("idEntrepot")
    public Object getIdEntrepot() {
        return idEntrepot;
    }

    @JsonProperty("idEntrepot")
    public void setIdEntrepot(Object idEntrepot) {
        this.idEntrepot = idEntrepot;
    }

    @JsonProperty("codeEntrepot")
    public Object getCodeEntrepot() {
        return codeEntrepot;
    }

    @JsonProperty("codeEntrepot")
    public void setCodeEntrepot(Object codeEntrepot) {
        this.codeEntrepot = codeEntrepot;
    }

    @JsonProperty("libelleEntrepot")
    public Object getLibelleEntrepot() {
        return libelleEntrepot;
    }

    @JsonProperty("libelleEntrepot")
    public void setLibelleEntrepot(Object libelleEntrepot) {
        this.libelleEntrepot = libelleEntrepot;
    }

    @JsonProperty("idLot")
    public Integer getIdLot() {
        return idLot;
    }

    @JsonProperty("idLot")
    public void setIdLot(Integer idLot) {
        this.idLot = idLot;
    }

    @JsonProperty("numLot")
    public String getNumLot() {
        return numLot;
    }

    @JsonProperty("numLot")
    public void setNumLot(String numLot) {
        this.numLot = numLot;
    }

    @JsonProperty("libelleLot")
    public String getLibelleLot() {
        return libelleLot;
    }

    @JsonProperty("libelleLot")
    public void setLibelleLot(String libelleLot) {
        this.libelleLot = libelleLot;
    }

    @JsonProperty("emplacement")
    public String getEmplacement() {
        return emplacement;
    }

    @JsonProperty("emplacement")
    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    @JsonProperty("idUnite")
    public Object getIdUnite() {
        return idUnite;
    }

    @JsonProperty("idUnite")
    public void setIdUnite(Object idUnite) {
        this.idUnite = idUnite;
    }

    @JsonProperty("codeUnite")
    public String getCodeUnite() {
        return codeUnite;
    }

    @JsonProperty("codeUnite")
    public void setCodeUnite(String codeUnite) {
        this.codeUnite = codeUnite;
    }

    @JsonProperty("libelleUnite")
    public Object getLibelleUnite() {
        return libelleUnite;
    }

    @JsonProperty("libelleUnite")
    public void setLibelleUnite(Object libelleUnite) {
        this.libelleUnite = libelleUnite;
    }

    @JsonProperty("dateLot")
    public String getDateLot() {
        return dateLot;
    }

    @JsonProperty("dateLot")
    public void setDateLot(String dateLot) {
        this.dateLot = dateLot;
    }

    @JsonProperty("dluo")
    public String getDluo() {
        return dluo;
    }

    @JsonProperty("dluo")
    public void setDluo(String dluo) {
        this.dluo = dluo;
    }

    @JsonProperty("gestionLot")
    public Boolean getGestionLot() {
        return gestionLot;
    }

    @JsonProperty("gestionLot")
    public void setGestionLot(Boolean gestionLot) {
        this.gestionLot = gestionLot;
    }

    @JsonProperty("versionObj")
    public Object getVersionObj() {
        return versionObj;
    }

    @JsonProperty("versionObj")
    public void setVersionObj(Object versionObj) {
        this.versionObj = versionObj;
    }

    @JsonProperty("qteSelec")
    public Object getQteSelec() {
        return qteSelec;
    }

    @JsonProperty("qteSelec")
    public void setQteSelec(Object qteSelec) {
        this.qteSelec = qteSelec;
    }

    @JsonProperty("qte1")
    public Double getQte1() {
        return qte1;
    }

    @JsonProperty("qte1")
    public void setQte1(Double qte1) {
        this.qte1 = qte1;
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