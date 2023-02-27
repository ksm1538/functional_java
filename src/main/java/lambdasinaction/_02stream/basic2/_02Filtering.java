package lambdasinaction._02stream.basic2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lambdasinaction._02stream.basic1.Dish;

public class _02Filtering {

    public static void main(String...args){

        // 1. Filtering with predicate ( isVegeterian() )
        System.out.println("[1]");
        List<Dish> vegeList = Dish.menu.stream()        // List<Dish> -> Stream<Dish>
                .filter(Dish::isVegetarian)             // Stream<Dish>
                .collect(Collectors.toList());          // Stream<Dish> -> List<Dish>



        // 2. Filtering unique elements & upper 2
        System.out.println("[2]");
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream().distinct()
                .filter(num -> num >= 2)
                .forEach(System.out::println);


        //3. Truncating 3 stream ( d.getCalories() > 300 )
        System.out.println("[3]");
        List<Dish> dishesLimit3 =
                Dish.menu.stream()
                        .filter(dish -> dish.getCalories() > 300)
                        .limit(3)
                        .collect(Collectors.toList());
        dishesLimit3.forEach(System.out::println);


        //4. Skipping elements
        System.out.println("[4]");
        List<Dish> dishesSkip2 =
                Dish.menu.stream()
                        .skip(2)
                        .collect(Collectors.toList());

        dishesSkip2.forEach(System.out::println);
    }
}
