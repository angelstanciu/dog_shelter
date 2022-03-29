package entity;

import java.util.Objects;

public class Dog {
    private final String color;
    private final int age;
    private final String breed;
    private int dogEnergy;
    private double distanceAbleToWalk;
    private double price;
    private final boolean saleFlag;
    private final boolean adoptFlag;

    public Dog(String color, int age, String breed, int dogEnergy, double distanceAbleToWalk, double price, boolean adoptFlag, boolean saleFlag) {
        this.color = color;
        this.age = age;
        this.breed = breed;
        this.dogEnergy = dogEnergy;
        this.distanceAbleToWalk = distanceAbleToWalk;
        this.price = price;
        this.adoptFlag = adoptFlag;
        this.saleFlag = saleFlag;
    }

    public Dog(String color, int age, String breed, int dogEnergy, double distanceAbleToWalk, boolean adoptFlag, boolean saleFlag) {
        this.color = color;
        this.age = age;
        this.breed = breed;
        this.dogEnergy = dogEnergy;
        this.distanceAbleToWalk = distanceAbleToWalk;
        this.adoptFlag = adoptFlag;
        this.saleFlag = saleFlag;
    }

    public String getColor() {
        return color;
    }

    public int getAge() {
        return age;
    }

    public String getBreed() {
        return breed;
    }

    public int getDogEnergy() {
        return dogEnergy;
    }

    public void setDogEnergy(int dogEnergy) {
        this.dogEnergy = dogEnergy;
    }

    public double getDistanceAbleToWalk() {
        return distanceAbleToWalk;
    }

    public void setDistanceAbleToWalk(double distanceAbleToWalk) {
        this.distanceAbleToWalk = distanceAbleToWalk;
    }

    public double getPrice() {
        return price;
    }

    public boolean isSaleFlag() {
        return saleFlag;
    }

    public boolean isAdoptFlag() {
        return adoptFlag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dog dog = (Dog) o;
        return age == dog.age && dogEnergy == dog.dogEnergy && Double.compare(dog.distanceAbleToWalk, distanceAbleToWalk) == 0 && Double.compare(dog.price, price) == 0 && saleFlag == dog.saleFlag && adoptFlag == dog.adoptFlag && color.equals(dog.color) && breed.equals(dog.breed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, age, breed, dogEnergy, distanceAbleToWalk, price, saleFlag, adoptFlag);
    }

    @Override
    public String toString() {
        return "Dog{" +
                "color='" + color + '\'' +
                ", age=" + age +
                ", breed='" + breed + '\'' +
                ", dogEnergy=" + dogEnergy + "%" +
                ", distanceAbleToWalk=" + distanceAbleToWalk + "km" +
                ", price=" + price + "Ron" +
                '}';
    }

    public String toStringForCSVLineAdd() {
        return color + "," + age + "," + breed + "," + dogEnergy + "," + distanceAbleToWalk + "," + price + "," + adoptFlag + "," + saleFlag + "\n";
    }
}
