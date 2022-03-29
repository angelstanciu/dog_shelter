import entity.Dog;
import entity.Food;
import repository.DogFileRepository;
import repository.FoodFileRepository;
import service.DogService;
import service.FoodService;
import ui.DogConsoleMenu;
import ui.DogShelter;

import java.util.ArrayList;
import java.util.List;

public class Main {

//    FermaApp
//
//    * Sa se dezvolte o aplicatie ce simuleaza procesul de adoptie/cumparare al cateilor dintr-o crescatorie de catei.
//
//    * Prima data se va popula crescatoria cu catei.
//
//    Se va afisa urmatorul meniu cat timp utilizatorul nu introduce explicit optiunea Exit:

//  1.Afiseaza catei disponibili spre adoptie.
//  2.Afiseaza catei disponibili spre cumparare.
//  3.Cumparare catel. - > trebuie afisat array-ul de catei disponibili cu posibilitatea de alegere -> cu pret
//  4.Adoptare catel.  trebuie afisat array-ul de catei disponibili cu posibilitatea de alegere -> fara pret
//  5.Hranire catel. -> se va alege tipul de mancare cu care se va hranii catelul. -> in functie de acesta catelului
//              hranit ii va creste nivelul de energie cu x% specificat pentru hrana respectiva.
//  6.Plimbare catel -> Se va specifica distanta aproximativa pe care o va parcurge catelul,
//              iar in urma acesteia, nivelul sau de energie va scadea. Parcurge 1km -> -10% energie. 2km -> 15% energie.
//  7.Afisare catei dupa un anumit criteriu(rasa/ani/culoare)
//  8.Exit
//
//   * Cateii nu au voie sa ramana fara energie sau ca energia lor sa depaseasca 100%. In aceste cazuri inainte
//     sa iasa la plimbare sau sa manance se vor afisa mesaje corespunzatoare.
//
//    * Se vor defini clase separate pentru Hrana, Caine, Crescatorie.

    public static void main(String[] args) {

        // TODO: 21-Jan-22 : Implement DRY an SOLID principles

        List<Food> foodList = new ArrayList<>();
        List<Dog> dogList = new ArrayList<>();
        List<Dog> dogListHistory = new ArrayList<>();

        DogConsoleMenu consoleMenu = new DogConsoleMenu();
        DogFileRepository dogFileRepository = new DogFileRepository(dogList, dogListHistory);
        FoodFileRepository foodFileRepository = new FoodFileRepository(foodList);
        DogService dogService = new DogService(dogFileRepository);
        FoodService foodService = new FoodService(foodFileRepository);
        DogShelter dogShelter = new DogShelter(dogService, foodService, consoleMenu);

        dogShelter.startApp();
    }
}
