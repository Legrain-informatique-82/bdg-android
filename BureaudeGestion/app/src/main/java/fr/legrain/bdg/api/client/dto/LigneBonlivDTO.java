package fr.legrain.bdg.api.client.dto;

import java.io.Serializable;
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
        "idDocument",
        "codeTLigne",
        "idArticle",
        "numLigneLDocument",
        "codeArticle",
        "libLDocument",
        "qteLDocument",
        "qte2LDocument",
        "prixULDocument",
        "tauxTvaLDocument",
        "codeTvaLDocument",
        "codeTTvaLDocument",
        "mtHtLDocument",
        "remTxLDocument",
        "mtTtcLDocument",
        "remHtLDocument",
        "mtHtLApresRemiseGlobaleDocument",
        "mtTtcLApresRemiseGlobaleDocument",
        "ipAcces",
        "u1LDocument",
        "u2LDocument",
        "codeEntrepot",
        "emplacement",
        "dluo",
        "numLot",
        "libelleLot",
        "codeBarre",
        "versionObj",
        "txRemHtDocument",
        "idLDocument"
})
public class LigneBonlivDTO implements Serializable, ILigneDocumentDTO {

    @JsonProperty("idDocument")
    private Object idDocument;
    @JsonProperty("codeTLigne")
    private Object codeTLigne;
    @JsonProperty("idArticle")
    private Object idArticle;
    @JsonProperty("numLigneLDocument")
    private Object numLigneLDocument;
    @JsonProperty("codeArticle")
    private String codeArticle;
    @JsonProperty("libLDocument")
    private Object libLDocument;
    @JsonProperty("qteLDocument")
    private Integer qteLDocument;
    @JsonProperty("qte2LDocument")
    private Integer qte2LDocument;
    @JsonProperty("prixULDocument")
    private Object prixULDocument;
    @JsonProperty("tauxTvaLDocument")
    private Object tauxTvaLDocument;
    @JsonProperty("codeTvaLDocument")
    private Object codeTvaLDocument;
    @JsonProperty("codeTTvaLDocument")
    private Object codeTTvaLDocument;
    @JsonProperty("mtHtLDocument")
    private Object mtHtLDocument;
    @JsonProperty("remTxLDocument")
    private Object remTxLDocument;
    @JsonProperty("mtTtcLDocument")
    private Object mtTtcLDocument;
    @JsonProperty("remHtLDocument")
    private Object remHtLDocument;
    @JsonProperty("mtHtLApresRemiseGlobaleDocument")
    private Object mtHtLApresRemiseGlobaleDocument;
    @JsonProperty("mtTtcLApresRemiseGlobaleDocument")
    private Object mtTtcLApresRemiseGlobaleDocument;
    @JsonProperty("ipAcces")
    private Object ipAcces;
    @JsonProperty("u1LDocument")
    private Object u1LDocument;
    @JsonProperty("u2LDocument")
    private Object u2LDocument;
    @JsonProperty("codeEntrepot")
    private Object codeEntrepot;
    @JsonProperty("emplacement")
    private Object emplacement;
    @JsonProperty("dluo")
    private Object dluo;
    @JsonProperty("numLot")
    private String numLot;
    @JsonProperty("libelleLot")
    private String libelleLot;
    @JsonProperty("codeBarre")
    private String codeBarre;
    @JsonProperty("versionObj")
    private Integer versionObj;
    @JsonProperty("txRemHtDocument")
    private Object txRemHtDocument;
    @JsonProperty("idLDocument")
    private Integer idLDocument;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("idDocument")
    public Object getIdDocument() {
        return idDocument;
    }

    @JsonProperty("idDocument")
    public void setIdDocument(Object idDocument) {
        this.idDocument = idDocument;
    }

    @JsonProperty("codeTLigne")
    public Object getCodeTLigne() {
        return codeTLigne;
    }

    @JsonProperty("codeTLigne")
    public void setCodeTLigne(Object codeTLigne) {
        this.codeTLigne = codeTLigne;
    }

    @JsonProperty("idArticle")
    public Object getIdArticle() {
        return idArticle;
    }

    @JsonProperty("idArticle")
    public void setIdArticle(Object idArticle) {
        this.idArticle = idArticle;
    }

    @JsonProperty("numLigneLDocument")
    public Object getNumLigneLDocument() {
        return numLigneLDocument;
    }

    @JsonProperty("numLigneLDocument")
    public void setNumLigneLDocument(Object numLigneLDocument) {
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
    public Object getLibLDocument() {
        return libLDocument;
    }

    @JsonProperty("libLDocument")
    public void setLibLDocument(Object libLDocument) {
        this.libLDocument = libLDocument;
    }

    @JsonProperty("qteLDocument")
    public Integer getQteLDocument() {
        return qteLDocument;
    }

    @JsonProperty("qteLDocument")
    public void setQteLDocument(Integer qteLDocument) {
        this.qteLDocument = qteLDocument;
    }

    @JsonProperty("qte2LDocument")
    public Integer getQte2LDocument() {
        return qte2LDocument;
    }

    @JsonProperty("qte2LDocument")
    public void setQte2LDocument(Integer qte2LDocument) {
        this.qte2LDocument = qte2LDocument;
    }

    @JsonProperty("prixULDocument")
    public Object getPrixULDocument() {
        return prixULDocument;
    }

    @JsonProperty("prixULDocument")
    public void setPrixULDocument(Object prixULDocument) {
        this.prixULDocument = prixULDocument;
    }

    @JsonProperty("tauxTvaLDocument")
    public Object getTauxTvaLDocument() {
        return tauxTvaLDocument;
    }

    @JsonProperty("tauxTvaLDocument")
    public void setTauxTvaLDocument(Object tauxTvaLDocument) {
        this.tauxTvaLDocument = tauxTvaLDocument;
    }

    @JsonProperty("codeTvaLDocument")
    public Object getCodeTvaLDocument() {
        return codeTvaLDocument;
    }

    @JsonProperty("codeTvaLDocument")
    public void setCodeTvaLDocument(Object codeTvaLDocument) {
        this.codeTvaLDocument = codeTvaLDocument;
    }

    @JsonProperty("codeTTvaLDocument")
    public Object getCodeTTvaLDocument() {
        return codeTTvaLDocument;
    }

    @JsonProperty("codeTTvaLDocument")
    public void setCodeTTvaLDocument(Object codeTTvaLDocument) {
        this.codeTTvaLDocument = codeTTvaLDocument;
    }

    @JsonProperty("mtHtLDocument")
    public Object getMtHtLDocument() {
        return mtHtLDocument;
    }

    @JsonProperty("mtHtLDocument")
    public void setMtHtLDocument(Object mtHtLDocument) {
        this.mtHtLDocument = mtHtLDocument;
    }

    @JsonProperty("remTxLDocument")
    public Object getRemTxLDocument() {
        return remTxLDocument;
    }

    @JsonProperty("remTxLDocument")
    public void setRemTxLDocument(Object remTxLDocument) {
        this.remTxLDocument = remTxLDocument;
    }

    @JsonProperty("mtTtcLDocument")
    public Object getMtTtcLDocument() {
        return mtTtcLDocument;
    }

    @JsonProperty("mtTtcLDocument")
    public void setMtTtcLDocument(Object mtTtcLDocument) {
        this.mtTtcLDocument = mtTtcLDocument;
    }

    @JsonProperty("remHtLDocument")
    public Object getRemHtLDocument() {
        return remHtLDocument;
    }

    @JsonProperty("remHtLDocument")
    public void setRemHtLDocument(Object remHtLDocument) {
        this.remHtLDocument = remHtLDocument;
    }

    @JsonProperty("mtHtLApresRemiseGlobaleDocument")
    public Object getMtHtLApresRemiseGlobaleDocument() {
        return mtHtLApresRemiseGlobaleDocument;
    }

    @JsonProperty("mtHtLApresRemiseGlobaleDocument")
    public void setMtHtLApresRemiseGlobaleDocument(Object mtHtLApresRemiseGlobaleDocument) {
        this.mtHtLApresRemiseGlobaleDocument = mtHtLApresRemiseGlobaleDocument;
    }

    @JsonProperty("mtTtcLApresRemiseGlobaleDocument")
    public Object getMtTtcLApresRemiseGlobaleDocument() {
        return mtTtcLApresRemiseGlobaleDocument;
    }

    @JsonProperty("mtTtcLApresRemiseGlobaleDocument")
    public void setMtTtcLApresRemiseGlobaleDocument(Object mtTtcLApresRemiseGlobaleDocument) {
        this.mtTtcLApresRemiseGlobaleDocument = mtTtcLApresRemiseGlobaleDocument;
    }

    @JsonProperty("ipAcces")
    public Object getIpAcces() {
        return ipAcces;
    }

    @JsonProperty("ipAcces")
    public void setIpAcces(Object ipAcces) {
        this.ipAcces = ipAcces;
    }

    @JsonProperty("u1LDocument")
    public Object getU1LDocument() {
        return u1LDocument;
    }

    @JsonProperty("u1LDocument")
    public void setU1LDocument(Object u1LDocument) {
        this.u1LDocument = u1LDocument;
    }

    @JsonProperty("u2LDocument")
    public Object getU2LDocument() {
        return u2LDocument;
    }

    @JsonProperty("u2LDocument")
    public void setU2LDocument(Object u2LDocument) {
        this.u2LDocument = u2LDocument;
    }

    @JsonProperty("codeEntrepot")
    public Object getCodeEntrepot() {
        return codeEntrepot;
    }

    @JsonProperty("codeEntrepot")
    public void setCodeEntrepot(Object codeEntrepot) {
        this.codeEntrepot = codeEntrepot;
    }

    @JsonProperty("emplacement")
    public Object getEmplacement() {
        return emplacement;
    }

    @JsonProperty("emplacement")
    public void setEmplacement(Object emplacement) {
        this.emplacement = emplacement;
    }

    @JsonProperty("dluo")
    public Object getDluo() {
        return dluo;
    }

    @JsonProperty("dluo")
    public void setDluo(Object dluo) {
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

    @JsonProperty("versionObj")
    public Integer getVersionObj() {
        return versionObj;
    }

    @JsonProperty("versionObj")
    public void setVersionObj(Integer versionObj) {
        this.versionObj = versionObj;
    }

    @JsonProperty("txRemHtDocument")
    public Object getTxRemHtDocument() {
        return txRemHtDocument;
    }

    @JsonProperty("txRemHtDocument")
    public void setTxRemHtDocument(Object txRemHtDocument) {
        this.txRemHtDocument = txRemHtDocument;
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

