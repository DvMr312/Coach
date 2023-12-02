package com.example.coach.modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.coach.outils.MesOutils;
import com.example.coach.outils.MySQLiteOpenHelper;

import java.util.Date;

/**
 * Classe exploitant MySQLiteOpenHelper pour manipuler la bdd au format SQLite
 */
public class AccesLocal {

    private String nomBase = "bdCoach.sqlite";
    private Integer versionBase = 1;
    private MySQLiteOpenHelper accesBD;
    private static AccesLocal instance;
    private SQLiteDatabase bd;

    /**
     * Constructeur : création du lien avec la bdd au format SQLite
     * @param context
     */
    private AccesLocal(Context context){
        accesBD= new MySQLiteOpenHelper(context, nomBase, versionBase);
    }

    /**
     * Création d'une instance unique de la classe
     * @param context
     * @return instance unique de la classe
     */
    public static AccesLocal getInstance(Context context){
        if(instance == null){
            instance = new AccesLocal(context);
        }
        return instance;
    }

    /**
     * Ajoute un profil dans la bdd
     * @param profil
     */
    public void ajout(Profil profil){
        bd = accesBD.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("poids", profil.getPoids());
        values.put("taille", profil.getTaille());
        values.put("age", profil.getAge());
        values.put("sexe", profil.getSexe());
        values.put("dateMesure", profil.getDateMesure().toString());
        bd.insert("profil", null, values);
        bd.close();
    }

    /**
     * Récupération du dernier profil enregistré dans la bdd
     * @return dernier profil
     */
    public Profil recupDernier(){
        Profil profil = null;
        bd = accesBD.getReadableDatabase();
        String req = "select * from profil";
        Cursor curseur = bd.rawQuery(req, null);
        curseur.moveToLast();
        if(!curseur.isAfterLast()){
            Date dateMesure = MesOutils.convertStringToDate(curseur.getString(0));
            Log.d("date","*********** date="+dateMesure);
            Integer poids = curseur.getInt(1);
            Integer taille = curseur.getInt(2);
            Integer age = curseur.getInt(3);
            Integer sexe = curseur.getInt(4);
            profil = new Profil(dateMesure, poids, taille, age, sexe);
        }
        curseur.close();
        bd.close();
        return profil;
    }

}