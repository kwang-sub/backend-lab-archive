package modernjavainaction.base.chap04;

import static java.util.stream.Collectors.toList;

import java.util.List;

public class HighCaloriesNames {

  public static void main(String[] args) {
    List<String> names = Dish.menu.stream()
        .filter(dish -> {
          System.out.println("filtering " + dish.getName());
          return dish.getCalories() > 300;
        })
        .map(dish -> {
          System.out.println("mapping " + dish.getName());
          return dish.getName();
        })
        .limit(3)
        .collect(toList());
    System.out.println(names);
  }

}
