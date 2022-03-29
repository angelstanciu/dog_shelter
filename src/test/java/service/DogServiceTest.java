package service;

import entity.Dog;
import entity.Food;
import exception.NoDogFoundException;
import org.junit.jupiter.api.*;
import repository.DogFileRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DogServiceTest {

    private DogService dogService;
    private List<Dog> dogList;
    private List<Dog> dogListHistory;
    private DogFileRepository dogRepository;

    @BeforeEach
    void beforeEach() {
        dogListHistory = new ArrayList<>();
        dogList = new ArrayList<>();
        dogRepository = new DogFileRepository(dogList, dogListHistory);
        dogService = new DogService(dogRepository);
    }

    @Test
    @DisplayName("Should add a Dog in a dogList")
    public void testAddDog() {
        //given
        Dog dog = new Dog("rosu", 3, "caucazian", 50, 5.0, true, false);

        //when
        dogService.addDog(dog);

        //then
        assertThat(dog).isIn(dogList);
    }

    @Test
    @DisplayName("Should add a Dog in a dogListHistory")
    void testAddDogInHistory() {
        //given
        Dog dog = new Dog("rosu", 3, "caucazian", 50, 5.0, true, false);

        //when
        dogService.addDogInHistory(dog);

        //then
        assertThat(dog).isIn(dogListHistory);
    }

    @Test
    @DisplayName("Should delete a fog from dogList")
    void testDeleteDog() {
        //given
        Dog dog = new Dog("rosu", 3, "caucazian", 50, 5.0, true, false);
        dogService.addDog(dog);

        //when
        dogService.deleteDog(dog);

        //then
        assertThat(dogList).isEmpty();
    }

    @Test
    @DisplayName("Should return a chosen dog for adoption(dogNumber)")
    void testGetAdoptionDog() {
        //given
        Dog dog = new Dog("rosu", 3, "caucazian", 50, 5.0, true, false);
        dogService.addDog(dog);

        //when
        Dog adoptedDog = dogService.getAdoptionDog(0);

        //then
        assertThat(adoptedDog).isEqualTo(dog);
    }

    @Test
    @DisplayName("Should returd a dog for sale(dogNumber)")
    void testGetSaleDog() {
        //given
        Dog dog = new Dog("rosu", 3, "caucazian", 50, 5.0, false, true);
        dogService.addDog(dog);

        //when
        Dog dogForSale = dogService.getSaleDog(0);

        //then
        assertThat(dogForSale).isEqualTo(dog);
        assertThat(dogForSale.isAdoptFlag()).isFalse();
        assertThat(dogForSale.isSaleFlag()).isTrue();
    }

    @Test
    @DisplayName("Should return a list with all dogs")
    void testGetDogList() throws NoDogFoundException {
        //given
        Dog dog1 = new Dog("rosu", 3, "caucazian", 50, 5.0, false, true);
        Dog dog2 = new Dog("rosu", 3, "caucazian", 50, 5.0, true, false);
        dogService.addDog(dog1);
        dogService.addDog(dog2);

        //when
        List<Dog> resultedList = dogService.getDogList();

        //then
        assertThat(resultedList).containsOnly(dog1, dog2);
        assertThat(resultedList).isSameAs(dogList);
    }

    @Test
    @DisplayName("Should return a list with all dogs for adoption")
    void testGetDogListForAdoption() throws NoDogFoundException {
        //given
        Dog dog1 = new Dog("rosu", 3, "caucazian", 50, 5.0, false, true);
        Dog dog2 = new Dog("negru", 5, "ciobanesc", 50, 10.0, true, false);
        dogService.addDog(dog1);
        dogService.addDog(dog2);

        //when
        List<Dog> resultedList = dogService.getDogListForAdoption();

        //then
        assertThat(resultedList).containsOnly(dog2);
    }

    @Test
    @DisplayName("Should return a list with all adopted dogs")
    void testGetDogListForAdoptionHistory() throws NoDogFoundException {
        //given
        Dog dog1 = new Dog("rosu", 3, "caucazian", 50, 5.0, false, true);
        Dog dog2 = new Dog("negru", 5, "ciobanesc", 50, 10.0, true, false);
        dogService.addDogInHistory(dog1);
        dogService.addDogInHistory(dog2);

        //when
        List<Dog> resultedList = dogService.getDogListForAdoptionHistory();

        //then
        assertThat(resultedList).containsOnly(dog2);
    }

    @Test
    @DisplayName("Should remove a dog from dogList and add it to dogListHistory")
    void testUpdateAdoptedDog() throws NoDogFoundException {
        //given
        Dog dogNr1 = new Dog("rosu", 3, "caucazian", 50, 5.0, true, false);
        Dog dogNr2 = new Dog("negru", 5, "ciobanesc", 100, 10.0, true, false);
        dogService.addDog(dogNr1);
        dogService.addDog(dogNr2);

        //when
        dogService.updateAdoptedDog(2);

        //then
        assertThat(dogService.getDogListForAdoptionHistory()).containsOnly(dogNr2);
        assertThat(dogService.getDogList()).doesNotContain(dogNr2);
    }

    @Test
    @DisplayName("Should return a list with all dogs for sale")
    void testGetDogListForSale() throws NoDogFoundException {
        //given
        Dog dog1 = new Dog("rosu", 3, "caucazian", 50, 5.0, false, true);
        Dog dog2 = new Dog("negru", 5, "ciobanesc", 50, 10.0, true, false);
        dogService.addDog(dog1);
        dogService.addDog(dog2);

        //when
        List<Dog> resultedList = dogService.getDogListForSale();

        //then
        assertThat(resultedList).containsOnly(dog1);
    }

    @Test
    @DisplayName("Should return a list with all sold dogs")
    void testGetDogListForSaleHistory() throws NoDogFoundException {
        //given
        Dog dog1 = new Dog("rosu", 3, "caucazian", 50, 5.0, false, true);
        Dog dog2 = new Dog("negru", 5, "ciobanesc", 50, 10.0, true, false);
        dogService.addDogInHistory(dog1);
        dogService.addDogInHistory(dog2);

        //when
        List<Dog> resultedList = dogService.getDogListForSaleHistory();

        //then
        assertThat(resultedList).containsOnly(dog2);
    }

    @Test
    @DisplayName("Should return a list with all sold dogs")
    void testGetSelectedDogToFeed() throws NoDogFoundException {
        //given
        Dog dog1 = new Dog("rosu", 3, "caucazian", 50, 5.0, false, true);
        Dog dog2 = new Dog("negru", 5, "ciobanesc", 70, 7.0, true, false);
        dogService.addDog(dog1);
        dogService.addDog(dog2);
        //when
        Dog resultedDog = dogService.getDogToFeed(2);

        //then
        assertThat(resultedDog).isEqualTo(dog2);
    }

    @Test
    @DisplayName("Should return a list with all dogs with energy below 100%")
    void testGetDogsWithEnergyBelow100() throws NoDogFoundException {
        //given
        Dog dog1 = new Dog("rosu", 3, "caucazian", 50, 5.0, false, true);
        Dog dog2 = new Dog("negru", 5, "ciobanesc", 100, 10, true, false);
        dogService.addDog(dog1);
        dogService.addDog(dog2);
        //when
        List<Dog> resultedList = dogService.getDogsWithEnergyBelow100();

        //then
        assertThat(resultedList).containsOnly(dog1);
    }

    @Test
    @DisplayName("Should increase dog energy by food.energyIncreaser")
    void testDogEnergyUpdate() {
        //given
        Food food1 = new Food("branza", 5);
        Dog dogNr1 = new Dog("rosu", 3, "caucazian", 50, 5.0, true, false);

        //when
        dogService.updateDogEnergy(dogNr1, food1);

        //then
        assertThat(dogNr1.getDogEnergy()).isEqualTo(55);
    }

    @Test
    @DisplayName("Should return true/false if dogEnergy is above 99 respectively under 100")
    void testIsDogFull() {
        //given
        Dog dogNr1 = new Dog("rosu", 3, "caucazian", 99, 9.9, true, false);
        Dog dogNr2 = new Dog("rosu", 3, "caucazian", 100, 10, true, false);


        //when
        boolean result = dogService.isDogFull(dogNr1);
        boolean result2 = dogService.isDogFull(dogNr2);

        //then
        assertThat(result).isFalse();
        assertThat(result2).isTrue();
    }

    @Test
    @DisplayName("Should return a list with dogs for a certain km walk")
    void testGetDogsToWalk() throws NoDogFoundException {
        //given
        Dog dog1 = new Dog("rosu", 3, "caucazian", 50, 5.0, false, true);
        Dog dog2 = new Dog("negru", 5, "ciobanesc", 100, 10, true, false);
        dogService.addDog(dog1);
        dogService.addDog(dog2);
        //when
        List<Dog> resultedList = dogService.getDogsToWalk(7.5);

        //then
        assertThat(resultedList).containsOnly(dog2);
    }

    @Test
    @DisplayName("Should update energy and distance that a dog can walk")
    void testDogDistanceUpdate() {
        //given
        Double distance = 4.0;
        Dog dogNr1 = new Dog("rosu", 3, "caucazian", 50, 5.0, true, false);

        //when
        dogService.updateDogDistance(distance, dogNr1);

        //then
        assertThat(dogNr1.getDistanceAbleToWalk()).isEqualTo(1.0);
        assertThat(dogNr1.getDogEnergy()).isEqualTo(10);
    }

    @Test
    @DisplayName("Should if a dog can walk a certain distance")
    void testIsDogAbleToWalk() {
        //given
        Double distance = 7.5;
        Dog dogNr1 = new Dog("rosu", 3, "caucazian", 50, 5.0, true, false);
        Dog dogNr2 = new Dog("rosu", 3, "caucazian", 100, 10.0, true, false);

        //when
        boolean result1 = dogService.isDogAbleToWalk(dogNr1, distance);
        boolean result2 = dogService.isDogAbleToWalk(dogNr2, distance);

        //then
        assertThat(result1).isFalse();
        assertThat(result2).isTrue();
    }

    @Test
    @DisplayName("Should return a list with dogs below a certain age")
    void testFindDogsByAge() throws NoDogFoundException {
        //given
        Dog dog1 = new Dog("rosu", 3, "caucazian", 50, 5.0, false, true);
        Dog dog2 = new Dog("negru", 5, "ciobanesc", 100, 10, true, false);
        dogService.addDog(dog1);
        dogService.addDog(dog2);
        //when
        List<Dog> resultedList = dogService.getDogsByAge(4);

        //then
        assertThat(resultedList).containsOnly(dog1);
    }

    @Test
    @DisplayName("Should return a list with a certain dog color")
    void testFindDogsByColor() throws NoDogFoundException {
        //given
        Dog dog1 = new Dog("rosu", 3, "caucazian", 50, 5.0, false, true);
        dogService.addDog(dog1);

        //when
        List<Dog> resultedList = dogService.getDogsByColor("rosu");

        //then
        assertThat(resultedList).containsOnly(dog1);
    }

    @Test
    @DisplayName("Should return a list with a certain dog breed")
    void testFindDogsByBreed() throws NoDogFoundException {
        //given
        Dog dog1 = new Dog("rosu", 3, "caucazian", 50, 5.0, false, true);
        dogService.addDog(dog1);

        //when
        List<Dog> resultedList = dogService.getDogsByBreed("caucazian");

        //then
        assertThat(resultedList).containsOnly(dog1);
    }
}