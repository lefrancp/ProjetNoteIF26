package fr.utt.if26.projetnoteif26;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class EtudiantPersistance extends SQLiteOpenHelper implements PersistanceInterface {



    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "projetIF26.db"; //nom de la base de données
    private static final String TABLE_ETUDIANTS = "etudiants"; //nom de la table
    private static final String ATTRIBUT_NUMEROETU = "numeroEtu"; //clé primaire -> numéro étudiant
    private static final String ATTRIBUT_NOM = "nom";
    private static final String ATTRIBUT_PRENOM = "prenom";
    private static final String ATTRIBUT_ADMISSION = "admission"; //admis en tc ou branche
    private static final String ATTRIBUT_FILIERE = "filiere"; //filière actuelle de l'étudiant

    private static final String TABLE_UVS = "uvs"; //nom de la table
    private static final String ATTRIBUT_SIGLE = "sigle"; //clé primaire -> sigle (LO07, IF26, etc.)
    private static final String ATTRIBUT_CATEGORIE = "categorie"; //cs tm ht etc
    private static final String ATTRIBUT_CREDITS = "credits"; //crédits que rapporte l'uv


    final String table_etudiant_create =
            "CREATE TABLE " + TABLE_ETUDIANTS + "(" +
                    ATTRIBUT_NUMEROETU + " INTEGER primary key," +
                    ATTRIBUT_NOM + " TEXT, " +
                    ATTRIBUT_PRENOM + " TEXT, " +
                    ATTRIBUT_ADMISSION + " TEXT, " +
                    ATTRIBUT_FILIERE + " TEXT" +
                    ")";

    final String table_uvs_create =
            "CREATE TABLE " + TABLE_UVS + "(" +
                    "id INTEGER primary key autoincrement, " +
                    ATTRIBUT_SIGLE + " TEXT, " +
                    ATTRIBUT_CATEGORIE + " TEXT, " +
                    ATTRIBUT_CREDITS + " INTEGER" +
                    ")";


    public EtudiantPersistance(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(table_etudiant_create);
        sqLiteDatabase.execSQL(table_uvs_create);


    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_ETUDIANTS);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_UVS);
            onCreate(sqLiteDatabase);
    }

    @Override
    public void addEtudiant(Etudiant e) {
        ContentValues cv = new ContentValues();
        cv.put(ATTRIBUT_NUMEROETU, e.getNumeroEtu());
        cv.put(ATTRIBUT_NOM, e.getNom());
        cv.put(ATTRIBUT_PRENOM, e.getPrenom());
        cv.put(ATTRIBUT_ADMISSION, e.getAdmission());
        cv.put(ATTRIBUT_FILIERE, e.getFiliere());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_ETUDIANTS,null,cv);

        db.close();
    }

    @Override
    public ArrayList<Etudiant> getAllEtudiants() {

        ArrayList<Etudiant> etudiants = new ArrayList<Etudiant>();
        String query = "SELECT * FROM " + TABLE_ETUDIANTS + ";";
        Cursor cursor = getWritableDatabase().rawQuery(query, null);
        if (cursor.moveToFirst())
            do  {
                Etudiant etudiant = new Etudiant(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3), cursor.getString(4));
                Log.i("query",etudiant.toString());
                etudiants.add(etudiant);
            } while (cursor.moveToNext());
        this.getWritableDatabase().close();
        //Log.d("getetus",etudiants.toString());
        return etudiants;
    }

    @Override
    public void addUV(UE e) {
        ContentValues cv = new ContentValues();
        cv.put(ATTRIBUT_SIGLE, e.getSigle());
        cv.put(ATTRIBUT_CATEGORIE, e.getCategorie());
        cv.put(ATTRIBUT_CREDITS, e.getCredit());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_UVS,null,cv);

        db.close();
    }

    @Override
    public ArrayList<UE> getAllUVs() {

        ArrayList<UE> ues = new ArrayList<UE>();
        String query = "SELECT * FROM " + TABLE_UVS + ";";
        Cursor cursor = getWritableDatabase().rawQuery(query, null);
        if (cursor.moveToFirst())
            do  {
                UE ue = new UE(cursor.getString(0),cursor.getString(1),cursor.getInt(2));
                Log.i("query",ue.toString());
                ues.add(ue);
            } while (cursor.moveToNext());
        this.getWritableDatabase().close();
        //Log.d("getetus",etudiants.toString());
        return ues;
    }

    @Override
    public ArrayList<ArrayList<String>> getAllCSlabels() {
        ArrayList<ArrayList<String>> labels = new ArrayList<ArrayList<String>>();
        ArrayList<String> labelsCS = new ArrayList<String>(Arrays.asList("CS", "Aucun"));
        ArrayList<String> labelsTM = new ArrayList<String>(Arrays.asList("TM", "Aucun"));
        ArrayList<String> labelsMECTHT = new ArrayList<String>(Arrays.asList("ME/CT/HT", "Aucun"));
        ArrayList<String> labelsAutre = new ArrayList<String>(Arrays.asList("UE Supplémentaire", "Aucun"));
        String query = "SELECT * FROM " + TABLE_UVS + ";";
        Cursor cursor = getWritableDatabase().rawQuery(query, null);
        if (cursor.moveToFirst())
            do  {
            labelsAutre.add(cursor.getString(1));
            String categorieLabel = cursor.getString(2);
                switch (categorieLabel){
                    case "CS" :
                    labelsCS.add(cursor.getString(1));
                        break;
                    case "TM" :
                        labelsTM.add(cursor.getString(1));
                        break;
                    case "ME" :
                        labelsMECTHT.add(cursor.getString(1));
                        break;
                    case "CT" :
                        labelsMECTHT.add(cursor.getString(1));
                        break;
                    case "HT" :
                        labelsMECTHT.add(cursor.getString(1));
                        break;
                }
            } while (cursor.moveToNext());
        this.getWritableDatabase().close();
        labels.add(labelsCS);
        labels.add(labelsTM);
        labels.add(labelsMECTHT);
        labels.add(labelsAutre);
        return labels;
    }


}
