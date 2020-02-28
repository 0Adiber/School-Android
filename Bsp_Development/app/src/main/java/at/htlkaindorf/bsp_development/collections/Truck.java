package at.htlkaindorf.bsp_development.collections;

import java.time.YearMonth;

import androidx.annotation.NonNull;

public class Truck extends Vehicle {

    private float maxWeight;

    public Truck(String brand, String model, YearMonth constructionYear, float maxWeight) {
        super(brand, model, constructionYear);
        this.maxWeight = maxWeight;
    }

    public float getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(float maxWeight) {
        this.maxWeight = maxWeight;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("%s (%1.2f)", super.toString(), maxWeight);
    }
}
