package at.htlkaindorf.bsp_development.collections;

import java.time.YearMonth;

import androidx.annotation.NonNull;

public class Vehicle {

    private String brand;
    private String model;
    private YearMonth constructionYear;

    public Vehicle(String brand, String model, YearMonth constructionYear) {
        this.brand = brand;
        this.model = model;
        this.constructionYear = constructionYear;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public YearMonth getConstructionYear() {
        return constructionYear;
    }

    public void setConstructionYear(YearMonth constructionYear) {
        this.constructionYear = constructionYear;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("%s %s", brand, model);
    }

    protected void test() {

    }

}
