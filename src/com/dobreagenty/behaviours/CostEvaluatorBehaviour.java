package com.dobreagenty.behaviours;

import com.dobreagenty.payloads.CostEvaluation;
import com.dobreagenty.payloads.Offer;
import jade.lang.acl.ACLMessage;
import org.json.JSONObject;

public class CostEvaluatorBehaviour extends EvaluatorBehaviour {
    @Override
    public void action() {
        ACLMessage msg = myAgent.receive();
        if (msg != null) {
            try {
                String content = msg.getContent();
                System.out.println("CostEvaluator received: " + content);
                JSONObject json = new JSONObject(content);
                Offer offer = new Offer(json);
                CostEvaluation evaluation = new CostEvaluation(offer);
                reply(msg, evaluation);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            block();
        }
    }
}
