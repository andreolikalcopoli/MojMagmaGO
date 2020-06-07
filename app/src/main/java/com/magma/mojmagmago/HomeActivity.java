package com.magma.mojmagmago;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class HomeActivity extends AppCompatActivity {

    private PublicFunctions publicFunctions;
    private DataWizard dataWizard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        publicFunctions = new PublicFunctions(HomeActivity.this);

        dataWizard = new DataWizard(HomeActivity.this);

        Route route = dataWizard.getTotal();

        Log.d("length",route.getLength()+"");
        Log.d("profit",route.getProfit()+"");
        Log.d("time",route.getTime()+"");
        Log.d("efficiency",route.getEfficiency()+"");

    }

}
