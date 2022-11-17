package de.cronn;

import de.cronn.person.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        List<Person> people = List.of(
                new YoungPerson("dwadwa", "Dawda"),
                new YoungPerson("dawwa", "dawdwa"),
                new YoungPerson("dawdwa", "Dawdwa")
        );
    }
    //WITHOUT
    private static void print(int value) {
        System.out.println(value);
    }

    private static void print(String value) {
        System.out.println(value);
    }

    private static void print(char value) {
        System.out.println(value);
    }

    //WITH
    private static <T> void printGeneric(T value) {
        System.out.println(value);
    }

    //WITHOUT
    private static void printEnriched(int value) {
        System.out.println(value + 50);
    }

    private static void printEnriched(String value) {
        System.out.println(value + "0350");
    }

    private static void printEnriched(char value) {
        System.out.println(value + 'V');
    }

    //WITH

    private static <T> void printEnrichedGeneric(T value, Function<T, String> function) {
        System.out.println(function.apply(value));
    }

    private static void printPeople(List<Person> people) {
        people.forEach(Person::personMethod);
    }

    private static void printStudents(List<Student> students) {
        students.forEach(Student::study);
    }

    private static <T extends Person & Student> void printPeopleStudying(List<T> students) {
        students.forEach(Person::personMethod);
        students.forEach(Student::study);
    }

    private static void superMethod(List<? super Person> superPeople){
        superPeople.forEach(System.out::println);
    }
    /*
        private static void perform(List<YoungPerson> people) {
            for (YoungPerson person : people) {
                System.out.println(person.getName().toLowerCase());
            }
        }

        private static void perform(List<MiddleAgedPerson> people) {
            for (MiddleAgedPerson person : people) {
                System.out.println(person.getName().toUpperCase());
            }
        }

        private static void perform(List<OldPerson> people) {
            for (OldPerson person : people) {
                System.out.println(Arrays.toString(person.getName().getBytes()));
            }
        }


     */
    private static void performSolutionOne(List<Person> people) {
        for (Person person : people) {
            if (person instanceof YoungPerson youngPerson) {
                System.out.println(youngPerson.getName().toLowerCase());
            }
            if (person instanceof MiddleAgedPerson middleAgedPerson) {
                System.out.println(middleAgedPerson.getName().toUpperCase());
            }
            if (person instanceof OldPerson oldPerson) {
                System.out.println(Arrays.toString(oldPerson.getName().getBytes()));
            }
        }
    }

    private static void performSolutionTwo(List<Person> people) {
        for (Person person : people) {
            switch (person) {
                case YoungPerson youngPerson -> System.out.println(youngPerson.getName().toLowerCase());
                case MiddleAgedPerson middleAgedPerson -> System.out.println(middleAgedPerson.getName().toUpperCase());
                case OldPerson oldPerson -> System.out.println(Arrays.toString(oldPerson.getName().getBytes()));
                case Person defaultPerson -> System.out.println("Default operation");
            }
        }
    }

    static Map<Class<? extends Person>, Consumer<Person>> operations = Map.of(
            YoungPerson.class, person
                    -> System.out.println(person.getName().toLowerCase()),

            MiddleAgedPerson.class, person
                    -> System.out.println(person.getName().toUpperCase()),

            OldPerson.class, person
                    -> System.out.println(Arrays.toString(person.getName().getBytes()))
    );

    private static void performSolutionThree(List<? extends Person> people) {
        people.forEach(person -> operations.get(person.getClass()).accept(person));
    }

    /*
    private static void wildcardCase(List<? extends Person> people) {
        Consumer<? extends Person> consumer = Person::personMethod;
        people.forEach(consumer::accept);
    }


     */
    private static <T extends Person> void genericTypeCase(List<T> people) {
        Consumer<T> consumer = Person::personMethod;
        people.forEach(consumer);
    }

    private static <T> Function<T, Function<List<Predicate<T>>, Long>> passedPredicatesCount() {
        return obj -> listOfPredicates -> listOfPredicates.stream()
                .filter(predicate -> predicate.test(obj))
                .count();
    }
}
