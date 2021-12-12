package com.dobreagenty.behaviours;

import com.dobreagenty.payloads.AgeStructEvaluation;
import com.dobreagenty.payloads.Offer;
import jade.lang.acl.ACLMessage;
import org.json.JSONObject;

public class AgeStructEvaluatorBehaviour extends EvaluatorBehaviour {
    @Override
    public void action() {
        ACLMessage msg = myAgent.receive();
        if (msg != null) {
            try {
                String content = msg.getContent();
                JSONObject json = new JSONObject(content);
                Offer offer = new Offer(json);
                AgeStructEvaluation evaluation = new AgeStructEvaluation(offer);
                reply(msg, evaluation);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            block();
        }
    }
}
