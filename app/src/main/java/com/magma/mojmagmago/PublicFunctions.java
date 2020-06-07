package com.magma.mojmagmago;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

public class PublicFunctions {

    private Context context;
    private FirebaseConfiguration firebaseConfiguration;

    PublicFunctions(Context context){
        this.context = context;
        firebaseConfiguration=new FirebaseConfiguration(context);
        firebaseConfiguration.setUpFirebase(loadString("company"));
    }

    void displayToast(String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }

    boolean isLogged(){
        return !loadString("company").equals("no");
    }

    String getUsername(){ return loadString("username"); }

    DatabaseReference getDatabaseReferenceUsername(){ return firebaseConfiguration.getDatabaseReference().child("Taxi").child(getUsername()); }

    DatabaseReference getDatabaseReference(){ return firebaseConfiguration.getDatabaseReference(); }

    void saveString(String s,String zaCuvanje){
        try {
            SharedPreferences sharedPreferences = PreferenceManager
                    .getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(s, zaCuvanje);
            editor.apply();
        } catch (Exception e) {
            //Crashlytics.logException(e);
        }
    }

    String loadString(String s){
        try {
            SharedPreferences sharedPreferences = PreferenceManager
                    .getDefaultSharedPreferences(context);
            return sharedPreferences.getString(s, "no");
        } catch (Exception e) {
            //Crashlytics.logException(e);
            return "no";
        }
    }

    void saveBool(String s,boolean zaCuvanje){
        try {
            SharedPreferences sharedPreferences = PreferenceManager
                    .getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(s, zaCuvanje);
            editor.apply();
        } catch (Exception e) {
            //Crashlytics.logException(e);
        }
    }

    boolean loadBool(String s){
        try {
            SharedPreferences sharedPreferences = PreferenceManager
                    .getDefaultSharedPreferences(context);
            return sharedPreferences.getBoolean(s, false);
        } catch (Exception e) {
            //Crashlytics.logException(e);
            return false;
        }
    }
}
