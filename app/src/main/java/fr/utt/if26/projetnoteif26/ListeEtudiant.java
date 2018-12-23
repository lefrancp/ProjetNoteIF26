package fr.utt.if26.projetnoteif26;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ListeEtudiant extends AppCompatActivity {

    Button afficherCursus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_etudiant);

        ListView listeEtu = (ListView) findViewById(R.id.etudiant_liste);
        EtudiantPersistance persistance = new EtudiantPersistance(this,"projetIF26.db",null,1);

        ArrayList<Etudiant> etudiants = persistance.getAllEtudiants();
        EtudiantAdaptateur adaptateur = new EtudiantAdaptateur(this, R.layout.etudiant,etudiants);
        listeEtu.setAdapter(adaptateur);

    }

}
