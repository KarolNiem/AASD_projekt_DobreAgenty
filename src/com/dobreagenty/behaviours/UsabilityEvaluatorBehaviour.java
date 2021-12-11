package com.dobreagenty.behaviours;

import com.dobreagenty.payloads.Offer;
import com.dobreagenty.payloads.UsabilityEvaluation;
import jade.lang.acl.ACLMessage;
import org.json.JSONObject;

public class UsabilityEvaluatorBehaviour extends EvaluatorBehaviour {

    @Override
    public void action() {
        ACLMessage msg = myAgent.receive();
        if (msg != null) {
            try {
                String content = msg.getContent();
                JSONObject json = new JSONObject(content);
                Offer offer = new Offer(json);
                UsabilityEvaluation evaluation = new UsabilityEvaluation(offer);
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