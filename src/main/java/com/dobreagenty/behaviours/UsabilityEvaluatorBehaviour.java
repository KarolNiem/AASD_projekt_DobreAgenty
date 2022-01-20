package com.dobreagenty.behaviours;

import com.dobreagenty.payloads.BaseEvaluation;
import com.dobreagenty.payloads.BudgetEvaluation;
import com.dobreagenty.payloads.Offer;
import com.dobreagenty.payloads.UsabilityEvaluation;
import jade.lang.acl.ACLMessage;
import org.json.JSONObject;

public class UsabilityEvaluatorBehaviour extends EvaluatorBehaviour {
    @Override
    public UsabilityEvaluation evaluate(ACLMessage msg) {
        String content = msg.getContent();
        System.out.println("UsabilityEvaluator received: " + content);
        JSONObject json = new JSONObject(content);
        Offer offer = new Offer(json);
        return new UsabilityEvaluation(offer);
    }
}