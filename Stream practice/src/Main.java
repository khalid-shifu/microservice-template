import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = List.of(1,2,3,23,64,61,29,34,101);
        int[] arr = {1,2,3,23,64,61,29,34,101};

        Arrays.stream(arr)
                .filter(n -> n % 2 == 0)
                .findFirst()
                .ifPresent(System.out::println);

        list.stream()
                .filter(n -> n % 2 == 1)
                .reduce((prev, current) -> current)
                .ifPresent(System.out::println);

        list.stream()
                .filter(n -> n % 2 == 1)
                .collect(Collectors.collectingAndThen(
                    Collectors.toList(), lst -> {
                            Collections.reverse(lst);
                            return lst;
                    }))
                .forEach(n -> System.out.print(n + " "));

    }
}