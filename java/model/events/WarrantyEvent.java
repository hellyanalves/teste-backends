package model.events;

import model.Warranty;

import java.util.UUID;

public class WarrantyEvent extends Event {
    private UUID eventWarrantyId;
    private Warranty eventWarranty;

    public WarrantyEvent(String[] attributes, String[] eventWarrantyAttributes) {
        super(attributes);
        this.eventWarrantyId = UUID.fromString(eventWarrantyAttributes[0]);
        this.eventWarranty = new Warranty(eventWarrantyAttributes);
    }

    public WarrantyEvent(String[] attributes, String warrantyIdStr) {
        super(attributes);
        this.eventWarrantyId = UUID.fromString(warrantyIdStr);
    }

    public Warranty getEventWarranty() {
        return eventWarranty;
    }

    public UUID getEventWarrantyId(){
        return eventWarrantyId;
    }
}
