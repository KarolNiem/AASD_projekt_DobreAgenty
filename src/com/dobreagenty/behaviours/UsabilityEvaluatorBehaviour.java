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
                reply(msg, evaluation);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            block();
        }
    }
}