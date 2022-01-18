package com.dobreagenty.behaviours;

import com.dobreagenty.payloads.BudgetEvaluation;
import com.dobreagenty.payloads.CostEvaluation;
import com.dobreagenty.payloads.Offer;
import jade.lang.acl.ACLMessage;
import org.json.JSONObject;

public class BudgetCheckerBehaviour extends EvaluatorBehaviour {
    @Override
    public BudgetEvaluation evaluate(ACLMessage msg) {
        String content = msg.getContent();
        System.out.println("BudgetChecker received: " + content);
        JSONObject json = new JSONObject(content);
        Offer offer = new Offer(json);
        return new BudgetEvaluation(offer);
    }
}