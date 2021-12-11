package com.dobreagenty.behaviours;

import com.dobreagenty.payloads.CostEvaluation;
import com.dobreagenty.payloads.Offer;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import org.json.JSONObject;

public class CommunicateWithCostEvaluator extends CyclicBehaviour {
    public boolean sentOffer = false;
    @Override
    public void action() {
        try {
            if (!sentOffer) { // proof of concept of agent communication
                ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
                msg.addReceiver(new AID("CostEvaluator", AID.ISLOCALNAME));
                JSONObject json = new JSONObject("{\"type\": 1}");
                Offer offer = new Offer(json);
                msg.setContent(offer.toString());
                myAgent.send(msg);
                sentOffer = true;
            }
            ACLMessage msg = myAgent.receive();
            if (msg != null) {

                    String content = msg.getContent();
                    System.out.println("CustomerSystemInterface received: " + content);
                    JSONObject json = new JSONObject(content);
                    CostEvaluation evaluation = new CostEvaluation(json);

            } else {
                block();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
