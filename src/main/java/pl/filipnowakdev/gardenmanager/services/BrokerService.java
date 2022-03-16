package pl.filipnowakdev.gardenmanager.services;

import pl.filipnowakdev.gardenmanager.events.Event;
import pl.filipnowakdev.gardenmanager.events.EventListener;
import pl.filipnowakdev.gardenmanager.events.bus.EventBus;

public class BrokerService implements EventListener {

    private final EventBus eventBus;

    public BrokerService(final EventBus eventBus) {
        this.eventBus = eventBus;
        eventBus.registerListener(this);
    }

    @Override
    public void consumeEvent(Event event) {

    }
}
