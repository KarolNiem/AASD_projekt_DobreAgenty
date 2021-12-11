package com.dobreagenty.payloads;

import org.json.JSONObject;

import java.util.UUID;

public class BudgetEvaluation {
    public UUID offerID;
    public boolean withinBudget;

    public BudgetEvaluation(Offer offer) {
        offerID = offer.id;
    }

    public BudgetEvaluation(JSONObject json) {
        offerID = UUID.fromString(json.getString("offerID"));
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