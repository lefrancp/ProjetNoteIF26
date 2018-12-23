package fr.utt.if26.projetnoteif26;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

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
    Button ajouterUV;

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

        ajouterUV = (Button) findViewById(R.id.activity_creer_cursus_Button_ajouterUV_id);

        ajouterUV.setOnClickListener(this);


        loadSpinnerData();



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_creer_cursus_Button_ajouterUV_id :
                Intent intent = new Intent(this,AjouterUV.class);
                startActivity(intent);
        }

    }

    private void loadSpinnerData() {

        persistance = new EtudiantPersistance(this,null,null,1);
        List<String> labels = persistance.getAllCSlabels();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, labels);

        cS1.setAdapter(dataAdapter);
        cS2.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
