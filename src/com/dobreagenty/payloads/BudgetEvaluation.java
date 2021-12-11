package com.dobreagenty.payloads;

import org.json.JSONObject;

public class BudgetEvaluation {
    public boolean withinBudget;

    public BudgetEvaluation(Offer offer) {
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("withinBudget", withinBudget);
        return json.toString();
    }
}