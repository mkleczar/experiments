package org.mk.experiments;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mk.experiments.financial.Matrix;
import org.mk.experiments.financial.Transfer;

import java.util.List;

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
                .eventName(this::nameConsumer)
                .eventParticipants(this::participantsConsumer)
                .allTransfers(this::allTransferConsumer)
                .grossMatrix(this::matrixConsumer)
                .netMatrix(this::matrixConsumer)
                .calculationFor("Eve")
                .theEnd();

        /*
        Trades:
                Total       Eve      Yvonne      Adam      Mark      Jack      Mario
        Eve       420        70          70        70        70        70         70
        Mark      840       140         140       140       140       140        140
        Jack      330       110                             110       110
        Mario     390                   130       130                            130

        Gross matrix
                  Eve      Yvonne      Adam      Mark      Jack      Mario
        Eve         x          70        70        70        70         70
        Yvonne      0           x         0         0         0          0
        Adam        0           0         x         0         0          0
        Mark      140         140       140         x       140        140
        Jack      110           0         0       110         x          0
        Mario       0         130       130         0         0          x

        Net matrix
                  Eve      Yvonne      Adam      Mark      Jack      Mario
        Eve         x          70        70       -70       -40         70
        Yvonne    -70           x         0      -140         0       -130
        Adam      -70           0         x      -140         0       -130
        Mark       70         140       140         x        30        140
        Jack       40           0         0       -30         x          0
        Mario     -70         130       130      -140         0          x
         */
    }

    private void nameConsumer(String name) {
        log.info("Event name is: {}", name);
    }
    private void participantsConsumer(List<String> participants) {
        log.info("Participants names are: {}", participants);
    }

    private void allTransferConsumer(List<Transfer> transfers) {
        transfers.forEach(t -> log.info("{} -> {} -> {} for '{}'",
                t.getFrom(), t.getAmount(), t.getTo(), t.getDescription()));
    }

    private void matrixConsumer(Matrix matrix) {
        log.info("Matrix is: {}", matrix.getMatrix());
    }
}
