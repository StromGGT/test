package com.qunar.fin;

import com.qunar.fin.listener.Event;

import java.util.function.*;

/**
 * @author guotao.gou
 * @version 1.0
 * @date 2020/6/23 17:03
 */
public class FunctionInterfaceTest {

    public static void main(String[] args) {
//        printFunction(10, Double::new);
//        printUnaryOperator(5, input -> input * 2);
//        printPredicate(5, input -> input > 2);
//        printConsumer(System.out::println);
//        printSupplier(() -> 5);

        Event event = new Event();
        event.setName("1111");
        printFunction1(event, event1 -> System.out.println(event1.getName()));
        event.setName("2222");
    }

    public static void printFunction(Integer input, Function<Integer, Double> function) {
        System.out.println(function.apply(input));
    }

    public static void printUnaryOperator(Integer input, UnaryOperator<Integer> operator) {
        System.out.println(operator.apply(input));
    }

    public static void printPredicate(Integer input, Predicate<Integer> predicate) {
        System.out.println(predicate.test(input));
    }

    private static void printConsumer(Consumer<Integer> consumer) {
        consumer.accept(5);
    }

    private static void printSupplier(Supplier<Integer> supplier) {
        System.out.println(supplier.get());
    }

    private static void printFunction1(Event event, Consumer<Event> consumer) {
        consumer.accept(event);
    }
}
