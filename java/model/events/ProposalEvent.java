package model.events;

import model.Proposal;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.UUID;

public class ProposalEvent extends Event {
    private UUID eventProposalId;
    private Proposal eventProposal;

    public ProposalEvent(String[] attributes, String[] eventProposalAttributes) {
        super(attributes);
        this.eventProposal = new Proposal(eventProposalAttributes);
    }

    public ProposalEvent(String[] attributes, String proposalIdStr) {
        super(attributes);
        this.eventProposalId = UUID.fromString(proposalIdStr);
    }

    public Proposal getEventProposal(){
        return eventProposal;
    }

    public UUID getEventProposalId(){
        return eventProposalId;
    }
}
