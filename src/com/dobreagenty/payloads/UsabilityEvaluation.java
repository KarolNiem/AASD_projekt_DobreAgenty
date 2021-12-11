package com.dobreagenty.payloads;

import org.json.JSONObject;

public class UsabilityEvaluation {
    public UsabilityEvaluation(Offer offer) {
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        return json.toString();
    }
}
