package repository;

import entity.Food;
import interfaces.FoodRepositoryInterface;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class FoodFileRepository implements FoodRepositoryInterface {

    private final List<Food> foodList;

    public FoodFileRepository(List<Food> foodList) {
        this.foodList = foodList;
        readFoodsFromCVS();
    }

    public void readFoodsFromCVS() {
        String name;
        int energyIncreaser;
        Food food;
        String line;
        try {
            BufferedReader in = new BufferedReader(new FileReader("foods.csv"));
            while ((line = in.readLine()) != null) {
                String[] foodLine = line.split(",");
                name = foodLine[0];
                energyIncreaser = Integer.parseInt(foodLine[1]);

                food = new Food(name, energyIncreaser);
                foodList.add(food);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addFood(Food food) {
        foodList.add(food);
    }

    @Override
    public Food getFood(int foodNumber) {
        return foodList.get(foodNumber);
    }

    @Override
    public List<Food> getFoodList() {
        return foodList;
    }
}
