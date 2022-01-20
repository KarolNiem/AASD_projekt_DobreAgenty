package com.dobreagenty.behaviours;

import com.dobreagenty.payloads.AgeStructEvaluation;
import com.dobreagenty.payloads.BaseEvaluation;
import com.dobreagenty.payloads.Offer;
import com.dobreagenty.payloads.UsabilityEvaluation;
import jade.lang.acl.ACLMessage;
import org.json.JSONObject;

public class AgeStructEvaluatorBehaviour extends EvaluatorBehaviour {
    @Override
    public AgeStructEvaluation evaluate(ACLMessage msg) {
        String content = msg.getContent();
        System.out.println("AgeStructEvaluator received: " + content);
        JSONObject json = new JSONObject(content);
        Offer offer = new Offer(json);
        return new AgeStructEvaluation(offer);
    }
}
