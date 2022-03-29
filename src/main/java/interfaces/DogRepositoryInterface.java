package interfaces;

import entity.Dog;

import java.util.List;

public interface DogRepositoryInterface {
    void addDog(Dog dog);

    void addDogInHistory(Dog dog);

    void deleteDog(Dog dog);

    Dog getAdoptionDog(int dogNumber);

    Dog getSaleDog(int dogNumber);

    List<Dog> getDogList();

    List<Dog> getDogListForAdoption();

    List<Dog> getDogListForAdoptionHistory();

    List<Dog> getDogListForSale();

    List<Dog> getDogListForSaleHistory();
}
