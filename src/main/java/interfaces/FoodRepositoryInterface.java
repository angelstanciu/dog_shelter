package interfaces;

import entity.Food;

import java.util.List;

public interface FoodRepositoryInterface {
    void addFood(Food food);

    Food getFood(int foodNumber);

    List<Food> getFoodList();
}
