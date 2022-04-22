package fr.legrain.bdg.api.client.dto;

import androidx.databinding.BindingAdapter;
import android.widget.TextView;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "codeFlash",
        "dateFlash",
        "dateTransfert",
        "libelleFlash",
//        "idAdresse",
//        "idAdresseLiv",
        "idTiers",
        "codeTiers",
        "nomTiers",
//        "prenomTiers",
//        "surnomTiers",
//        "nomEntreprise",
        "ipAcces",
        "ttc",
        "codeEtat",
        "liblEtat",
        "versionObj",

        "numeroCommandeFournisseur",
        "gestionLot",
        "idUtilisateur",
        "nom",
        "idGroupePreparation",
        "codeGroupePreparation",
        "idTDoc",
        "codeTDoc"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlashDTO implements Serializable, IDocumentDTO {

    /*
    private Integer id=0;


	private String numeroCommandeFournisseur;
	private Boolean gestionLot = false;

	private Integer idUtilisateur;
	private String nom = LgrConstantes.C_STR_VIDE;

	private Integer idGroupePreparation;
	private String codeGroupePreparation = LgrConstantes.C_STR_VIDE;
	private String codeTDoc;
	private Integer idTDoc;
     */
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("codeFlash")
    private String codeFlash;
    @JsonProperty("dateFlash")
    private Date dateFlash;
    @JsonProperty("dateTransfert")
    private Date dateTransfert;
    @JsonProperty("libelleFlash")
    private String libelleFlash;

//    @JsonProperty("idAdresse")
//    private Integer idAdresse;
//    @JsonProperty("idAdresseLiv")
//    private Integer idAdresseLiv;
    @JsonProperty("idTiers")
    private Integer idTiers;
    @JsonProperty("codeTiers")
    private String codeTiers;
    @JsonProperty("nomTiers")
    private String nomTiers;

//    @JsonProperty("prenomTiers")
//    private String prenomTiers;
//    @JsonProperty("surnomTiers")
//    private String surnomTiers;
//    @JsonProperty("nomEntreprise")
//    private String nomEntreprise;

    @JsonProperty("ipAcces")
    private String ipAcces;
    @JsonProperty("ttc")
    private Boolean ttc;
    @JsonProperty("codeEtat")
    private String codeEtat;
    @JsonProperty("liblEtat")
    private String liblEtat;
    @JsonProperty("versionObj")
    private Integer versionObj;

    @JsonProperty("numeroCommandeFournisseur")
    private String numeroCommandeFournisseur;
    @JsonProperty("gestionLot")
    private Boolean gestionLot;
    @JsonProperty("idUtilisateur")
    private Integer idUtilisateur;
    @JsonProperty("nom")
    private String nom;
    @JsonProperty("idGroupePreparation")
    private Integer idGroupePreparation;



    @JsonProperty("codeGroupePreparation")
    private String codeGroupePreparation;
    @JsonProperty("idTDoc")
    private Integer idTDoc;
    @JsonProperty("codeTDoc")
    private String codeTDoc;


    @JsonProperty("lignesDTO")
    private List<LigneFlashDTO> lignesDTO = null;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

//    public String getDateFormatted(){
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//        return formatter.format(dateFlash);
//    }

    @BindingAdapter("android:date")
    public static void setText(TextView view, Date date) {
        if (date!=null) {
            DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
            String localizedDate = df.format(date);
            //
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            localizedDate = formatter.format(date);

            view.setText(localizedDate);
        }
    }

//    @BindingAdapter("android:text")
//    public static void setText(TextView view, BigDecimal date) {
//        if (date!=null) {
////            DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
//            //String localizedDate = df.format(date);
//            String localizedDate = date.toString();
//            //
////            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
////            localizedDate = formatter.format(date);
//
//            view.setText(localizedDate);
//        }
//    }

    public static final int REGROUPEMENT_AUCUN = 1;
    public static final int REGROUPEMENT_ARTICLE = 2;
    public static final int REGROUPEMENT_LOT = 3;

    /**
     * Regroupe les lignes pour un même numéro de lot en faisant la somme des quantités.
     */
    public void regroupeLigne(Integer mode) {
        if(mode!=null && mode.intValue()!=REGROUPEMENT_AUCUN) {
            if (lignesDTO != null) {
                Map<String, LigneFlashDTO> mapNumlotLigneBl = new HashMap<>();
                List<LigneFlashDTO> ligneSansNumLot = new ArrayList<>();
                String cle_codeBarreNumLot = "";
                for (LigneFlashDTO l : lignesDTO) {
                    if(mode.intValue()==REGROUPEMENT_LOT) {
                        if (l.getNumLot() != null) {
                            cle_codeBarreNumLot = l.getNumLot() + l.getCodeBarre();
                            if (mapNumlotLigneBl.get(cle_codeBarreNumLot) == null) {
                                //premiere ligne avec ce numero de lot, on l'ajoute
                                mapNumlotLigneBl.put(cle_codeBarreNumLot, l);
                            } else {
                                //deuxieme fois qu'on utilise ce numero de lot, on cumule les quantités sur la meme ligne
                                mapNumlotLigneBl.get(cle_codeBarreNumLot).setQteLDocument(mapNumlotLigneBl.get(cle_codeBarreNumLot).getQteLDocument().add(l.getQteLDocument()));
                            }
                        } else {
                            ligneSansNumLot.add(l);
                        }
                    } else if(mode.intValue()==REGROUPEMENT_ARTICLE) {
                        if (l.getCodeArticle() != null) {
                            cle_codeBarreNumLot = l.getCodeArticle() + l.getCodeBarre();
                            if (mapNumlotLigneBl.get(cle_codeBarreNumLot) == null) {
                                //premiere ligne avec ce numero de lot, on l'ajoute
                                mapNumlotLigneBl.put(cle_codeBarreNumLot, l);
                            } else {
                                //deuxieme fois qu'on utilise ce numero de lot, on cumule les quantités sur la meme ligne
                                mapNumlotLigneBl.get(cle_codeBarreNumLot).setQteLDocument(mapNumlotLigneBl.get(cle_codeBarreNumLot).getQteLDocument().add(l.getQteLDocument()));
                            }
                        } else {
                            ligneSansNumLot.add(l);
                        }
                    }
                }
                lignesDTO.clear();
                for (LigneFlashDTO l : mapNumlotLigneBl.values()) {
                    lignesDTO.add(l);
                }
                lignesDTO.addAll(ligneSansNumLot);

                //tri en fonction du libellé
                Collections.sort(lignesDTO, new Comparator<LigneFlashDTO>() {
                    @Override
                    public int compare(LigneFlashDTO l1, LigneFlashDTO l2) {
                        String libLot1 = l1.getLibelleLot();
                        String libLot2 = l2.getLibelleLot();
                        String lot1 = lot(l1.getNumLot());
                        String lot2 = lot(l2.getNumLot());
                        libLot1 = libLot1 + lot1;
                        libLot2 = libLot2 + lot2;

                        return libLot1.compareTo(libLot2);
                    }

                    public String lot(String numLot) {
                        String r = "";
//                    if(numLot!=null) {
//                        String datelot = numLot.substring(0, 9);
//                        datelot = "20" + datelot.split("/")[2] + datelot.split("/")[1] + datelot.split("/")[0];
//                        String heurelot = numLot.split("_")[1].replace("(", "").replace(")", "").replace(":", "");
//                        r = datelot + heurelot;
//                    }
                        return r;
                    }
                });
            }
        }
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("codeFlash")
    public String getCodeFlash() {
        return codeFlash;
    }

    @JsonProperty("codeFlash")
    public void setCodeFlash(String codeFlash) {
        this.codeFlash = codeFlash;
    }

    @JsonProperty("dateFlash")
    public Date getDateFlash() {
        return dateFlash;
    }

    @JsonProperty("dateFlash")
    public void setDateFlash(Date dateFlash) {
        this.dateFlash = dateFlash;
    }

    @JsonProperty("dateTransfert")
    public Date getDateTransfert() {
        return dateTransfert;
    }

    @JsonProperty("dateTransfert")
    public void setDateTransfert(Date dateTransfert) {
        this.dateTransfert = dateTransfert;
    }

    @JsonProperty("libelleFlash")
    public String getLibelleFlash() {
        return libelleFlash;
    }

    @JsonProperty("libelleFlash")
    public void setLibelleFlash(String libelleFlash) {
        this.libelleFlash = libelleFlash;
    }

//    @JsonProperty("idAdresse")
//    public Integer getIdAdresse() {
//        return idAdresse;
//    }
//
//    @JsonProperty("idAdresse")
//    public void setIdAdresse(Integer idAdresse) {
//        this.idAdresse = idAdresse;
//    }
//
//    @JsonProperty("idAdresseLiv")
//    public Integer getIdAdresseLiv() {
//        return idAdresseLiv;
//    }
//
//    @JsonProperty("idAdresseLiv")
//    public void setIdAdresseLiv(Integer idAdresseLiv) {
//        this.idAdresseLiv = idAdresseLiv;
//    }

    @JsonProperty("idTiers")
    public Integer getIdTiers() {
        return idTiers;
    }

    @JsonProperty("idTiers")
    public void setIdTiers(Integer idTiers) {
        this.idTiers = idTiers;
    }

    @JsonProperty("codeTiers")
    public String getCodeTiers() {
        return codeTiers;
    }

    @JsonProperty("codeTiers")
    public void setCodeTiers(String codeTiers) {
        this.codeTiers = codeTiers;
    }

    @JsonProperty("nomTiers")
    public String getNomTiers() {
        return nomTiers;
    }

    @JsonProperty("nomTiers")
    public void setNomTiers(String nomTiers) {
        this.nomTiers = nomTiers;
    }

//    @JsonProperty("prenomTiers")
//    public String getPrenomTiers() {
//        return prenomTiers;
//    }
//
//    @JsonProperty("prenomTiers")
//    public void setPrenomTiers(String prenomTiers) {
//        this.prenomTiers = prenomTiers;
//    }
//
//    @JsonProperty("surnomTiers")
//    public String getSurnomTiers() {
//        return surnomTiers;
//    }
//
//    @JsonProperty("surnomTiers")
//    public void setSurnomTiers(String surnomTiers) {
//        this.surnomTiers = surnomTiers;
//    }
//
//    @JsonProperty("nomEntreprise")
//    public String getNomEntreprise() {
//        return nomEntreprise;
//    }
//
//    @JsonProperty("nomEntreprise")
//    public void setNomEntreprise(String nomEntreprise) {
//        this.nomEntreprise = nomEntreprise;
//    }


    @JsonProperty("ipAcces")
    public String getIpAcces() {
        return ipAcces;
    }

    @JsonProperty("ipAcces")
    public void setIpAcces(String ipAcces) {
        this.ipAcces = ipAcces;
    }

    @JsonProperty("ttc")
    public Boolean getTtc() {
        return ttc;
    }

    @JsonProperty("ttc")
    public void setTtc(Boolean ttc) {
        this.ttc = ttc;
    }

    @JsonProperty("codeEtat")
    public String getCodeEtat() {
        return codeEtat;
    }

    @JsonProperty("codeEtat")
    public void setCodeEtat(String codeEtat) {
        this.codeEtat = codeEtat;
    }

    @JsonProperty("liblEtat")
    public String getLiblEtat() {
        return liblEtat;
    }

    @JsonProperty("liblEtat")
    public void setLiblEtat(String liblEtat) {
        this.liblEtat = liblEtat;
    }

    @JsonProperty("versionObj")
    public Integer getVersionObj() {
        return versionObj;
    }

    @JsonProperty("versionObj")
    public void setVersionObj(Integer versionObj) {
        this.versionObj = versionObj;
    }


    @JsonProperty("lignesDTO")
    public List<LigneFlashDTO> getLignesDTO() {
        return lignesDTO;
    }

    @JsonProperty("lignesDTO")
    public void setLignesDTO(List<LigneFlashDTO> lignesDTO) {
        this.lignesDTO = lignesDTO;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public String getNumeroCommandeFournisseur() {
        return numeroCommandeFournisseur;
    }

    public void setNumeroCommandeFournisseur(String numeroCommandeFournisseur) {
        this.numeroCommandeFournisseur = numeroCommandeFournisseur;
    }

    public Boolean getGestionLot() {
        return gestionLot;
    }

    public void setGestionLot(Boolean gestionLot) {
        this.gestionLot = gestionLot;
    }

    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getIdGroupePreparation() {
        return idGroupePreparation;
    }

    public void setIdGroupePreparation(Integer idGroupePreparation) {
        this.idGroupePreparation = idGroupePreparation;
    }

    public String getCodeGroupePreparation() {
        return codeGroupePreparation;
    }

    public void setCodeGroupePreparation(String codeGroupePreparation) {
        this.codeGroupePreparation = codeGroupePreparation;
    }

    public Integer getIdTDoc() {
        return idTDoc;
    }

    public void setIdTDoc(Integer idTDoc) {
        this.idTDoc = idTDoc;
    }

    public String getCodeTDoc() {
        return codeTDoc;
    }

    public void setCodeTDoc(String codeTDoc) {
        this.codeTDoc = codeTDoc;
    }

}