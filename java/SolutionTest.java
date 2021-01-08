import static org.junit.Assert.assertEquals;

import model.Proponent;
import model.Proposal;
import model.Province;
import model.Warranty;
import org.junit.Test;

import java.util.UUID;

public class SolutionTest {
    @Test
    public void isValidProposalTest(){
        Proposal proposal = new Proposal(UUID.randomUUID(), 30000,24);
        Proponent proponent1 = new Proponent(UUID.randomUUID(), "João Silva", 25, 5500,false);
        Proponent proponent2 = new Proponent(UUID.randomUUID(), "Maria Silva", 25, 75000,true);
        Warranty warranty1 = new Warranty(UUID.randomUUID(), 450000, Province.SP);
        proposal.addOrUpdateProponent(proponent1.getProponentId(), proponent1);
        proposal.addOrUpdateProponent(proponent2.getProponentId(), proponent2);
        proposal.addOrUpdateWarranty(warranty1.getWarrantyId(), warranty1);
        assertEquals(true, proposal.IsValid());
    }
}
