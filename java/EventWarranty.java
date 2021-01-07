import java.util.UUID;

public class EventWarranty extends Event {
    private Warranty eventWarranty;

    public EventWarranty(String[] attributes, String[] eventWarrantyAttributes) {
        super(attributes);
        this.eventWarranty = new Warranty(eventWarrantyAttributes);
    }
}
