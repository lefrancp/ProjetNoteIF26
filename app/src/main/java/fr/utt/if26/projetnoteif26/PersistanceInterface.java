package fr.utt.if26.projetnoteif26;

import java.util.ArrayList;

public interface PersistanceInterface {

public void addEtudiant(Etudiant e) ;

public ArrayList<Etudiant> getAllEtudiants();

    public void addUV(UE e) ;

    public ArrayList<UE> getAllUVs();

    public ArrayList<ArrayList<String>> getAllCSlabels();


    void addCursus(Cursus c);

    ArrayList<Cursus> getAllCursus();

    Etudiant getEtudiant(Integer num_etu);

    UE getUV(String sigle);
}

