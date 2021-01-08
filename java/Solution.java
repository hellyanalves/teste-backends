import model.*;
import model.events.*;

import java.util.*;

public class Solution {

  public static Proposal handleProposalEvent(ProposalEvent proposalEvent, Proposal originalProposal){
    switch (proposalEvent.getEventAction()){
      case created:
        return proposalEvent.getEventProposal();
      case updated:
        if (originalProposal.getUpdateTimestamp().isBefore(proposalEvent.getEventTimestamp())){
          return proposalEvent.getEventProposal();
        }else{
          return originalProposal;
        }
      case deleted:
      default:
        return null;
    }
  }

  public static Proposal handleProponentEvent(ProponentEvent proponentEvent, Proposal proposal){
    switch (proponentEvent.getEventAction()){
      case added:
      case updated:
        if(proposal != null){
          proposal.addOrUpdateProponent(proponentEvent.getEventProponentId(), proponentEvent.getEventProponent());
          return proposal;
        }
        return null;
      case deleted:
        proposal.removeProponent(proponentEvent.getEventProponentId());
        return proposal;
      default: return null;
    }
  }

  public static Proposal handleWarrantyEvent(WarrantyEvent warrantyEvent, Proposal proposal){
      switch (warrantyEvent.getEventAction()){
        case added:
        case updated:
          proposal.addOrUpdateWarranty(warrantyEvent.getEventWarrantyId(), warrantyEvent.getEventWarranty());
          return proposal;
        case deleted:
          proposal.removeWarranty(warrantyEvent.getEventWarrantyId());
          return proposal;
        default: return null;
    }
  }


  // Essa função recebe uma lista de mensagens, por exemplo:
  //
  // [
  //   "72ff1d14-756a-4549-9185-e60e326baf1b,proposal,created,2019-11-11T14:28:01Z,80921e5f-4307-4623-9ddb-5bf826a31dd7,1141424.0,240",
  //   "af745f6d-d5c0-41e9-b04f-ee524befa425,warranty,added,2019-11-11T14:28:01Z,80921e5f-4307-4623-9ddb-5bf826a31dd7,31c1dd83-8fb7-44ff-8cb7-947e604f6293,3245356.0,DF",
  //   "450951ee-a38d-475c-ac21-f22b4566fb29,warranty,added,2019-11-11T14:28:01Z,80921e5f-4307-4623-9ddb-5bf826a31dd7,c8753500-1982-4003-8287-3b46c75d4803,3413113.45,DF",
  //   "66882b68-baa4-47b1-9cc7-7db9c2d8f823,proponent,added,2019-11-11T14:28:01Z,80921e5f-4307-4623-9ddb-5bf826a31dd7,3f52890a-7e9a-4447-a19b-bb5008a09672,Ismael Streich Jr.,42,62615.64,true"
  // ]
  //
  // Complete a função para retornar uma String com os IDs das propostas válidas no seguinte formato (separado por vírgula):
  // "52f0b3f2-f838-4ce2-96ee-9876dd2c0cf6,51a41350-d105-4423-a9cf-5a24ac46ae84,50cedd7f-44fd-4651-a4ec-f55c742e3477"
  public static String processMessages(List<String> messages) {
    List<UUID> processedEvents = new ArrayList<>();
    Map<UUID, Proposal> proposals = new HashMap<>();
    List<Proposal> proposalList = new ArrayList<>();
    Map<UUID, Integer> proposalListIndexes = new HashMap<>();

    for (String line : messages) {
      String[] attributes = line.split(",");
      String[] eventAttributes = Arrays.copyOfRange(attributes, 0, 5);
      EventSchema eventSchema = EventSchema.valueOf(attributes[1]);
      ActionType eventAction = ActionType.valueOf(attributes[2]);
      UUID eventId = UUID.fromString(attributes[0]);

      Event instance = null;

      if (eventAction == ActionType.deleted){
        instance = new ProposalEvent(eventAttributes, attributes[4]);
      }else if(eventAction == ActionType.removed){
        String instanceId = attributes[5];
        switch (eventSchema){
          case proposal: instance = new ProposalEvent(eventAttributes, instanceId); break;
          case proponent: instance = new ProponentEvent(eventAttributes, instanceId); break;
          case warranty: instance = new WarrantyEvent(eventAttributes, instanceId); break;
          default: break;
        }
      }else{
        switch (eventSchema){
          case proposal: instance = new ProposalEvent(eventAttributes, Arrays.copyOfRange(attributes, 4, attributes.length)); break;
          case proponent: instance = new ProponentEvent(eventAttributes, Arrays.copyOfRange(attributes, 5, attributes.length)); break;
          case warranty: instance = new WarrantyEvent(eventAttributes, Arrays.copyOfRange(attributes, 5, attributes.length)); break;
          default: break;
        }
      }

      if(!processedEvents.contains(eventId)){
        UUID proposalId = UUID.fromString(attributes[4]);
        Proposal proposal = proposals.get(proposalId);
        int index = proposalListIndexes.getOrDefault(proposalId, -1);
        if(index>=0) proposalList.remove(index);

        Proposal newProposal = null;
        switch (eventSchema){
          case proposal: newProposal = handleProposalEvent((ProposalEvent) instance, proposal); break;
          case warranty: newProposal = handleWarrantyEvent((WarrantyEvent) instance, proposal); break;
          case proponent: newProposal = handleProponentEvent((ProponentEvent) instance, proposal); break;
          default: break;
        }
        proposalList.add(newProposal);
        proposalListIndexes.put(proposalId,proposalList.indexOf(newProposal));
        proposals.put(proposalId, newProposal);
        processedEvents.add(eventId);
      }
    }

    List<String> validProposalIds = new ArrayList<>();
    for (Proposal p : proposalList){
      if(p == null) continue;
      if (p.IsValid()){
        validProposalIds.add(p.getProposalId().toString());
      }
    }
    String validProposalIdsStr = String.join(",",validProposalIds);
    System.out.println(validProposalIdsStr);
    return validProposalIdsStr;
  }
}
