package fr.utt.if26.projetnoteif26;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AjouterUV extends AppCompatActivity implements View.OnClickListener {

    Button submit;
    Button retour;

    EditText sigleUE;
    Spinner categorieUE;
    EditText creditUE;

    EtudiantPersistance persistence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_uv);

        submit = (Button) findViewById(R.id.activity_ajouter_uv_Button_submit_id);
        retour = (Button) findViewById(R.id.activity_ajouter_uv_Button_retour_id);

        sigleUE = (EditText) findViewById(R.id.activity_ajouter_uv_EditText_sigleUE_id);
        categorieUE = (Spinner) findViewById(R.id.activity_ajouter_uv_Spinner_categorieUE_id);
        creditUE = (EditText) findViewById(R.id.activity_ajouter_uv_EditText_creditUE_id);

        submit.setOnClickListener(this);
        retour.setOnClickListener(this);

        persistence = new EtudiantPersistance(this,null,null,1);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_ajouter_uv_Button_submit_id :
                UE newUE = new UE(sigleUE.getText().toString(), categorieUE.getSelectedItem().toString(), Integer.parseInt(creditUE.getText().toString()));
                persistence.addUV(newUE);
                Log.i("AAAAAAAAAAAAAAAAAA", persistence.getAllCSlabels().toString());
                Log.i("AAAAAAAAAAAAAAAAAAaaaaa", persistence.getAllUVs().toString());
                Toast.makeText(this, "UE ajoutée", Toast.LENGTH_SHORT).show();
                this.finish();
            case R.id.activity_ajouter_uv_Button_retour_id :
                this.finish();
        }

    }
}
