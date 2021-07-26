package by.epam.grigorieva.olga.model.truck;

import by.epam.grigorieva.olga.model.cargo.Cargo;
import com.sun.source.tree.BreakTree;

import java.util.ArrayList;
import java.util.List;

public class Truck {

    private final int maxAmountOfCargo;
    private final int maxAmountOfTotal;
    private final List<Cargo> cargoArrayList;

    public Truck(int maxAmountOfCoffee, int maxAmountOfTotal){
        this.maxAmountOfCargo = maxAmountOfCoffee;
        this.maxAmountOfTotal = maxAmountOfTotal;
        this.cargoArrayList = new ArrayList<>();
    }

    public void addCargo(Cargo cargo){
        if(cargo.getAmountOfCoffeeWithPack() + this.getCurrentAmountOfCargo() > maxAmountOfCargo
        || cargo.getPriceOfCoffee() + this.getCurrentAmountOfTotal() > maxAmountOfTotal) {
            System.out.println("Amount or total of coffee bigger than amount of coffee truck!");
        } else {
            cargoArrayList.add(cargo);
            System.out.println("Cargo was added.");
        }
    }

    public int getCurrentAmountOfCargo(){
        int currentAmount = 0;
        for (Cargo cargo : cargoArrayList) {
            currentAmount = currentAmount + cargo.getAmountOfCoffeeWithPack();
        }
        return currentAmount;
    }

    public double getCurrentAmountOfTotal(){
        double currentAmount = 0;
        for (Cargo cargo : cargoArrayList) {
            currentAmount = currentAmount + cargo.getPriceOfCoffee();
        }
        return currentAmount;
    }

    public List<Cargo> getListOfCargos(){
        return new ArrayList<>(cargoArrayList);
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
            return result.append(cargoArrayList).toString();
    }

}
