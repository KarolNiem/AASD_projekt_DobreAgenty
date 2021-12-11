package com.dobreagenty.payloads;

import org.json.JSONObject;

import java.util.UUID;

public class CostEvaluation {
    public UUID offerID;
    public int cost;

    public CostEvaluation(Offer offer) {
        offerID = offer.id;
        cost = 1000;
    }

    public CostEvaluation(JSONObject json) {
        offerID = UUID.fromString(json.getString("offerID"));
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
