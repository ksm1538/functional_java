package lambdasinaction._02stream.basic2;

import lambdasinaction._02stream.basic1.Dish;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import static lambdasinaction._02stream.basic1.Dish.menu;

public class _05Reducing {

    public static void main(String... args) {

        //reduce - a + b 연산
        List<Integer> numbers = Arrays.asList(3, 4, 5, 1, 2);


        Integer sum = numbers.stream().reduce(0, (n1, n2) -> n1+n2);


        sum = numbers.stream().mapToInt(Integer::intValue).sum();

        sum = numbers.stream().flatMapToInt(IntStream::of).sum();

        // reduce -  최대값
        // (n1, n2) -> Integer.max(n1, n2);
        int max = numbers.stream().reduce(0, Integer::max);
        System.out.println(max);

        //reduce - 최대값
        int minNum = numbers.stream().min(Integer::compareTo).get();
        System.out.println(minNum);

        Optional<Integer> min = numbers.stream().reduce(Integer::min);
        min.ifPresent(System.out::println);

        //Dish 의  총 칼로리 합계를 구하는 여러가지 방법

        // 1.reduce()
        Integer totalValue = menu.stream()
                .map(Dish::getCalories)
                .reduce(0, (c1, c2) -> c1+c2);
        System.out.println("totalValue1 : " + totalValue);

        // 2. reduce() Integer.sum()
        totalValue = menu.stream()
                .map(Dish::getCalories)
                .reduce(Integer::sum)
                .get();
        System.out.println("totalValue2 : " + totalValue);

        // 3. mapToInt() -> IntStream
        totalValue = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();
        System.out.println("totalValue3 : " + totalValue);

        // 4. Collectors summingInt()
        totalValue = menu.stream()
                .collect(Collectors.summingInt(Dish::getCalories));
        System.out.println("totalValue4 : " + totalValue);

        // 5. summarizing
        IntSummaryStatistics statistics = menu.stream()
                .collect(Collectors.summarizingInt(Dish::getCalories));
        System.out.println(statistics);
    }
}
