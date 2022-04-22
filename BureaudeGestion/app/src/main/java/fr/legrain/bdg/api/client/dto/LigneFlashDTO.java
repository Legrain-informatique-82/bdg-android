package fr.legrain.bdg.api.client.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "idDocument",
        "idArticle",
        "numLigneLDocument",
        "codeArticle",
        "libLDocument",
        "qteLDocument",
        "qte2LDocument",
        "ipAcces",
        "u1LDocument",
        "u2LDocument",
        "codeEntrepot",
        "emplacement",
        "dluo",
        "numLot",
        "libelleLot",
        "codeBarre",
        "codeBarreLu",
        "versionObj",
        "idLDocument",
        "codeDocument",
        "codeEtat"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class LigneFlashDTO implements Serializable, ILigneDocumentDTO {

    @JsonProperty("idDocument")
    private Integer idDocument;
    @JsonProperty("codeDocument")
    private String codeDocument;
    @JsonProperty("codeEtat")
    private String codeEtat;
    @JsonProperty("idArticle")
    private Integer idArticle;
    @JsonProperty("numLigneLDocument")
    private Integer numLigneLDocument;
    @JsonProperty("codeArticle")
    private String codeArticle;
    @JsonProperty("libLDocument")
    private String libLDocument;
    @JsonProperty("qteLDocument")
    private BigDecimal qteLDocument;
    @JsonProperty("qte2LDocument")
    private BigDecimal qte2LDocument;
    @JsonProperty("ipAcces")
    private String ipAcces;
    @JsonProperty("u1LDocument")
    private String u1LDocument;
    @JsonProperty("u2LDocument")
    private String u2LDocument;
    @JsonProperty("codeEntrepot")
    private String codeEntrepot;
    @JsonProperty("emplacement")
    private String emplacement;
    @JsonProperty("dluo")
    private Date dluo;
    @JsonProperty("numLot")
    private String numLot;
    @JsonProperty("libelleLot")
    private String libelleLot;
    @JsonProperty("codeBarre")
    private String codeBarre;
    @JsonProperty("codeBarreLu")
    private String codeBarreLu;
    @JsonProperty("versionObj")
    private Integer versionObj;
    @JsonProperty("idLDocument")
    private Integer idLDocument;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("idDocument")
    public Object getIdDocument() {
        return idDocument;
    }

    @JsonProperty("idDocument")
    public void setIdDocument(Integer idDocument) {
        this.idDocument = idDocument;
    }

    @JsonProperty("idArticle")
    public Integer getIdArticle() {
        return idArticle;
    }

    @JsonProperty("idArticle")
    public void setIdArticle(Integer idArticle) {
        this.idArticle = idArticle;
    }

    @JsonProperty("numLigneLDocument")
    public Integer getNumLigneLDocument() {
        return numLigneLDocument;
    }

    @JsonProperty("numLigneLDocument")
    public void setNumLigneLDocument(Integer numLigneLDocument) {
        this.numLigneLDocument = numLigneLDocument;
    }

    @JsonProperty("codeArticle")
    public String getCodeArticle() {
        return codeArticle;
    }

    @JsonProperty("codeArticle")
    public void setCodeArticle(String codeArticle) {
        this.codeArticle = codeArticle;
    }

    @JsonProperty("libLDocument")
    public String getLibLDocument() {
        return libLDocument;
    }

    @JsonProperty("libLDocument")
    public void setLibLDocument(String libLDocument) {
        this.libLDocument = libLDocument;
    }

    @JsonProperty("qteLDocument")
    public BigDecimal getQteLDocument() {
        return qteLDocument;
    }

    @JsonProperty("qteLDocument")
    public void setQteLDocument(BigDecimal qteLDocument) {
        this.qteLDocument = qteLDocument;
    }

    @JsonProperty("qte2LDocument")
    public BigDecimal getQte2LDocument() {
        return qte2LDocument;
    }

    @JsonProperty("qte2LDocument")
    public void setQte2LDocument(BigDecimal qte2LDocument) {
        this.qte2LDocument = qte2LDocument;
    }

    @JsonProperty("ipAcces")
    public String getIpAcces() {
        return ipAcces;
    }

    @JsonProperty("ipAcces")
    public void setIpAcces(String ipAcces) {
        this.ipAcces = ipAcces;
    }

    @JsonProperty("u1LDocument")
    public String getU1LDocument() {
        return u1LDocument;
    }

    @JsonProperty("u1LDocument")
    public void setU1LDocument(String u1LDocument) {
        this.u1LDocument = u1LDocument;
    }

    @JsonProperty("u2LDocument")
    public String getU2LDocument() {
        return u2LDocument;
    }

    @JsonProperty("u2LDocument")
    public void setU2LDocument(String u2LDocument) {
        this.u2LDocument = u2LDocument;
    }

    @JsonProperty("codeEntrepot")
    public String getCodeEntrepot() {
        return codeEntrepot;
    }

    @JsonProperty("codeEntrepot")
    public void setCodeEntrepot(String codeEntrepot) {
        this.codeEntrepot = codeEntrepot;
    }

    @JsonProperty("emplacement")
    public String getEmplacement() {
        return emplacement;
    }

    @JsonProperty("emplacement")
    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    @JsonProperty("dluo")
    public Date getDluo() {
        return dluo;
    }

    @JsonProperty("dluo")
    public void setDluo(Date dluo) {
        this.dluo = dluo;
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

    @JsonProperty("codeBarre")
    public String getCodeBarre() {
        return codeBarre;
    }

    @JsonProperty("codeBarre")
    public void setCodeBarre(String codeBarre) {
        this.codeBarre = codeBarre;
    }

    @JsonProperty("codeBarreLu")
    public String getCodeBarreLu() {
        return codeBarreLu;
    }

    @JsonProperty("codeBarreLu")
    public void setCodeBarreLu(String codeBarreLu) {
        this.codeBarreLu = codeBarreLu;
    }

    @JsonProperty("versionObj")
    public Integer getVersionObj() {
        return versionObj;
    }

    @JsonProperty("versionObj")
    public void setVersionObj(Integer versionObj) {
        this.versionObj = versionObj;
    }

    @JsonProperty("idLDocument")
    public Integer getIdLDocument() {
        return idLDocument;
    }

    @JsonProperty("idLDocument")
    public void setIdLDocument(Integer idLDocument) {
        this.idLDocument = idLDocument;
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

