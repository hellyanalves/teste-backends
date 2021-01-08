package model;

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

    public Boolean IsValid(){
        // Validar estado
        if (warrantyProvince == Province.PR || warrantyProvince == Province.SC || warrantyProvince == Province.RS){
            return false;
        }
        return true;
    }

    public Double GetWarrantyValue(){
        return this.warrantValue;
    }
}
