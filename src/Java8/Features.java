/**
 * Copyright 2017 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package Java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * @author ashish gupta (akonda@expedia.com)
 */


interface Formula{
    double calculate(int a);
    default double sqrt(int a){
        return Math.sqrt(a);
    }
}

@FunctionalInterface
interface Converter<F, T>{
    T convert(F from);
}

class Person{
    String first,last;

    Person(){
    }

    Person(String fname, String lname){
        this.first = fname;
        this.last = lname;
    }
}


interface PersonFactory< P extends Person>{
    P create(String fname, String lname);
}



public class Features {

    public static void main(String[] args){

        Formula f = new Formula(){
            @Override
            public double calculate(int a) {
                return Math.exp(a);
            }
        };

        System.out.println(f.calculate(5));
        System.out.println(f.sqrt(5));

        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
//        Collections.sort(names, new Comparator<String>(){
//
//            @Override
//            public int compare(String o1, String o2) {
//                return o1.compareTo(o2);
//            }
//        });


        names.sort((a,b) -> a.compareTo(b));
        //Collections.sort(names, (a,b) -> a.compareTo(b));

        System.out.println(Arrays.toString(names.toArray()));

        Converter<String, Integer> conv = (s) -> Integer.valueOf(s);
        System.out.println(conv.convert("123"));

        Converter<Integer, String> conv1 = String::valueOf;
        System.out.println(conv1.convert(243));

        PersonFactory<Person> p = Person::new;
        Person person = p.create("Peter","Park");


        Predicate<String> predicate = a -> a.length()>0;
        predicate.test("dgsdgs");

        Predicate<String> isEmpty = String::isEmpty;


        //Optionals

        Optional<String> op = Optional.of("asad");
        op.orElse("vfe");


        //Streams
        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");

        stringCollection.stream()
                        .filter(s -> s.startsWith("a"))
                        .forEach(System.out::println);

        stringCollection.stream()
                        .sorted()
                        .filter(s -> s.startsWith("a"))
                        .forEach(System.out::println);


        stringCollection.stream()
                        .map(s -> s.toUpperCase())
                        .sorted((a,b) -> b.compareTo(a))
                        .forEach( s -> System.out.print(s + " "));

        System.out.println(stringCollection);

        Optional<String> reduced = stringCollection.stream()
                                                    .sorted()
                                                    .reduce((s1,s2) -> s1+"#"+s2);

        reduced.ifPresent(System.out::println);

    }
}
