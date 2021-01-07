import  java.util.UUID;
public class Warranty {
    private UUID warrantyId;
    private double warrantValue;
    private Province warrantyProvince;

    public Warranty(UUID warrantyId, double warrantValue, Province warrantyProvince) {
        this.warrantyId = warrantyId;
        this.warrantValue = warrantValue;
        this.warrantyProvince = warrantyProvince;
    }

    public Warranty(String[] attributes) {
        this.warrantyId = UUID.fromString(attributes[0]);
        this.warrantValue = Double.parseDouble(attributes[1]);
        this.warrantyProvince = Province.valueOf(attributes[2]);
    }
}
