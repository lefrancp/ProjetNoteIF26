package fr.utt.if26.projetnoteif26;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button listeEtuButton;
    Button creerEtuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listeEtuButton = findViewById(R.id.main_acivity_button_liste_etudiant_id);
        creerEtuButton = findViewById(R.id.main_acivity_button_creer_etudiant_id);

        listeEtuButton.setOnClickListener(this);
        creerEtuButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_acivity_button_liste_etudiant_id :
                OuvreListeEtudiant();
                break;
            case R.id.main_acivity_button_creer_etudiant_id :
                OuvreCreerEtudiant();
                break;
        }

    }

    public void OuvreListeEtudiant() {
        Intent intent = new Intent(this,ListeEtudiant.class);
        startActivity(intent);
    }
    public void OuvreCreerEtudiant() {
        Intent intent = new Intent(this, CreerEtudiant.class);
        startActivity(intent);
    }
}
