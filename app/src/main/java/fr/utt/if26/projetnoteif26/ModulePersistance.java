package fr.utt.if26.projetnoteif26;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class ModulePersistance extends SQLiteOpenHelper implements PersistanceInterface {

    public ModulePersistance(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "projetIF26.db"; //nom de la base de données
    private static final String TABLE_ETUDIANTS = "etudiants"; //nom de la table
    private static final String ATTRIBUT_NUMETU = "numEtu"; //clé primaire -> numéro étudiant
    private static final String ATTRIBUT_NOM = "nomEtu";
    private static final String ATTRIBUT_PRENOM = "prenomEtu";
    private static final String ATTRIBUT_ADMISSION = "admissionEtu"; //admis en tc ou branche
    private static final String ATTRIBUT_FILIERE = "filiereEtu"; //filière actuelle de l'étudiant


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String table_etudiant_create =
                "CREATE TABLE " + TABLE_ETUDIANTS + "(" +
                        ATTRIBUT_NUMETU + "TEXT primary key," +
                        ATTRIBUT_NOM + "TEXT, " +
                        ATTRIBUT_PRENOM + "TEXT, " +
                        ATTRIBUT_ADMISSION + "TEXT, " +
                        ATTRIBUT_FILIERE + "TEXT" +
                        ");";
        sqLiteDatabase.execSQL(table_etudiant_create);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        final String table_etudiants_delete =
                "DELETE TABLE " + TABLE_ETUDIANTS;
    }

}
