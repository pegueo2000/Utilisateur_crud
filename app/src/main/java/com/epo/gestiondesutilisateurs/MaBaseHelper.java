package com.epo.gestiondesutilisateurs;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MaBaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "Utilisateur.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "users";
    private   static final String COLUMN_ID = "id";
    private static final String COLUMN_NOM = "nom";
    private static final String COLUMN_PRENOM = "prenom";
    private static final String COLUMN_SEXE = "sexe";
    private static final String COLUMN_AGE = "age";
    private static final String COLUMN_PROFESSION = "profession";
    private  static final String COLUMN_TELEPHONE = "telephone";

    public MaBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context= context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_NOM + " TEXT, "+
                COLUMN_PRENOM + " TEXT, " +
                COLUMN_AGE + " INTEGER, " +
                COLUMN_SEXE + " TEXT, " +
                COLUMN_PROFESSION + " TEXT,"+
                COLUMN_TELEPHONE + " TEXT); ";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    void addUser(String nom, String prenom, int age, String sexe, String profession, String telephone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NOM, nom);
        cv.put(COLUMN_PRENOM, prenom);
        cv.put(COLUMN_AGE, age);
        cv.put(COLUMN_SEXE, sexe);
        cv.put(COLUMN_PROFESSION, profession);
        cv.put(COLUMN_TELEPHONE, telephone);
        long result = db.insert(TABLE_NAME, null,cv);
        if (result==-1){
            Toast.makeText(context, "Echec", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Succes", Toast.LENGTH_SHORT).show();
        }
        
    }
}
