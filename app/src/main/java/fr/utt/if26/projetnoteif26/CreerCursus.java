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
    Spinner affectationCS1;
    Spinner cS2;
    Spinner resultatCS2;
    Spinner affectationCS2;
    Spinner tM1;
    Spinner resultatTM1;
    Spinner affectationTM1;
    Spinner tM2;
    Spinner resultatTM2;
    Spinner affectationTM2;
    Spinner mECTHT1;
    Spinner resultatMECTHT1;
    Spinner mECTHT2;
    Spinner resultatMECTHT2;
    Spinner autre1;
    Spinner resultatAutre1;
    Spinner affectationAutre1;
    Spinner autre2;
    Spinner resultatAutre2;
    Spinner affectationAutre2;

    Button ajouterUV;
    Button ajouterSemestre;
    Button terminerCursus;

    Integer numEtu;

    EtudiantPersistance persistance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_cursus);



        numeroSemestre = (TextView) findViewById(R.id.activity_creer_cursus_textView_numeroSemestre_id);
        labelSemestre = (Spinner) findViewById(R.id.activity_creer_cursus_spinner_labelSemestre_id);

        cS1 = (Spinner) findViewById(R.id.activity_creer_cursus_Spinner_CS1_id);
        resultatCS1 = (Spinner) findViewById(R.id.activity_creer_cursus_Spinner_ResultatCS1_id);
        affectationCS1 = (Spinner) findViewById(R.id.activity_creer_cursus_Spinner_AffectationCS1_id);
        cS2 = (Spinner) findViewById(R.id.activity_creer_cursus_Spinner_CS2_id);
        resultatCS2 = (Spinner) findViewById(R.id.activity_creer_cursus_Spinner_ResultatCS2_id);
        affectationCS2 = (Spinner) findViewById(R.id.activity_creer_cursus_Spinner_AffectationCS2_id);
        tM1 = (Spinner) findViewById(R.id.activity_creer_cursus_Spinner_TM1_id);
        resultatTM1 = (Spinner) findViewById(R.id.activity_creer_cursus_Spinner_ResultatTM1_id);
        affectationTM1 = (Spinner) findViewById(R.id.activity_creer_cursus_Spinner_AffectationTM1_id);
        tM2 = (Spinner) findViewById(R.id.activity_creer_cursus_Spinner_TM2_id);
        resultatTM2 = (Spinner) findViewById(R.id.activity_creer_cursus_Spinner_ResultatTM2_id);
        affectationTM2 = (Spinner) findViewById(R.id.activity_creer_cursus_Spinner_AffectationTM2_id);
        mECTHT1 = (Spinner) findViewById(R.id.activity_creer_cursus_Spinner_MECTHT1_id);
        resultatMECTHT1 = (Spinner) findViewById(R.id.activity_creer_cursus_Spinner_ResultatMECTHT1_id);
        mECTHT2 = (Spinner) findViewById(R.id.activity_creer_cursus_Spinner_MECTHT2_id);
        resultatMECTHT2 = (Spinner) findViewById(R.id.activity_creer_cursus_Spinner_ResultatMECTHT2_id);
        autre1 = (Spinner) findViewById(R.id.activity_creer_cursus_Spinner_autre1_id);
        resultatAutre1 = (Spinner) findViewById(R.id.activity_creer_cursus_Spinner_ResultatAutre1_id);
        affectationAutre1 = (Spinner) findViewById(R.id.activity_creer_cursus_Spinner_AffectationAutre1_id);
        autre2 = (Spinner) findViewById(R.id.activity_creer_cursus_Spinner_autre2_id);
        resultatAutre2 = (Spinner) findViewById(R.id.activity_creer_cursus_Spinner_ResultatAutre2_id);
        affectationAutre2 = (Spinner) findViewById(R.id.activity_creer_cursus_Spinner_AffectationAutre2_id);

        ajouterUV = (Button) findViewById(R.id.activity_creer_cursus_Button_ajouterUV_id);
        ajouterSemestre = (Button) findViewById(R.id.activity_creer_cursus_Button_ajouterSemestre_id);
        terminerCursus = (Button) findViewById(R.id.activity_creer_cursus_Button_TerminerCursus_id);



        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.getString("numero_semestre")!= null) {
                String numSemestreExtra = extras.getString("numero_semestre");
                numeroSemestre.setText(numSemestreExtra, TextView.BufferType.EDITABLE);
            } else {
                numeroSemestre.setText(String.valueOf(0));
            }
            if (extras.getString("numero_etu")!= null) {
                numEtu = Integer.parseInt(extras.getString("numero_etu"));
            } else {
                numEtu = 99999;
            }
        } else {
            numeroSemestre.setText(String.valueOf(0));
            numEtu = 99999;
        }

        ajouterUV.setOnClickListener(this);
        ajouterSemestre.setOnClickListener(this);
        terminerCursus.setOnClickListener(this);

        numeroSemestre.setText(String.valueOf(Integer.parseInt(numeroSemestre.getText().toString())+ 1));

        loadSpinnerData();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_creer_cursus_Button_ajouterUV_id :
                Intent intentUV = new Intent(this,AjouterUV.class);
                int requestCode =0;
                startActivityForResult(intentUV, requestCode);
                break;
            case R.id.activity_creer_cursus_Button_ajouterSemestre_id :
                Intent intentSemestre = new Intent(this, CreerCursus.class);
                intentSemestre.putExtra("numero_semestre", numeroSemestre.getText().toString());
                intentSemestre.putExtra("numero_etu", String.valueOf(numEtu));
                ajouterUESaBDD();
                startActivity(intentSemestre);
                this.finish();
                break;
            case R.id.activity_creer_cursus_Button_TerminerCursus_id :
                Intent intentTerminer = new Intent(this, MainActivity.class);
                ajouterUESaBDD();
                startActivity(intentTerminer);
                this.finish();
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case RESULT_OK:
                this.recreate();
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

    private void ajouterUESaBDD() {
        Cursus CursusUE1 = new Cursus(numEtu, cS1.getSelectedItem().toString(), resultatCS1.getSelectedItem().toString(), labelSemestre.getSelectedItem().toString(), "false");
        CursusUE1.setAffectation(affectationCS1.getSelectedItem().toString());
        persistance.addCursus(CursusUE1);
        Cursus CursusUE2 = new Cursus(numEtu, cS2.getSelectedItem().toString(), resultatCS2.getSelectedItem().toString(), labelSemestre.getSelectedItem().toString(), "false");
        CursusUE2.setAffectation(affectationCS2.getSelectedItem().toString());
        persistance.addCursus(CursusUE2);
        Cursus CursusUE3 = new Cursus(numEtu, tM1.getSelectedItem().toString(), resultatTM1.getSelectedItem().toString(), labelSemestre.getSelectedItem().toString(), "false");
        CursusUE3.setAffectation(affectationTM1.getSelectedItem().toString());
        persistance.addCursus(CursusUE3);
        Cursus CursusUE4 = new Cursus(numEtu, tM2.getSelectedItem().toString(), resultatTM2.getSelectedItem().toString(), labelSemestre.getSelectedItem().toString(), "false");
        CursusUE4.setAffectation(affectationTM2.getSelectedItem().toString());
        persistance.addCursus(CursusUE4);
        Cursus CursusUE5 = new Cursus(numEtu, mECTHT1.getSelectedItem().toString(), resultatMECTHT1.getSelectedItem().toString(), labelSemestre.getSelectedItem().toString(), "false");
        persistance.addCursus(CursusUE5);
        Cursus CursusUE6 = new Cursus(numEtu, mECTHT2.getSelectedItem().toString(), resultatMECTHT2.getSelectedItem().toString(), labelSemestre.getSelectedItem().toString(), "false");
        persistance.addCursus(CursusUE6);
        Cursus CursusUE7 = new Cursus(numEtu, autre1.getSelectedItem().toString(), resultatAutre1.getSelectedItem().toString(), labelSemestre.getSelectedItem().toString(), "false");
        if (affectationAutre1.getSelectedItem().toString().equals(" ")) {
            CursusUE7.setAffectation("default");
        } else {
            CursusUE7.setAffectation(affectationAutre1.getSelectedItem().toString());
        }
        persistance.addCursus(CursusUE7);
        Cursus CursusUE8 = new Cursus(numEtu, autre2.getSelectedItem().toString(), resultatAutre2.getSelectedItem().toString(), labelSemestre.getSelectedItem().toString(), "false");
        if (affectationAutre2.getSelectedItem().toString().equals(" ")) {
            CursusUE8.setAffectation("default");
        } else {
            CursusUE8.setAffectation(affectationAutre2.getSelectedItem().toString());
        }
        persistance.addCursus(CursusUE8);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
