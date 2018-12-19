package fr.utt.if26.projetnoteif26;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AjouterUV extends AppCompatActivity implements View.OnClickListener {

    Button submit;
    Button retour;

    EditText sigleUE;
    Spinner categorieUE;
    EditText creditUE;

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


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_ajouter_uv_Button_submit_id :
                break;
            case R.id.activity_ajouter_uv_Button_retour_id :
                break;
        }

    }
}
