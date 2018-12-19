package fr.utt.if26.projetnoteif26;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;

public class UVPersistance extends SQLiteOpenHelper implements PersistanceUEInterface {



    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "projetIF26.db"; //nom de la base de données
    private static final String TABLE_UVS = "uvs"; //nom de la table
    private static final String ATTRIBUT_SIGLE = "sigle"; //clé primaire -> sigle (LO07, IF26, etc.)
    private static final String ATTRIBUT_CATEGORIE = "categorie"; //cs tm ht etc
    private static final String ATTRIBUT_AFFECTATION = "affectation"; //a quoi appartient l'uv : tc ou branche
    private static final String ATTRIBUT_CREDITS = "credits"; //crédits que rapporte l'uv
    //private static final String ATTRIBUT_RESULTAT = "filiere"; //filière de l'uv (tc si tc)

    public UVPersistance(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String table_uvs_create =
                "CREATE TABLE " + TABLE_UVS + "(" +
                        ATTRIBUT_SIGLE + " TEXT primary key," +
                        ATTRIBUT_CATEGORIE + " TEXT, " +
                        ATTRIBUT_AFFECTATION + " TEXT, " +
                        ATTRIBUT_CREDITS + " INTEGER " +
                        ")";
        sqLiteDatabase.execSQL(table_uvs_create);

    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        final String table_uvs_delete =
                "DROP TABLE IF EXISTS " + TABLE_UVS + ";";
        sqLiteDatabase.execSQL(table_uvs_delete);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void addUV(UE e) {
        ContentValues cv = new ContentValues();
        cv.put(ATTRIBUT_SIGLE, e.getSigle());
        cv.put(ATTRIBUT_CATEGORIE, e.getCategorie());
        cv.put(ATTRIBUT_AFFECTATION, e.getAffectation());
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
                UE ue = new UE(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3));
                Log.i("query",ue.toString());
                ues.add(ue);
            } while (cursor.moveToNext());
        this.getWritableDatabase().close();
        //Log.d("getetus",etudiants.toString());
        return ues;
    }


}
