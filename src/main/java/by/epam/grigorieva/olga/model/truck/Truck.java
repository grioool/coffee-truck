package by.epam.grigorieva.olga.model.truck;

import by.epam.grigorieva.olga.model.cargo.Cargo;
import com.sun.source.tree.BreakTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public boolean equals(Object  comparable) {
        if (this ==  comparable) return true;
        if (comparable == null || getClass() !=  comparable.getClass()) return false;
        Truck truck = (Truck)  comparable;
        return maxAmountOfCargo == truck.maxAmountOfCargo && maxAmountOfTotal == truck.maxAmountOfTotal && Objects.equals(cargoArrayList, truck.cargoArrayList);
    }

    @Override
    public int hashCode() {
        int result = 1;
        return 31 * result + maxAmountOfTotal + maxAmountOfCargo;
    }

    @Override
    public String toString(){
        return String.valueOf(cargoArrayList);
    }

}
