package homework15;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;


public class Main {
    public static void main(String[] args) {
        emptyStream();
        streamWithListToSet();
        streamWithSetToList();
        streamMapSetToCollection();
        streamUniqueCollectionFromStringToInt();
        parallelStream();
        streamWithReferenceMethods();
    }

    public static void emptyStream() {
        Optional<String> empty = Stream.empty().map(str -> str + " - empty").skip(1).findFirst();

        empty.ifPresent(System.out::println);
    }

    public static void streamWithListToSet() {

        List<String> list = new ArrayList<>();

        list.add("Nikita");
        list.add("Artem");
        list.add("Kirill");
        list.add("Andrew");
        list.add("Kate");
        list.add("Sofia");

        Set<String> set = list.stream().filter(a -> a.length() > 5).skip(1).collect(toSet());

        set.forEach(System.out::println);
    }

    public static void streamWithSetToList() {
        Set<Students> studentsSet = new HashSet<>();
        studentsSet.add(new Students("Nikita","Solovei",19,65,"Male"));
        studentsSet.add(new Students("Anton","Antonino",25,89,"Male"));
        studentsSet.add(new Students("Anya","Hillel",28,67,"Female"));
        studentsSet.add(new Students("Kate","Lisovskaya",20,80,"Female"));
        studentsSet.add(new Students("Sofia","Markova",22,55,"Female"));
        studentsSet.add(new Students("Dmirty","Shevchenko",40,102,"Male"));

        List<Students> studentsList = studentsSet.stream().collect(toList());

        studentsList.forEach(System.out::println);
    }

    public static void streamMapSetToCollection() {
        Map<Integer,String> map = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            map.put(i, "java" + i);
        }

        Set<Integer> set = map.keySet().stream().collect(toSet());
        set.forEach(System.out::println);
    }

    public static void streamUniqueCollectionFromStringToInt() {
        List<String> collection = Arrays.asList("1","2","3","4","8","6","7","8","9","10");

        collection.stream().skip(1).limit(8).mapToInt(Integer::parseInt).filter(a -> a % 2 == 0).distinct().forEach(System.out::println);
    }

    public static void parallelStream() {
        Stream<Integer> integerStream = Stream.of(12,13,14,15,16,17,11,21,23,3,33,60);

        Optional<Integer> parallelStreamInteger = integerStream.parallel().filter(i -> i % 3 == 0).sorted().reduce((n, m) -> n + m);

        System.out.println(parallelStreamInteger);
    }

    public static void streamWithReferenceMethods() {
        List<Teachers> teachersList = new ArrayList<>();

        teachersList.add(new Teachers("Andrew"));
        teachersList.add(new Teachers("Sergey"));
        teachersList.add(new Teachers("Dima"));

        List<Integer> li = teachersList.stream().map(Teachers::getName).map(Main::parse).sorted(Integer::compareTo).collect(Collectors.toCollection(ArrayList::new));

        li.forEach(System.out::println);
    }

    public static Integer parse(String s) {
        return s.length();
    }
}
