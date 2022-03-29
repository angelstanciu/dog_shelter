package repository;

import entity.Dog;
import interfaces.DogRepositoryInterface;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class DogFileRepository implements DogRepositoryInterface {
    private final List<Dog> dogList;
    private final List<Dog> dogListHistory;

    public DogFileRepository(List<Dog> dogList, List<Dog> dogListHistory) {
        this.dogList = dogList;
        this.dogListHistory = dogListHistory;
        readDogsFromCVS();
        readDogsHistoryFromCVS();
    }

    public void readDogsFromCVS() {
         String line;
        try {
            BufferedReader in = new BufferedReader(new FileReader("dogs.csv"));
            while ((line = in.readLine()) != null) {
                String[] dogLine = line.split(",");
                String color = dogLine[0];
                int age = Integer.parseInt(dogLine[1]);
                String breed = dogLine[2];
                int dogEnergy = Integer.parseInt(dogLine[3]);
                double distanceAbleToWalk = Double.parseDouble(dogLine[4]);
                double price = Double.parseDouble(dogLine[5]);
                boolean saleFlag = Boolean.parseBoolean(dogLine[6]);
                boolean adoptFlag = Boolean.parseBoolean(dogLine[7]);
                Dog dog = new Dog(color, age, breed, dogEnergy, distanceAbleToWalk, price, saleFlag, adoptFlag);
                dogList.add(dog);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readDogsHistoryFromCVS() {
        String line;
        try {
            BufferedReader in = new BufferedReader(new FileReader("dogsHistory.csv"));
            while ((line = in.readLine()) != null) {
                String[] dogLine = line.split(",");
                String color = dogLine[0];
                int age = Integer.parseInt(dogLine[1]);
                String breed = dogLine[2];
                int dogEnergy = Integer.parseInt(dogLine[3]);
                double distanceAbleToWalk = Double.parseDouble(dogLine[4]);
                double price = Double.parseDouble(dogLine[5]);
                boolean saleFlag = Boolean.parseBoolean(dogLine[6]);
                boolean adoptFlag = Boolean.parseBoolean(dogLine[7]);
                Dog dog = new Dog(color, age, breed, dogEnergy, distanceAbleToWalk, price, saleFlag, adoptFlag);
                dogListHistory.add(dog);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeDogsInCVS() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("dogs.csv"));
            for (Dog dog : dogList) {
                writer.write(dog.toStringForCSVLineAdd());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeDogsHistoryInCVS() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("dogsHistory.csv"));
            for (Dog dog : dogListHistory) {
                writer.write(dog.toStringForCSVLineAdd());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addDog(Dog dog) {
        dogList.add(dog);
        writeDogsInCVS();
    }

    @Override
    public void addDogInHistory(Dog dog) {
        dogListHistory.add(dog);
        writeDogsHistoryInCVS();
    }

    @Override
    public void deleteDog(Dog dog) {
        dogList.remove(dog);
        writeDogsHistoryInCVS();
        writeDogsInCVS();
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
