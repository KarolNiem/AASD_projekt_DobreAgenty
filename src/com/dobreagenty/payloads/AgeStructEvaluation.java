package com.dobreagenty.payloads;

import org.json.JSONObject;

import java.util.UUID;

public class AgeStructEvaluation extends BaseEvaluation {
    public AgeStructEvaluation(Offer offer) {
        super(offer);
    }

    public AgeStructEvaluation(JSONObject json) {
        super(json);
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("offerID", offerID);
        return json.toString();
    }
}
