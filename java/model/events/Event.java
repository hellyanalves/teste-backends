package model.events;

import model.ActionType;


import  java.util.UUID;
import  java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Event {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssz");
    private UUID eventId;
    private EventSchema eventSchema;
    private ActionType eventAction;
    private LocalDateTime eventTimestamp;
    private UUID proposalId;

    public UUID getEventId (){
        return  eventId;
    }

    public EventSchema getEventSchema() {
        return eventSchema;
    }

    public ActionType getEventAction() {
        return eventAction;
    }

    public LocalDateTime getEventTimestamp() {
        return eventTimestamp;
    }

    public Event(String[] attributes){
        eventId = UUID.fromString(attributes[0]);
        eventSchema = EventSchema.valueOf(attributes[1]);
        eventAction = ActionType.valueOf(attributes[2]);
        eventTimestamp = LocalDateTime.parse(attributes[3], formatter);
        proposalId = UUID.fromString(attributes[4]);
    }
}
