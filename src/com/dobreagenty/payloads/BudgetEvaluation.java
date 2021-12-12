package com.dobreagenty.payloads;

import org.json.JSONObject;

public class BudgetEvaluation extends BaseEvaluation {
    public boolean withinBudget;

    public BudgetEvaluation(Offer offer) {
        super(offer);
    }

    public BudgetEvaluation(JSONObject json) {
        super(json);
        withinBudget = json.getBoolean("withinBudget");
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("offerID", offerID);
        json.put("withinBudget", withinBudget);
        return json.toString();
    }
}