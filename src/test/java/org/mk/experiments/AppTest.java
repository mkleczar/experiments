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
        SplitExpBuilder
                .oneDay()
                .aBunchOfFriends("Eve", "Yvonne", "Adam", "Mark", "Jack", "Mario")
                .decidedToOrganizeEvent("Trip to the Love Island")
                .teamMember("Eve").spent(Currency.of("423.00")).on("Food").onBehalfOfAll()
                .next()
                .teamMember("Mark").spent(Currency.of("750.00")).on("Drinks").onBehalfOfAll()
                .next()
                .teamMember("Jack").spent(Currency.of("330.00")).on("Jack: Travel to destination").onBehalfOf("Jack", "Eve", "Mark")
                .next()
                .teamMember("Mario").spent(Currency.of("370.00")).on("Mario: Travel to destination").onBehalfOf("Mario", "Yvonne", "Adam")
                .showMe()
                .grossMatrix()
                .netMatrix()
                .calculationFor("Eve")
                .theEnd();
    }
}
