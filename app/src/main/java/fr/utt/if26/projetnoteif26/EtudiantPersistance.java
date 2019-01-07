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

    private static final String TABLE_CURSUS = "cursus";
    private static final String ATTRIBUT_CURSUS_NUMETU = "numetu"; //clé étrangère de étudiant : permet d'identifier l'étudiant
    private static final String ATTRIBUT_CURSUS_SIGLE = "sigle"; //clé étrangère de UV : identifie l'uv
    private static final String ATTRIBUT_CURSUS_RESULTAT = "resultat"; //résultat à cette uv
    private static final String ATTRIBUT_CURSUS_SEMESTRE = "semestre"; //à quel semestre ça a été fait (à group by par semestre pour affichage)
    private static final String ATTRIBUT_CURSUS_AFFECTATION = "affectation";
    private static final String ATTRIBUT_CURSUS_NPML = "npml";


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

    final String table_cursus_create =
            "CREATE TABLE " + TABLE_CURSUS + "(" +
                    "id INTEGER primary key autoincrement, " +
                    ATTRIBUT_CURSUS_NUMETU + " INTEGER, " +
                    ATTRIBUT_CURSUS_SIGLE + " TEXT, " +
                    ATTRIBUT_CURSUS_RESULTAT + " TEXT, " +
                    ATTRIBUT_CURSUS_SEMESTRE + " TEXT, " +
                    ATTRIBUT_CURSUS_AFFECTATION + " TEXT, " +
                    ATTRIBUT_CURSUS_NPML + " TEXT" +
                    ")";


    public EtudiantPersistance(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    final String table_uv_insert =
            "INSERT INTO " + TABLE_UVS + " (" + ATTRIBUT_SIGLE + "," + ATTRIBUT_CATEGORIE + "," + ATTRIBUT_CREDITS + ")" +
                    "VALUES " +
                    "('MATH01', 'CS', '6')," +
                    "('CHMA01', 'CS', '6')," +
                    "('PHYS01', 'CS', '6')," +
                    "('MATH02', 'CS', '6')," +
                    "('CHMA02', 'CS', '6')," +
                    "('PHYS02', 'CS', '6')," +
                    "('MS11', 'TM', '6')," +
                    "('PHYS03', 'CS', '6')," +
                    "('LO02', 'TM', '6')," +
                    "('IF14', 'TM', '6')," +
                    "('LO07', 'TM', '6')," +
                    "('GE31', 'ME', '6')," +
                    "('LE02', 'EC', '4')," +
                    "('LE03', 'EC', '4')," +
                    "('LE08', 'EC', '4')," +
                    "('BESST', 'TM', '2')," +
                    "('SI10', 'EC', '4')," +
                    "('TN01', 'TM', '6')," +
                    "('GE31', 'ME', '4')," +
                    "('GE21', 'ME', '4')," +
                    "('ST05', 'ST', '6')," +
                    "('NF04', 'TM', '6')," +
                    "('SH01', 'CS', '6')," +
                    "('CHMA04', 'CS', '6')," +
                    "('C2I', 'TM', '4')," +
                    "('LG02', 'EC', '4')," +
                    "('GE44', 'ME', '4')," +
                    "('LG03', 'EC', '4')," +
                    "('MTC01', 'CT', '4')," +
                    "('IF02', 'CS', '6')," +
                    "('LO12', 'CS', '6')," +
                    "('CS03', 'TM', '6')," +
                    "('EG23', 'TM', '6')," +
                    "('NF16', 'CS', '6')," +
                    "('NF21', 'TM', '6')," +
                    "('GE25', 'ME', '4')," +
                    "('TN02', 'TM', '6')," +
                    "('TN04', 'TM', '6')," +
                    "('GL01', 'TM', '6')," +
                    "('LG11', 'EC', '4')," +
                    "('GE28', 'ME', '4');";

    final String table_etudiant_insert =
            "INSERT INTO " + TABLE_ETUDIANTS + " (" + ATTRIBUT_NUMEROETU + "," + ATTRIBUT_NOM + "," +ATTRIBUT_PRENOM + "," + ATTRIBUT_ADMISSION + "," + ATTRIBUT_FILIERE +")" +
                    "VALUES" +
                    "('39749', 'Lallement', 'Théo', 'TC', 'aucune')";

    final String table_cursus_insert =
            "INSERT  INTO " + TABLE_CURSUS + " (" + ATTRIBUT_CURSUS_NUMETU + "," + ATTRIBUT_CURSUS_SIGLE + "," + ATTRIBUT_CURSUS_RESULTAT + "," + ATTRIBUT_CURSUS_SEMESTRE + "," + ATTRIBUT_CURSUS_AFFECTATION + "," + ATTRIBUT_CURSUS_NPML+ ")" +
                    "VALUES" +
                    "(39749, 'MATH01', 'D', 'TC01', 'TC', 'false')," +
                    "(39749, 'CHMA01', 'D', 'TC01', 'TC', 'false')," +
                    "(39749, 'TN04', 'D', 'TC01', 'TC', 'false')," +
                    "(39749, 'LE02', 'D', 'TC01', 'TC', 'false')," +
                    "(39749, 'SI10', 'D', 'TC01', 'TC', 'false')," +

                    "(39749, 'MATH02', 'F', 'TC02', 'TC', 'false')," +
                    "(39749, 'PHYS01', 'D', 'TC02', 'TC', 'false')," +
                    "(39749, 'BESST', 'E', 'TC02', 'TC', 'false')," +
                    "(39749, 'MS11', 'E', 'TC02', 'TC', 'false')," +
                    "(39749, 'TN01', 'D', 'TC02', 'TC', 'false')," +
                    "(39749, 'LE03', 'D', 'TC02', 'TC', 'false')," +
                    "(39749, 'GE31', 'C', 'TC02', 'TC', 'false')," +

                    "(39749, 'CHMA02', 'F', 'TC03', 'TC', 'false')," +
                    "(39749, 'PHYS02', 'F', 'TC03', 'TC', 'false')," +
                    "(39749, 'NF04', 'E', 'TC03', 'TC', 'false')," +
                    "(39749, 'TN02', 'D', 'TC03', 'TC', 'false')," +
                    "(39749, 'ST05', 'D', 'TC03', 'TC', 'false')," +
                    "(39749, 'LE08', 'C', 'TC03', 'TC', 'false')," +
                    "(39749, 'GE21', 'D', 'TC03', 'TC', 'false')," +

                    "(39749, 'CHMA04', 'B', 'TC04', 'TC', 'false')," +
                    "(39749, 'SH01', 'D', 'TC04', 'TC', 'false')," +
                    "(39749, 'C2I', 'C', 'TC04', 'TC', 'false')," +
                    "(39749, 'GL01', 'C', 'TC04', 'TC', 'false')," +
                    "(39749, 'LG02', 'D', 'TC04', 'TC', 'false')," +
                    "(39749, 'GE44', 'D', 'TC04', 'TC', 'false')," +

                    "(39749, 'CHMA02', 'D', 'TC05', 'TC', 'false')," +
                    "(39749, 'PHYS03', 'E', 'TC05', 'TC', 'false')," +
                    "(39749, 'IF14', 'D', 'TC05', 'TCBR', 'false')," +
                    "(39749, 'LO02', 'F', 'TC05', 'TCBR', 'false')," +
                    "(39749, 'LG03', 'E', 'TC05', 'TC', 'false')," +
                    "(39749, 'MTC01', 'D', 'TC05', 'TC', 'false')," +

                    "(39749, 'IF02', 'C', 'ISI1', 'TCBR', 'false')," +
                    "(39749, 'LO12', 'E', 'ISI1', 'TCBR', 'false')," +
                    "(39749, 'CS03', 'B', 'ISI1', 'TCBR', 'false')," +
                    "(39749, 'EG23', 'D', 'ISI1', 'TCBR', 'false')," +
                    "(39749, 'LO07', 'C', 'ISI1', 'TCBR', 'false')," +
                    "(39749, 'LG11', 'E', 'ISI1', 'TCBR', 'false')"
            ;


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(table_etudiant_create);
        sqLiteDatabase.execSQL(table_uvs_create);
        sqLiteDatabase.execSQL(table_cursus_create);

        sqLiteDatabase.execSQL(table_uv_insert);
        sqLiteDatabase.execSQL(table_etudiant_insert);
        sqLiteDatabase.execSQL(table_cursus_insert);

        Log.i("TABLECURSUS", table_cursus_create);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_ETUDIANTS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_UVS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CURSUS);

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
        db.insert(TABLE_ETUDIANTS, null, cv);

        db.close();
    }

    @Override
    public ArrayList<Etudiant> getAllEtudiants() {

        ArrayList<Etudiant> etudiants = new ArrayList<Etudiant>();
        String query = "SELECT * FROM " + TABLE_ETUDIANTS + ";";
        Cursor cursor = getWritableDatabase().rawQuery(query, null);
        if (cursor.moveToFirst())
            do {
                Etudiant etudiant = new Etudiant(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
                Log.i("query", etudiant.toString());
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
        db.insert(TABLE_UVS, null, cv);
        //Log.i("SLTTTTT",String.valueOf(db.insert(TABLE_UVS,null,cv)));

        db.close();
    }

    @Override
    public ArrayList<UE> getAllUVs() {

        ArrayList<UE> ues = new ArrayList<UE>();
        String query = "SELECT * FROM " + TABLE_UVS + ";";
        Cursor cursor = getWritableDatabase().rawQuery(query, null);
        if (cursor.moveToFirst())
            do {
                UE ue = new UE(cursor.getString(1), cursor.getString(2), cursor.getInt(3));
                Log.i("query", ue.toString());
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
        ArrayList<String> labelsMECTHT = new ArrayList<String>(Arrays.asList("ME/EC/CT", "Aucun"));
        ArrayList<String> labelsAutre = new ArrayList<String>(Arrays.asList("UE Supplémentaire", "Aucun"));
        String query = "SELECT * FROM " + TABLE_UVS + ";";
        Cursor cursor = getWritableDatabase().rawQuery(query, null);
        if (cursor.moveToFirst())
            do {
                labelsAutre.add(cursor.getString(1));
                String categorieLabel = cursor.getString(2);
                switch (categorieLabel) {
                    case "CS":
                        labelsCS.add(cursor.getString(1));
                        break;
                    case "TM":
                        labelsTM.add(cursor.getString(1));
                        break;
                    case "ME":
                        labelsMECTHT.add(cursor.getString(1));
                        break;
                    case "EC":
                        labelsMECTHT.add(cursor.getString(1));
                        break;
                    case "CT":
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

    @Override
    public void addCursus(Cursus c) {
        ContentValues cv = new ContentValues();
        cv.put(ATTRIBUT_CURSUS_NUMETU, c.getNum_etu());
        cv.put(ATTRIBUT_CURSUS_SIGLE, c.getSigle());
        cv.put(ATTRIBUT_CURSUS_RESULTAT, c.getResultat());
        cv.put(ATTRIBUT_CURSUS_SEMESTRE, c.getSemestre());
        cv.put(ATTRIBUT_CURSUS_AFFECTATION, c.getAffectation());
        cv.put(ATTRIBUT_CURSUS_NPML, c.getNPML());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_CURSUS, null, cv);


        db.close();
    }

    @Override
    public ArrayList<Cursus> getAllCursus() {

        ArrayList<Cursus> cursus = new ArrayList<Cursus>();
        String query = "SELECT * FROM " + TABLE_CURSUS + ";";
        Cursor cursor = getWritableDatabase().rawQuery(query, null);
        if (cursor.moveToFirst())
            do {
                Cursus c = new Cursus(cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(6));
                c.setAffectation(cursor.getString(5));
                cursus.add(c);
            } while (cursor.moveToNext());
        this.getWritableDatabase().close();
        //Log.d("getetus",etudiants.toString());
        return cursus;
    }

    @Override
    public ArrayList<Cursus> getCursusFromNumEtu(Integer numEtu) {

        ArrayList<Cursus> cursus = new ArrayList<Cursus>();
        String query = "SELECT * FROM " + TABLE_CURSUS + " WHERE " + ATTRIBUT_CURSUS_NUMETU + " = " + numEtu + ";";
        Cursor cursor = getWritableDatabase().rawQuery(query, null);
        if (cursor.moveToFirst())
            do {
                Cursus c = new Cursus(cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(6));
                c.setAffectation(cursor.getString(5));
                cursus.add(c);
            } while (cursor.moveToNext());
        this.getWritableDatabase().close();
        //Log.d("getetus",etudiants.toString());
        return cursus;
    }


    @Override
    public Etudiant getEtudiant(Integer num_etu) {
        Etudiant etudiant = new Etudiant();
        String query = "SELECT * FROM " + TABLE_ETUDIANTS + " WHERE " + ATTRIBUT_NUMEROETU + " = " + num_etu + ";";
        Cursor cursor = getWritableDatabase().rawQuery(query, null);
        if (cursor.moveToFirst())
            do {
                etudiant = new Etudiant(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
            } while (cursor.moveToNext());
        this.getWritableDatabase().close();
        return etudiant;

    }

    @Override
    public ArrayList<UE> getUV(String sigle) {

        ArrayList<UE> ues = new ArrayList<UE>();
        String query = "SELECT * FROM " + TABLE_UVS + " WHERE " + ATTRIBUT_SIGLE + " = '" + sigle + "';";
        Cursor cursor = getWritableDatabase().rawQuery(query, null);
        if (cursor.moveToFirst())
            do {
                UE uv = new UE(cursor.getString(1), cursor.getString(2), cursor.getInt(3));
                ues.add(uv);
            } while (cursor.moveToNext());
        this.getWritableDatabase().close();
        return ues;
    }

    @Override
    public UE getUVseule(String sigle) {

        UE ue = new UE();
        String query = "SELECT * FROM " + TABLE_UVS + " WHERE " + ATTRIBUT_SIGLE + " = '" + sigle + "';";
        Cursor cursor = getWritableDatabase().rawQuery(query, null);
        if (cursor.moveToFirst())
            do {
                ue = new UE(cursor.getString(1), cursor.getString(2), cursor.getInt(3));
            } while (cursor.moveToNext());


        this.getWritableDatabase().close();
        return ue;
    }

    @Override
    public String getResultatFromCursus(String sigle, int numeroEtu, String labelSemestre) {

        String resultat = "";
        String query = "SELECT c.resultat FROM " + TABLE_CURSUS + " AS c WHERE c.sigle='" + sigle + "' AND c.numEtu=" + numeroEtu + " AND c.semestre='" + labelSemestre + "'";
        Cursor cursor = getWritableDatabase().rawQuery(query, null);
        if (cursor.moveToFirst())
            do {
                resultat = cursor.getString(0);
            } while (cursor.moveToNext());
        this.getWritableDatabase().close();
        return resultat;
    }


}