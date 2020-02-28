package at.htlkaindorf.bsp_development.collections;

import android.graphics.Color;

import java.time.YearMonth;

import androidx.annotation.NonNull;

public class Car extends Vehicle {
    private Color color;
    private double price;

    public Car(String brand, String model, YearMonth constructionYear, Color color, double price) {
        super(brand, model, constructionYear);
        this.color = color;
        this.price = price;
    }

    public Car(Color color, double price) {
        this("BMW","X5", YearMonth.now());
        this.color = color;
        this.price = price;
    }

    public Car(String brand, String model, YearMonth constructionYear) {
        super(brand, model, constructionYear);
    }

    @NonNull
    @Override
    public String toString() {
//        return String.format("%s %s %1.2f", getBrand(), getModel(), price);
        return String.format("%s %1.2f", super.toString(),price);
    }

    @Override
    public void test() {
        super.test();
    }

    public static void main(String[] args) {
        Car car = new Car("BMW","X3", YearMonth.now(), Color.valueOf(Color.BLACK), 12000);
    }

}
