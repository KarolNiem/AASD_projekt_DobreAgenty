package com.dobreagenty.payloads;

import org.json.JSONObject;

public class CostEvaluation {
    public int cost;

    public CostEvaluation(int cost) {
        this.cost = cost;
    }

    public CostEvaluation(Offer offer) {
        cost = 1000;
    }

    public CostEvaluation(JSONObject json) {
        cost = json.getInt("cost");
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("cost", cost);
        return json.toString();
    }
}
