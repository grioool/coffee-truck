package by.epam.grigorieva.olga.model.coffee;

public class ArabicaCoffee extends Coffee {
    public ArabicaCoffee(CoffeePhysicalCondition coffeePhysicalCondition){
        super(coffeePhysicalCondition);
    }

    @Override
    public double getPriceForKilo(){
        return 30 * getPhysicalCondition().getPriceMultiplier();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString(){
        return "Arabica: physical condition - " + getPhysicalCondition() + " price for kilo - " + getPriceForKilo();
    }
}
