package model;

import java.util.UUID;

public class Proponent {
    private UUID proponentId;
    private String proponentName;
    private  int proponentAge;
    private double proponentMonthlyIncome;
    private  boolean proponentIsMain;

    public UUID getProponentId() {
        return proponentId;
    }

    public Proponent(UUID proponentId, String proponentName, int proponentAge, double proponentMonthlyIncome, boolean proponentIsMain) {
        this.proponentId = proponentId;
        this.proponentName = proponentName;
        this.proponentAge = proponentAge;
        this.proponentMonthlyIncome = proponentMonthlyIncome;
        this.proponentIsMain = proponentIsMain;
    }

    public Proponent(String[] attributes){
        this.proponentId = UUID.fromString(attributes[0]);
        this.proponentName = attributes[1];
        this.proponentAge = Integer.parseInt(attributes[2]);
        this.proponentMonthlyIncome = Double.parseDouble(attributes[3]);
        this.proponentIsMain = Boolean.parseBoolean(attributes[4]);
    }

    public int getProponentAge() {
        return proponentAge;
    }
    public boolean isProponentMain() {
        return proponentIsMain;
    }

    public Boolean hasValidIncome(double proposalInstallmentValue){
        //A renda do proponente principal deve ser pelo menos:
        //4 vezes o valor da parcela do empréstimo, se a idade dele for entre 18 e 24 anos
        if ((this.proponentAge >= 18 && this.proponentAge <= 24) && (this.proponentMonthlyIncome < (4 * proposalInstallmentValue))){
            return false;
        }

        //3 vezes o valor da parcela do empréstimo, se a idade dele for entre 24 e 50 anos
        if ((this.proponentAge > 24 && this.proponentAge <= 50) && (this.proponentMonthlyIncome < (3 * proposalInstallmentValue))){
            return false;
        }

        //2 vezes o valor da parcela do empréstimo, se a idade dele for acima de 50 anos
        if ((this.proponentAge > 50) && (this.proponentMonthlyIncome < (2 * proposalInstallmentValue))){
            return false;
        }

        return true;
    }
}
