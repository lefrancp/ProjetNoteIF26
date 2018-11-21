package fr.utt.if26.projetnoteif26;

import java.util.ArrayList;

public class Semestre {

    Integer numero; // numéro de semestre à l'UTT (exemple 1 pour un premier semestre en TC ou en branche)
    String label; //TC1, ... TC6, ISI1 ... ISI8, SRT1,..., MTE, ....
    private ArrayList<UE> uEs; //UEs suivies ce semestre

    public Semestre() {
        this.numero = 0;
        this.label = "default";
        this.uEs = new ArrayList();
    }

    public Semestre(Integer numero, String label) {
        this.numero = numero;
        this.label = label;
    }



    public void ajouteUE(UE newUE) {
        uEs.add(newUE);
    }
}
