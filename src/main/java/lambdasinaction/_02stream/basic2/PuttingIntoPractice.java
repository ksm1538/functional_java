package lambdasinaction._02stream.basic2;

import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class PuttingIntoPractice{
    public static void main(String ...args){
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );


        // Query 1: Find all transactions from year 2011 and sort them by value (small to high).
        transactions.stream()
                .filter(tx -> tx.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .forEach(System.out::println);



        // Query 2: What are all the unique cities where the traders work?
        transactions.stream()
                .map(tx -> tx.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);



        // Query 3: Find all traders from Cambridge and sort them by name.
        transactions.stream()
                .map(tx -> tx.getTrader())
                .filter(tx -> tx.getCity().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .forEach(System.out::println);


        // Query 4: Return a string of all traders names sorted alphabetically.
        transactions.stream()
                .map(tx -> tx.getTrader().getName())
                .sorted()       // Stream<String>의 경우 바로 정렬 가능
                .reduce("", (s1, s2) -> s1 + s2);



        // Query 5: Are there any trader based in Milan?
        System.out.println("[5]");
        boolean milan = transactions.stream()
                .map(tx -> tx.getTrader().getCity())
                .anyMatch(city -> city.equals("Milan"));
        System.out.println(milan);

        // Query 6: Update all transactions so that the traders from Milan are set to Cambridge.
        transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Milan"))
                .forEach(trader -> trader.setCity("Cambridge"));



        // Query 7: What's the highest value in all the transactions?
        int maxValue = transactions.stream()
                .mapToInt(Transaction::getValue)
                .max()
                .orElse(0);
        System.out.println(maxValue);


    }
}