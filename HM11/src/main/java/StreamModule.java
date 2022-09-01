import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamModule {

    public static void main(String[] args) {

        //Task 1
        List<String> names = Arrays.asList("Ivan", "Dean", "Oleg", "Anna", "Oksana", "Dima", "Sam",
                "Ivan", "Orest", "Peter", "Sasha", "Petro", "Olha", "Karina", "Ihor", "Misha", "Derek");
        System.out.println(namesPrintedFromOddPositions(names));

        //Task 2

        System.out.println(namesReverse(names));

        //Task 3

        String[] input = new String[]{"1, 2, 0", "4, 5"};
        System.out.println(numberSort(input));

        //Task 4

         endlessStream().forEach(System.out::println);

         //Task 5
        Stream<String> first = Stream.of("1", "2", "3");
        Stream<String> second = Stream.of("22", "23", "24");
        List<String> result = zip(first, second).collect(Collectors.toList());
        System.out.println(result);

    }

    public static String namesPrintedFromOddPositions (List<String> names) {
        return IntStream.range(0, names.size())
                .filter(n -> n % 2 != 0)
                .mapToObj(index -> String.format("%d. %s",index, names.get(index)))
                .collect(Collectors.joining(", "));
    }

    public static List<String> namesReverse (List<String> names) {
        return names.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    public static String numberSort (String [] number) {
        return Arrays.stream(number)
                .flatMap(s -> Stream.of(s.split((", "))))
                .map(Integer::valueOf)
                        .sorted(Integer::compareTo)
                        .map(String::valueOf)
                        .collect(Collectors.joining(", "));
    }

    private static Stream<Long> endlessStream () {
        long a = 25214903917L;
        long c = 11;
        long m = (long) Math.pow(2,48);
        long seed = 0;
        return Stream.iterate(seed, x -> (a * (x + c) % m));
    }

    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        List<T> firstList = first.collect(Collectors.toList());
        List<T> secondList = second.collect(Collectors.toList());

        int size = Math.min(firstList.size(), secondList.size());
        List<T> result = new ArrayList<>();

        IntStream.range(0,size).forEach((element) ->{
                    result.add(firstList.get(element));
                    result.add(secondList.get(element));
                }
        );
        return result.stream();
    }
}
