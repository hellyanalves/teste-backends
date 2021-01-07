import java.sql.Timestamp;
import  java.util.UUID;
import  java.time.LocalDateTime;

enum  ActionType{
    created,
    added
}

public abstract class Event {
    private UUID eventId;
    private EventSchema eventSchema;
    private ActionType eventAction;
    private LocalDateTime eventTimestamp;
    private  UUID proposalId;

    public UUID getEventId (){
        return  eventId;
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
        eventTimestamp = LocalDateTime.parse(attributes[3]);
        proposalId = UUID.fromString(attributes[4]);
    }
}
