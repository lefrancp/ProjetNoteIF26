package fr.utt.if26.projetnoteif26;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class CreerEtudiant extends AppCompatActivity implements View.OnClickListener{

    EditText numeroEtu;
    EditText nom;
    EditText prenom;
    Spinner spinnerAdmission;
    Spinner spinnerFiliere;
    Button submit;
    EtudiantPersistance persistence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_etudiant);

        numeroEtu = (EditText) findViewById(R.id.activity_creer_etudiant_edit_text_num_id);
        nom = (EditText) findViewById(R.id.activity_creer_etudiant_edit_text_nom_id);
        prenom = (EditText) findViewById(R.id.activity_creer_etudiant_edit_text_prenom_id);
        spinnerAdmission = (Spinner) findViewById(R.id.activity_creer_etudiant_spinner_admission_id);
        spinnerFiliere = (Spinner) findViewById(R.id.activity_creer_etudiant_spinner_filiere_id);
        submit = (Button) findViewById(R.id.activity_creer_etudiant_button_id);

        submit.setOnClickListener(this);

        persistence = new EtudiantPersistance(this, null, null, 1);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_creer_etudiant_button_id :
                Etudiant newEtudiant = new Etudiant(Integer.parseInt(numeroEtu.getText().toString()),nom.getText().toString(),prenom.getText().toString(),spinnerAdmission.getSelectedItem().toString(),spinnerFiliere.getSelectedItem().toString());
                persistence.addEtudiant(newEtudiant);

                OuvreCreerCursus();
                break;
    }
}
    public void OuvreCreerCursus() {
        Intent intent = new Intent(this,CreerCursus.class);
        intent.putExtra("numero_etu", numeroEtu.getText().toString());
        startActivity(intent);
    }
}

