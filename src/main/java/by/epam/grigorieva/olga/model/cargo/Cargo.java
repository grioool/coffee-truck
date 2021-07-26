package by.epam.grigorieva.olga.model.cargo;

import by.epam.grigorieva.olga.model.coffee.Coffee;

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
    public String toString() {
        StringBuilder result = new StringBuilder();
        return result.append(coffeeBeanType).append(coffeePackingType).toString();
    }
}
