package org.mk.experiments;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class CageTest {

    @Test
    public void putToCage() {
        Cage<Mammal> mammalCage = new Cage<>();
        mammalCage = new Cage<>(new Dog());
        mammalCage = new Cage<>(new Whale());
        mammalCage = new Cage<>(new Mammal());

        mammalCage.put(new Dog());
        mammalCage.put(new Whale());
        mammalCage.put(new Mammal());
    }

    @Test
    public void notAllowedTest() {
        // Cage<Dog> dogCage = new Cage<>(new Whale());
        // Cage<Dog> dogCage = new Cage<>(new Mammal());
    }


    private String introduce(Cage<Mammal> mammalCage) {
        return mammalCage.get().whoami();
    }

    @Test
    public void functionTest() {
        introduce(new Cage<>(new Mammal()));
        introduce(new Cage<>(new Dog()));
        introduce(new Cage<>(new Whale()));
        introduce(new Cage<Mammal>(new Mammal()));
        introduce(new Cage<Mammal>(new Dog()));
        introduce(new Cage<Mammal>(new Whale()));

        // introduce(new Cage<Whale>(new Whale()));
    }

    @Test
    public void whatWouldBeIf() {
        Cage<Mammal> mammalCage = new Cage<>();
        mammalCage.put(new Mammal());
        mammalCage.put(new Dog());

        // mammalCage = new Cage<Dog>();

        mammalCage.put(new Whale());
    }

    private void whaleGenerator(Cage<Mammal> mammalCage) {
        mammalCage.put(new Whale());
    }

    @Test
    public void misuseOfWhaleGenerator() {
        Cage<Dog> dogCage = new Cage<>();
        //whaleGenerator(dogCage);
    }

    //private String genericIntroduce(Cage<Mammal> mammalCage) {
    private String genericIntroduce(Cage<? extends Mammal> mammalCage) {
        return mammalCage.get().whoami();
    }

    @Test
    public void genericIntroduceTest() {
        Cage<Dog> dogCage = new Cage<>(new Dog());
        genericIntroduce(dogCage);

        Cage<Whale> whaleCage = new Cage<>(new Whale());
        genericIntroduce(whaleCage);
    }

    private String genericIntroduceMisuse(Cage<? extends Mammal> mammalCage) {
        // mammalCage.put(new Dog());
        // mammalCage.put(new Mammal());
        return mammalCage.get().whoami();
    }

    @Test
    public void genericIntroduceMisuse() {
        Cage<Object> objectCage = new Cage<>();
        //genericIntroduce(objectCage);
    }
}
