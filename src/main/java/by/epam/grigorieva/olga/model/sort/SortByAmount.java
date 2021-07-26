package by.epam.grigorieva.olga.model.sort;

import by.epam.grigorieva.olga.model.cargo.Cargo;

import java.util.Comparator;

public class SortByAmount implements Comparator<Cargo>{
    public int compare(Cargo cargo1, Cargo cargo2) {
           return ((Integer)cargo1.getAmountOfCoffeeWithPack()).compareTo(cargo2.getAmountOfCoffeeWithPack());
    }
}

