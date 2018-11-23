package fr.utt.if26.projetnoteif26;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class CreerEtudiant extends AppCompatActivity {

    EditText numeroEtu;
    EditText nom;
    EditText prenom;
    Spinner spinnerAdmission;
    Spinner spinnerFiliere;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_etudiant);

        numeroEtu = findViewById(R.id.activity_creer_etudiant_edit_text_num_id);
        nom = findViewById(R.id.activity_creer_etudiant_edit_text_nom_id);
        prenom = findViewById(R.id.activity_creer_etudiant_edit_text_prenom_id);
        spinnerAdmission = findViewById(R.id.activity_creer_etudiant_spinner_admission_id);
        spinnerFiliere = findViewById(R.id.activity_creer_etudiant_spinner_filiere_id);
        submit = findViewById(R.id.activity_creer_etudiant_button_id);

        


    }
}
