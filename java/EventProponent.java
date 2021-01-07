import java.util.UUID;

public class EventProponent extends Event {
    private Proponent eventProponent;

    public EventProponent (String[] eventAttributes, String[] proponentAttributes){
        super(eventAttributes);
        eventProponent = new Proponent(proponentAttributes);
    }

}
