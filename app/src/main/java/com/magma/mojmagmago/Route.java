package com.magma.mojmagmago;

import java.util.ArrayList;

public class Route {

    private double length = 0;
    private double time = 0;
    private int numberOfRoutes = 0;
    private int profit = 0;
    private double efficiency = 0;
    private double lengthNonRoute = 0;
    private double timeNonRoute = 0;
    private double weight = 0;

    public Route(){}

    public Route(double length, double time, int numberOfRoutes, int profit, double efficiency, double lengthNonRoute, double timeNonRoute, double weight) {
        this.length = length;
        this.time = time;
        this.numberOfRoutes = numberOfRoutes;
        this.profit = profit;
        this.efficiency = efficiency;
        this.lengthNonRoute = lengthNonRoute;
        this.timeNonRoute = timeNonRoute;
        this.weight = weight;
    }

    Route sumRoutes(ArrayList<Route> routes){
        for(int i=0;i<routes.size();i++){
            length+=routes.get(i).getLength();
            time+=routes.get(i).getTime();
            numberOfRoutes+=routes.get(i).getNumberOfRoutes();
            profit+=routes.get(i).getProfit();
            efficiency+=routes.get(i).getEfficiency();
            lengthNonRoute+=routes.get(i).getLengthNonRoute();
            timeNonRoute+=routes.get(i).getTimeNonRoute();
            weight+=routes.get(i).getWeight();
        }

        Route route = new Route(length,time,numberOfRoutes,profit,efficiency,lengthNonRoute,timeNonRoute,weight);

        return route;
    }

    //<editor-fold desc="getters and setters">
    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getNumberOfRoutes() {
        return numberOfRoutes;
    }

    public void setNumberOfRoutes(int numberOfRoutes) {
        this.numberOfRoutes = numberOfRoutes;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit += profit;
    }

    public double getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(double efficiency) {
        this.efficiency = efficiency;
    }

    public double getLengthNonRoute() {
        return lengthNonRoute;
    }

    public void setLengthNonRoute(double lengthNonRoute) {
        this.lengthNonRoute = lengthNonRoute;
    }

    public double getTimeNonRoute() {
        return timeNonRoute;
    }

    public void setTimeNonRoute(double timeNonRoute) {
        this.timeNonRoute = timeNonRoute;
    }
    //</editor-fold>
}
