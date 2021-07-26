package by.epam.grigorieva.olga;

import by.epam.grigorieva.olga.model.cargo.Cargo;
import by.epam.grigorieva.olga.model.coffee.ArabicaCoffee;
import by.epam.grigorieva.olga.model.coffee.Coffee;
import by.epam.grigorieva.olga.model.coffee.LibericaCoffee;
import by.epam.grigorieva.olga.model.coffee.RobustaCoffee;
import by.epam.grigorieva.olga.model.sort.SortByAmount;
import by.epam.grigorieva.olga.model.sort.SortByPrice;
import by.epam.grigorieva.olga.model.truck.Truck;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CoffeeTruckApp {

    private static Truck truck;
    private static Scanner in;

    public static void main(String[] args) {
        in = new Scanner(System.in);
        while (chooseOption()) ;
        in.close();
    }

    public static boolean chooseOption() {
        System.out.println("Hello! It's coffee truck!");
        System.out.println("You can make your own truck with special amount of coffee and of total.");
        System.out.println("Please, choose option. \n 1 - make new truck \n 2 - fill truck with coffee \n 3 - sort coffee \n 4 - find coffee with special characteristics \n 5 - get information about app \n 6 - exit \n");
        switch (in.nextLine()) {
            case "1":
                makeNewTruck();
                return true;
            case "2":
                fillTruck();
                return true;
            case "3":
                sortCoffee();
                return true;
            case "4":
                findNeededCoffee();
                return true;
            case "5":
                getInformationAboutApp();
                return true;
            case "6":
                System.out.println("Thank you for using app! Goodbye!");
                return false;
            default:
                System.out.println("Please, enter number from 1 to 5.");
                return true;
        }
    }

    public static String input() {
        return in.nextLine();
    }

    public static boolean isValidInteger(int input) {
        return input > 0 && input < Integer.MAX_VALUE;
    }

    public static void makeNewTruck() {
        int amountOfCoffee = 0;
        int amountOfTotal = 0;

        do {
            System.out.println("Please, enter amount of coffee.");
            try {
                String inputAmountOfCoffee = input();
                amountOfCoffee = Integer.parseInt(inputAmountOfCoffee);
            } catch (Exception e) {
                System.out.println("Invalid number");
            }
        } while (!isValidInteger(amountOfCoffee));

        do {
            System.out.println("Please, enter amount of total.");
            try {
                String inputAmountOfTotal = input();
                amountOfTotal = Integer.parseInt(inputAmountOfTotal);
            } catch (Exception e) {
                System.out.println("Invalid number");
            }
        } while (!isValidInteger(amountOfTotal));

        truck = new Truck(amountOfCoffee, amountOfTotal);

        System.out.println("Your coffee truck's amount of coffee: " + amountOfCoffee + " and of total: " + amountOfTotal);
    }

    public static Coffee chooseType(String[] currentLine, Coffee coffeeType, Coffee.CoffeePhysicalCondition physicalCondition){
        if(currentLine[0].equals("Arabica")){
            coffeeType = new ArabicaCoffee(physicalCondition);
            return coffeeType;
        } else if (currentLine[0].equals("Liberica")){
            coffeeType = new LibericaCoffee(physicalCondition);
            return coffeeType;
        } else if (currentLine[0].equals("Robusta")) {
            coffeeType = new RobustaCoffee(physicalCondition);
            return coffeeType;
        } else System.out.println("Unknown coffee sort.");
        return null;
    }

    public static void fillTruck() {
        if(truck == null){
            System.out.println("Truck does not exist");
            return;
        }
        File fileForFilling = new File("src/main/resources/filling_truck.txt");
        System.out.println("File includes: ");
        try(Scanner in = new Scanner(fileForFilling)) {
            while(in.hasNextLine()){
                Coffee coffeeType = null;
                String[] currentLine = in.nextLine().split(" ", 3);
                System.out.println(Arrays.toString(currentLine));
                Coffee.CoffeePackingType packingType = Coffee.CoffeePackingType.valueOf(currentLine[1]);
                Coffee.CoffeePhysicalCondition physicalCondition = Coffee.CoffeePhysicalCondition.valueOf(currentLine[2]);
                coffeeType = chooseType(currentLine, coffeeType, physicalCondition);
                truck.addCargo(new Cargo(coffeeType, packingType));
                System.out.println(packingType + " " + physicalCondition);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void sortByPrice() {
        List<Cargo> cargoArrayList = truck.getListOfCargos();
        Collections.sort(cargoArrayList, new SortByPrice());
        System.out.println(cargoArrayList.toString());
    }

    public static void sortByAmount() {
        List<Cargo> cargoArrayList = truck.getListOfCargos();
        Collections.sort(cargoArrayList, new SortByAmount());
        System.out.println(cargoArrayList.toString());

    }

    public static void sortByPriceForKilo() {
    }
    public static void sortCoffee() {
        Scanner in = new Scanner(System.in);
        System.out.println("Please, choose way of sorting: \n  1 - sort by price \n 2 - sort by amount. \n 3 - sort by price for kilo.");
        switch (in.nextLine()) {
            case "1":
                sortByPrice();
                break;
            case "2":
                sortByAmount();
                break;
            case "3":
                sortByPriceForKilo();
            default:
                System.out.println("Please, enter 1, 2 or 3.");
        }

    }

    public static void findNeededCoffee() {
        Scanner in = new Scanner(System.in);
        System.out.println("Please, choose way of finding: 1 - finding by price, 2 - finding by amount.");
        if(in.nextLine().equals("1")) {
            System.out.println("Please, enter min of price.");
            double minPrice = in.nextInt();
            System.out.println("Please, enter max of price.");
            double maxPrice = in.nextInt();
            findByPrice(minPrice, maxPrice);
        } else if(in.nextLine().equals("2")){
            System.out.println("Please, enter min of amount.");
            double minAmount = in.nextInt();
            System.out.println("Please, enter max of amount.");
            double maxAmount = in.nextInt();
            findByAmount(minAmount, maxAmount);
        }
    }

    public static List<Cargo> findByPrice(double minPrice, double maxPrice) {
        List<Cargo> cargoArrayList = truck.getListOfCargos();
        for (Cargo cargo : cargoArrayList) {
            if (minPrice <= cargo.getPriceOfCoffee()
                    && maxPrice >= cargo.getPriceOfCoffee()) {
                System.out.println(cargo.toString());
            }
        }
        return cargoArrayList;
    }

    public static List<Cargo> findByAmount(double minAmount, double maxAmount) {
        List<Cargo> cargoArrayList = truck.getListOfCargos();
        for (Cargo cargo : cargoArrayList) {
            if (minAmount <= cargo.getAmountOfCoffeeWithPack()
                    && maxAmount >= cargo.getAmountOfCoffeeWithPack()) {
                System.out.println(cargo.toString());
            }
        } return cargoArrayList;
    }

    public static void getInformationAboutApp() {
        System.out.println("Coffee truck app. You can make your own truck with special amount of coffee and total. We have different types of coffee beans in different physical conditions with different packing types.");
        System.out.println("You can sort coffee in price and volume relation");
        System.out.println("Also you can find coffee by special characteristics");
        System.out.println("App was made by Grigorieva Olga");
    }
}
