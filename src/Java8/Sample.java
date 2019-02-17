package Java8; /**
 * Copyright 2017 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @author ashish gupta (akonda@expedia.com)
 */
public class Sample {

    public static int totalVal(List<Integer> numbers, Predicate<Integer> selector){

        return numbers.stream()
                        .filter(selector)
                        .reduce(0, (c,e) -> c+e);

        /*
        int total = 0;
        for(int e:numbers)
            if(selector.test(e)) total += e;
        return total;
        */
    }

    public static void main(String[] args){
        List<Integer> values = Arrays.asList(1,2,3,4,5);

        /*
        //external iterator
        for(int e:values)
            System.out.println(e);
            */


        //internal iterator - anonymous inner class
        /*
        values.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        });
        */

        //anonymous function
        //values.forEach((Integer value)->System.out.println(value));


        /*
        main objectives of func
        1) func name - not required
        2) func body
        3) return value
        4) func parameters

                       param      func body
        */
        values.forEach(value -> System.out.println(value));

        //method reference
        //values.forEach(System.out::println);


        //imperative style - involves mutation for total
        /*
        int total =0;
        for(int e:values)
            total += e*2;
        System.out.println(total);
        */

        //declarative style - easy to parallelize
        System.out.println(
                values.stream()
                        .map( e-> 2*e)
                        .reduce(0, (c,e) -> c+e)
        );


        //strategy pattern instead of anonymous inner class
        System.out.println(totalVal(values, e -> true));
        System.out.println(totalVal(values, e -> e%2 == 1));
        System.out.println(totalVal(values, e -> e%2 != 1));

        //lazy initializer - efficient
        System.out.println(
                values.stream()
                        .filter(e -> e>3)
                        .filter(e -> e%2 == 0)
                        .map(e -> 2*e)
                        .findFirst()
                        .orElse(0)
        );
    }

}
