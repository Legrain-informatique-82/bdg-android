package fr.legrain.bdg.api.client.dto;


import java.io.Serializable;
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
        "id",
        "codeArticle",
        "libellecArticle",
        "libellelArticle",
        "numcptArticle",
        "diversArticle",
        "idTTva",
        "stockMinArticle",
        "idFamille",
        "idPrix",
        "prixPrix",
        "prixttcPrix",
        "longueur",
        "largeur",
        "hauteur",
        "poids",
        "idUnite",
        "codeUnite",
        "codeFamille",
        "libcFamille",
        "idTva",
        "codeTva",
        "tauxTva",
        "numcptTva",
        "codeTTva",
        "libTTva",
        "idRapportUnite",
        "idUnite2",
        "codeUnite2",
        "idUniteReference",
        "codeUniteReference",
        "rapport",
        "nbDecimal",
        "actif",
        "sens",
        "codeTitreTransport",
        "qteTitreTransport",
        "commentaireArticle",
        "paramDluo",
        "matierePremiere",
        "produitFini",
        "gestionLot",
        "utiliseDlc",
        "codeBarre",
        "versionObj",
        "codeUniteStock",
        "idUniteStock",
        "codeMarqueArticle",
        "idMarqueArticle",
        "autoAlimenteFournisseurs",
        "nbArticle",
        "codeTTarif",
        "libTTarif",
        "sensTTarif",
        "pourcentageTTarif",
        "valeurTTarif",
        "accepte",
        "prixPrixCalc",
        "prixttcPrixCalc",
        "coef",
        "idPrixCalc",
        "existant",
        "codeTiers",
        "taFamilleDTO",
        "taFamilleDTOs",
        "taFournisseurDTOs",
        "swtUnite",
        "swtPrix",
        "swtRefPrix",
        "swtRapportUnite",
        "listePrix",
        "listeRapportUnite"
})
public class ArticleDTO implements Serializable {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("codeArticle")
    private String codeArticle;
    @JsonProperty("libellecArticle")
    private String libellecArticle;
    @JsonProperty("libellelArticle")
    private String libellelArticle;
    @JsonProperty("numcptArticle")
    private String numcptArticle;
    @JsonProperty("diversArticle")
    private Object diversArticle;
    @JsonProperty("idTTva")
    private Object idTTva;
    @JsonProperty("stockMinArticle")
    private Double stockMinArticle;
    @JsonProperty("idFamille")
    private Object idFamille;
    @JsonProperty("idPrix")
    private Object idPrix;
    @JsonProperty("prixPrix")
    private Double prixPrix;
    @JsonProperty("prixttcPrix")
    private Double prixttcPrix;
    @JsonProperty("longueur")
    private Object longueur;
    @JsonProperty("largeur")
    private Object largeur;
    @JsonProperty("hauteur")
    private Object hauteur;
    @JsonProperty("poids")
    private Object poids;
    @JsonProperty("idUnite")
    private Object idUnite;
    @JsonProperty("codeUnite")
    private String codeUnite;
    @JsonProperty("codeFamille")
    private String codeFamille;
    @JsonProperty("libcFamille")
    private String libcFamille;
    @JsonProperty("idTva")
    private Object idTva;
    @JsonProperty("codeTva")
    private String codeTva;
    @JsonProperty("tauxTva")
    private Double tauxTva;
    @JsonProperty("numcptTva")
    private String numcptTva;
    @JsonProperty("codeTTva")
    private String codeTTva;
    @JsonProperty("libTTva")
    private String libTTva;
    @JsonProperty("idRapportUnite")
    private Object idRapportUnite;
    @JsonProperty("idUnite2")
    private Object idUnite2;
    @JsonProperty("codeUnite2")
    private Object codeUnite2;
    @JsonProperty("idUniteReference")
    private Object idUniteReference;
    @JsonProperty("codeUniteReference")
    private Object codeUniteReference;
    @JsonProperty("rapport")
    private Object rapport;
    @JsonProperty("nbDecimal")
    private Object nbDecimal;
    @JsonProperty("actif")
    private Boolean actif;
    @JsonProperty("sens")
    private Boolean sens;
    @JsonProperty("codeTitreTransport")
    private Object codeTitreTransport;
    @JsonProperty("qteTitreTransport")
    private Object qteTitreTransport;
    @JsonProperty("commentaireArticle")
    private Object commentaireArticle;
    @JsonProperty("paramDluo")
    private String paramDluo;
    @JsonProperty("matierePremiere")
    private Boolean matierePremiere;
    @JsonProperty("produitFini")
    private Boolean produitFini;
    @JsonProperty("gestionLot")
    private Boolean gestionLot;
    @JsonProperty("utiliseDlc")
    private Boolean utiliseDlc;
    @JsonProperty("codeBarre")
    private String codeBarre;
    @JsonProperty("versionObj")
    private Integer versionObj;
    @JsonProperty("codeUniteStock")
    private Object codeUniteStock;
    @JsonProperty("idUniteStock")
    private Object idUniteStock;
    @JsonProperty("codeMarqueArticle")
    private Object codeMarqueArticle;
    @JsonProperty("idMarqueArticle")
    private Object idMarqueArticle;
    @JsonProperty("autoAlimenteFournisseurs")
    private Boolean autoAlimenteFournisseurs;
    @JsonProperty("nbArticle")
    private Integer nbArticle;
    @JsonProperty("codeTTarif")
    private Object codeTTarif;
    @JsonProperty("libTTarif")
    private Object libTTarif;
    @JsonProperty("sensTTarif")
    private Boolean sensTTarif;
    @JsonProperty("pourcentageTTarif")
    private Boolean pourcentageTTarif;
    @JsonProperty("valeurTTarif")
    private Object valeurTTarif;
    @JsonProperty("accepte")
    private Object accepte;
    @JsonProperty("prixPrixCalc")
    private Object prixPrixCalc;
    @JsonProperty("prixttcPrixCalc")
    private Object prixttcPrixCalc;
    @JsonProperty("coef")
    private Object coef;
    @JsonProperty("idPrixCalc")
    private Object idPrixCalc;
    @JsonProperty("existant")
    private Boolean existant;
    @JsonProperty("codeTiers")
    private Object codeTiers;
    @JsonProperty("taFamilleDTO")
    private Object taFamilleDTO;
    @JsonProperty("taFamilleDTOs")
    private Object taFamilleDTOs;
    @JsonProperty("taFournisseurDTOs")
    private Object taFournisseurDTOs;
    //@JsonProperty("swtUnite")
//private SwtUnite swtUnite;
//@JsonProperty("swtPrix")
//private SwtPrix swtPrix;
//@JsonProperty("swtRefPrix")
//private SwtRefPrix swtRefPrix;
//@JsonProperty("swtRapportUnite")
//private SwtRapportUnite swtRapportUnite;
    @JsonProperty("listePrix")
    private List<Object> listePrix = null;
    @JsonProperty("listeRapportUnite")
    private List<Object> listeRapportUnite = null;
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

    @JsonProperty("codeArticle")
    public String getCodeArticle() {
        return codeArticle;
    }

    @JsonProperty("codeArticle")
    public void setCodeArticle(String codeArticle) {
        this.codeArticle = codeArticle;
    }

    @JsonProperty("libellecArticle")
    public String getLibellecArticle() {
        return libellecArticle;
    }

    @JsonProperty("libellecArticle")
    public void setLibellecArticle(String libellecArticle) {
        this.libellecArticle = libellecArticle;
    }

    @JsonProperty("libellelArticle")
    public String getLibellelArticle() {
        return libellelArticle;
    }

    @JsonProperty("libellelArticle")
    public void setLibellelArticle(String libellelArticle) {
        this.libellelArticle = libellelArticle;
    }

    @JsonProperty("numcptArticle")
    public String getNumcptArticle() {
        return numcptArticle;
    }

    @JsonProperty("numcptArticle")
    public void setNumcptArticle(String numcptArticle) {
        this.numcptArticle = numcptArticle;
    }

    @JsonProperty("diversArticle")
    public Object getDiversArticle() {
        return diversArticle;
    }

    @JsonProperty("diversArticle")
    public void setDiversArticle(Object diversArticle) {
        this.diversArticle = diversArticle;
    }

    @JsonProperty("idTTva")
    public Object getIdTTva() {
        return idTTva;
    }

    @JsonProperty("idTTva")
    public void setIdTTva(Object idTTva) {
        this.idTTva = idTTva;
    }

    @JsonProperty("stockMinArticle")
    public Double getStockMinArticle() {
        return stockMinArticle;
    }

    @JsonProperty("stockMinArticle")
    public void setStockMinArticle(Double stockMinArticle) {
        this.stockMinArticle = stockMinArticle;
    }

    @JsonProperty("idFamille")
    public Object getIdFamille() {
        return idFamille;
    }

    @JsonProperty("idFamille")
    public void setIdFamille(Object idFamille) {
        this.idFamille = idFamille;
    }

    @JsonProperty("idPrix")
    public Object getIdPrix() {
        return idPrix;
    }

    @JsonProperty("idPrix")
    public void setIdPrix(Object idPrix) {
        this.idPrix = idPrix;
    }

    @JsonProperty("prixPrix")
    public Double getPrixPrix() {
        return prixPrix;
    }

    @JsonProperty("prixPrix")
    public void setPrixPrix(Double prixPrix) {
        this.prixPrix = prixPrix;
    }

    @JsonProperty("prixttcPrix")
    public Double getPrixttcPrix() {
        return prixttcPrix;
    }

    @JsonProperty("prixttcPrix")
    public void setPrixttcPrix(Double prixttcPrix) {
        this.prixttcPrix = prixttcPrix;
    }

    @JsonProperty("longueur")
    public Object getLongueur() {
        return longueur;
    }

    @JsonProperty("longueur")
    public void setLongueur(Object longueur) {
        this.longueur = longueur;
    }

    @JsonProperty("largeur")
    public Object getLargeur() {
        return largeur;
    }

    @JsonProperty("largeur")
    public void setLargeur(Object largeur) {
        this.largeur = largeur;
    }

    @JsonProperty("hauteur")
    public Object getHauteur() {
        return hauteur;
    }

    @JsonProperty("hauteur")
    public void setHauteur(Object hauteur) {
        this.hauteur = hauteur;
    }

    @JsonProperty("poids")
    public Object getPoids() {
        return poids;
    }

    @JsonProperty("poids")
    public void setPoids(Object poids) {
        this.poids = poids;
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

    @JsonProperty("codeFamille")
    public String getCodeFamille() {
        return codeFamille;
    }

    @JsonProperty("codeFamille")
    public void setCodeFamille(String codeFamille) {
        this.codeFamille = codeFamille;
    }

    @JsonProperty("libcFamille")
    public String getLibcFamille() {
        return libcFamille;
    }

    @JsonProperty("libcFamille")
    public void setLibcFamille(String libcFamille) {
        this.libcFamille = libcFamille;
    }

    @JsonProperty("idTva")
    public Object getIdTva() {
        return idTva;
    }

    @JsonProperty("idTva")
    public void setIdTva(Object idTva) {
        this.idTva = idTva;
    }

    @JsonProperty("codeTva")
    public String getCodeTva() {
        return codeTva;
    }

    @JsonProperty("codeTva")
    public void setCodeTva(String codeTva) {
        this.codeTva = codeTva;
    }

    @JsonProperty("tauxTva")
    public Double getTauxTva() {
        return tauxTva;
    }

    @JsonProperty("tauxTva")
    public void setTauxTva(Double tauxTva) {
        this.tauxTva = tauxTva;
    }

    @JsonProperty("numcptTva")
    public String getNumcptTva() {
        return numcptTva;
    }

    @JsonProperty("numcptTva")
    public void setNumcptTva(String numcptTva) {
        this.numcptTva = numcptTva;
    }

    @JsonProperty("codeTTva")
    public String getCodeTTva() {
        return codeTTva;
    }

    @JsonProperty("codeTTva")
    public void setCodeTTva(String codeTTva) {
        this.codeTTva = codeTTva;
    }

    @JsonProperty("libTTva")
    public String getLibTTva() {
        return libTTva;
    }

    @JsonProperty("libTTva")
    public void setLibTTva(String libTTva) {
        this.libTTva = libTTva;
    }

    @JsonProperty("idRapportUnite")
    public Object getIdRapportUnite() {
        return idRapportUnite;
    }

    @JsonProperty("idRapportUnite")
    public void setIdRapportUnite(Object idRapportUnite) {
        this.idRapportUnite = idRapportUnite;
    }

    @JsonProperty("idUnite2")
    public Object getIdUnite2() {
        return idUnite2;
    }

    @JsonProperty("idUnite2")
    public void setIdUnite2(Object idUnite2) {
        this.idUnite2 = idUnite2;
    }

    @JsonProperty("codeUnite2")
    public Object getCodeUnite2() {
        return codeUnite2;
    }

    @JsonProperty("codeUnite2")
    public void setCodeUnite2(Object codeUnite2) {
        this.codeUnite2 = codeUnite2;
    }

    @JsonProperty("idUniteReference")
    public Object getIdUniteReference() {
        return idUniteReference;
    }

    @JsonProperty("idUniteReference")
    public void setIdUniteReference(Object idUniteReference) {
        this.idUniteReference = idUniteReference;
    }

    @JsonProperty("codeUniteReference")
    public Object getCodeUniteReference() {
        return codeUniteReference;
    }

    @JsonProperty("codeUniteReference")
    public void setCodeUniteReference(Object codeUniteReference) {
        this.codeUniteReference = codeUniteReference;
    }

    @JsonProperty("rapport")
    public Object getRapport() {
        return rapport;
    }

    @JsonProperty("rapport")
    public void setRapport(Object rapport) {
        this.rapport = rapport;
    }

    @JsonProperty("nbDecimal")
    public Object getNbDecimal() {
        return nbDecimal;
    }

    @JsonProperty("nbDecimal")
    public void setNbDecimal(Object nbDecimal) {
        this.nbDecimal = nbDecimal;
    }

    @JsonProperty("actif")
    public Boolean getActif() {
        return actif;
    }

    @JsonProperty("actif")
    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    @JsonProperty("sens")
    public Boolean getSens() {
        return sens;
    }

    @JsonProperty("sens")
    public void setSens(Boolean sens) {
        this.sens = sens;
    }

    @JsonProperty("codeTitreTransport")
    public Object getCodeTitreTransport() {
        return codeTitreTransport;
    }

    @JsonProperty("codeTitreTransport")
    public void setCodeTitreTransport(Object codeTitreTransport) {
        this.codeTitreTransport = codeTitreTransport;
    }

    @JsonProperty("qteTitreTransport")
    public Object getQteTitreTransport() {
        return qteTitreTransport;
    }

    @JsonProperty("qteTitreTransport")
    public void setQteTitreTransport(Object qteTitreTransport) {
        this.qteTitreTransport = qteTitreTransport;
    }

    @JsonProperty("commentaireArticle")
    public Object getCommentaireArticle() {
        return commentaireArticle;
    }

    @JsonProperty("commentaireArticle")
    public void setCommentaireArticle(Object commentaireArticle) {
        this.commentaireArticle = commentaireArticle;
    }

    @JsonProperty("paramDluo")
    public String getParamDluo() {
        return paramDluo;
    }

    @JsonProperty("paramDluo")
    public void setParamDluo(String paramDluo) {
        this.paramDluo = paramDluo;
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

    @JsonProperty("gestionLot")
    public Boolean getGestionLot() {
        return gestionLot;
    }

    @JsonProperty("gestionLot")
    public void setGestionLot(Boolean gestionLot) {
        this.gestionLot = gestionLot;
    }

    @JsonProperty("utiliseDlc")
    public Boolean getUtiliseDlc() {
        return utiliseDlc;
    }

    @JsonProperty("utiliseDlc")
    public void setUtiliseDlc(Boolean utiliseDlc) {
        this.utiliseDlc = utiliseDlc;
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

    @JsonProperty("codeUniteStock")
    public Object getCodeUniteStock() {
        return codeUniteStock;
    }

    @JsonProperty("codeUniteStock")
    public void setCodeUniteStock(Object codeUniteStock) {
        this.codeUniteStock = codeUniteStock;
    }

    @JsonProperty("idUniteStock")
    public Object getIdUniteStock() {
        return idUniteStock;
    }

    @JsonProperty("idUniteStock")
    public void setIdUniteStock(Object idUniteStock) {
        this.idUniteStock = idUniteStock;
    }

    @JsonProperty("codeMarqueArticle")
    public Object getCodeMarqueArticle() {
        return codeMarqueArticle;
    }

    @JsonProperty("codeMarqueArticle")
    public void setCodeMarqueArticle(Object codeMarqueArticle) {
        this.codeMarqueArticle = codeMarqueArticle;
    }

    @JsonProperty("idMarqueArticle")
    public Object getIdMarqueArticle() {
        return idMarqueArticle;
    }

    @JsonProperty("idMarqueArticle")
    public void setIdMarqueArticle(Object idMarqueArticle) {
        this.idMarqueArticle = idMarqueArticle;
    }

    @JsonProperty("autoAlimenteFournisseurs")
    public Boolean getAutoAlimenteFournisseurs() {
        return autoAlimenteFournisseurs;
    }

    @JsonProperty("autoAlimenteFournisseurs")
    public void setAutoAlimenteFournisseurs(Boolean autoAlimenteFournisseurs) {
        this.autoAlimenteFournisseurs = autoAlimenteFournisseurs;
    }

    @JsonProperty("nbArticle")
    public Integer getNbArticle() {
        return nbArticle;
    }

    @JsonProperty("nbArticle")
    public void setNbArticle(Integer nbArticle) {
        this.nbArticle = nbArticle;
    }

    @JsonProperty("codeTTarif")
    public Object getCodeTTarif() {
        return codeTTarif;
    }

    @JsonProperty("codeTTarif")
    public void setCodeTTarif(Object codeTTarif) {
        this.codeTTarif = codeTTarif;
    }

    @JsonProperty("libTTarif")
    public Object getLibTTarif() {
        return libTTarif;
    }

    @JsonProperty("libTTarif")
    public void setLibTTarif(Object libTTarif) {
        this.libTTarif = libTTarif;
    }

    @JsonProperty("sensTTarif")
    public Boolean getSensTTarif() {
        return sensTTarif;
    }

    @JsonProperty("sensTTarif")
    public void setSensTTarif(Boolean sensTTarif) {
        this.sensTTarif = sensTTarif;
    }

    @JsonProperty("pourcentageTTarif")
    public Boolean getPourcentageTTarif() {
        return pourcentageTTarif;
    }

    @JsonProperty("pourcentageTTarif")
    public void setPourcentageTTarif(Boolean pourcentageTTarif) {
        this.pourcentageTTarif = pourcentageTTarif;
    }

    @JsonProperty("valeurTTarif")
    public Object getValeurTTarif() {
        return valeurTTarif;
    }

    @JsonProperty("valeurTTarif")
    public void setValeurTTarif(Object valeurTTarif) {
        this.valeurTTarif = valeurTTarif;
    }

    @JsonProperty("accepte")
    public Object getAccepte() {
        return accepte;
    }

    @JsonProperty("accepte")
    public void setAccepte(Object accepte) {
        this.accepte = accepte;
    }

    @JsonProperty("prixPrixCalc")
    public Object getPrixPrixCalc() {
        return prixPrixCalc;
    }

    @JsonProperty("prixPrixCalc")
    public void setPrixPrixCalc(Object prixPrixCalc) {
        this.prixPrixCalc = prixPrixCalc;
    }

    @JsonProperty("prixttcPrixCalc")
    public Object getPrixttcPrixCalc() {
        return prixttcPrixCalc;
    }

    @JsonProperty("prixttcPrixCalc")
    public void setPrixttcPrixCalc(Object prixttcPrixCalc) {
        this.prixttcPrixCalc = prixttcPrixCalc;
    }

    @JsonProperty("coef")
    public Object getCoef() {
        return coef;
    }

    @JsonProperty("coef")
    public void setCoef(Object coef) {
        this.coef = coef;
    }

    @JsonProperty("idPrixCalc")
    public Object getIdPrixCalc() {
        return idPrixCalc;
    }

    @JsonProperty("idPrixCalc")
    public void setIdPrixCalc(Object idPrixCalc) {
        this.idPrixCalc = idPrixCalc;
    }

    @JsonProperty("existant")
    public Boolean getExistant() {
        return existant;
    }

    @JsonProperty("existant")
    public void setExistant(Boolean existant) {
        this.existant = existant;
    }

    @JsonProperty("codeTiers")
    public Object getCodeTiers() {
        return codeTiers;
    }

    @JsonProperty("codeTiers")
    public void setCodeTiers(Object codeTiers) {
        this.codeTiers = codeTiers;
    }

    @JsonProperty("taFamilleDTO")
    public Object getTaFamilleDTO() {
        return taFamilleDTO;
    }

    @JsonProperty("taFamilleDTO")
    public void setTaFamilleDTO(Object taFamilleDTO) {
        this.taFamilleDTO = taFamilleDTO;
    }

    @JsonProperty("taFamilleDTOs")
    public Object getTaFamilleDTOs() {
        return taFamilleDTOs;
    }

    @JsonProperty("taFamilleDTOs")
    public void setTaFamilleDTOs(Object taFamilleDTOs) {
        this.taFamilleDTOs = taFamilleDTOs;
    }

    @JsonProperty("taFournisseurDTOs")
    public Object getTaFournisseurDTOs() {
        return taFournisseurDTOs;
    }

    @JsonProperty("taFournisseurDTOs")
    public void setTaFournisseurDTOs(Object taFournisseurDTOs) {
        this.taFournisseurDTOs = taFournisseurDTOs;
    }

//@JsonProperty("swtUnite")
//public SwtUnite getSwtUnite() {
//return swtUnite;
//}
//
//@JsonProperty("swtUnite")
//public void setSwtUnite(SwtUnite swtUnite) {
//this.swtUnite = swtUnite;
//}
//
//@JsonProperty("swtPrix")
//public SwtPrix getSwtPrix() {
//return swtPrix;
//}
//
//@JsonProperty("swtPrix")
//public void setSwtPrix(SwtPrix swtPrix) {
//this.swtPrix = swtPrix;
//}
//
//@JsonProperty("swtRefPrix")
//public SwtRefPrix getSwtRefPrix() {
//return swtRefPrix;
//}
//
//@JsonProperty("swtRefPrix")
//public void setSwtRefPrix(SwtRefPrix swtRefPrix) {
//this.swtRefPrix = swtRefPrix;
//}
//
//@JsonProperty("swtRapportUnite")
//public SwtRapportUnite getSwtRapportUnite() {
//return swtRapportUnite;
//}
//
//@JsonProperty("swtRapportUnite")
//public void setSwtRapportUnite(SwtRapportUnite swtRapportUnite) {
//this.swtRapportUnite = swtRapportUnite;
//}

    @JsonProperty("listePrix")
    public List<Object> getListePrix() {
        return listePrix;
    }

    @JsonProperty("listePrix")
    public void setListePrix(List<Object> listePrix) {
        this.listePrix = listePrix;
    }

    @JsonProperty("listeRapportUnite")
    public List<Object> getListeRapportUnite() {
        return listeRapportUnite;
    }

    @JsonProperty("listeRapportUnite")
    public void setListeRapportUnite(List<Object> listeRapportUnite) {
        this.listeRapportUnite = listeRapportUnite;
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
//-----------------------------------com.example.SwtPrix.java-----------------------------------
//
//package com.example;
//
//import java.util.HashMap;
//import java.util.Map;
//import com.fasterxml.jackson.annotation.JsonAnyGetter;
//import com.fasterxml.jackson.annotation.JsonAnySetter;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.annotation.JsonPropertyOrder;
//
//@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonPropertyOrder({
//"id",
//"prixPrix",
//"prixttcPrix",
//"idArticle",
//"codeArticle",
//"libellecArticle",
//"tauxTva",
//"idRefPrix",
//"prixPrixCalc",
//"prixttcPrixCalc",
//"coef",
//"idTTarif",
//"codeTTarif",
//"codeTiers",
//"diff",
//"mtTva",
//"versionObj",
//"accepte"
//})
//public class SwtPrix {
//
//@JsonProperty("id")
//private Object id;
//@JsonProperty("prixPrix")
//private Object prixPrix;
//@JsonProperty("prixttcPrix")
//private Object prixttcPrix;
//@JsonProperty("idArticle")
//private Object idArticle;
//@JsonProperty("codeArticle")
//private Object codeArticle;
//@JsonProperty("libellecArticle")
//private Object libellecArticle;
//@JsonProperty("tauxTva")
//private Object tauxTva;
//@JsonProperty("idRefPrix")
//private Object idRefPrix;
//@JsonProperty("prixPrixCalc")
//private Object prixPrixCalc;
//@JsonProperty("prixttcPrixCalc")
//private Object prixttcPrixCalc;
//@JsonProperty("coef")
//private Object coef;
//@JsonProperty("idTTarif")
//private Object idTTarif;
//@JsonProperty("codeTTarif")
//private Object codeTTarif;
//@JsonProperty("codeTiers")
//private Object codeTiers;
//@JsonProperty("diff")
//private Boolean diff;
//@JsonProperty("mtTva")
//private Object mtTva;
//@JsonProperty("versionObj")
//private Object versionObj;
//@JsonProperty("accepte")
//private Object accepte;
//@JsonIgnore
//private Map<String, Object> additionalProperties = new HashMap<String, Object>();
//
//@JsonProperty("id")
//public Object getId() {
//return id;
//}
//
//@JsonProperty("id")
//public void setId(Object id) {
//this.id = id;
//}
//
//@JsonProperty("prixPrix")
//public Object getPrixPrix() {
//return prixPrix;
//}
//
//@JsonProperty("prixPrix")
//public void setPrixPrix(Object prixPrix) {
//this.prixPrix = prixPrix;
//}
//
//@JsonProperty("prixttcPrix")
//public Object getPrixttcPrix() {
//return prixttcPrix;
//}
//
//@JsonProperty("prixttcPrix")
//public void setPrixttcPrix(Object prixttcPrix) {
//this.prixttcPrix = prixttcPrix;
//}
//
//@JsonProperty("idArticle")
//public Object getIdArticle() {
//return idArticle;
//}
//
//@JsonProperty("idArticle")
//public void setIdArticle(Object idArticle) {
//this.idArticle = idArticle;
//}
//
//@JsonProperty("codeArticle")
//public Object getCodeArticle() {
//return codeArticle;
//}
//
//@JsonProperty("codeArticle")
//public void setCodeArticle(Object codeArticle) {
//this.codeArticle = codeArticle;
//}
//
//@JsonProperty("libellecArticle")
//public Object getLibellecArticle() {
//return libellecArticle;
//}
//
//@JsonProperty("libellecArticle")
//public void setLibellecArticle(Object libellecArticle) {
//this.libellecArticle = libellecArticle;
//}
//
//@JsonProperty("tauxTva")
//public Object getTauxTva() {
//return tauxTva;
//}
//
//@JsonProperty("tauxTva")
//public void setTauxTva(Object tauxTva) {
//this.tauxTva = tauxTva;
//}
//
//@JsonProperty("idRefPrix")
//public Object getIdRefPrix() {
//return idRefPrix;
//}
//
//@JsonProperty("idRefPrix")
//public void setIdRefPrix(Object idRefPrix) {
//this.idRefPrix = idRefPrix;
//}
//
//@JsonProperty("prixPrixCalc")
//public Object getPrixPrixCalc() {
//return prixPrixCalc;
//}
//
//@JsonProperty("prixPrixCalc")
//public void setPrixPrixCalc(Object prixPrixCalc) {
//this.prixPrixCalc = prixPrixCalc;
//}
//
//@JsonProperty("prixttcPrixCalc")
//public Object getPrixttcPrixCalc() {
//return prixttcPrixCalc;
//}
//
//@JsonProperty("prixttcPrixCalc")
//public void setPrixttcPrixCalc(Object prixttcPrixCalc) {
//this.prixttcPrixCalc = prixttcPrixCalc;
//}
//
//@JsonProperty("coef")
//public Object getCoef() {
//return coef;
//}
//
//@JsonProperty("coef")
//public void setCoef(Object coef) {
//this.coef = coef;
//}
//
//@JsonProperty("idTTarif")
//public Object getIdTTarif() {
//return idTTarif;
//}
//
//@JsonProperty("idTTarif")
//public void setIdTTarif(Object idTTarif) {
//this.idTTarif = idTTarif;
//}
//
//@JsonProperty("codeTTarif")
//public Object getCodeTTarif() {
//return codeTTarif;
//}
//
//@JsonProperty("codeTTarif")
//public void setCodeTTarif(Object codeTTarif) {
//this.codeTTarif = codeTTarif;
//}
//
//@JsonProperty("codeTiers")
//public Object getCodeTiers() {
//return codeTiers;
//}
//
//@JsonProperty("codeTiers")
//public void setCodeTiers(Object codeTiers) {
//this.codeTiers = codeTiers;
//}
//
//@JsonProperty("diff")
//public Boolean getDiff() {
//return diff;
//}
//
//@JsonProperty("diff")
//public void setDiff(Boolean diff) {
//this.diff = diff;
//}
//
//@JsonProperty("mtTva")
//public Object getMtTva() {
//return mtTva;
//}
//
//@JsonProperty("mtTva")
//public void setMtTva(Object mtTva) {
//this.mtTva = mtTva;
//}
//
//@JsonProperty("versionObj")
//public Object getVersionObj() {
//return versionObj;
//}
//
//@JsonProperty("versionObj")
//public void setVersionObj(Object versionObj) {
//this.versionObj = versionObj;
//}
//
//@JsonProperty("accepte")
//public Object getAccepte() {
//return accepte;
//}
//
//@JsonProperty("accepte")
//public void setAccepte(Object accepte) {
//this.accepte = accepte;
//}
//
//@JsonAnyGetter
//public Map<String, Object> getAdditionalProperties() {
//return this.additionalProperties;
//}
//
//@JsonAnySetter
//public void setAdditionalProperty(String name, Object value) {
//this.additionalProperties.put(name, value);
//}
//
//}
//-----------------------------------com.example.SwtRapportUnite.java-----------------------------------
//
//package com.example;
//
//import java.util.HashMap;
//import java.util.Map;
//import com.fasterxml.jackson.annotation.JsonAnyGetter;
//import com.fasterxml.jackson.annotation.JsonAnySetter;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.annotation.JsonPropertyOrder;
//
//@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonPropertyOrder({
//"id",
//"codeArticle",
//"codeUnite1",
//"codeUnite2",
//"rapport",
//"nbDecimal",
//"sens",
//"versionObj"
//})
//public class SwtRapportUnite {
//
//@JsonProperty("id")
//private Object id;
//@JsonProperty("codeArticle")
//private Object codeArticle;
//@JsonProperty("codeUnite1")
//private Object codeUnite1;
//@JsonProperty("codeUnite2")
//private Object codeUnite2;
//@JsonProperty("rapport")
//private Integer rapport;
//@JsonProperty("nbDecimal")
//private Integer nbDecimal;
//@JsonProperty("sens")
//private Object sens;
//@JsonProperty("versionObj")
//private Object versionObj;
//@JsonIgnore
//private Map<String, Object> additionalProperties = new HashMap<String, Object>();
//
//@JsonProperty("id")
//public Object getId() {
//return id;
//}
//
//@JsonProperty("id")
//public void setId(Object id) {
//this.id = id;
//}
//
//@JsonProperty("codeArticle")
//public Object getCodeArticle() {
//return codeArticle;
//}
//
//@JsonProperty("codeArticle")
//public void setCodeArticle(Object codeArticle) {
//this.codeArticle = codeArticle;
//}
//
//@JsonProperty("codeUnite1")
//public Object getCodeUnite1() {
//return codeUnite1;
//}
//
//@JsonProperty("codeUnite1")
//public void setCodeUnite1(Object codeUnite1) {
//this.codeUnite1 = codeUnite1;
//}
//
//@JsonProperty("codeUnite2")
//public Object getCodeUnite2() {
//return codeUnite2;
//}
//
//@JsonProperty("codeUnite2")
//public void setCodeUnite2(Object codeUnite2) {
//this.codeUnite2 = codeUnite2;
//}
//
//@JsonProperty("rapport")
//public Integer getRapport() {
//return rapport;
//}
//
//@JsonProperty("rapport")
//public void setRapport(Integer rapport) {
//this.rapport = rapport;
//}
//
//@JsonProperty("nbDecimal")
//public Integer getNbDecimal() {
//return nbDecimal;
//}
//
//@JsonProperty("nbDecimal")
//public void setNbDecimal(Integer nbDecimal) {
//this.nbDecimal = nbDecimal;
//}
//
//@JsonProperty("sens")
//public Object getSens() {
//return sens;
//}
//
//@JsonProperty("sens")
//public void setSens(Object sens) {
//this.sens = sens;
//}
//
//@JsonProperty("versionObj")
//public Object getVersionObj() {
//return versionObj;
//}
//
//@JsonProperty("versionObj")
//public void setVersionObj(Object versionObj) {
//this.versionObj = versionObj;
//}
//
//@JsonAnyGetter
//public Map<String, Object> getAdditionalProperties() {
//return this.additionalProperties;
//}
//
//@JsonAnySetter
//public void setAdditionalProperty(String name, Object value) {
//this.additionalProperties.put(name, value);
//}
//
//}
//-----------------------------------com.example.SwtRefPrix.java-----------------------------------
//
//package com.example;
//
//import java.util.HashMap;
//import java.util.Map;
//import com.fasterxml.jackson.annotation.JsonAnyGetter;
//import com.fasterxml.jackson.annotation.JsonAnySetter;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.annotation.JsonPropertyOrder;
//
//@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonPropertyOrder({
//"id",
//"idArticle",
//"idPrix",
//"codeArticle",
//"prixPrix",
//"prixttcPrix",
//"versionObj"
//})
//public class SwtRefPrix {
//
//@JsonProperty("id")
//private Object id;
//@JsonProperty("idArticle")
//private Object idArticle;
//@JsonProperty("idPrix")
//private Object idPrix;
//@JsonProperty("codeArticle")
//private Object codeArticle;
//@JsonProperty("prixPrix")
//private Object prixPrix;
//@JsonProperty("prixttcPrix")
//private Object prixttcPrix;
//@JsonProperty("versionObj")
//private Object versionObj;
//@JsonIgnore
//private Map<String, Object> additionalProperties = new HashMap<String, Object>();
//
//@JsonProperty("id")
//public Object getId() {
//return id;
//}
//
//@JsonProperty("id")
//public void setId(Object id) {
//this.id = id;
//}
//
//@JsonProperty("idArticle")
//public Object getIdArticle() {
//return idArticle;
//}
//
//@JsonProperty("idArticle")
//public void setIdArticle(Object idArticle) {
//this.idArticle = idArticle;
//}
//
//@JsonProperty("idPrix")
//public Object getIdPrix() {
//return idPrix;
//}
//
//@JsonProperty("idPrix")
//public void setIdPrix(Object idPrix) {
//this.idPrix = idPrix;
//}
//
//@JsonProperty("codeArticle")
//public Object getCodeArticle() {
//return codeArticle;
//}
//
//@JsonProperty("codeArticle")
//public void setCodeArticle(Object codeArticle) {
//this.codeArticle = codeArticle;
//}
//
//@JsonProperty("prixPrix")
//public Object getPrixPrix() {
//return prixPrix;
//}
//
//@JsonProperty("prixPrix")
//public void setPrixPrix(Object prixPrix) {
//this.prixPrix = prixPrix;
//}
//
//@JsonProperty("prixttcPrix")
//public Object getPrixttcPrix() {
//return prixttcPrix;
//}
//
//@JsonProperty("prixttcPrix")
//public void setPrixttcPrix(Object prixttcPrix) {
//this.prixttcPrix = prixttcPrix;
//}
//
//@JsonProperty("versionObj")
//public Object getVersionObj() {
//return versionObj;
//}
//
//@JsonProperty("versionObj")
//public void setVersionObj(Object versionObj) {
//this.versionObj = versionObj;
//}
//
//@JsonAnyGetter
//public Map<String, Object> getAdditionalProperties() {
//return this.additionalProperties;
//}
//
//@JsonAnySetter
//public void setAdditionalProperty(String name, Object value) {
//this.additionalProperties.put(name, value);
//}
//
//}
//-----------------------------------com.example.SwtUnite.java-----------------------------------
//
//package com.example;
//
//import java.util.HashMap;
//import java.util.Map;
//import com.fasterxml.jackson.annotation.JsonAnyGetter;
//import com.fasterxml.jackson.annotation.JsonAnySetter;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.annotation.JsonPropertyOrder;
//
//@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonPropertyOrder({
//"id",
//"codeUnite",
//"liblUnite",
//"codeFamille",
//"nbUnite",
//"hauteur",
//"longueur",
//"largeur",
//"poids",
//"versionObj"
//})
//public class SwtUnite {
//
//@JsonProperty("id")
//private Object id;
//@JsonProperty("codeUnite")
//private Object codeUnite;
//@JsonProperty("liblUnite")
//private Object liblUnite;
//@JsonProperty("codeFamille")
//private Object codeFamille;
//@JsonProperty("nbUnite")
//private Object nbUnite;
//@JsonProperty("hauteur")
//private Object hauteur;
//@JsonProperty("longueur")
//private Object longueur;
//@JsonProperty("largeur")
//private Object largeur;
//@JsonProperty("poids")
//private Object poids;
//@JsonProperty("versionObj")
//private Object versionObj;
//@JsonIgnore
//private Map<String, Object> additionalProperties = new HashMap<String, Object>();
//
//@JsonProperty("id")
//public Object getId() {
//return id;
//}
//
//@JsonProperty("id")
//public void setId(Object id) {
//this.id = id;
//}
//
//@JsonProperty("codeUnite")
//public Object getCodeUnite() {
//return codeUnite;
//}
//
//@JsonProperty("codeUnite")
//public void setCodeUnite(Object codeUnite) {
//this.codeUnite = codeUnite;
//}
//
//@JsonProperty("liblUnite")
//public Object getLiblUnite() {
//return liblUnite;
//}
//
//@JsonProperty("liblUnite")
//public void setLiblUnite(Object liblUnite) {
//this.liblUnite = liblUnite;
//}
//
//@JsonProperty("codeFamille")
//public Object getCodeFamille() {
//return codeFamille;
//}
//
//@JsonProperty("codeFamille")
//public void setCodeFamille(Object codeFamille) {
//this.codeFamille = codeFamille;
//}
//
//@JsonProperty("nbUnite")
//public Object getNbUnite() {
//return nbUnite;
//}
//
//@JsonProperty("nbUnite")
//public void setNbUnite(Object nbUnite) {
//this.nbUnite = nbUnite;
//}
//
//@JsonProperty("hauteur")
//public Object getHauteur() {
//return hauteur;
//}
//
//@JsonProperty("hauteur")
//public void setHauteur(Object hauteur) {
//this.hauteur = hauteur;
//}
//
//@JsonProperty("longueur")
//public Object getLongueur() {
//return longueur;
//}
//
//@JsonProperty("longueur")
//public void setLongueur(Object longueur) {
//this.longueur = longueur;
//}
//
//@JsonProperty("largeur")
//public Object getLargeur() {
//return largeur;
//}
//
//@JsonProperty("largeur")
//public void setLargeur(Object largeur) {
//this.largeur = largeur;
//}
//
//@JsonProperty("poids")
//public Object getPoids() {
//return poids;
//}
//
//@JsonProperty("poids")
//public void setPoids(Object poids) {
//this.poids = poids;
//}
//
//@JsonProperty("versionObj")
//public Object getVersionObj() {
//return versionObj;
//}
//
//@JsonProperty("versionObj")
//public void setVersionObj(Object versionObj) {
//this.versionObj = versionObj;
//}
//
//@JsonAnyGetter
//public Map<String, Object> getAdditionalProperties() {
//return this.additionalProperties;
//}
//
//@JsonAnySetter
//public void setAdditionalProperty(String name, Object value) {
//this.additionalProperties.put(name, value);
//}
//
//}
