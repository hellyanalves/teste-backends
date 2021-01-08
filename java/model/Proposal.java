package model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Proposal {
    private UUID proposalId;
    private double proposalLoanValue;
    private int proposalMonthlyInstallments;
    private HashMap<UUID, Proponent> proponents = new HashMap<>();
    private HashMap<UUID, Warranty> warranties = new HashMap<>();
    private LocalDateTime updateTimestamp;

    public Proposal(UUID proposalId, double proposalLoanValue, int proposalMonthlyInstallments) {
        this.proposalId = proposalId;
        this.proposalLoanValue = proposalLoanValue;
        this.proposalMonthlyInstallments = proposalMonthlyInstallments;
    }

    public Proposal(String[] attributes) {
        // TODO add updated timestamp
        this.proposalId = UUID.fromString(attributes[0]);
        this.proposalLoanValue = Double.parseDouble(attributes[1]);
        this.proposalMonthlyInstallments = Integer.parseInt(attributes[2]);
    }

    public UUID getProposalId() {
        return proposalId;
    }

    public Double warrantiesValue(){
        Double warrantiesValue = 0.0 ;
        for (Warranty warranty : warranties.values()){
            warrantiesValue += warranty.GetWarrantyValue();
        }
        return warrantiesValue;
    }

    public LocalDateTime getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void addOrUpdateProponent (UUID proponentId, Proponent newProponent){
        proponents.put(newProponent.getProponentId(), newProponent);
    }

    public void removeProponent (UUID removeId){
        proponents.remove(removeId);
    }

    public void addOrUpdateWarranty (UUID warrantyId, Warranty newWarranty){
        warranties.put(warrantyId, newWarranty);
    }

    public void removeWarranty (UUID warrantyId){
        proponents.remove(warrantyId);
    }

    private Boolean hasValidProponents(){
        // Deve haver no mínimo 2 proponentes por proposta
        if (proponents.size() < 2){
            return  false;
        }

        for (Proponent p : proponents.values()){
            if(!p.hasValidIncome(proposalLoanValue/proposalMonthlyInstallments)) {
                return false;
            }
        }

        // Deve haver exatamente 1 proponente principal por proposta
        //Todos os proponentes devem ser maiores de 18 anos
        int mainProponents = 0;
        for (Proponent p : proponents.values()){
            if (p.getProponentAge() < 18){
                return false;
            }
            if (p.isProponentMain()) {
                mainProponents++;
            }
        }
        if (mainProponents > 1){
            return  false;
        }

        return true;
    }

    private Boolean hasValidWarranties(){
        //As garantias de imóvel dos estados PR, SC e RS não são aceitas
        for(Warranty w :warranties.values()){
            if(!w.IsValid()){
                return false;
            }
        }

        //Dever haver no mínimo 1 garantia de imóvel por proposta
        if (warranties.size() < 1){
            return false;
        }

        //A soma do valor das garantias deve ser maior ou igual ao dobro do valor do empréstimo
        if (warrantiesValue() < (2 * proposalLoanValue)){
            return false;
        }

        return true;
    }

    public Boolean IsValid(){
        // Validação de proponentes
        if (!hasValidProponents()){
            return false;
        }

        // Validações de garantias
        if (!hasValidWarranties()){
            return false;
        }

        // O valor do empréstimo deve estar entre R$ 30.000,00 e R$ 3.000.000,00
        if (proposalLoanValue < 30000 || proposalLoanValue > 3000000){
            return false;
        }

        // O empréstimo deve ser pago em no mínimo 2 anos e no máximo 15 anos
        if (proposalMonthlyInstallments < 24 || proposalLoanValue > 180){
            return false;
        }

        return true;
    }
}
