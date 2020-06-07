package com.magma.mojmagmago;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LoginActivity extends AppCompatActivity {

    private EditText etCompanyName, etPassword;
    private Button btnLogin;
    private DatabaseReference databaseReference;
    private String companyName, password;
    private PublicFunctions publicFunctions;
    private FirebaseConfiguration firebaseConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etCompanyName = (EditText)findViewById(R.id.eCompanyName);
        etPassword = (EditText)findViewById(R.id.ePassword);

        btnLogin = (Button)findViewById(R.id.btnSignIn);

        publicFunctions = new PublicFunctions(LoginActivity.this);

        firebaseConfiguration = new FirebaseConfiguration(LoginActivity.this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkEmpty()) {
                    if (firebaseConfiguration.setUpFirebase(etCompanyName.getText().toString().trim())) {
                        databaseReference = firebaseConfiguration.getDatabaseReference();
                        checkDetails();
                    }
                }
            }
        });

        if(publicFunctions.isLogged())
            move();
    }

    private boolean checkEmpty(){
        if (etCompanyName.getText().toString().trim().equals("")) {
            publicFunctions.displayToast("Unesite ime kompanije!");
            return false;
        } else if (etPassword.getText().toString().trim().equals("")) {
            publicFunctions.displayToast("Unesite lozinku!");
            return false;
        } else return true;
    }

    private void checkDetails() {
        getDetails(new Callback() {
            @Override
            public void onCallback(String value) {
                if (!etCompanyName.getText().toString().trim().equals(companyName))
                    publicFunctions.displayToast("Pogresan naziv kompanije!");
                else if (!etPassword.getText().toString().trim().equals(password))
                    publicFunctions.displayToast("Pogresna lozinka!");
                else success();
            }
        });
    }

    private void success(){
        publicFunctions.displayToast("Uspesno prijavljivanje!");
        publicFunctions.saveString("company", companyName);
        move();
    }

    private void move(){
        saveTime();
    }

    private void getDetails(final Callback callback){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                companyName = dataSnapshot.child("companyName").getValue(String.class);
                password = dataSnapshot.child("password").getValue(String.class);

                callback.onCallback(password);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void saveTime(){
        String timeStamp = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
        databaseReference.child("Owner").child("loginTime").setValue(timeStamp);
    }

    protected interface Callback{
        void onCallback(String value);
    }
}
