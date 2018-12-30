package fr.utt.if26.projetnoteif26;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AfficheCursus extends AppCompatActivity {

    TextView numEtuTextView;
    Integer numeroEtu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche_cursus);

        numEtuTextView = (TextView) findViewById(R.id.activity_affiche_cursus_numetu_id);
        ListView listeSemestre = (ListView) findViewById(R.id.activity_affiche_cursus_listview);
        EtudiantPersistance persistance = new EtudiantPersistance(this, "projetIF26.db", null, 1);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            numEtuTextView.setText(extras.getString("ListeEtudiant_num_etu"), TextView.BufferType.EDITABLE);
        } else {
            numEtuTextView.setText("99999");
        }

        numeroEtu = Integer.parseInt(numEtuTextView.getText().toString());
        ArrayList<Cursus> cursus = persistance.getCursusFromNumEtu(numeroEtu);

        SemestreAdaptateur adaptateur = new SemestreAdaptateur(this, R.layout.sub_item_cursus_listview_semestre, cursus);
        listeSemestre.setAdapter(adaptateur);
    }
}
