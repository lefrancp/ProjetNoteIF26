package fr.utt.if26.projetnoteif26;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AfficheCursus extends AppCompatActivity {

    TextView nomEtuTextView;
    TextView prenomEtuTextView;

    TextView csDeTcTextView;
    TextView tmDeTcTextView;
    TextView csTmTcbrTextView;
    TextView csTmFiliereTextView;
    TextView totalBRTextView;
    TextView globalCSTMTextView;
    TextView globalSTTextView;
    TextView globalECTextView;
    TextView globalMETextView;
    TextView globalCTTextView;

    TextView totalCreditTextView;
    TextView globalCreditTextView;
    Integer numeroEtu;
    int creditsTotaux = 0;
    int creditsGlobaux = 300;
    int creditsCSdeTC = 0;
    int creditsTMdeTC = 0;
    int creditsCSTMdeTCBR = 0;
    int creditsCSTMdeFliere = 0;
    int creditsEC = 0;
    int creditsME = 0;
    int creditsCT = 0;
    int creditsST = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche_cursus);

        totalCreditTextView = (TextView) findViewById(R.id.total_credits_id);
        globalCreditTextView = (TextView) findViewById(R.id.global_credit_id);
        ListView listeSemestre = (ListView) findViewById(R.id.activity_affiche_cursus_listview);
        EtudiantPersistance persistance = new EtudiantPersistance(this, "projetIF26.db", null, 1);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            numeroEtu = Integer.parseInt(extras.getString("ListeEtudiant_num_etu", "99999"));
        } else {
            numeroEtu = 99999;
        }

        ArrayList<Cursus> cursus = persistance.getCursusFromNumEtu(numeroEtu);
        Etudiant etudiant = persistance.getEtudiant(numeroEtu);

        nomEtuTextView = (TextView) findViewById(R.id.activity_affiche_cursus_nomEtu_id);
        nomEtuTextView.setText(etudiant.getNom());
        prenomEtuTextView = (TextView) findViewById(R.id.activity_affiche_cursus_pernomEtu_id);
        prenomEtuTextView.setText(etudiant.getPrenom());
        csDeTcTextView = (TextView) findViewById(R.id.activity_affiche_cursus_TextView_CSdeTC);
        tmDeTcTextView = (TextView) findViewById(R.id.activity_affiche_cursus_TextView_TMdeTC);
        csTmTcbrTextView = (TextView) findViewById(R.id.activity_affiche_cursus_TextView_CSTMdeTCBR);
        csTmFiliereTextView = (TextView) findViewById(R.id.activity_affiche_cursus_TextView_TotalCSTMFiliere);
        totalBRTextView = (TextView) findViewById(R.id.activity_affiche_cursus_TextView_TotalBR);
        globalCSTMTextView = (TextView) findViewById(R.id.activity_affiche_cursus_TextView_GlobalCSTM);
        globalSTTextView = (TextView) findViewById(R.id.activity_affiche_cursus_TextView_GlobalST);
        globalECTextView = (TextView) findViewById(R.id.activity_affiche_cursus_TextView_GlobalEC);
        globalMETextView = (TextView) findViewById(R.id.activity_affiche_cursus_TextView_GlobalME);
        globalCTTextView = (TextView) findViewById(R.id.activity_affiche_cursus_TextView_GlobalCT);


        UEAdaptateur adaptateur = new UEAdaptateur(this, R.layout.sub_item_semestre_listview_uv, cursus);
        listeSemestre.setAdapter(adaptateur);

        if (etudiant.getAdmission().equals("Branche")) {
            creditsTotaux = 120;
            creditsCSdeTC = 42;
            creditsTMdeTC = 48;
            creditsEC = 8;
            creditsCT = 8;
            creditsME = 8;
            creditsST = 6;
        }



        for (Cursus item : cursus) {
            String resultat = persistance.getResultatFromCursus(item.getSigle(), numeroEtu, item.getSemestre());
            UE ue = persistance.getUVseule(item.getSigle());
            Integer credit = ue.getCredit();
            if (!(resultat.equals("Fx") || resultat.equals("F"))) {
                creditsTotaux += credit;
                switch (ue.getCategorie()) {
                    case ("CS"):
                        switch (item.getAffectation()) {
                            case ("TC"):
                                creditsCSdeTC += credit;
                                break;
                            case ("TCBR"):
                                creditsCSTMdeTCBR += credit;
                                break;
                            case ("Filiere"):
                                creditsCSTMdeFliere += credit;
                                break;
                        }
                        break;
                    case ("TM"):
                        switch (item.getAffectation()) {
                            case ("TC"):
                                creditsTMdeTC += credit;
                                break;
                            case ("TCBR"):
                                creditsCSTMdeTCBR += credit;
                                break;
                            case ("Filiere"):
                                creditsCSTMdeFliere += credit;
                                break;
                        }
                        break;
                    case ("ME") :
                        creditsME += credit;
                        break;
                    case ("CT") :
                        creditsEC += credit;
                        break;
                    case ("HT") :
                        creditsCT += credit;
                        break;
                    case ("ST") :
                        creditsST += credit;
                        break;
                }
            }
        }

        int totalBR = creditsCSTMdeTCBR + creditsCSTMdeFliere;
        int globalCSTM = creditsCSdeTC + creditsTMdeTC + creditsCSTMdeTCBR + creditsCSTMdeFliere;

        csDeTcTextView.setText(getString(R.string.creditCsdeTC, creditsCSdeTC));
        tmDeTcTextView.setText(getString(R.string.creditTmdeTC, creditsTMdeTC));
        csTmTcbrTextView.setText(getString(R.string.creditCSTmdeTCBR, creditsCSTMdeTCBR));
        csTmFiliereTextView.setText(getString(R.string.creditCsTMFiliere, creditsCSTMdeFliere));
        totalBRTextView.setText(getString(R.string.totalBR, totalBR));
        globalCSTMTextView.setText(getString((R.string.globalCSTM), globalCSTM));
        globalSTTextView.setText(getString(R.string.globalST, creditsST));
        globalECTextView.setText(getString(R.string.globalEC, creditsEC));
        globalMETextView.setText(getString(R.string.globalME, creditsME));
        globalCTTextView.setText(getString(R.string.globalCT, creditsCT));



        totalCreditTextView.setText(String.valueOf(creditsTotaux));
        globalCreditTextView.setText(String.valueOf(creditsGlobaux));
    }
}
