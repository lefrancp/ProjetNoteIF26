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
        this.nom = nom;
        this.prenom = prenom;
        this.admission = admission;
        this.filiere = filiere;
    }

    public void ajouteCursus(Cursus cursus) {
        this.cursus = cursus;
    }
}
