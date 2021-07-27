package by.epam.grigorieva.olga.model.coffee;

import java.util.Objects;

public abstract class Coffee {

    public Coffee(CoffeePhysicalCondition physicalCondition) {
        this.coffeePhysicalCondition = physicalCondition;
    }

    private final CoffeePhysicalCondition coffeePhysicalCondition;

    public CoffeePhysicalCondition getPhysicalCondition() {
        return coffeePhysicalCondition;
    }

    public abstract double getPriceForKilo();

    public enum CoffeePhysicalCondition {
        COFFEE_GROUNDS(1.5),
        GROUND_COFFEE(1.2),
        INSTANT_COFFEE(1.0),
        INSTANT_COFFEE_PACKAGED(1.1);

        private final double priceMultiplier;

        CoffeePhysicalCondition(double priceMultiplier) {
            this.priceMultiplier = priceMultiplier;
        }

        public double getPriceMultiplier() {
            return priceMultiplier;
        }

        @Override
        public String toString(){
            return String.valueOf(priceMultiplier);
        }
    }

    public enum CoffeePackingType {
        SMALL_PACKAGE(10, 40),
        BIG_PACKAGE(30,200),
        TIN(20, 60),
        BAG(100, 500);

        private final int amountOfPack;
        private final int amountOfCoffee;

        CoffeePackingType(int amountOfPack, int amountOfCoffee) {
            this.amountOfPack = amountOfPack;
            this.amountOfCoffee = amountOfCoffee;
        }

        public int getAmountOfPack() {
            return amountOfPack;
        }

        public int getAmountOfCoffee() {
            return amountOfCoffee;
        }

        @Override
        public String toString(){
            return "{\n\tAmount of coffee: " + String.valueOf(amountOfCoffee) + "\n\tAmount of pack: " + amountOfPack + "\n}";
        }
    }

    @Override
    public boolean equals(Object comparable) {
        if (this == comparable) return true;
        if (comparable == null || getClass() != comparable.getClass()) return false;
        Coffee coffee = (Coffee) comparable;
        return coffeePhysicalCondition == coffee.coffeePhysicalCondition;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + coffeePhysicalCondition.hashCode();
        return result;
    }
}