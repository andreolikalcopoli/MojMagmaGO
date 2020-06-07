package com.magma.mojmagmago;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.List;

public class FirebaseConfiguration {

    private Context context;
    private int companyIndex = -1;
    private List<String> companies = Arrays.asList("TestCompany","Magma");
    private List<String> projectIDs = Arrays.asList("testtaxi-7c684","testtaxi2-bd67d");
    private List<String> applicationIDs = Arrays.asList("1:811353275024:android:3b212b32a02849f5c83615","1:501233926070:android:6c5d0ebb644a349b194b17");
    private List<String> APIkeys = Arrays.asList("AIzaSyD-acKvPDn3uVniQ-8qFrp1qeRxeonCm2g","AIzaSyARBUIjzY94Nm6ZNHKqXeUfTJVCfe6F9Nw");
    private List<String> databseURLs = Arrays.asList("https://testtaxi-7c684.firebaseio.com/","https://testtaxi2-bd67d.firebaseio.com/");
    private DatabaseReference databaseReference;

    FirebaseConfiguration(Context context){
        this.context = context;
    }

    boolean setUpFirebase(String companyName){
        try {
            if(!companyName.equals("no")) {
                companyIndex = companies.indexOf(companyName);
                if (companyIndex == -1) {
                    //Toast.makeText(context, "Vasa kompanija nije registrovana", Toast.LENGTH_SHORT).show();
                    return false;
                } else {
                    configureFirebase(projectIDs.get(companyIndex), applicationIDs.get(companyIndex), APIkeys.get(companyIndex), databseURLs.get(companyIndex));
                    return true;
                }
            } else return false;
        } catch (Exception e) {
            //Crashlytics.logException(e);
            Toast.makeText(context,"Greska prilikom konekcije sa bazom. Kontaktirajte programere!",Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void configureFirebase(String projectID, String applicationID, String APIkey, String databaseURL) {
        try {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setProjectId(projectID)
                    .setApplicationId(applicationID)
                    .setApiKey(APIkey)
                    .setDatabaseUrl(databaseURL)
                    //.setStorageBucket(storageBucket)
                    .build();

            try {
                FirebaseApp.initializeApp(context, options, "secondary");
            } catch (Exception e) {
                Log.d("Exception",e.toString());
            }

            FirebaseApp secondary = FirebaseApp.getInstance("secondary");
            FirebaseDatabase otherDatabase = FirebaseDatabase.getInstance(secondary);

            databaseReference = otherDatabase.getReference();
        } catch (Exception e) {
            //Crashlytics.logException(e);
        }
    }

    DatabaseReference getDatabaseReference(){
        return databaseReference;
    }
}
