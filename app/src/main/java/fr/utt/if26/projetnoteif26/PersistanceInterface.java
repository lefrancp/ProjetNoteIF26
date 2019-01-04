package fr.utt.if26.projetnoteif26;

import java.util.ArrayList;

public interface PersistanceInterface {

public void addEtudiant(Etudiant e) ;

public ArrayList<Etudiant> getAllEtudiants();

    public void addUV(UE e) ;

    public ArrayList<UE> getAllUVs();

    public ArrayList<ArrayList<String>> getAllCSlabels();

    public ArrayList<Cursus> getCursusFromNumEtu(Integer numEtu);


    void addCursus(Cursus c);

    ArrayList<Cursus> getAllCursus();

    Etudiant getEtudiant(Integer num_etu);

    public ArrayList<UE> getUV(String sigle);

    public String getResultatFromCursus(String sigle, int numeroEtu, String labelSemestre);

    public UE getUVseule(String sigle);


}

