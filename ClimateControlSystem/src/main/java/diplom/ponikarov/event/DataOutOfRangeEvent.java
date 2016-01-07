package diplom.ponikarov.event;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

public class DataOutOfRangeEvent extends Event {

    public static final EventType<DataOutOfRangeEvent> DATA_OUT_OF_RANGE_EVENT_EVENT_TYPE =
            new EventType<>("DATA_OUT_OF_RANGE_EVENT_EVENT_TYPE");

    public DataOutOfRangeEvent() {
        super(DATA_OUT_OF_RANGE_EVENT_EVENT_TYPE);
    }

    @Override
    public DataOutOfRangeEvent copyFor(Object newSource, EventTarget newTarget) {
        return (DataOutOfRangeEvent) super.copyFor(newSource, newTarget);
    }

    @Override
    public EventType<? extends DataOutOfRangeEvent> getEventType() {
        return (EventType<? extends DataOutOfRangeEvent>) super.getEventType();
    }


}
