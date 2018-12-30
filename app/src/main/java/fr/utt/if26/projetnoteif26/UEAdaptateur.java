package fr.utt.if26.projetnoteif26;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.ArrayList;

public class UEAdaptateur extends ArrayAdapter<UE> {

    ArrayList<UE> ues;
    ArrayList<Cursus> cursus;
    Context c;
    int r;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = ((Activity) c).getLayoutInflater();
        View v = inflater.inflate(r,parent,false);

        UE elt = ues.get(position);

        EtudiantPersistance persistance = new EtudiantPersistance(parent.getContext(), "projetIF26",null, 1);
        String resultat = persistance.getResultatFromCursus(elt.getSigle());


        TextView tx = (TextView) v.findViewById(R.id.listview_uv_categorie_id);
        tx.setText(elt.getCategorie());
        TextView tx2 = (TextView) v.findViewById(R.id.listview_uv_credit_id);
        tx2.setText(Integer.toString(elt.getCredit()));
        TextView tx3 = (TextView) v.findViewById(R.id.listview_uv_sigle_id);
        tx3.setText(elt.getSigle());
        TextView tx4 = (TextView) v.findViewById(R.id.listview_uv_resultat_id);
        tx4.setText(resultat);


        return v;
    }

    public UEAdaptateur(Context context, int resource, ArrayList<UE> objects) {
        super(context, resource, objects);
        this.ues = objects;
        this.c = context;
        this.r = resource;
    }
}
