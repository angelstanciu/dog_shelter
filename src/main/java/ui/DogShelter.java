package ui;

import entity.Dog;
import entity.Food;
import exception.NoDogFoundException;

import java.util.Scanner;

import service.DogService;
import service.FoodService;

public class DogShelter {

    private final DogConsoleMenu consoleMenu;
    private final DogService dogService;
    private final FoodService foodService;
    private final Scanner scannerNumber = new Scanner(System.in);
    private final Scanner scannerText = new Scanner(System.in);

    public DogShelter(DogService dogService, FoodService foodService, DogConsoleMenu consoleMenu) {
        this.dogService = dogService;
        this.consoleMenu = consoleMenu;
        this.foodService = foodService;
    }

    public void startApp() {
        showMenu();
        int option;
        String option2 = null;
        do {
            System.out.print("Choose an option from above: ");
            option = scannerNumber.nextInt();
            switch (option) {
                case 1 -> {
                    try {
                        showDogsForAdoption();
                    } catch (NoDogFoundException adoptException) {
                        System.out.println(adoptException.getMessage());
                    }
                }
                case 2 -> {
                    try {
                        showDogsForSale();
                    } catch (NoDogFoundException saleException) {
                        System.out.println(saleException.getMessage());
                    }
                }
                case 3 -> {
                    try {
                        adoptADog();
                    } catch (NoDogFoundException saleException) {
                        System.out.println(saleException.getMessage());
                    }
                }
                case 4 -> {
                    try {
                        buyADog();
                    } catch (NoDogFoundException buyDogException) {
                        System.out.println(buyDogException.getMessage());
                    }
                }
                case 5 -> {
                    try {
                        feedDog();
                    } catch (NoDogFoundException feedException) {
                        System.out.println(feedException.getMessage());
                    }
                }
                case 6 -> {
                    try {
                        walkDog();
                    } catch (NoDogFoundException walkException) {
                        System.out.println(walkException.getMessage());
                    }
                }
                case 7 -> {
                    try {
                        findDogsByCriteria();
                    } catch (NoDogFoundException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 8 -> {
                    try {
                        showAllDogs();
                    } catch (NoDogFoundException noDogFoundException) {
                        System.out.println(noDogFoundException.getMessage());
                    }
                }
                case 81 -> {
                    try {
                        showAllAdoptedDogs();
                    } catch (NoDogFoundException historyException) {
                        System.out.println(historyException.getMessage());
                    }
                }
                case 82 -> {
                    try {
                        showAllSoldDogs();
                    } catch (NoDogFoundException historyException) {
                        System.out.println(historyException.getMessage());
                    }
                }
                case 9 -> exit();
                default -> System.out.println("You did not choose a valid option !");
            }
            if (option != 9) {
                do {
                    System.out.print("Do you want another dog shelter operation ? (Y/N) : ");
                    option2 = scannerText.next();
                    while (!option2.equalsIgnoreCase("y") && !option2.equalsIgnoreCase("n")) {
                        System.out.print("Introduce a valid option (Y/N) : ");
                        option2 = scannerText.next();
                    }
                    if (option2.equalsIgnoreCase("n")) {
                        exit();
                    }
                    if (option2.equalsIgnoreCase("y")) {
                        showMenu();
                    }
                } while (!option2.equalsIgnoreCase("n") && !option2.equalsIgnoreCase("y"));
            }
        } while (option != 9 && !option2.equalsIgnoreCase("n"));
    }

    // 1.Show dogs for adoption
    public void showDogsForAdoption() throws NoDogFoundException {
        consoleMenu.showDogsForAdoption(dogService.getDogListForAdoption());
    }

    // 2.Show dogs for sale
    public void showDogsForSale() throws NoDogFoundException {
        consoleMenu.showDogsForSale(dogService.getDogListForSale());
    }

    // 3.Adopt a dog
    public void adoptADog() throws NoDogFoundException {
        consoleMenu.showDogsForAdoption(dogService.getDogListForAdoption());

        System.out.print("Select number of the dog you want to adopt: ");
        int dogNumber = scannerNumber.nextInt();
        System.out.println("You just adopted dog: " + dogService.getAdoptionDog(dogNumber - 1));

        dogService.updateAdoptedDog(dogNumber);
    }

    // 4.Buy a dog
    public void buyADog() throws NoDogFoundException {
        consoleMenu.showDogsForAdoption(dogService.getDogListForSale());

        System.out.print("Select number of the dog you want to buy: ");
        int dogNumber = scannerNumber.nextInt();

        Dog saleDog = dogService.getSaleDog(dogNumber - 1);
        System.out.println("You just brought dog: " + saleDog + " for " + saleDog.getPrice() + " Ron");

        dogService.addDogInHistory(saleDog);
        dogService.deleteDog(saleDog);
    }

    // 5.Feed a dog
    public void feedDog() throws NoDogFoundException {

        consoleMenu.showDogsForFeed(dogService.getDogsWithEnergyBelow100());

        System.out.print("Select number of the dog you want to feed: ");
        int dogSelected = scannerNumber.nextInt();
        Dog dogToFeed = dogService.getDogToFeed(dogSelected);

        System.out.println("You have selected the " + dogToFeed.getBreed() +
                " with current energy " + dogToFeed.getDogEnergy() + "%");

        consoleMenu.showFoods(foodService.getFoodList());

        System.out.print("Select the food number: ");
        int foodOption = scannerNumber.nextInt();
        Food selectedFood = foodService.getFood(foodOption - 1);

        dogService.updateDogEnergy(dogToFeed, selectedFood);

        System.out.println(dogService.isDogFull(dogToFeed) ? "Dog " + dogToFeed.getBreed() + " is full of energy" :
                "Dog " + dogToFeed.getBreed() + " energy" + " is now " + dogToFeed.getDogEnergy() + "% !");
    }

    // 6.Walk a dog
    public void walkDog() throws NoDogFoundException {
        System.out.print("What distance you want to walk ? (km) : ");
        double distanceToWalk = scannerNumber.nextDouble();

        consoleMenu.showDogsForWalk(dogService.getDogsToWalk(distanceToWalk), distanceToWalk);

        System.out.print("Select number of the dog you want to walk: ");
        int dogNumber = scannerNumber.nextInt();

        Dog dog = dogService.getDogsToWalk(distanceToWalk).get(dogNumber - 1);
        System.out.println("You have selected the " + dog.getBreed() + " ! Have fun !");

        dogService.updateDogDistance(distanceToWalk, dog);
        System.out.println("After the walk dog remaining energy will be " + dog.getDogEnergy() + " %");
    }

    // 7.Show dogs by age, color or breed
    public void findDogsByCriteria() throws NoDogFoundException {
        criteriaMenu();
        System.out.print("Choose from above options: ");
        int option = scannerNumber.nextInt();

        switch (option) {
            case 1 -> {
                System.out.print("Introduce the age : ");
                int age = scannerNumber.nextInt();
                try {
                    consoleMenu.showDogs(dogService.getDogsByAge(age));
                } catch (NoDogFoundException ageException) {
                    System.out.println(ageException.getMessage());
                }
            }
            case 2 -> {
                System.out.print("Introduce the color : ");
                String color = scannerText.next();
                try {
                    consoleMenu.showDogs(dogService.getDogsByColor(color));
                } catch (NoDogFoundException colorException) {
                    System.out.println(colorException.getMessage());
                }
            }
            case 3 -> {
                System.out.print("Introduce the breed : ");
                String breed = scannerText.next();
                try {
                    consoleMenu.showDogs(dogService.getDogsByBreed(breed));
                } catch (NoDogFoundException breedException) {
                    System.out.println(breedException.getMessage());
                }
            }
            default -> System.out.println("You did not entered a valid option !");
        }
    }

    // 8. Show all dogs
    public void showAllDogs() throws NoDogFoundException {
        consoleMenu.showDogs(dogService.getDogList());
    }

    // 8.1 Show all adopted dogs
    public void showAllAdoptedDogs() throws NoDogFoundException {
        consoleMenu.showDogs(dogService.getDogListForAdoptionHistory());
    }

    // 8.2 Show all sold dogs
    public void showAllSoldDogs() throws NoDogFoundException {
        consoleMenu.showDogs(dogService.getDogListForSaleHistory());
    }

    // 9.Exit
    public void exit() {
        System.out.println("BYE !");
    }

    public void showMenu() {
        System.out.println();
        System.out.println("1.Show dogs for adoption");
        System.out.println("2.Show dogs for sale");
        System.out.println("3.Adopt a dog");
        System.out.println("4.Buy a dog");
        System.out.println("5.Feed a dog");
        System.out.println("6.Walk a dog");
        System.out.println("7.Show dogs by age, color or breed");
        System.out.println("8.Show all dogs");
        System.out.println("81.Show all adopted dogs in our shelter");
        System.out.println("82.Show all sold dogs in our shelter");
        System.out.println("9.Exit");
        System.out.println();
    }

    public void criteriaMenu() {
        System.out.println();
        System.out.println("1. Age");
        System.out.println("2. Color");
        System.out.println("3. Breed");
        System.out.println();
    }

}
