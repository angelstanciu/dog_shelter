package ui;

import entity.Dog;
import entity.Food;

import java.util.List;

public class DogConsoleMenu {

    public void showDogs(List<Dog> dogs) {
        System.out.println();
        for (int i = 0; i < dogs.size(); i++) {
            System.out.println((i + 1) + ". " + dogs.get(i));
        }
        System.out.println();
    }

    public void showDogsForAdoption(List<Dog> dogs) {
        System.out.println("Available dogs for adoption:");
        for (int i = 0; i < dogs.size(); i++) {
            System.out.println((i + 1) + ". " + dogs.get(i));
        }
        System.out.println();
    }

    public void showDogsForSale(List<Dog> dogs) {
        System.out.println("Available dogs for sale:");
        for (int i = 0; i < dogs.size(); i++) {
            System.out.println((i + 1) + ". " + dogs.get(i));
        }
        System.out.println();
    }

    public void showDogsForFeed(List<Dog> dogs) {
        System.out.println("Our dogs available to feed:");
        for (int i = 0; i < dogs.size(); i++) {
            System.out.println((i + 1) + ". " + dogs.get(i));
        }
        System.out.println();
    }

    public void showDogsForWalk(List<Dog> dogs, double distance) {
        System.out.println("Our dogs able to walk " + distance + " km walk:");
        for (int i = 0; i < dogs.size(); i++) {
            System.out.println((i + 1) + ". " + dogs.get(i));
        }
        System.out.println();
    }

    public void showFoods(List<Food> foods) {
        System.out.println("These are the foods we have:");
        for (int i = 0; i < foods.size(); i++) {
            System.out.println((i + 1) + ". " + foods.get(i));
        }
    }
}
