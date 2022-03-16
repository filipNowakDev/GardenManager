package pl.filipnowakdev.gardenmanager.events.bus;

import org.springframework.stereotype.Component;
import pl.filipnowakdev.gardenmanager.events.Event;
import pl.filipnowakdev.gardenmanager.events.EventListener;

import java.util.LinkedList;
import java.util.List;

@Component
public class DefaultEventBus implements EventBus {

    private List<EventListener> eventListeners = new LinkedList<>();


    @Override
    public void publishEvent(final Event event) {
        eventListeners.forEach(listener -> listener.consumeEvent(event));
    }

    @Override
    public void registerListener(final EventListener eventListener) {
        eventListeners.add(eventListener);
    }
}
