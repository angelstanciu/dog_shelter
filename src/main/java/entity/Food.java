package entity;

import java.util.Objects;

public class Food {
    private final String foodName;
    private final int energyIncrease;

    public Food(String foodName, int energyIncreaser) {
        this.foodName = foodName;
        this.energyIncrease = energyIncreaser;
    }

    public int getEnergyIncrease() {
        return energyIncrease;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return energyIncrease == food.energyIncrease && foodName.equals(food.foodName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(foodName, energyIncrease);
    }

    @Override
    public String toString() {
        return "Food{" +
                "foodName='" + foodName + '\'' +
                ", energyIncrease=" + energyIncrease +
                '}';
    }
}
