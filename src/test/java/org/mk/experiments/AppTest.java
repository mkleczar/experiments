package org.mk.experiments;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class AppTest {

    @Test
    public void shouldAnswerWithTrue() {
        assertThat(true).isTrue();
    }


    @Test
    public void aStoryTest() {
        /*
        Pewnego dnia
        grupa przyjaciół: Eve, Yvonne, Adam, Mark, Jack, Mario
        postanowili zorganizować imprezę: wyprawa na wyspę miłości.
        Eve wydała 423,00 na jedzenie dla wszystkich.
        Mark wydał 750,00 na picie dla wszystkich.
        Jack wydał 330,00 na podróż na miejsce dla: siebie, Eve i Marka.
        Mario, wydał 370,00 na podróż na miejsce dla: siebie, Yvonne i Adama.
        Powiedz mi:
        kto ile na kogo wydał,
        kto komu ile jest winien,
        kto ile jest winien Eve i ile ona jest komu winna.
        Koniec.
         */
        SplitExpBuilder
                .oneDay()
                .aBunchOfFriends("Eve", "Yvonne", "Adam", "Mark", "Jack", "Mario")
                .decidedToOrganizeEvent("Trip to the Love Island")
                .teamMember("Eve").spent(Currency.of("420.00")).on("Food").onBehalfOfAll()
                .next()
                .teamMember("Mark").spent(Currency.of("840.00")).on("Drinks").onBehalfOfAll()
                .next()
                .teamMember("Jack").spent(Currency.of("330.00")).on("Jack: Travel to destination").onBehalfOf("Jack", "Eve", "Mark")
                .next()
                .teamMember("Mario").spent(Currency.of("390.00")).on("Mario: Travel to destination").onBehalfOf("Mario", "Yvonne", "Adam")
                .showMe()
                .grossMatrix()
                .netMatrix()
                .calculationFor("Eve")
                .theEnd();
    }
}
