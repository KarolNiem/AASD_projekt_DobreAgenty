package com.dobreagenty.behaviours;

import com.dobreagenty.payloads.BudgetEvaluation;
import com.dobreagenty.payloads.Offer;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import org.json.JSONObject;

public class CheckBudget extends CyclicBehaviour {

    @Override
    public void action() {
        ACLMessage msg = myAgent.receive();
        if (msg != null) {
            try {
                String content = msg.getContent();
                JSONObject json = new JSONObject(content);
                Offer offer = new Offer(json);
                BudgetEvaluation evaluation = new BudgetEvaluation(offer);
                ACLMessage reply = msg.createReply();
                reply.setPerformative(ACLMessage.INFORM);
                reply.setContent(evaluation.toString());
                myAgent.send(reply);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            block();
        }
    }
}