package com.dobreagenty.payloads;

import org.json.JSONObject;

import java.util.UUID;

public class UsabilityEvaluation {
    public UUID offerID;

    public UsabilityEvaluation(Offer offer) {
        offerID = offer.id;
    }

    public UsabilityEvaluation(JSONObject json) {
        offerID = UUID.fromString(json.getString("offerID"));
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("offerID", offerID);
        return json.toString();
    }
}
