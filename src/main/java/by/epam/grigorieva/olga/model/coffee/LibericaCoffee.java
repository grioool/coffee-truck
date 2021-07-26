package by.epam.grigorieva.olga.model.coffee;

public class LibericaCoffee extends Coffee {
    public LibericaCoffee(CoffeePhysicalCondition coffeePhysicalCondition){
        super(coffeePhysicalCondition);
    }

    @Override
    public double getPriceForKilo(){
        return 20 * getPhysicalCondition().getPriceMultiplier();
    }
}
