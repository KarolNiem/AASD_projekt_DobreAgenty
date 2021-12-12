package com.dobreagenty.payloads;

import org.json.JSONObject;

public class UsabilityEvaluation extends BaseEvaluation{
    public UsabilityEvaluation(Offer offer) {
        super(offer);
    }

    public UsabilityEvaluation(JSONObject json) {
        super(json);
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("offerID", offerID);
        return json.toString();
    }
}
