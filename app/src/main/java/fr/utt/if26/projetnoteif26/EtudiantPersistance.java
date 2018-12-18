package fr.utt.if26.projetnoteif26;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;

public class EtudiantPersistance extends SQLiteOpenHelper implements PersistanceInterface {



    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "projetIF26.db"; //nom de la base de données
    private static final String TABLE_ETUDIANTS = "etudiants"; //nom de la table
    private static final String ATTRIBUT_NUMEROETU = "numeroEtu"; //clé primaire -> numéro étudiant
    private static final String ATTRIBUT_NOM = "nom";
    private static final String ATTRIBUT_PRENOM = "prenom";
    private static final String ATTRIBUT_ADMISSION = "admission"; //admis en tc ou branche
    private static final String ATTRIBUT_FILIERE = "filiere"; //filière actuelle de l'étudiant

    public EtudiantPersistance(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String table_etudiant_create =
                "CREATE TABLE " + TABLE_ETUDIANTS + "(" +
                        ATTRIBUT_NUMEROETU + " INTEGER primary key," +
                        ATTRIBUT_NOM + " TEXT, " +
                        ATTRIBUT_PRENOM + " TEXT, " +
                        ATTRIBUT_ADMISSION + " TEXT, " +
                        ATTRIBUT_FILIERE + " TEXT" +
                        ")";
        sqLiteDatabase.execSQL(table_etudiant_create);

    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        final String table_etudiants_delete =
                "DROP TABLE IF EXISTS " + TABLE_ETUDIANTS + ";";
        sqLiteDatabase.execSQL(table_etudiants_delete);
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


}
