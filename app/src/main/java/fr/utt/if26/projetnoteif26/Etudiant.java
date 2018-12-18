package fr.utt.if26.projetnoteif26;

public class Etudiant {

    Integer numeroEtu;
    String nom;
    String prenom;
    String admission; //TC, BR (permet de savoir si le cursus devra contenir 300 ou 180 crédits)
    String filiere; //?, MPL, MSI, MRI, LIB. (“?” sera utilisé pour un étudiant en TCBR)
    Cursus cursus;

    public Etudiant() {
        this.numeroEtu = 0;
        this.nom = "default";
        this.prenom = "default";
        this.admission = "default";
        this.filiere = "default";
        this.cursus = new Cursus();
    }

    public Etudiant(Integer numeroEtu, String nom, String prenom, String admission, String filiere) {
        this.numeroEtu = numeroEtu;
        this.nom = nom;
        this.prenom = prenom;
        this.admission = admission;
        this.filiere = filiere;
    }

    public void ajouteCursus(Cursus cursus) {
        this.cursus = cursus;
    }

    public Integer getNumeroEtu() {
        return numeroEtu;
    }

    public void setNumeroEtu(Integer numeroEtu) {
        this.numeroEtu = numeroEtu;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdmission() {
        return admission;
    }

    public void setAdmission(String admission) {
        this.admission = admission;
    }

    public String getFiliere() {
        return filiere;
    }

    public void setFiliere(String filiere) {
        this.filiere = filiere;
    }
}
