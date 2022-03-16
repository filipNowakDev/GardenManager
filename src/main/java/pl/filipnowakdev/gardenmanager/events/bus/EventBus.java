package pl.filipnowakdev.gardenmanager.events.bus;

import pl.filipnowakdev.gardenmanager.events.Event;
import pl.filipnowakdev.gardenmanager.events.EventListener;

public interface EventBus {

    void publishEvent(final Event event);

    void registerListener(final EventListener eventListener);

}
