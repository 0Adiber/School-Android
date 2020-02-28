package at.htlkaindorf.bsp_development.collections;

import android.graphics.Color;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class VehicleRental {

    private List<Vehicle> vehicles = new ArrayList<>();

    public void addVehicle(Vehicle v) {
        vehicles.add(v);
    }

    public void printVehicles(String type) {
        if(type.equalsIgnoreCase("truck"))
            for(Vehicle v : vehicles) {
                if(v instanceof Truck)
                    System.out.println(v);
            }
        else if(type.equalsIgnoreCase("car"))
            for(Vehicle v : vehicles) {
                if(v instanceof Car)
                    System.out.println(v);
            }
        else
            for(Vehicle v : vehicles) {
                System.out.println(v);
            }
    }

    public static void main(String[] args) {
        VehicleRental vr = new VehicleRental();

        Vehicle vehicle = new Vehicle("nix", "unkwn", YearMonth.now());

        vr.addVehicle(vehicle);

        Car car1 = new Car("VW", "GOLF", YearMonth.now(), null, 6777.99);
        vr.addVehicle(car1);
        Vehicle truck1 = new Truck("asdf", "asdf", YearMonth.now(), 3_000);
        vr.addVehicle(truck1);
        if(truck1 instanceof Truck)
            ((Truck)truck1).setMaxWeight(4_000);
        vr.printVehicles("car");
        System.out.println("-----");
        vr.printVehicles("truck");
        System.out.println("------");
        vr.printVehicles("none");
    }

}
