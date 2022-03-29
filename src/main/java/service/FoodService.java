package service;

import entity.Food;
import repository.FoodFileRepository;

import java.util.List;

public class FoodService {

    private final FoodFileRepository foodFileRepository;

    public FoodService(FoodFileRepository foodFileRepository) {
        this.foodFileRepository = foodFileRepository;
    }

    public Food getFood(int foodNumber) {
        return foodFileRepository.getFood(foodNumber);
    }

    public List<Food> getFoodList() {
        return foodFileRepository.getFoodList();
    }
}
