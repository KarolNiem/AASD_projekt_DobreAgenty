package com.dobreagenty.payloads;

import org.json.JSONObject;

public class CostEvaluation extends BaseEvaluation {
    public int cost;

    public CostEvaluation(Offer offer) {
        super(offer);
    }

    public CostEvaluation(JSONObject json) {
        super(json);
        cost = json.getInt("cost");
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("offerID", offerID);
        json.put("cost", cost);
        return json.toString();
    }
}
