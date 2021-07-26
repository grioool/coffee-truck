package by.epam.grigorieva.olga.model.sort;

import by.epam.grigorieva.olga.model.cargo.Cargo;

import java.util.Comparator;

public class SortByPrice implements Comparator<Cargo>{
    public int compare(Cargo cargo1, Cargo cargo2) {
            return ((Double) cargo1.getPriceOfCoffee()).compareTo(cargo2.getPriceOfCoffee());
    }
}
