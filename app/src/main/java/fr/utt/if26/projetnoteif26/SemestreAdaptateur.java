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
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SemestreAdaptateur extends ArrayAdapter<Cursus> {

    ArrayList<Cursus> cursus;
    Context c;
    int r;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = ((Activity) c).getLayoutInflater();
        View v = inflater.inflate(r,parent,false);

        Cursus elt = cursus.get(position);

        TextView txSemestre = (TextView) v.findViewById(R.id.sub_item_textview_labelSemestre);
        txSemestre.setText(elt.getSemestre());
        ListView lv = (ListView) v.findViewById(R.id.sub_item_semestre_listview);

        EtudiantPersistance persistance = new EtudiantPersistance(parent.getContext(), "projetIF26.db",null,1);
        String sigle = elt.getSigle().toString();
        ArrayList<UE> ues = persistance.getUV(sigle);
        UEAdaptateur adaptateur = new UEAdaptateur(parent.getContext(),R.layout.sub_item_semestre_listview_uv, ues);
        lv.setAdapter(adaptateur);
        return v;
    }

    public SemestreAdaptateur(Context context, int resource, ArrayList<Cursus> objects) {
        super(context, resource, objects);
        this.cursus = objects;
        this.c = context;
        this.r = resource;
    }
}
