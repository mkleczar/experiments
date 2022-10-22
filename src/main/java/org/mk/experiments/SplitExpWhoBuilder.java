package org.mk.experiments;

import lombok.AllArgsConstructor;
import org.mk.experiments.context.EventContext;

import java.util.List;

@AllArgsConstructor
public class SplitExpWhoBuilder {

    private EventContext context;

    public SplitExpEventBuilder aBunchOfFriends(String ... friends) {
        context.setParticipants(List.of(friends));
        return new SplitExpEventBuilder(context);
    }
}
