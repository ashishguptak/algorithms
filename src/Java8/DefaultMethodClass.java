package Java8; /**
 * Copyright 2017 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

/**
 * @author ashish gupta (akonda@expedia.com)
 */

interface Fly{
    default public void takeOff(){
        System.out.println("Java8.Fly::takeOff");
    }
    default public void turn(){
        System.out.println("Java8.Fly::turn");
    }
    default public void cruise(){
        System.out.println("Java8.Fly::cruise");
    }
    default public void land(){
        System.out.println("Java8.Fly::land");
    }
}

interface Fastfly extends Fly{
    default public void takeOff(){
        System.out.println("FastFly::takeOff");
    }
}

class Vehicle{
    public void land(){
        System.out.println("Java8.Vehicle::land");
    }
}

interface Sail{
    default public void cruise(){
        System.out.println("Java8.Sail::cruise");
    }
}

class SeaPlane extends Vehicle implements Fastfly, Sail{
    //collision of cruise method and not in class hierarchy
    public void cruise(){
        //System.out.println("Seaplane::cruise");
        Fastfly.super.cruise();
    }
}

public class DefaultMethodClass {

    public void use(){
        SeaPlane sp = new SeaPlane();
        // method calls based on class hierarchy
        sp.takeOff();
        sp.turn();
        sp.cruise();
        sp.land();
    }
    public static void main(String[] args) {
        new DefaultMethodClass().use();
    }
}
