package fr.utt.if26.projetnoteif26;

import java.util.ArrayList;

public class Cursus {

    String affectation; //TC, BR(TCBR + Filiere), TCBR, Filière ou tout en même temps
    private ArrayList<Semestre> semestres;
    Boolean NPML;

    public Cursus() {
        this.affectation = "default";
        this.semestres = new ArrayList();
        this.NPML = false;
    }

    public Cursus(String affectation, Boolean NPML) {
        this.affectation = affectation;
        this.NPML = NPML;
    }

    public void ajouteSemestre(Semestre semestre) {
        semestres.add(semestre);
    }
}
