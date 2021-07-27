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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CoffeeTruckApp {

    private static Logger logger = LoggerFactory.getLogger(CoffeeTruckApp.class);
    private static Truck truck;
    private static Scanner in;

    public static void main(String[] args) {
        in = new Scanner(System.in);
        while (chooseOption()) ;
        in.close();
    }

    public static boolean chooseOption() {
        logger.info("Hello! It's coffee truck!");
        logger.info("You can make your own truck with special amount of coffee and of total.");
        logger.info("Please, choose option. \n 1 - make new truck \n 2 - fill truck with coffee \n 3 - sort coffee \n 4 - find coffee with special characteristics \n 5 - get information about app \n 6 - exit \n");
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
                logger.info("Thank you for using app! Goodbye!");
                return false;
            default:
                logger.info("Please, enter number from 1 to 5.");
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
            logger.info("Please, enter amount of coffee.");
            try {
                String inputAmountOfCoffee = input();
                amountOfCoffee = Integer.parseInt(inputAmountOfCoffee);
            } catch (Exception e) {
                logger.error("", e);
            }
        } while (!isValidInteger(amountOfCoffee));

        do {
            logger.info("Please, enter amount of total.");
            try {
                String inputAmountOfTotal = input();
                amountOfTotal = Integer.parseInt(inputAmountOfTotal);
            } catch (Exception e) {
                logger.error("Invalid number");
            }
        } while (!isValidInteger(amountOfTotal));

        truck = new Truck(amountOfCoffee, amountOfTotal);

        logger.info("Your coffee truck's amount of coffee: " + amountOfCoffee + " and of total: " + amountOfTotal);
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
        } else logger.error("Unknown coffee sort.");
        return null;
    }

    public static void fillTruck() {
        if(truck == null){
            logger.info("Truck does not exist");
            return;
        }
        File fileForFilling = new File("src/main/resources/filling_truck.txt");
        logger.info("File includes: ");
        try(Scanner in = new Scanner(fileForFilling)) {
            while(in.hasNextLine()){
                Coffee coffeeType = null;
                String[] currentLine = in.nextLine().split(" ", 3);
                logger.info(Arrays.toString(currentLine));
                Coffee.CoffeePackingType packingType = Coffee.CoffeePackingType.valueOf(currentLine[1]);
                Coffee.CoffeePhysicalCondition physicalCondition = Coffee.CoffeePhysicalCondition.valueOf(currentLine[2]);
                coffeeType = chooseType(currentLine, coffeeType, physicalCondition);
                truck.addCargo(new Cargo(coffeeType, packingType));
                logger.info(packingType + " " + physicalCondition);
            }
        } catch (FileNotFoundException e) {
            logger.error("", e);
        }
    }

    public static void sortByPrice() {
        List<Cargo> cargoArrayList = truck.getListOfCargos();
        Collections.sort(cargoArrayList, new SortByPrice());
        logger.info(cargoArrayList.toString());
    }

    public static void sortByAmount() {
        List<Cargo> cargoArrayList = truck.getListOfCargos();
        Collections.sort(cargoArrayList, new SortByAmount());
        logger.info(cargoArrayList.toString());

    }

    public static void sortByPriceForKilo() {
    }
    public static void sortCoffee() {
        Scanner in = new Scanner(System.in);
        logger.info("Please, choose way of sorting: \n1 - sort by price \n2 - sort by amount. ");
        switch (in.nextLine()) {
            case "1":
                sortByPrice();
                break;
            case "2":
                sortByAmount();
                break;
            default:
                logger.info("Please, enter 1, 2 or 3.");
        }

    }

    public static void findNeededCoffee() {
        Scanner in = new Scanner(System.in);
        logger.info("Please, choose way of finding:\n1 - finding by price, \n2 - finding by amount.");
        if(in.nextLine().equals("1")) {
            logger.info("Please, enter min of price.");
            double minPrice = in.nextInt();
            logger.info("Please, enter max of price.");
            double maxPrice = in.nextInt();
            findByPrice(minPrice, maxPrice);
        } else if(in.nextLine().equals("2")){
            logger.info("Please, enter min of amount.");
            double minAmount = in.nextInt();
            logger.info("Please, enter max of amount.");
            double maxAmount = in.nextInt();
            findByAmount(minAmount, maxAmount);
        }
    }

    public static List<Cargo> findByPrice(double minPrice, double maxPrice) {
        List<Cargo> cargoArrayList = truck.getListOfCargos();
        for (Cargo cargo : cargoArrayList) {
            if (minPrice <= cargo.getPriceOfCoffee()
                    && maxPrice >= cargo.getPriceOfCoffee()) {
                logger.info(cargo.toString());
            }
        }
        return cargoArrayList;
    }

    public static List<Cargo> findByAmount(double minAmount, double maxAmount) {
        List<Cargo> cargoArrayList = truck.getListOfCargos();
        for (Cargo cargo : cargoArrayList) {
            if (minAmount <= cargo.getAmountOfCoffeeWithPack()
                    && maxAmount >= cargo.getAmountOfCoffeeWithPack()) {
                logger.info(cargo.toString());
            }
        } return cargoArrayList;
    }

    public static void getInformationAboutApp() {
        logger.info("Coffee truck app. You can make your own truck with special amount of coffee and total. We have different types of coffee beans in different physical conditions with different packing types.");
        logger.info("You can sort coffee in price and volume relation");
        logger.info("Also you can find coffee by special characteristics");
        logger.info("App was made by Grigorieva Olga");
    }
}
