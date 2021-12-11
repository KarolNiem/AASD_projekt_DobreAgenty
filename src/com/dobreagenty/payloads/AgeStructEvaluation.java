package com.dobreagenty.payloads;

import org.json.JSONObject;

import java.util.UUID;

public class AgeStructEvaluation {
    public UUID offerID;

    public AgeStructEvaluation(Offer offer) {
        offerID = offer.id;
    }

    public AgeStructEvaluation(JSONObject json) {
        offerID = UUID.fromString(json.getString("offerID"));
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("offerID", offerID);
        return json.toString();
    }
}
