package by.epam.grigorieva.olga.model.coffee;

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
            StringBuilder result = new StringBuilder();
            return result.append(priceMultiplier).toString();
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
            StringBuilder result = new StringBuilder();
            return result.append(amountOfCoffee).append(amountOfPack).toString();
        }

    }
}