package gr.nikolis.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class Shoe {
}

class IPhone {
}

interface Fruit {
}

class Apple implements Fruit {
    int apple;
}

class Banana implements Fruit {
    int bannana;
}

class GrannySmith extends Apple {
    int grannySmith;
}

public class WildCards {

    public void eatAll(Collection<? extends Fruit> fruits) {
    } //read (From Fruit and above)

    public void addApple(Collection<? super Apple> apples) {
    } //write (From Apple and up)


    private void testWildCards() {
        List<Fruit> fruits = new ArrayList<>();
        fruits.add(new Apple()); // Allowed, as Apple is a Fruit
        fruits.add(new Banana()); // Allowed, as Banana is a Fruit
        addApple(fruits); // Allowed, as "Fruit super Apple"
        eatAll(fruits); // Allowed

        Collection<Banana> bananas = new ArrayList<>();
        bananas.add(new Banana()); // Allowed
        //addApple(bananas); // Compile error: may only contain Bananas!
        eatAll(bananas); // Allowed, as all Bananas are Fruits

        Collection<Apple> apples = new ArrayList<>();
        Collection<GrannySmith> grannySmithApples = new ArrayList<>();
        addApple(apples); // Allowed
        apples.add(new GrannySmith()); // Allowed, as this is an Apple
        eatAll(apples); // Allowed, as all Apples are Fruits.
        //addApple(grannySmithApples); //Compile error: Not allowed. GrannySmith is not a supertype of Apple
        apples.add(new GrannySmith()); //Still allowed, GrannySmith is an Apple
        eatAll(grannySmithApples);//Still allowed, GrannySmith is a Fruit

        Collection<Object> objects = new ArrayList<>();
        addApple(objects); // Allowed, as Object super Apple
        objects.add(new Shoe()); // Not a fruit
        objects.add(new IPhone()); // Not a fruit
        //eatAll(objects); // Compile error: may contain a Shoe, too!
    }
}
