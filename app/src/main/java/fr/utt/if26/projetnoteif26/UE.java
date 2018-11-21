package fr.utt.if26.projetnoteif26;

public class UE {    // On stocke sigle, catégorie et credit dans la BDD. Affectation et resultat sont renseignés au moment de la création de l'étudiant et seront donc associés à l'étudiant.
                     // ON NE STOCKE DONC JAMAIS AFFECTATION ET RESULTAT DANS LA BDD

    String sigle;
    String categorie; //CS, TM, EC, ME, HT, ST,
    String affectation; //TC, TCBR, Filiere
    Integer credit;
    String resultat;

    public UE() {
        this.sigle = "default";
        this.categorie = "default";
        this.affectation = "default";
        this.credit = 0;
        this.resultat = "default";
    }

    public UE(String sigle, String categorie, String affectation, Integer credit, String resultat) {
        this.sigle = sigle;
        this.categorie = categorie;
        this.affectation = affectation;
        this.credit = credit;
        this.resultat = resultat;
    }
}
