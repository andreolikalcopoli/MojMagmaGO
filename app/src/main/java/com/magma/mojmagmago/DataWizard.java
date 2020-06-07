package com.magma.mojmagmago;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DataWizard {

    private PublicFunctions publicFunctions;
    private DatabaseReference databaseReference, monthlyDatabaseReference;
    private ArrayList<Route> routes = new ArrayList<>();
    private ArrayList<Route> monthlyRoutes = new ArrayList<>();
    private Route route;
    private Route totalRoute;
    private Route monthlyRoute;

    DataWizard(Context context){
        publicFunctions = new PublicFunctions(context);
        databaseReference = publicFunctions.getDatabaseReference().child("Taxi");
        monthlyDatabaseReference = publicFunctions.getDatabaseReference().child("Monthly");
        route = new Route();
    }

    void readRoutes(final RoutesCallback routesCallback){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Route route = snapshot.child("daily").getValue(Route.class);
                    routes.add(route);
                }

                routesCallback.onCallback(routes);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    void readMonthlyRoutes(final RoutesCallback routesCallback){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Route route = snapshot.getValue(Route.class);
                    monthlyRoutes.add(route);
                }

                routesCallback.onCallback(monthlyRoutes);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    Route getTotal(){
        if(routes.size() > 0)
            totalRoute = route.sumRoutes(routes);
        else {
            readRoutes(new RoutesCallback() {
                @Override
                public void onCallback(ArrayList<Route> routes) {
                    totalRoute = route.sumRoutes(routes);
                }
            });
        }

        return totalRoute;
    }

    Route getMonthly(){
        if(routes.size() > 0)
            monthlyRoute = route.sumRoutes(monthlyRoutes);
        else {
            readMonthlyRoutes(new RoutesCallback() {
                @Override
                public void onCallback(ArrayList<Route> routes) {
                    monthlyRoute = route.sumRoutes(routes);
                }
            });
        }

        return monthlyRoute;
    }

    public interface RoutesCallback{
        void onCallback(ArrayList<Route> routes);
    }

}
