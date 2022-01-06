package com.dobreagenty.payloads;

import org.json.JSONObject;

public class AgeStructEvaluation extends BaseEvaluation {
    public double result;

    public AgeStructEvaluation(Offer offer) {
        super(offer);
    }

    public AgeStructEvaluation(JSONObject json) {
        super(json);
        result = json.getDouble("result");
    }

    @Override
    public void evaluate() {
        result = Math.min(1, 2 * Double.valueOf(offer.getNumberOfTargetPopulation())
                / Double.valueOf(offer.district.getTotalPopulation(offer.district.districtEnum)));
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