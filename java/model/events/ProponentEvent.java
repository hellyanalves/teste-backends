package model.events;

import model.Proponent;
import java.util.UUID;

public class ProponentEvent extends Event {
    private UUID eventProponentId;
    private Proponent eventProponent;

    public ProponentEvent(String[] eventAttributes, String[] proponentAttributes){
        super(eventAttributes);
        eventProponent = new Proponent(proponentAttributes);
    }

    public ProponentEvent(String[] eventAttributes, String proponentIdStr){
        super(eventAttributes);
        eventProponentId = UUID.fromString(proponentIdStr);
    }

    public Proponent getEventProponent(){
        return eventProponent;
    }

    public UUID getEventProponentId(){
        return eventProponentId;
    }
}
