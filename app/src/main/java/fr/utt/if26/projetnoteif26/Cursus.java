package fr.utt.if26.projetnoteif26;

import java.util.ArrayList;

public class Cursus {

    Integer num_etu; //-> recupde etudiant
    String sigle;//sigle -> recup de UV
    String resultat; //resultalt de l'uv
    String semestre; //semestre à laquelle l'uv est effectuée
    String NPML;

    public Cursus(Etudiant e, UE u, String resultat, String semestre, String NPML) {
        this.num_etu = e.getNumeroEtu();
        this.sigle = u.getSigle();
        this.resultat = resultat;
        this.semestre = semestre;
        this.NPML = NPML;
    }


    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getNPML() {
        return NPML;
    }

    public void setNPML(String NPML) {
        this.NPML = NPML;
    }

    public Integer getNum_etu() {
        return num_etu;
    }

    public void setNum_etu(Integer num_etu) {
        this.num_etu = num_etu;
    }

    public String getSigle() {
        return sigle;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }
}