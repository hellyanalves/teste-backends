import java.util.UUID;

public class EventProposal extends Event {
    private Proposal eventProposal;

    public EventProposal(String[] attributes, String[] eventProposalAttributes) {
        super(attributes);
        this.eventProposal = new Proposal(eventProposalAttributes);
    }
}
