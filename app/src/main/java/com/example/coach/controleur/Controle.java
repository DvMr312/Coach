package com.example.coach.controleur;

import android.content.Context;

import com.example.coach.modele.AccesLocal;
import com.example.coach.modele.Profil;
import com.example.coach.outils.Serializer;

import java.util.Date;

public final class Controle {
    private static String nomFic = "saveprofil";
    private static Controle instance;
    private static Profil profil;
    private AccesLocal accesLocal;
    private Controle(Context context) {
        //recupSerialize(context);
        accesLocal = AccesLocal.getInstance(context);
        profil = accesLocal.recupDernier();
    }

    /**
     * Création d'une instance unique de la classe
     * @return l'instance unique
     */
    public final static Controle getInstance(Context context){
        if(instance==null){
            instance = new Controle(context);
        }
        return instance;

    }

    /**
     * Création du profil par rapport aux informations saisies
     * @param poids
     * @param taille en cm
     * @param age
     * @param sexe 1 pour homme, 0 pour femme
     */
    public void creerProfil(Integer poids, Integer taille, Integer age, Integer sexe, Context context){

        profil = new Profil(new Date(), poids, taille, age, sexe);
        accesLocal.ajout(profil);
        //Serializer.serialize(nomFic, profil, context);
    }


    /**
     * getter sur le résultat du calcul de l'IMG pour le profil
     * @return img du profil
     */
    public float getImg(){
        if (profil != null){
            return profil.getImg();
        }else{
            return 0;
        }
    }

    /**
     * getter sur le message correspondant à l'img du profil
     * @return message du profil
     */
    public String getMessage(){
        if (profil != null){
            return profil.getMessage();
        }else {
            return "";
        }

    }

    /**
     * Retourne la taille si le profil existe
     * @return
     */
    public Integer getTaille(){
        if (profil == null){
            return null;
        }else{
            return profil.getTaille();
        }
    }

    /**
     * Retourne l'age si le profil existe
     * @return
     */
    public Integer getAge(){
        if (profil == null){
            return null;
        }else{
            return profil.getAge();
        }
    }

    /**
     * Retourne le poids si le profil existe
     * @return
     */
    public Integer getPoids(){
        if (profil == null){
            return null;
        }else{
            return profil.getPoids();
        }
    }

    /**
     * Retourne le sexe si le profil existe
     * @return
     */
    public Integer getSexe(){
        if(profil == null){
            return null;
        }else{
            return profil.getSexe();
        }
    }

    /**
     * Récupération du profil sérialisé
     * @param context
     */
    private static void recupSerialize(Context context){
        profil = (Profil)Serializer.deSerialize(nomFic, context);


    }


}