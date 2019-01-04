package fr.utt.if26.projetnoteif26;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.ArrayList;

public class UEAdaptateur extends ArrayAdapter<Cursus> {

    //ArrayList<UE> ues;
    ArrayList<Cursus> cursus;
    String creditsObtenus;
    Context c;
    int r;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = ((Activity) c).getLayoutInflater();
        View v = inflater.inflate(r,parent,false);

        Cursus elt = cursus.get(position);
        String sigle = elt.getSigle().toString();

        EtudiantPersistance persistance = new EtudiantPersistance(parent.getContext(), "projetIF26",null, 1);
        ArrayList<UE> ues = persistance.getUV(sigle);


        for (UE item: ues) {
            String resultat = persistance.getResultatFromCursus(item.getSigle(), elt.num_etu, elt.getSemestre());
            if (resultat.equals("Fx") || resultat.equals("F")) {
                creditsObtenus = "0";
            } else {
                creditsObtenus = Integer.toString(item.getCredit());
            }
            TextView tx = (TextView) v.findViewById(R.id.listview_uv_categorie_id);
            tx.setText(item.getCategorie());
            TextView tx2 = (TextView) v.findViewById(R.id.listview_uv_credit_id);
            tx2.setText(creditsObtenus + " / " + Integer.toString(item.getCredit()) + " credits");
            TextView tx3 = (TextView) v.findViewById(R.id.listview_uv_sigle_id);
            tx3.setText(item.getSigle());
            TextView tx4 = (TextView) v.findViewById(R.id.listview_uv_resultat_id);
            tx4.setText(resultat);
            TextView txSemestre = (TextView) v.findViewById(R.id.sub_item_textview_labelSemestre);
            txSemestre.setText(elt.getSemestre());
        }

        return v;
    }

    public UEAdaptateur(Context context, int resource, ArrayList<Cursus> objects) {
        super(context, resource, objects);
        this.cursus = objects;
        this.c = context;
        this.r = resource;
    }
}