package com.dobreagenty.behaviours;

import com.dobreagenty.payloads.CostEvaluation;
import com.dobreagenty.payloads.Offer;
import jade.lang.acl.ACLMessage;
import org.json.JSONObject;

public class CostEvaluatorBehaviour extends EvaluatorBehaviour {
    @Override
    public CostEvaluation evaluate(ACLMessage msg) {
        String content = msg.getContent();
        System.out.println("CostEvaluator received: " + content);
        JSONObject json = new JSONObject(content);
        Offer offer = new Offer(json);
        return new CostEvaluation(offer);
    }
}
