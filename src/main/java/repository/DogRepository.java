package repository;

import entity.Dog;
import interfaces.DogRepositoryInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DogRepository implements DogRepositoryInterface {

    private final List<Dog> dogList;
    private final List<Dog> dogListHistory;

    public DogRepository(List<Dog> dogList, List<Dog> dogListHistory) {
        this.dogList = dogList;
        this.dogListHistory = dogListHistory;
    }

    @Override
    public void addDog(Dog dog) {
        dogList.add(dog);
    }

    @Override
    public void addDogInHistory(Dog dog) {
        dogListHistory.add(dog);
    }

    @Override
    public void deleteDog(Dog dog) {
        dogList.remove(dog);
    }

    @Override
    public Dog getAdoptionDog(int dogNumber) {
        return getDogListForAdoption().get(dogNumber);
    }

    @Override
    public Dog getSaleDog(int dogNumber) {
        return getDogListForSale().get(dogNumber);
    }

    @Override
    public List<Dog> getDogList() {
        return dogList;
    }

    @Override
    public List<Dog> getDogListForAdoption() {
        return dogList.stream().filter(Dog::isAdoptFlag).collect(Collectors.toList());
    }

    @Override
    public List<Dog> getDogListForSale() {
        return dogList.stream().filter(Dog::isSaleFlag).collect(Collectors.toList());
    }

    @Override
    public List<Dog> getDogListForAdoptionHistory() {
        return dogListHistory.stream().filter(Dog::isAdoptFlag).collect(Collectors.toList());
    }

    @Override
    public List<Dog> getDogListForSaleHistory() {
        return dogListHistory.stream().filter(Dog::isSaleFlag).collect(Collectors.toList());
    }
}
