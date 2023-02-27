package lambdasinaction._02stream.basic1;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class StreamBasic {

    public static void main(String...args){
        // Java 7
        getLowCaloricDishesNamesInJava7(Dish.menu).forEach(System.out::println);

        System.out.println("---");

        // Java 8
        getLowCaloricDishesNamesInJava8(Dish.menu).forEach(System.out::println);

        System.out.println(getGroupingMenu(Dish.menu));

        System.out.println(getMaxCaloryDish(Dish.menu));
        System.out.println(getMaxCaloryDish2(Dish.menu));
    }

    public static List<String> getLowCaloricDishesNamesInJava7(List<Dish> dishes){
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for(Dish d: dishes){
            if(d.getCalories() <= 400){
                lowCaloricDishes.add(d);
            }
        }
        List<String> lowCaloricDishesName = new ArrayList<>();
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            public int compare(Dish d1, Dish d2){
                return Integer.compare(d1.getCalories(), d2.getCalories());
            }
        });
        for(Dish d: lowCaloricDishes){
            lowCaloricDishesName.add(d.getName());
        }
        List<String> lowCaloricLimit3DishesName = new ArrayList<>();
        lowCaloricLimit3DishesName = lowCaloricDishesName.subList(0,3);

        return lowCaloricLimit3DishesName;
    }

    //Java 8
    public static List<String> getLowCaloricDishesNamesInJava8(List<Dish> dishes){
        return dishes.stream()      // List<Dish> -> Stream<Dish>
                .filter(dish -> dish.getCalories() <= 400)              // Predicate
                // .sorted(Comparator.comparing(dish -> dish.getCalories()))
                .sorted(Comparator.comparing(Dish::getCalories))        // 정렬
                .map(Dish::getName)         // .map(dish -> dish.getName())     Stream<Dish> -> Stream<String>
                .collect(Collectors.toList())         // Stream<String> -> List<String>
                .subList(0,3);              // 3개



    }

    //400칼로리 이하인 메뉴를 다이어트로, 아닐 경우 일반으로 그룹핑해라.
    public static Map<String, List<Dish>>  getGroupingMenu(List<Dish> dishes){
        return dishes.stream()          // List<Dish>   -> Stream<Dish>
                .collect(Collectors.groupingBy(dish -> {
                    if(dish.getCalories() <= 400)
                        return "Diet";          // 이 때의 Diet는 반환하는 Map의 Key(String)
                    else
                        return "Normal";
                }));
    }


    //가장 칼로리가 높은 메뉴를 찾아라
    public static Dish getMaxCaloryDish (List<Dish> dishes) {
        return dishes.stream()
                .max(Comparator.comparingInt(Dish::getCalories))
                .get();       // Optional<Dish>
    }

    public static Dish getMaxCaloryDish2 (List<Dish> dishes) {
        return dishes.stream()
                .sorted(Comparator.comparingInt(Dish::getCalories).reversed())
                .collect(Collectors.toList())
                .get(0);
    }
}
