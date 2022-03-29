package service;

import entity.Dog;
import entity.Food;
import exception.NoDogFoundException;

import interfaces.DogRepositoryInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DogService {

    private final DogRepositoryInterface dogRepositoryInterface;

    public DogService(DogRepositoryInterface dogRepositoryInterface) {
        this.dogRepositoryInterface = dogRepositoryInterface;
    }

    public void addDog(Dog dog) {
        dogRepositoryInterface.addDog(dog);
    }

    public void addDogInHistory(Dog dog) {
        dogRepositoryInterface.addDogInHistory(dog);
    }

    public void deleteDog(Dog dog) {
        dogRepositoryInterface.deleteDog(dog);
    }

    public Dog getAdoptionDog(int dogNumber) {
        return dogRepositoryInterface.getAdoptionDog(dogNumber);
    }

    public Dog getSaleDog(int dogNumber) {
        return dogRepositoryInterface.getSaleDog(dogNumber);
    }

    public Dog getDogToFeed(int dogNumber) throws NoDogFoundException {
        return getDogsWithEnergyBelow100().get(dogNumber - 1);
    }

    public List<Dog> getDogList() throws NoDogFoundException {
        if (dogRepositoryInterface.getDogList().isEmpty()) {
            throw new NoDogFoundException("No dog found in our shelter");
        }
        return dogRepositoryInterface.getDogList();
    }

    public List<Dog> getDogListForAdoption() throws NoDogFoundException {
        if (dogRepositoryInterface.getDogListForAdoption().isEmpty()) {
            throw new NoDogFoundException("No dog for adoption found, try instead to buy one !");
        }
        return dogRepositoryInterface.getDogListForAdoption();
    }

    public List<Dog> getDogListForSale() throws NoDogFoundException {
        List<Dog> saleList = dogRepositoryInterface.getDogListForSale();
        if (saleList.isEmpty()) {
            throw new NoDogFoundException("No dog for sale found, try instead to adopt one !");
        }
        return saleList;
    }

    public List<Dog> getDogListForAdoptionHistory() throws NoDogFoundException {
        if (dogRepositoryInterface.getDogListForAdoptionHistory().isEmpty()) {
            throw new NoDogFoundException("No dog was adopted yet in our shelter");
        }
        return dogRepositoryInterface.getDogListForAdoptionHistory();
    }

    public List<Dog> getDogListForSaleHistory() throws NoDogFoundException {
        List<Dog> saleHistory = dogRepositoryInterface.getDogListForSaleHistory();
        if (saleHistory.isEmpty()) {
            throw new NoDogFoundException("No dog found in sale history !");
        }
        return saleHistory;
    }

    public List<Dog> getDogsToWalk(double distanceToWalk) throws NoDogFoundException {
        List<Dog> dogsToWalk = getDogList().stream()
                .filter(dog -> isDogAbleToWalk(dog, distanceToWalk))
                .collect(Collectors.toList());

        if (dogsToWalk.isEmpty()) {
            throw new NoDogFoundException("No dog found for such a long walk !");
        }

        return dogsToWalk;
    }

    public List<Dog> getDogsWithEnergyBelow100() throws NoDogFoundException {
        List<Dog> dogsWithEnergyBelow100 = getDogList().stream()
                .filter(dog -> !isDogFull(dog))
                .collect(Collectors.toList());

        if (dogsWithEnergyBelow100.isEmpty()) {
            throw new NoDogFoundException("No dog to feed found, all dogs are full of energy !");
        }

        return dogsWithEnergyBelow100;
    }

    public List<Dog> getDogsByAge(Integer age) throws NoDogFoundException {
        List<Dog> dogsByAge = getDogList().stream()
                .filter(dog -> dog.getAge() <= age)
                .collect(Collectors.toList());

        if (dogsByAge.isEmpty()) {
            throw new NoDogFoundException("No dog found for value = " + age + " years.");
        }

        return dogsByAge;
    }

    public List<Dog> getDogsByBreed(String breed) throws NoDogFoundException {
        List<Dog> dogsByBreed = dogRepositoryInterface.getDogList().stream().filter(dog -> dog.getBreed().equalsIgnoreCase(breed)).collect(Collectors.toList());

        if (dogsByBreed.isEmpty()) {
            throw new NoDogFoundException("No dog found for '" + breed + "' breed.");
        }
        return dogsByBreed;
    }

    public List<Dog> getDogsByColor(String color) throws NoDogFoundException {
        List<Dog> dogsByColor = dogRepositoryInterface.getDogList().stream().filter(dog -> dog.getColor().equalsIgnoreCase(color)).collect(Collectors.toList());
        if (dogsByColor.isEmpty()) {
            throw new NoDogFoundException("No dog found for color " + color + ".");
        }
        return dogsByColor;
    }

    public void updateDogEnergy(Dog dog, Food food) {
        if (dog.getDogEnergy() + food.getEnergyIncrease() > 99) {
            dog.setDogEnergy(100);
            dog.setDistanceAbleToWalk(10.0);
        } else {
            dog.setDogEnergy(dog.getDogEnergy() + food.getEnergyIncrease());
            dog.setDistanceAbleToWalk(dog.getDistanceAbleToWalk() + (food.getEnergyIncrease()) / 10.0);
        }
    }

    public void updateAdoptedDog(int dogNumber) {
        Dog adoptionDog = getAdoptionDog(dogNumber - 1);
        addDogInHistory(adoptionDog);
        deleteDog(adoptionDog);
    }

    public void updateDogDistance(double distanceToWalk, Dog dog) {
        dog.setDogEnergy((dog.getDogEnergy() - (int) distanceToWalk * 10));
        dog.setDistanceAbleToWalk((dog.getDistanceAbleToWalk() - distanceToWalk));
    }

    public boolean isDogFull(Dog dog) {
        return dog.getDogEnergy() > 99;
    }

    public boolean isDogAbleToWalk(Dog dog, double distanceToWalk) {
        return dog.getDistanceAbleToWalk() > distanceToWalk;
    }
}
