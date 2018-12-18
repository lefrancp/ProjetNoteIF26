package fr.utt.if26.projetnoteif26;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EtudiantAdaptateur extends ArrayAdapter<Etudiant> {

    ArrayList<Etudiant> etudiants;
    Context c;
    int r;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = ((Activity) c).getLayoutInflater();
        View v = inflater.inflate(r, parent, false);

        Etudiant elt = etudiants.get(position);

        TextView tx = (TextView) v.findViewById(R.id.etudiant_num);
        tx.setText(Integer.toString(elt.getNumeroEtu()));
        TextView tx2 = (TextView) v.findViewById(R.id.etudiant_nom);
        tx2.setText(elt.getNom());
        TextView tx3 = (TextView) v.findViewById(R.id.etudiant_prenom);
        tx3.setText(elt.getPrenom());
        TextView tx4 = (TextView) v.findViewById(R.id.etudiant_admission);
        tx4.setText(elt.getAdmission());
        TextView tx5 = (TextView) v.findViewById(R.id.etudiant_filiere);
        tx5.setText(elt.getFiliere());

        return v;
    }

    public EtudiantAdaptateur(Context context, int resource,ArrayList<Etudiant> objects) {
        super(context, resource, objects);
        this.etudiants = objects;
        this.c = context;
        this.r = resource;
    }
}
