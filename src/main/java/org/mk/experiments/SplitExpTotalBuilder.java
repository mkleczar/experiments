package org.mk.experiments;

import org.mk.experiments.context.EventContext;
import org.mk.experiments.financial.Matrix;
import org.mk.experiments.financial.Transfer;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class SplitExpTotalBuilder {

    private final EventContext eventContext;

    public SplitExpTotalBuilder(EventContext eventContext) {
        this.eventContext = eventContext;
    }

    public SplitExpTotalBuilder eventName(Consumer<String> nameConsumer) {
        nameConsumer.accept(eventContext.getName());
        return this;
    }

    public SplitExpTotalBuilder eventParticipants(Consumer<List<String>> participantsConsumer) {
        participantsConsumer.accept(eventContext.getParticipants());
        return this;
    }


    public SplitExpTotalBuilder netMatrix(Consumer<Matrix> matrixConsumer) {
        Matrix matrix = new Matrix();
        generateTransfers().forEach(t -> matrix.net(t.getFrom(), t.getTo(), t.getAmount()));
        matrixConsumer.accept(matrix);
        return this;
    }

    public SplitExpTotalBuilder grossMatrix(Consumer<Matrix> matrixConsumer) {
        Matrix matrix = new Matrix();
        generateTransfers().forEach(t -> matrix.add(t.getFrom(), t.getTo(), t.getAmount()));
        matrixConsumer.accept(matrix);
        return this;
    }


    public SplitExpTotalBuilder allTransfers(Consumer<List<Transfer>> transfersConsumer) {
        transfersConsumer.accept(generateTransfers());
        return this;
    }

    public SplitExpTotalBuilder calculationFor(String name) {
        // TODO: single person financial nett
        return this;
    }

    public void theEnd() {
    }

    private List<Transfer> generateTransfers() {
        return eventContext.getExpenses()
                .stream()
                .flatMap(e -> e.getForWhom().stream().map(to ->
                        Transfer.builder()
                                .from(e.getWho())
                                .to(to)
                                .amount(e.getAmount().divide(e.getForWhom().size()))
                                .description(e.getForWhat())
                                .build()))
                .collect(Collectors.toList());
    }
}
