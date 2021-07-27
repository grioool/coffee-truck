package by.epam.grigorieva.olga.model.cargo;

import by.epam.grigorieva.olga.model.coffee.Coffee;

import java.util.Objects;

public class Cargo {

    private final Coffee coffeeBeanType;
    private final Coffee.CoffeePackingType coffeePackingType;

    public Cargo(Coffee coffeeBeanType, Coffee.CoffeePackingType coffeePackingType) {
        this.coffeeBeanType = coffeeBeanType;
        this.coffeePackingType = coffeePackingType;
    }


    public int getAmountOfCoffeeWithPack() {
        return coffeePackingType.getAmountOfCoffee() + coffeePackingType.getAmountOfPack();
    }

    public int getAmountOfCoffee(){
        return coffeePackingType.getAmountOfCoffee();
    }

    public double getPriceOfCoffee() {
        return coffeeBeanType.getPriceForKilo() * getAmountOfCoffee();
    }

    public double getGeneralPriceAmount() {
        return coffeeBeanType.getPriceForKilo();
    }

    @Override
    public boolean equals(Object comparable) {
        if (this == comparable) return true;
        if (comparable == null || getClass() != comparable.getClass()) return false;
        Cargo cargo = (Cargo) comparable;
        return Objects.equals(coffeeBeanType, cargo.coffeeBeanType) && coffeePackingType == cargo.coffeePackingType;
    }

    @Override
    public int hashCode(){
        return Objects.hash(coffeeBeanType, coffeePackingType);
    }

    @Override
    public String toString() {
        return String.valueOf(coffeeBeanType)+  " Packaging type:  " + coffeePackingType + " Price: " + getPriceOfCoffee();
    }
}
