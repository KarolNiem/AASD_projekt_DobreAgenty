package com.dobreagenty.payloads;

import org.json.JSONObject;

public class CostEvaluation extends BaseEvaluation {
    public double result;

    public CostEvaluation(Offer offer) {
        super(offer);
    }

    public CostEvaluation(JSONObject json) {
        super(json);
        result = json.getDouble("result");
    }

    @Override
    public void evaluate() {
        result = 1.0 - Math.min((double)offer.type.price / (double)offer.district.budget, 1.0);
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("id", offer.id.toString());
        json.put("name", offer.name);
        json.put("type", offer.type.type.ordinal());
        json.put("district", offer.district.districtEnum.ordinal());
        json.put("result", result);
        return json.toString();
    }
}
