package by.epam.grigorieva.olga.model.coffee;

public class RobustaCoffee extends Coffee {

    public RobustaCoffee(CoffeePhysicalCondition coffeePhysicalCondition) {
        super(coffeePhysicalCondition);
    }

    @Override
    public double getPriceForKilo() {
        return 10 * getPhysicalCondition().getPriceMultiplier();
    }

    @Override
    public String toString(){
        return "Robusta: physical condition - " + getPhysicalCondition() + " price for kilo - " + getPriceForKilo();
    }
}
