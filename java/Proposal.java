import java.util.List;
import java.util.UUID;

public class Proposal {
    private UUID proposalId;
    private double proposalLoanValue;
    private int proposalMonthlyInstallments;
    private List<Proponent> proponents;

    public Proposal(UUID proposalId, double proposalLoanValue, int proposalMonthlyInstallments) {
        this.proposalId = proposalId;
        this.proposalLoanValue = proposalLoanValue;
        this.proposalMonthlyInstallments = proposalMonthlyInstallments;
    }

    public Proposal(String[] attributes) {
        this.proposalId = UUID.fromString(attributes[0]);
        this.proposalLoanValue = Double.parseDouble(attributes[1]);
        this.proposalMonthlyInstallments = Integer.parseInt(attributes[2]);
    }

    public UUID getProposalId() {
        return proposalId;
    }

    public double getProposalLoanValue() {
        return proposalLoanValue;
    }

    public int getProposalMonthlyInstallments() {
        return proposalMonthlyInstallments;
    }

    public void addProponent (Proponent newProponent){
        proponents.add(newProponent);
    }

    public Boolean IsValid(){
        return  true;
    }
}
