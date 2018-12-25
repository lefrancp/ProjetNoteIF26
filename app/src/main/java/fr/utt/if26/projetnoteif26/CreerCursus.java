package fr.utt.if26.projetnoteif26;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreerCursus extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{

    TextView numeroSemestre;
    Spinner labelSemestre;
    Spinner cS1;
    Spinner resultatCS1;
    Spinner cS2;
    Spinner resultatCS2;
    Spinner tM1;
    Spinner resultatTM1;
    Spinner tM2;
    Spinner resultatTM2;
    Spinner mECTHT1;
    Spinner resultatMECTHT1;
    Spinner mECTHT2;
    Spinner resultatMECTHT2;
    Spinner autre1;
    Spinner resultatAutre1;
    Spinner autre2;
    Spinner resultatAutre2;
    Button ajouterUV;
    Button ajouterSemestre;

    EtudiantPersistance persistance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_cursus);

        numeroSemestre = (TextView) findViewById(R.id.activity_creer_cursus_textView_numeroSemestre_id);
        labelSemestre = (Spinner) findViewById(R.id.activity_creer_cursus_spinner_labelSemestre_id);

        cS1 = (Spinner) findViewById(R.id.activity_creer_cursus_Spinner_CS1_id);
        resultatCS1 = (Spinner) findViewById(R.id.activity_creer_cursus_Spinner_ResultatCS1_id);
        cS2 = (Spinner) findViewById(R.id.activity_creer_cursus_Spinner_CS2_id);
        resultatCS2 = (Spinner) findViewById(R.id.activity_creer_cursus_Spinner_ResultatCS2_id);
        tM1 = (Spinner) findViewById(R.id.activity_creer_cursus_Spinner_TM1_id);
        resultatTM1 = (Spinner) findViewById(R.id.activity_creer_cursus_Spinner_ResultatTM1_id);
        tM2 = (Spinner) findViewById(R.id.activity_creer_cursus_Spinner_TM2_id);
        resultatTM2 = (Spinner) findViewById(R.id.activity_creer_cursus_Spinner_ResultatTM2_id);
        mECTHT1 = (Spinner) findViewById(R.id.activity_creer_cursus_Spinner_MECTHT1_id);
        resultatMECTHT1 = (Spinner) findViewById(R.id.activity_creer_cursus_Spinner_ResultatMECTHT1_id);
        mECTHT2 = (Spinner) findViewById(R.id.activity_creer_cursus_Spinner_MECTHT2_id);
        resultatMECTHT2 = (Spinner) findViewById(R.id.activity_creer_cursus_Spinner_ResultatMECTHT2_id);
        autre1 = (Spinner) findViewById(R.id.activity_creer_cursus_Spinner_autre1_id);
        resultatAutre1 = (Spinner) findViewById(R.id.activity_creer_cursus_Spinner_ResultatAutre1_id);
        autre2 = (Spinner) findViewById(R.id.activity_creer_cursus_Spinner_autre2_id);
        resultatAutre2 = (Spinner) findViewById(R.id.activity_creer_cursus_Spinner_ResultatAutre2_id);

        ajouterUV = (Button) findViewById(R.id.activity_creer_cursus_Button_ajouterUV_id);
        ajouterSemestre = (Button) findViewById(R.id.activity_creer_cursus_Button_ajouterSemestre_id);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String numSemestreExtra = extras.getString("numero_semestre");
            numeroSemestre.setText(numSemestreExtra, TextView.BufferType.EDITABLE);
        } else {
            numeroSemestre.setText(String.valueOf(0));
        }

        ajouterUV.setOnClickListener(this);
        ajouterSemestre.setOnClickListener(this);

        numeroSemestre.setText(String.valueOf(Integer.parseInt(numeroSemestre.getText().toString())+ 1));

        loadSpinnerData();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_creer_cursus_Button_ajouterUV_id :
                Intent intentUV = new Intent(this,AjouterUV.class);
                startActivity(intentUV);
                break;
            case R.id.activity_creer_cursus_Button_ajouterSemestre_id :
                //PENSER A AJOUTER LE SEMESTRE A LA BDD DU CURSUS
                Intent intentSemestre = new Intent(this, CreerCursus.class);
                intentSemestre.putExtra("numero_semestre", numeroSemestre.getText().toString());
                startActivity(intentSemestre);
        }

    }

    private void loadSpinnerData() {

        persistance = new EtudiantPersistance(this,null,null,1);
        List<String> labelsCS = persistance.getAllCSlabels().get(0);
        List<String> labelsTM = persistance.getAllCSlabels().get(1);
        List<String> labelsMECTHT = persistance.getAllCSlabels().get(2);

        List<String> labelsAutre = persistance.getAllCSlabels().get(3);

        ArrayAdapter<String> dataAdapterCS = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, labelsCS);
        ArrayAdapter<String> dataAdapterTM = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, labelsTM);
        ArrayAdapter<String> dataAdapterMECTHT = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, labelsMECTHT);
        ArrayAdapter<String> dataAdapterAutre = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, labelsAutre);

        cS1.setAdapter(dataAdapterCS);
        cS2.setAdapter(dataAdapterCS);
        tM1.setAdapter(dataAdapterTM);
        tM2.setAdapter(dataAdapterTM);
        mECTHT1.setAdapter(dataAdapterMECTHT);
        mECTHT2.setAdapter(dataAdapterMECTHT);
        autre1.setAdapter(dataAdapterAutre);
        autre2.setAdapter(dataAdapterAutre);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
