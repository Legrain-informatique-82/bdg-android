package fr.legrain.bdg.api.client.dto;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "codeTiers",
        "codeCompta",
        "compte",
        "nomTiers",
        "prenomTiers",
        "surnomTiers",
        "actifTiers",
        "utiliseCompteClient",
        "emailCleCompteClientEnvoye",
        "ttcTiers",
        "idTCivilite",
        "codeTCivilite",
        "idEntreprise",
        "nomEntreprise",
        "liblEntreprise",
        "idTTiers",
        "codeTTiers",
        "libelleTTiers",
        "idCommentaire",
        "commentaire",
        "codeTTvaDoc",
        "idTTvaDoc",
        "dateDerniereConnexionCompteClient",
        "idTEntite",
        "codeTEntite",
        "liblTEntite",
        "tvaIComCompl",
        "siretCompl",
        "numAgrementSanitaire",
        "accise",
        "ics",
        "idAdresse",
        "adresse1Adresse",
        "adresse2Adresse",
        "adresse3Adresse",
        "codepostalAdresse",
        "villeAdresse",
        "paysAdresse",
        "idEmail",
        "adresseEmail",
        "adresseWeb",
        "numeroTelephone",
        "codeCPaiement",
        "libCPaiement",
        "reportCPaiement",
        "finMoisCPaiement",
        "codeTPaiement",
        "libTPaiement",
        "compteTPaiement",
        "reportTPaiement",
        "finMoisTPaiement",
        "codeTTarif",
        "accepte",
        "dateAnniv",
        "idFamilleTiers",
        "codeFamilleTiers",
        "libelleFamilleTiers",
        "maRefTiers",
        "versionObj",
        "nbTiers"
})
public class TiersDTO implements Serializable {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("codeTiers")
    private String codeTiers;
    @JsonProperty("codeCompta")
    private String codeCompta;
    @JsonProperty("compte")
    private Object compte;
    @JsonProperty("nomTiers")
    private String nomTiers;
    @JsonProperty("prenomTiers")
    private String prenomTiers;
    @JsonProperty("surnomTiers")
    private String surnomTiers;
    @JsonProperty("actifTiers")
    private Boolean actifTiers;
    @JsonProperty("utiliseCompteClient")
    private Boolean utiliseCompteClient;
    @JsonProperty("emailCleCompteClientEnvoye")
    private Boolean emailCleCompteClientEnvoye;
    @JsonProperty("ttcTiers")
    private Boolean ttcTiers;
    @JsonProperty("idTCivilite")
    private Object idTCivilite;
    @JsonProperty("codeTCivilite")
    private Object codeTCivilite;
    @JsonProperty("idEntreprise")
    private Object idEntreprise;
    @JsonProperty("nomEntreprise")
    private String nomEntreprise;
    @JsonProperty("liblEntreprise")
    private String liblEntreprise;
    @JsonProperty("idTTiers")
    private Object idTTiers;
    @JsonProperty("codeTTiers")
    private String codeTTiers;
    @JsonProperty("libelleTTiers")
    private String libelleTTiers;
    @JsonProperty("idCommentaire")
    private Object idCommentaire;
    @JsonProperty("commentaire")
    private String commentaire;
    @JsonProperty("codeTTvaDoc")
    private String codeTTvaDoc;
    @JsonProperty("idTTvaDoc")
    private Object idTTvaDoc;
    @JsonProperty("dateDerniereConnexionCompteClient")
    private Object dateDerniereConnexionCompteClient;
    @JsonProperty("idTEntite")
    private Object idTEntite;
    @JsonProperty("codeTEntite")
    private String codeTEntite;
    @JsonProperty("liblTEntite")
    private String liblTEntite;
    @JsonProperty("tvaIComCompl")
    private String tvaIComCompl;
    @JsonProperty("siretCompl")
    private String siretCompl;
    @JsonProperty("numAgrementSanitaire")
    private String numAgrementSanitaire;
    @JsonProperty("accise")
    private String accise;
    @JsonProperty("ics")
    private String ics;
    @JsonProperty("idAdresse")
    private Object idAdresse;
    @JsonProperty("adresse1Adresse")
    private String adresse1Adresse;
    @JsonProperty("adresse2Adresse")
    private String adresse2Adresse;
    @JsonProperty("adresse3Adresse")
    private String adresse3Adresse;
    @JsonProperty("codepostalAdresse")
    private String codepostalAdresse;
    @JsonProperty("villeAdresse")
    private String villeAdresse;
    @JsonProperty("paysAdresse")
    private String paysAdresse;
    @JsonProperty("idEmail")
    private Object idEmail;
    @JsonProperty("adresseEmail")
    private String adresseEmail;
    @JsonProperty("adresseWeb")
    private String adresseWeb;
    @JsonProperty("numeroTelephone")
    private String numeroTelephone;
    @JsonProperty("codeCPaiement")
    private String codeCPaiement;
    @JsonProperty("libCPaiement")
    private String libCPaiement;
    @JsonProperty("reportCPaiement")
    private Integer reportCPaiement;
    @JsonProperty("finMoisCPaiement")
    private Integer finMoisCPaiement;
    @JsonProperty("codeTPaiement")
    private Object codeTPaiement;
    @JsonProperty("libTPaiement")
    private Object libTPaiement;
    @JsonProperty("compteTPaiement")
    private Object compteTPaiement;
    @JsonProperty("reportTPaiement")
    private Integer reportTPaiement;
    @JsonProperty("finMoisTPaiement")
    private Integer finMoisTPaiement;
    @JsonProperty("codeTTarif")
    private Object codeTTarif;
    @JsonProperty("accepte")
    private Boolean accepte;
    @JsonProperty("dateAnniv")
    private Object dateAnniv;
    @JsonProperty("idFamilleTiers")
    private Integer idFamilleTiers;
    @JsonProperty("codeFamilleTiers")
    private String codeFamilleTiers;
    @JsonProperty("libelleFamilleTiers")
    private String libelleFamilleTiers;
    @JsonProperty("maRefTiers")
    private String maRefTiers;
    @JsonProperty("versionObj")
    private Object versionObj;
    @JsonProperty("nbTiers")
    private Integer nbTiers;
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

    @JsonProperty("codeTiers")
    public String getCodeTiers() {
        return codeTiers;
    }

    @JsonProperty("codeTiers")
    public void setCodeTiers(String codeTiers) {
        this.codeTiers = codeTiers;
    }

    @JsonProperty("codeCompta")
    public Object getCodeCompta() {
        return codeCompta;
    }

    @JsonProperty("codeCompta")
    public void setCodeCompta(String codeCompta) {
        this.codeCompta = codeCompta;
    }

    @JsonProperty("compte")
    public Object getCompte() {
        return compte;
    }

    @JsonProperty("compte")
    public void setCompte(Object compte) {
        this.compte = compte;
    }

    @JsonProperty("nomTiers")
    public String getNomTiers() {
        return nomTiers;
    }

    @JsonProperty("nomTiers")
    public void setNomTiers(String nomTiers) {
        this.nomTiers = nomTiers;
    }

    @JsonProperty("prenomTiers")
    public String getPrenomTiers() {
        return prenomTiers;
    }

    @JsonProperty("prenomTiers")
    public void setPrenomTiers(String prenomTiers) {
        this.prenomTiers = prenomTiers;
    }

    @JsonProperty("surnomTiers")
    public Object getSurnomTiers() {
        return surnomTiers;
    }

    @JsonProperty("surnomTiers")
    public void setSurnomTiers(String surnomTiers) {
        this.surnomTiers = surnomTiers;
    }

    @JsonProperty("actifTiers")
    public Boolean getActifTiers() {
        return actifTiers;
    }

    @JsonProperty("actifTiers")
    public void setActifTiers(Boolean actifTiers) {
        this.actifTiers = actifTiers;
    }

    @JsonProperty("utiliseCompteClient")
    public Boolean getUtiliseCompteClient() {
        return utiliseCompteClient;
    }

    @JsonProperty("utiliseCompteClient")
    public void setUtiliseCompteClient(Boolean utiliseCompteClient) {
        this.utiliseCompteClient = utiliseCompteClient;
    }

    @JsonProperty("emailCleCompteClientEnvoye")
    public Boolean getEmailCleCompteClientEnvoye() {
        return emailCleCompteClientEnvoye;
    }

    @JsonProperty("emailCleCompteClientEnvoye")
    public void setEmailCleCompteClientEnvoye(Boolean emailCleCompteClientEnvoye) {
        this.emailCleCompteClientEnvoye = emailCleCompteClientEnvoye;
    }

    @JsonProperty("ttcTiers")
    public Boolean getTtcTiers() {
        return ttcTiers;
    }

    @JsonProperty("ttcTiers")
    public void setTtcTiers(Boolean ttcTiers) {
        this.ttcTiers = ttcTiers;
    }

    @JsonProperty("idTCivilite")
    public Object getIdTCivilite() {
        return idTCivilite;
    }

    @JsonProperty("idTCivilite")
    public void setIdTCivilite(Object idTCivilite) {
        this.idTCivilite = idTCivilite;
    }

    @JsonProperty("codeTCivilite")
    public Object getCodeTCivilite() {
        return codeTCivilite;
    }

    @JsonProperty("codeTCivilite")
    public void setCodeTCivilite(Object codeTCivilite) {
        this.codeTCivilite = codeTCivilite;
    }

    @JsonProperty("idEntreprise")
    public Object getIdEntreprise() {
        return idEntreprise;
    }

    @JsonProperty("idEntreprise")
    public void setIdEntreprise(Object idEntreprise) {
        this.idEntreprise = idEntreprise;
    }

    @JsonProperty("nomEntreprise")
    public String getNomEntreprise() {
        return nomEntreprise;
    }

    @JsonProperty("nomEntreprise")
    public void setNomEntreprise(String nomEntreprise) {
        this.nomEntreprise = nomEntreprise;
    }

    @JsonProperty("liblEntreprise")
    public String getLiblEntreprise() {
        return liblEntreprise;
    }

    @JsonProperty("liblEntreprise")
    public void setLiblEntreprise(String liblEntreprise) {
        this.liblEntreprise = liblEntreprise;
    }

    @JsonProperty("idTTiers")
    public Object getIdTTiers() {
        return idTTiers;
    }

    @JsonProperty("idTTiers")
    public void setIdTTiers(Object idTTiers) {
        this.idTTiers = idTTiers;
    }

    @JsonProperty("codeTTiers")
    public String getCodeTTiers() {
        return codeTTiers;
    }

    @JsonProperty("codeTTiers")
    public void setCodeTTiers(String codeTTiers) {
        this.codeTTiers = codeTTiers;
    }

    @JsonProperty("libelleTTiers")
    public String getLibelleTTiers() {
        return libelleTTiers;
    }

    @JsonProperty("libelleTTiers")
    public void setLibelleTTiers(String libelleTTiers) {
        this.libelleTTiers = libelleTTiers;
    }

    @JsonProperty("idCommentaire")
    public Object getIdCommentaire() {
        return idCommentaire;
    }

    @JsonProperty("idCommentaire")
    public void setIdCommentaire(Object idCommentaire) {
        this.idCommentaire = idCommentaire;
    }

    @JsonProperty("commentaire")
    public Object getCommentaire() {
        return commentaire;
    }

    @JsonProperty("commentaire")
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @JsonProperty("codeTTvaDoc")
    public Object getCodeTTvaDoc() {
        return codeTTvaDoc;
    }

    @JsonProperty("codeTTvaDoc")
    public void setCodeTTvaDoc(String codeTTvaDoc) {
        this.codeTTvaDoc = codeTTvaDoc;
    }

    @JsonProperty("idTTvaDoc")
    public Object getIdTTvaDoc() {
        return idTTvaDoc;
    }

    @JsonProperty("idTTvaDoc")
    public void setIdTTvaDoc(Object idTTvaDoc) {
        this.idTTvaDoc = idTTvaDoc;
    }

    @JsonProperty("dateDerniereConnexionCompteClient")
    public Object getDateDerniereConnexionCompteClient() {
        return dateDerniereConnexionCompteClient;
    }

    @JsonProperty("dateDerniereConnexionCompteClient")
    public void setDateDerniereConnexionCompteClient(Object dateDerniereConnexionCompteClient) {
        this.dateDerniereConnexionCompteClient = dateDerniereConnexionCompteClient;
    }

    @JsonProperty("idTEntite")
    public Object getIdTEntite() {
        return idTEntite;
    }

    @JsonProperty("idTEntite")
    public void setIdTEntite(Object idTEntite) {
        this.idTEntite = idTEntite;
    }

    @JsonProperty("codeTEntite")
    public String getCodeTEntite() {
        return codeTEntite;
    }

    @JsonProperty("codeTEntite")
    public void setCodeTEntite(String codeTEntite) {
        this.codeTEntite = codeTEntite;
    }

    @JsonProperty("liblTEntite")
    public String getLiblTEntite() {
        return liblTEntite;
    }

    @JsonProperty("liblTEntite")
    public void setLiblTEntite(String liblTEntite) {
        this.liblTEntite = liblTEntite;
    }

    @JsonProperty("tvaIComCompl")
    public String getTvaIComCompl() {
        return tvaIComCompl;
    }

    @JsonProperty("tvaIComCompl")
    public void setTvaIComCompl(String tvaIComCompl) {
        this.tvaIComCompl = tvaIComCompl;
    }

    @JsonProperty("siretCompl")
    public String getSiretCompl() {
        return siretCompl;
    }

    @JsonProperty("siretCompl")
    public void setSiretCompl(String siretCompl) {
        this.siretCompl = siretCompl;
    }

    @JsonProperty("numAgrementSanitaire")
    public String getNumAgrementSanitaire() {
        return numAgrementSanitaire;
    }

    @JsonProperty("numAgrementSanitaire")
    public void setNumAgrementSanitaire(String numAgrementSanitaire) {
        this.numAgrementSanitaire = numAgrementSanitaire;
    }

    @JsonProperty("accise")
    public String getAccise() {
        return accise;
    }

    @JsonProperty("accise")
    public void setAccise(String accise) {
        this.accise = accise;
    }

    @JsonProperty("ics")
    public String getIcs() {
        return ics;
    }

    @JsonProperty("ics")
    public void setIcs(String ics) {
        this.ics = ics;
    }

    @JsonProperty("idAdresse")
    public Object getIdAdresse() {
        return idAdresse;
    }

    @JsonProperty("idAdresse")
    public void setIdAdresse(Object idAdresse) {
        this.idAdresse = idAdresse;
    }

    @JsonProperty("adresse1Adresse")
    public String getAdresse1Adresse() {
        return adresse1Adresse;
    }

    @JsonProperty("adresse1Adresse")
    public void setAdresse1Adresse(String adresse1Adresse) {
        this.adresse1Adresse = adresse1Adresse;
    }

    @JsonProperty("adresse2Adresse")
    public String getAdresse2Adresse() {
        return adresse2Adresse;
    }

    @JsonProperty("adresse2Adresse")
    public void setAdresse2Adresse(String adresse2Adresse) {
        this.adresse2Adresse = adresse2Adresse;
    }

    @JsonProperty("adresse3Adresse")
    public String getAdresse3Adresse() {
        return adresse3Adresse;
    }

    @JsonProperty("adresse3Adresse")
    public void setAdresse3Adresse(String adresse3Adresse) {
        this.adresse3Adresse = adresse3Adresse;
    }

    @JsonProperty("codepostalAdresse")
    public String getCodepostalAdresse() {
        return codepostalAdresse;
    }

    @JsonProperty("codepostalAdresse")
    public void setCodepostalAdresse(String codepostalAdresse) {
        this.codepostalAdresse = codepostalAdresse;
    }

    @JsonProperty("villeAdresse")
    public String getVilleAdresse() {
        return villeAdresse;
    }

    @JsonProperty("villeAdresse")
    public void setVilleAdresse(String villeAdresse) {
        this.villeAdresse = villeAdresse;
    }

    @JsonProperty("paysAdresse")
    public String getPaysAdresse() {
        return paysAdresse;
    }

    @JsonProperty("paysAdresse")
    public void setPaysAdresse(String paysAdresse) {
        this.paysAdresse = paysAdresse;
    }

    @JsonProperty("idEmail")
    public Object getIdEmail() {
        return idEmail;
    }

    @JsonProperty("idEmail")
    public void setIdEmail(Object idEmail) {
        this.idEmail = idEmail;
    }

    @JsonProperty("adresseEmail")
    public String getAdresseEmail() {
        return adresseEmail;
    }

    @JsonProperty("adresseEmail")
    public void setAdresseEmail(String adresseEmail) {
        this.adresseEmail = adresseEmail;
    }

    @JsonProperty("adresseWeb")
    public String getAdresseWeb() {
        return adresseWeb;
    }

    @JsonProperty("adresseWeb")
    public void setAdresseWeb(String adresseWeb) {
        this.adresseWeb = adresseWeb;
    }

    @JsonProperty("numeroTelephone")
    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    @JsonProperty("numeroTelephone")
    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    @JsonProperty("codeCPaiement")
    public String getCodeCPaiement() {
        return codeCPaiement;
    }

    @JsonProperty("codeCPaiement")
    public void setCodeCPaiement(String codeCPaiement) {
        this.codeCPaiement = codeCPaiement;
    }

    @JsonProperty("libCPaiement")
    public String getLibCPaiement() {
        return libCPaiement;
    }

    @JsonProperty("libCPaiement")
    public void setLibCPaiement(String libCPaiement) {
        this.libCPaiement = libCPaiement;
    }

    @JsonProperty("reportCPaiement")
    public Integer getReportCPaiement() {
        return reportCPaiement;
    }

    @JsonProperty("reportCPaiement")
    public void setReportCPaiement(Integer reportCPaiement) {
        this.reportCPaiement = reportCPaiement;
    }

    @JsonProperty("finMoisCPaiement")
    public Integer getFinMoisCPaiement() {
        return finMoisCPaiement;
    }

    @JsonProperty("finMoisCPaiement")
    public void setFinMoisCPaiement(Integer finMoisCPaiement) {
        this.finMoisCPaiement = finMoisCPaiement;
    }

    @JsonProperty("codeTPaiement")
    public Object getCodeTPaiement() {
        return codeTPaiement;
    }

    @JsonProperty("codeTPaiement")
    public void setCodeTPaiement(Object codeTPaiement) {
        this.codeTPaiement = codeTPaiement;
    }

    @JsonProperty("libTPaiement")
    public Object getLibTPaiement() {
        return libTPaiement;
    }

    @JsonProperty("libTPaiement")
    public void setLibTPaiement(Object libTPaiement) {
        this.libTPaiement = libTPaiement;
    }

    @JsonProperty("compteTPaiement")
    public Object getCompteTPaiement() {
        return compteTPaiement;
    }

    @JsonProperty("compteTPaiement")
    public void setCompteTPaiement(Object compteTPaiement) {
        this.compteTPaiement = compteTPaiement;
    }

    @JsonProperty("reportTPaiement")
    public Integer getReportTPaiement() {
        return reportTPaiement;
    }

    @JsonProperty("reportTPaiement")
    public void setReportTPaiement(Integer reportTPaiement) {
        this.reportTPaiement = reportTPaiement;
    }

    @JsonProperty("finMoisTPaiement")
    public Integer getFinMoisTPaiement() {
        return finMoisTPaiement;
    }

    @JsonProperty("finMoisTPaiement")
    public void setFinMoisTPaiement(Integer finMoisTPaiement) {
        this.finMoisTPaiement = finMoisTPaiement;
    }

    @JsonProperty("codeTTarif")
    public Object getCodeTTarif() {
        return codeTTarif;
    }

    @JsonProperty("codeTTarif")
    public void setCodeTTarif(Object codeTTarif) {
        this.codeTTarif = codeTTarif;
    }

    @JsonProperty("accepte")
    public Boolean getAccepte() {
        return accepte;
    }

    @JsonProperty("accepte")
    public void setAccepte(Boolean accepte) {
        this.accepte = accepte;
    }

    @JsonProperty("dateAnniv")
    public Object getDateAnniv() {
        return dateAnniv;
    }

    @JsonProperty("dateAnniv")
    public void setDateAnniv(Object dateAnniv) {
        this.dateAnniv = dateAnniv;
    }

    @JsonProperty("idFamilleTiers")
    public Integer getIdFamilleTiers() {
        return idFamilleTiers;
    }

    @JsonProperty("idFamilleTiers")
    public void setIdFamilleTiers(Integer idFamilleTiers) {
        this.idFamilleTiers = idFamilleTiers;
    }

    @JsonProperty("codeFamilleTiers")
    public String getCodeFamilleTiers() {
        return codeFamilleTiers;
    }

    @JsonProperty("codeFamilleTiers")
    public void setCodeFamilleTiers(String codeFamilleTiers) {
        this.codeFamilleTiers = codeFamilleTiers;
    }

    @JsonProperty("libelleFamilleTiers")
    public String getLibelleFamilleTiers() {
        return libelleFamilleTiers;
    }

    @JsonProperty("libelleFamilleTiers")
    public void setLibelleFamilleTiers(String libelleFamilleTiers) {
        this.libelleFamilleTiers = libelleFamilleTiers;
    }

    @JsonProperty("maRefTiers")
    public Object getMaRefTiers() {
        return maRefTiers;
    }

    @JsonProperty("maRefTiers")
    public void setMaRefTiers(String maRefTiers) {
        this.maRefTiers = maRefTiers;
    }

    @JsonProperty("versionObj")
    public Object getVersionObj() {
        return versionObj;
    }

    @JsonProperty("versionObj")
    public void setVersionObj(Object versionObj) {
        this.versionObj = versionObj;
    }

    @JsonProperty("nbTiers")
    public Integer getNbTiers() {
        return nbTiers;
    }

    @JsonProperty("nbTiers")
    public void setNbTiers(Integer nbTiers) {
        this.nbTiers = nbTiers;
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
