import java.util.UUID;

public class Proponent {
    private UUID proponentId;
    private String proponentName;
    private  int proponentAge;
    private double proponentMonthlyIncome;
    private  boolean proponentIsMain;

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
        this.proponentIsMain = Boolean.parseBoolean(attributes[0]);
    }


    public String getProponentName() {
        return proponentName;
    }

    public void setProponentName(String proponentName) {
        this.proponentName = proponentName;
    }

    public int getProponentAge() {
        return proponentAge;
    }

    public void setProponentAge(int proponentAge) {
        this.proponentAge = proponentAge;
    }

    public double getProponentMonthlyIncome() {
        return proponentMonthlyIncome;
    }

    public void setProponentMonthlyIncome(double proponentMonthlyIncome) {
        this.proponentMonthlyIncome = proponentMonthlyIncome;
    }

    public boolean isProponentIsMain() {
        return proponentIsMain;
    }

    public void setProponentIsMain(boolean proponentIsMain) {
        this.proponentIsMain = proponentIsMain;
    }
}
